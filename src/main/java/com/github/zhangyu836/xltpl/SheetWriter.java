package com.github.zhangyu836.xltpl;

import com.github.zhangyu836.xltpl.tree.CellNodz;
import com.github.zhangyu836.xltpl.tree.RowNodz;
import org.apache.poi.ss.usermodel.*;

public class SheetWriter {
    private final Sheet rdSheet;
    private final Sheet wtSheet;
    //private Workbook workbook;
    private final int maxColNum;
    private int currentRow = -1;
    private final Merger merger;

    SheetWriter(Workbook wtWorkbook, SheetResource sheetResource, String sheetName) {
        //workbook = wtWorkbook;
        rdSheet = sheetResource.sheet;
        wtSheet = wtWorkbook.createSheet(sheetName);
        maxColNum = sheetResource.maxColNum;
        merger = sheetResource.merger;
        copyColumnWidths();
        copySheetSettings();
    }

    private void copySheetSettings() {
        //PrintSetup printSetup = rdSheet.getPrintSetup();
        //Header header = rdSheet.getHeader();
        //Footer footer = rdSheet.getFooter();
    }

    private void copyColumnWidths() {
        for(int i = 0; i < maxColNum; i++) {
            wtSheet.setColumnWidth(i, rdSheet.getColumnWidth(i) );
        }
    }

    public void writeRow(RowNodz rowNode) {
        currentRow++;
        if(rowNode!=null){
            Row row = wtSheet.createRow(currentRow);
            rowNode.setWtRow(row);
        }
    }

    public void writeCell(CellNodz cellNode) {
        int rdRowNum = cellNode.rowNum;
        int rdColNum = cellNode.colNum;
        int wtRowNum = currentRow;
        int wtColNum = cellNode.colNum;
        merger.mergeCell(rdRowNum, rdColNum, wtRowNum, wtColNum);
    }

    public void collectRange() {
        merger.collectRange(wtSheet);
    }

}
