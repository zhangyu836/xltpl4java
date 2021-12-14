package com.github.zhangyu836.xltpl.tree;

import org.apache.poi.ss.usermodel.Cell;

public class XvCellNodz extends CellNodz {
    private final String value;
    public String var;
    private Object rv;

    XvCellNodz(Cell rdCell, int rowIndex, int colIndex, String string) {
        super(rdCell, rowIndex, colIndex);
        value = string;
        customTag = "xv";
        var = value.replaceAll("\\{% *xv|%}", "").strip();
    }

    public void setRv(Object rv) {  this.rv = rv; }

    protected void setCellValue(Cell cell) {
        setCellValueByType(cell, rv);
    }

}