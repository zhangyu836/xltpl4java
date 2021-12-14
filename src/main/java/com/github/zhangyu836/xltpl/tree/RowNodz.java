package com.github.zhangyu836.xltpl.tree;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class RowNodz extends Nodz {
    private final Row rdRow;
    private Row wtRow;
    public int rowNum;
    private short rowHeight;
    private CellStyle style;

    RowNodz(Row row, int rowIndex) {
        rdRow = row;
        rowNum = rowIndex;
        customTag = "row";
        if (rdRow!=null) {
            rowHeight = rdRow.getHeight();
            style = rdRow.getRowStyle();
        }
    }

    public void enter() {
        if (rdRow==null) {
            parent.writeRow(null);
        } else {
            parent.writeRow(this);
        }
    }

    protected void childReenter() { enter();}

    public void setWtRow(Row row){
        wtRow = row;
        row.setHeight(rowHeight);
        if(style!=null) {
            row.setRowStyle(style);
        }
    }

    protected void writeCell(CellNodz cellNode) {
        Cell wtCell = wtRow.createCell(cellNode.colNum);
        cellNode.setCell(wtCell);
        parent.writeCell(cellNode);
    }
}
