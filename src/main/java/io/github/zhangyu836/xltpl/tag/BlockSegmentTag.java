package io.github.zhangyu836.xltpl.tag;

import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.tree.TagNode;
import io.github.zhangyu836.xltpl.tree.NodzMap;

public class BlockSegmentTag extends NodzTag {

    @Override
    public String getName() {
        return "bseg";
    }

    @Override
    public String interpret(TagNode tagNode, JinjavaInterpreter interpreter) {
        String key = tagNode.getHelpers().strip();
        NodzMap.switchNodz(key);
        return key;
    }

    @Override
    public String getEndTagName() { return "endbseg"; }

}
