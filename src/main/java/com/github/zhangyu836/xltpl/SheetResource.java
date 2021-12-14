package com.github.zhangyu836.xltpl;

import com.github.zhangyu836.xltpl.tree.Tree;
import org.apache.poi.ss.usermodel.Sheet;

public class SheetResource {
    public Tree sheetTree;
    public String tpl;
    public Sheet sheet;
    public int maxColNum;
    public Merger merger;

    public SheetResource(Sheet rdSheet, Tree tree, int maxColIndex) {
        sheet = rdSheet;
        sheetTree = tree;
        tpl = sheetTree.childrenToTag();
        maxColNum = maxColIndex;
        merger = new Merger(rdSheet);
    }
}