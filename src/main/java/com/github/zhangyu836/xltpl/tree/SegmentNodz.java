package com.github.zhangyu836.xltpl.tree;

public class SegmentNodz extends Nodz {
    String value;

    SegmentNodz(String string) {
        value = string;
        customTag = "seg";
    }

    public void append(String rv) {
        TagCellNodz p = (TagCellNodz)parent;
        p.appendRv(rv);
    }

    protected String nodeTag() {
        NodzMap.putNodz(this.nodeKey(), this);
        return String.format("{%%seg %s %%}%s{%%endseg%%}", this.nodeKey(), value);
    }
}

class BlockSegmentNodz extends Nodz {
    String value;

    BlockSegmentNodz(String string) {
        value = string;
    }

    protected String nodeTag() {
        return value;
    }
}

