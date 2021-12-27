package io.github.zhangyu836.xltpl;

import io.github.zhangyu836.xltpl.tree.CellNodz;
import io.github.zhangyu836.xltpl.tree.RowNodz;
import org.apache.poi.ss.usermodel.*;

import java.util.HashMap;

public class SheetWriter {
    private final Sheet rdSheet;
    private final Sheet wtSheet;
    //private Workbook workbook;
    private final int maxColNum;
    private int currentRowNum = -1;
    private int currentColNum = -1;
    private Row currentRow;
    private HashMap<Integer,Integer> colNums = new HashMap<>();
    private final Merger merger;

    SheetWriter(Workbook wtWorkbook, SheetResource sheetResource, String sheetName) {
        //workbook = wtWorkbook;
        rdSheet = sheetResource.sheet;
        wtSheet = wtWorkbook.createSheet(sheetName);
        maxColNum = sheetResource.maxColNum;
        merger = sheetResource.merger;
        //copyColumnWidths();
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

    private void copyColumnWidth(int rdColNum, int wtColNum) {
        if(colNums.get(wtColNum)==null){
            wtSheet.setColumnWidth(wtColNum, rdSheet.getColumnWidth(rdColNum) );
            colNums.put(wtColNum,wtColNum);
        }
    }

    public void writeRow(RowNodz rowNode) {
        currentRowNum++;
        currentColNum = -1;
        if(rowNode!=null){
            currentRow = wtSheet.getRow(currentRowNum);
            if(currentRow==null) {
                currentRow = wtSheet.createRow(currentRowNum);
                rowNode.setWtRow(currentRow);
            }
        }
    }

    public void writeCell(CellNodz cellNode) {
        currentColNum++;
        merger.mergeCell(cellNode.rowNum, cellNode.colNum, currentRowNum, currentColNum);
        copyColumnWidth(cellNode.colNum, currentColNum);
        if(cellNode.getCell()!=null) {
            Cell wtCell = currentRow.createCell(currentColNum);
            cellNode.setCell(wtCell);
        }
    }

    public void collectRange() {
        merger.collectRange(wtSheet);
    }

}
