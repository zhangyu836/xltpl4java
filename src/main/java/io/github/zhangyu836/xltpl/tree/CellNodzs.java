package io.github.zhangyu836.xltpl.tree;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;

class NumericCellNodz extends CellNodz {
    private final double value;

    NumericCellNodz(Cell rdCell, int rowIndex, int colIndex) {
        super(rdCell, rowIndex, colIndex);
        value = rdCell.getNumericCellValue();
    }

    protected void setCellValue(Cell cell) {
        cell.setCellValue(value);
    }

}

class StringCellNodz extends CellNodz {
    private final String value;

    StringCellNodz(Cell rdCell, int rowIndex, int colIndex, String string) {
        super(rdCell, rowIndex, colIndex);
        value = string;
    }

    protected void setCellValue(Cell cell) {
        cell.setCellValue(value);
    }
}

class RichTextCellNodz extends CellNodz {
    private final RichTextString value;

    RichTextCellNodz(Cell rdCell, int rowIndex, int colIndex, RichTextString richText) {
        super(rdCell, rowIndex, colIndex);
        value = richText;
    }

    protected void setCellValue(Cell cell) {
        cell.setCellValue(value);
    }
}

class FormulaCellNodz extends CellNodz {
    private final String value;

    FormulaCellNodz(Cell rdCell, int rowIndex, int colIndex) {
        super(rdCell, rowIndex, colIndex);
        value = rdCell.getCellFormula();
    }

    protected void setCellValue(Cell cell) {
        cell.setCellFormula(value);
    }
}

class BlankCellNodz extends CellNodz {

    BlankCellNodz(Cell rdCell, int rowIndex, int colIndex) {
        super(rdCell, rowIndex, colIndex);
    }

    protected void setCellValue(Cell cell) {
        cell.setBlank();
    }
}

class BooleanCellNodz extends CellNodz {
    private final Boolean value;

    BooleanCellNodz(Cell rdCell, int rowIndex, int colIndex) {
        super(rdCell, rowIndex, colIndex);
        value = rdCell.getBooleanCellValue();
    }

    protected void setCellValue(Cell cell) {
        cell.setCellValue(value);
    }
}

class ErrorCellNodz extends CellNodz {
    private final byte value;

    ErrorCellNodz(Cell rdCell, int rowIndex, int colIndex) {
        super(rdCell, rowIndex, colIndex);
        value = rdCell.getErrorCellValue();
    }

    protected void setCellValue(Cell cell) {
        cell.setCellErrorValue(value);
    }
}