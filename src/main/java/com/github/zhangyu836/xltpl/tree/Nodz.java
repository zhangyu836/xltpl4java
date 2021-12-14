package com.github.zhangyu836.xltpl.tree;

import java.util.ArrayList;

public class Nodz {
    protected Nodz parent;
    private final ArrayList<Nodz> children = new ArrayList<>();
    protected String customTag = "nodz";
    protected int _depth = -1;
    protected int no;

    protected int depth() {
        if(_depth > 0){
            return _depth;
        }
        _depth = this.parent.depth() + 1;
        return _depth;
    }

    protected void addChild(Nodz child) {
        child.no = this.children.size();
        child.parent = this;
        this.children.add(child);
    }

    protected String nodeKey() {
        return String.format("%s,%d", this.parent.nodeKey(), this.no);
    }

    protected String nodeTag() {
        NodzMap.putNodz(this.nodeKey(), this);
        return String.format("{%% %s %s %%}", this.customTag, this.nodeKey());
    }

    public String childrenToTag() {
        if(this.children.size()==0) {
            return this.nodeTag();
        }
        StringBuilder stringBuilder = new StringBuilder(256);
        for(Nodz child: this.children) {
            stringBuilder.append(child.childrenToTag());
        }
        return stringBuilder.toString();
    }

    protected void writeRow(RowNodz rowNode) {}
    protected void writeCell(CellNodz cellNode) {}
    protected void childReenter() {}
    protected void reenter() {}
    protected void enter() {}
    protected void exit() {}
}
