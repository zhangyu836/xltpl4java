package com.github.zhangyu836.xltpl.tree;

import org.apache.poi.ss.usermodel.Cell;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TagCellNodz extends CellNodz {
    private final String value;
    private final StringBuilder rv = new StringBuilder();
    private static final String BLOCK = "(\\{%.+?%})+";
    private static final Pattern pBlock = Pattern.compile(BLOCK);
    private static final String XVBLOCK = "(\\{%) *(xv).+?(%})";
    private static final Pattern pXvBlock = Pattern.compile(XVBLOCK);

    TagCellNodz(Cell rdCell, int rowIndex, int colIndex, String string) {
        super(rdCell, rowIndex, colIndex);
        value = string;
        splitToChildren(value);
    }

    public static String replaceXvTag(String tag) {
        Matcher m = pXvBlock.matcher(tag);
        StringBuilder stringBuilder = new StringBuilder(tag);
        while (m.find()){
            stringBuilder.replace(m.start(1), m.end(1), "{{");
            stringBuilder.replace(m.start(2), m.end(2), "  ");
            stringBuilder.replace(m.start(3), m.end(3), "}}");
        }
        return stringBuilder.toString();
    }

    private void splitToChildren(String txt) {
        Matcher m = pBlock.matcher(txt);
        int st = 0;
        while (m.find()) {
            int mst = m.start();
            if (mst > st) {
                String sub = txt.substring(st, mst);
                SegmentNodz segment = new SegmentNodz(sub);
                addChild(segment);
            }
            st = m.end();
            BlockSegmentNodz blockSegment = new BlockSegmentNodz(m.group());
            addChild(blockSegment);
        }
        if (st < txt.length()) {
            String sub = txt.substring(st);
            SegmentNodz segment = new SegmentNodz(sub);
            addChild(segment);
        }
    }

    protected void enter() {
        rv.setLength(0);
    }

    public void appendRv(String childRv) {
        rv.append(childRv);
    }

    protected void setCellValue(Cell cell) {
        cell.setCellValue(rv.toString());
    }

}
