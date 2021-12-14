package io.github.zhangyu836.xltpl.tag;

import io.github.zhangyu836.xltpl.tree.NodzMap;
import io.github.zhangyu836.xltpl.tree.XvCellNodz;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.tree.TagNode;

public class XvCellTag extends NodzTag {

    @Override
    public String getName() {
        return "xv";
    }

    @Override
    public String interpret(TagNode tagNode, JinjavaInterpreter interpreter) {
        String key = tagNode.getHelpers().strip();
        XvCellNodz xvCellNodz = (XvCellNodz) NodzMap.switchNodz(key);
        Object rv = interpreter.resolveELExpression(xvCellNodz.var, tagNode.getLineNumber());
        xvCellNodz.setRv(rv);
        return key;
    }
}
