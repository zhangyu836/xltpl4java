package com.github.zhangyu836.xltpl;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.ArrayList;
import java.util.List;

class MergeRange  {
    private final int _firstRow;
    private final int _firstCol;
    private final int _lastRow;
    private final int _lastCol;
    private int startRdRow = -1;
    private int startRdCol = -1;
    private int startWtRow = -1;
    private int startWtCol = -1;
    private int endWtRow = -1;
    private int endWtCol = -1;
    public ArrayList<CellRangeAddress> rangeAddressList = new ArrayList<>();

    MergeRange(CellRangeAddress cellRangeAddress) {
        _firstRow = cellRangeAddress.getFirstRow();
        _firstCol = cellRangeAddress.getFirstColumn();
        _lastRow = cellRangeAddress.getLastRow();
        _lastCol = cellRangeAddress.getLastColumn();
    }

    public boolean isInRange(int rdRowNum, int rdColNum) {
        return _firstRow <= rdRowNum && rdRowNum <= _lastRow
                && _firstCol <= rdColNum && rdColNum <= _lastCol;
    }

    public boolean toBeMerged(int rdRowNum, int rdColNum) {
        if (rdRowNum > startRdRow) {
            return true;
        } else return rdRowNum == startRdRow && rdColNum > startRdCol;
    }

    public boolean mergeCell(int rdRowNum, int rdColNum, int wtRowNum, int wtColNum) {
        if(!isInRange(rdRowNum, rdColNum)) {
            return false;
        }
        if (startRdRow == -1) {
            startRdRow = rdRowNum;
            startRdCol = rdColNum;
            startWtRow = wtRowNum;
            startWtCol = wtColNum;
            endWtRow = wtRowNum;
            endWtCol = wtColNum;
        } else if (toBeMerged(rdRowNum, rdColNum)) {
            endWtRow = Math.max(endWtRow, wtRowNum);
            endWtCol = Math.max(endWtCol, wtColNum);
        } else {
            newRange();
            startRdRow = rdRowNum;
            startRdCol = rdColNum;
            startWtRow = wtRowNum;
            startWtCol = wtColNum;
            endWtRow = wtRowNum;
            endWtCol = wtColNum;
        }
        return true;
    }

    public void newRange() {
        if (startWtRow==endWtRow && startWtCol==endWtCol) {
            return;
        }
        CellRangeAddress rangeAddress = new CellRangeAddress(startWtRow, endWtRow, startWtCol, endWtCol);
        rangeAddressList.add(rangeAddress);
    }

    public void collectRange(Sheet wtSheet) {
        newRange();
        for(CellRangeAddress rangeAddress: rangeAddressList) {
            try {
                wtSheet.addMergedRegion(rangeAddress);
            } catch (Exception e) {
                System.out.println(rangeAddress);
            }
        }
        rangeAddressList.clear();
        startRdRow = -1;
        startRdCol = -1;
        startWtRow = -1;
        startWtCol = -1;
        endWtRow = -1;
        endWtCol = -1;
    }
}

public class Merger {
    ArrayList<MergeRange> mergeRangeList = new ArrayList<>();

    public Merger(Sheet sheet) {
        getMap(sheet);
    }

    public void getMap(Sheet sheet) {
        List<CellRangeAddress> rangeAddressList = sheet.getMergedRegions();
        for(CellRangeAddress rangeAddress: rangeAddressList) {
            MergeRange mergeRange = new MergeRange(rangeAddress);
            mergeRangeList.add(mergeRange);
        }
    }

    public void mergeCell(int rdRowNum, int rdColNum, int wtRowNum, int wtColNum) {
        for (MergeRange mergeRange: mergeRangeList) {
            boolean isInRange = mergeRange.mergeCell(rdRowNum, rdColNum, wtRowNum, wtColNum);
            if (isInRange) {
                break;
            }
        }
    }

    public void collectRange(Sheet wtSheet) {
        for (MergeRange mergeRange: mergeRangeList) {
            mergeRange.collectRange(wtSheet);
        }
    }

}
