package io.github.zhangyu836.xltpl.tree;

import org.apache.poi.ss.usermodel.Cell;

public class NullCellNodz extends CellNodz {

    NullCellNodz(Cell rdCell, int rowIndex, int colIndex) {
        super(rdCell, rowIndex, colIndex);
    }

    public void setCell(Cell cell) {}

}