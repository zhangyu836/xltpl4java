package io.github.zhangyu836.xltpl.tag;

import io.github.zhangyu836.xltpl.tree.NodzMap;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.lib.tag.Tag;
import com.hubspot.jinjava.tree.TagNode;

public class NodzTag implements Tag {

    @Override
    public String getName() {
        return "nodz";
    }

    @Override
    public String interpret(TagNode tagNode, JinjavaInterpreter interpreter) {
        String key = tagNode.getHelpers().strip();
        NodzMap.switchNodz(key);
        return key;
    }

    @Override
    public String getEndTagName() {
        return null;
    }
}

