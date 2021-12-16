package io.github.zhangyu836.xltpl.tree;

import io.github.zhangyu836.xltpl.Config;
import org.apache.poi.ss.usermodel.Cell;

import java.util.Objects;

public class XvCellNodz extends CellNodz {
    private final String value;
    public String var;
    private Object rv;
    private boolean isXv;

    XvCellNodz(Cell rdCell, int rowIndex, int colIndex, String string, boolean xv) {
        super(rdCell, rowIndex, colIndex);
        value = string;
        customTag = "xv";
        isXv = xv;
        if(isXv) {
            var = value.replaceAll("\\{% *xv|%}", "").strip();
        } else {
            var = value.replaceAll("\\{\\{|}}", "").strip();
        }
    }

    public void setRv(Object rv) {  this.rv = rv; }

    protected void setCellValue(Cell cell) {
        if(isXv | Config.isXvMode()){
            setCellValueByType(cell, rv);
        } else {
            cell.setCellValue(Objects.toString(rv));
        }
    }

}