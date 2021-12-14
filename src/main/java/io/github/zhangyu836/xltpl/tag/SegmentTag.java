package io.github.zhangyu836.xltpl.tag;

import io.github.zhangyu836.xltpl.tree.NodzMap;
import io.github.zhangyu836.xltpl.tree.SegmentNodz;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.tree.TagNode;
import com.hubspot.jinjava.tree.Node;
import com.hubspot.jinjava.util.LengthLimitingStringBuilder;

import java.util.Iterator;

public class SegmentTag extends NodzTag {

    @Override
    public String getName() {
        return "seg";
    }

    @Override
    public String interpret(TagNode tagNode, JinjavaInterpreter interpreter) {
        String key = tagNode.getHelpers().strip();
        SegmentNodz nodz = (SegmentNodz) NodzMap.switchNodz(key);
        String rv = interpretChildren(tagNode, interpreter);
        nodz.append(rv);
        return key;
    }

    public String interpretChildren(TagNode tagNode, JinjavaInterpreter interpreter) {
        String str;
        LengthLimitingStringBuilder stringBuilder =
                new LengthLimitingStringBuilder(interpreter.getConfig().getMaxOutputSize());
        Iterator<Node> iterator = tagNode.getChildren().iterator();
        while(true) {
            if (!iterator.hasNext()) {
                str = stringBuilder.toString();
                break;
            }
            Node child = iterator.next();
            stringBuilder.append(child.render(interpreter));
        }
        return str;
    }

    @Override
    public String getEndTagName() { return "endseg"; }

}
