package com.github.zhangyu836.xltpl.tree;

import java.util.ArrayList;
import java.util.Objects;
import java.util.HashMap;

public class NodzMap {
    protected static String currentKey;
    protected static Nodz lastNodz;
    protected static Nodz currentNodz;
    protected static final HashMap<String, Nodz> nodzMap = new HashMap<>();

    public static void setCurrentNodz(Nodz nodz) { currentNodz = nodz; }

    public static void putNodz(String key, Nodz nodz) {
        nodzMap.put(key, nodz);
    }

    private static void findLca(Nodz pre, Nodz next) {
        // find the lowest common ancestor
        ArrayList<Nodz> nodzList = new ArrayList<>();
        int preDepth = pre.depth();
        int nextDepth = next.depth();
        if(preDepth > nextDepth) {
            for(int i = nextDepth; i < preDepth; i++) {
                pre.exit();
                pre = pre.parent;
            }
        } else if(preDepth < nextDepth) {
            for(int i = preDepth; i < nextDepth; i++) {
                nodzList.add(0, next);
                next = next.parent;
            }
        }
        if (pre!=next) {
            Nodz preParent = pre.parent;
            Nodz nextParent = next.parent;
            while (preParent!=nextParent) {
                pre.exit();
                pre = preParent;
                preParent = pre.parent;
                nodzList.add(0, next);
                next = nextParent;
                nextParent = next.parent;
            }
            pre.exit();
            if (pre.no > next.no) {
                preParent.childReenter();
                next.reenter();
            }  else {
                next.enter();
            }
        }
        for(Nodz nodz : nodzList) {
            nodz.enter();
        }
    }

    public static Nodz switchNodz(String key) {
        if(Objects.equals(key, currentKey)) {
            return currentNodz;
        }
        lastNodz = currentNodz;
        currentKey = key;
        currentNodz = nodzMap.get(key);
        findLca(lastNodz, currentNodz);
        return currentNodz;
    }
}
