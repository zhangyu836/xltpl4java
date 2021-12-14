package com.github.zhangyu836.xltpl.tree;

import org.apache.poi.ss.usermodel.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class CellNodz extends Nodz {
    private final Cell cell;
    private CellStyle cellStyle;
    private Comment comment;
    private Hyperlink hyperlink;
    public final int rowNum;
    public final int colNum;

    CellNodz(Cell rdCell, int rowIndex, int colIndex) {
        cell = rdCell;
        customTag = "cell";
        rowNum = rowIndex;
        colNum = colIndex;
        if(cell!=null) {
            cellStyle = rdCell.getCellStyle();
            comment = rdCell.getCellComment();
            hyperlink = rdCell.getHyperlink();
        }
    }

    protected void exit() {
        if(cell==null) {
            return;
        }
        parent.writeCell(this);
    }

    public static CellNodz createCellNode(Cell rdCell, int rowIndex, int colIndex) {
        CellType cellType = rdCell.getCellType();
        switch (cellType) {
            case NUMERIC:
                return new NumericCellNodz(rdCell, rowIndex, colIndex);
            case STRING:
                String string = rdCell.getStringCellValue();
                if(Util.hasTag(string)) {
                    if(Util.hasXvTag(string)) {
                        if(Util.isXvCell(string)) {
                            return new XvCellNodz(rdCell, rowIndex, colIndex, string);
                        } else {
                            string = TagCellNodz.replaceXvTag(string);
                        }
                    }
                    return new TagCellNodz(rdCell, rowIndex, colIndex, string);
                }
                RichTextString richText = rdCell.getRichStringCellValue();
                int numRuns = richText.numFormattingRuns();
                if(numRuns > 0) {
                    return new RichTextCellNodz(rdCell, rowIndex, colIndex, richText);
                }
                return new StringCellNodz(rdCell, rowIndex, colIndex, string);
            case FORMULA:
                return new FormulaCellNodz(rdCell, rowIndex, colIndex);
            //case BLANK:
            //    return new BlankCellNode(rdCell, rowIndex, colIndex);
            case BOOLEAN:
                return new BooleanCellNodz(rdCell, rowIndex, colIndex);
            case ERROR:
                return new ErrorCellNodz(rdCell, rowIndex, colIndex);
            default:
                return new BlankCellNodz(rdCell, rowIndex, colIndex);
        }
    }

    protected void setCellValue(Cell cell) {}
    protected void setCellStyle(Cell cell) {
        if(cellStyle!=null) {
            cell.setCellStyle(cellStyle);
        }
        if(comment!=null) {
            cell.setCellComment(comment);
        }
        if(hyperlink!=null) {
            cell.setHyperlink(hyperlink);
        }
    }

    public static void setCellValueByType(Cell cell, Object object){
        if(object instanceof String){
            cell.setCellValue((String) object);
        }else if(object instanceof Integer){
            cell.setCellValue((Integer) object);
        }else if(object instanceof Long){
            cell.setCellValue((Long) object);
        }else if(object instanceof Float){
            cell.setCellValue((Float) object);
        }else if (object instanceof Double) {
            cell.setCellValue((Double) object);
        }else if (object instanceof Boolean) {
            cell.setCellValue((Boolean) object);
        }else if(object instanceof Date){
            cell.setCellValue((Date) object);
        }else if(object instanceof Calendar){
            cell.setCellValue((Calendar) object);
        }else if(object instanceof RichTextString){
            cell.setCellValue((RichTextString) object);
        } else if (object!=null){
            cell.setCellValue(Objects.toString(object));
        }
    }

    public void setCell(Cell cell) {
        setCellStyle(cell);
        setCellValue(cell);
    }
}





