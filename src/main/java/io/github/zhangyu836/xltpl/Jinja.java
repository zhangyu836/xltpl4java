package io.github.zhangyu836.xltpl;

import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.JinjavaConfig;
import com.hubspot.jinjava.interpret.Context;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.tree.Node;
import io.github.zhangyu836.xltpl.tag.*;

import java.util.Map;

public class Jinja {
    private static JinjavaConfig jinjavaConfig;
    private static Context context;
    public static Jinjava jinjava;
    public static JinjavaInterpreter interpreter;

    static {
        jinjavaConfig = new JinjavaConfig();
        jinjava = new Jinjava(jinjavaConfig);
        context = jinjava.getGlobalContext();
        RowTag rowTag = new RowTag();
        NodzTag nodzTag = new NodzTag();
        CellTag cellTag = new CellTag();
        SegmentTag segmentTag = new SegmentTag();
        BlockSegmentTag blockSegmentTag = new BlockSegmentTag();
        XvCellTag xvTag = new XvCellTag();
        context.registerTag(rowTag);
        context.registerTag(nodzTag);
        context.registerTag(cellTag);
        context.registerTag(segmentTag);
        context.registerTag(blockSegmentTag);
        context.registerTag(xvTag);
        interpreter = new JinjavaInterpreter(jinjava, context, jinjavaConfig);
    }

    public static void render(Node tree, Map<String, Object> map) {
        Context newContext = new Context(context, map, jinjavaConfig.getDisabled());
        JinjavaInterpreter interpreter = new JinjavaInterpreter(jinjava, newContext, jinjavaConfig);
        interpreter.render(tree);
    }
}