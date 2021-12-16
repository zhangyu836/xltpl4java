package io.github.zhangyu836.xltpl;

import io.github.zhangyu836.xltpl.tree.NodzMap;
import io.github.zhangyu836.xltpl.tree.Tree;
import com.hubspot.jinjava.tree.Node;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

public class SheetResource {
    public Tree sheetTree;
    public Node jinjaTree;
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

    public void renderSheet(Map<String, Object> map, SheetWriter sheetWriter) {
        if (jinjaTree==null) {
            jinjaTree = Jinja.interpreter.parse(tpl);
        }
        NodzMap.setCurrentNodz(sheetTree);
        sheetTree.setSheetWriter(sheetWriter);
        Config.setXvCurrentSheet(map);
        Jinja.render(jinjaTree, map);
        sheetWriter.collectRange();
    }
}