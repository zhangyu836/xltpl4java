package com.github.zhangyu836.xltpl;

import com.github.zhangyu836.xltpl.tag.*;
import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.JinjavaConfig;

public class Jinja {
    public Jinjava jinjava;

    Jinja() {
        JinjavaConfig jinjavaConfig = new JinjavaConfig();
        jinjava = new Jinjava(jinjavaConfig);
        RowTag rowTag = new RowTag();
        NodzTag nodzTag = new NodzTag();
        CellTag cellTag = new CellTag();
        SegmentTag segmentTag = new SegmentTag();
        XvCellTag xvTag = new XvCellTag();
        jinjava.getGlobalContext().registerTag(rowTag);
        jinjava.getGlobalContext().registerTag(nodzTag);
        jinjava.getGlobalContext().registerTag(cellTag);
        jinjava.getGlobalContext().registerTag(segmentTag);
        jinjava.getGlobalContext().registerTag(xvTag);
    }
}
