package io.github.zhangyu836.xltpl;

import java.util.Map;

public class Config {
    public static boolean xvAll = false;
    public static boolean xvCurrentSheet = false;

    public static boolean isXvMode() {
        return xvAll|xvCurrentSheet;
    }

    public static void setXvAll(boolean isXv) {
        xvAll = isXv;
    }

    public static void setXvCurrentSheet(boolean isXv) {
        xvCurrentSheet = isXv;
    }

    public static void setXvCurrentSheet(Map<String, Object> map) {
        Object xv = map.get("xv");
        try {
            xvCurrentSheet = (boolean) xv;
        } catch (Exception e) {
            xvCurrentSheet = false;
        }
    }
}
