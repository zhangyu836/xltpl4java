package io.github.zhangyu836.xltpl.tree;

import io.github.zhangyu836.xltpl.SheetResource;
import io.github.zhangyu836.xltpl.SheetWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Tree extends Nodz {
    private SheetWriter sheetWriter;

    public Tree(int sheetIndex) {
        no = sheetIndex;
        customTag = "tree";
    }

    protected int depth() {
        return 0;
    }

    protected String nodeKey() {
        return String.format("%d", this.no);
    }

    public void setSheetWriter(SheetWriter writer) {sheetWriter = writer; }

    protected void writeRow(RowNodz rowNode) { sheetWriter.writeRow(rowNode); }

    protected void writeCell(CellNodz cellNode) { sheetWriter.writeCell(cellNode); }

    public SheetResource build(Sheet rdSheet) {
        int maxColNum = 0;
        int lastRowNum = rdSheet.getLastRowNum();
        for(int rowNum = 0; rowNum <= lastRowNum; rowNum++)
        {
            Row row = rdSheet.getRow(rowNum);
            RowNodz rowNode = new RowNodz(row, rowNum);
            this.addChild(rowNode);
            if(row==null){
                continue;
            }
            int lastColNum = row.getLastCellNum();
            if (lastColNum > maxColNum) {
                maxColNum = lastColNum;
            }

            for(int colNum = 0; colNum < lastColNum; colNum++)
            {
                Cell cell = row.getCell(colNum);
                if(cell==null){
                    continue;
                }
                CellNodz cellNode = CellNodz.createCellNode(cell, rowNum, colNum);
                rowNode.addChild(cellNode);
            }
            rowNode.addChild(new Nodz());
        }
        //this.addChild(new Nodz());
        return new SheetResource(rdSheet, this, maxColNum);
    }
}
