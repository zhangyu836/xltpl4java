package io.github.zhangyu836.xltpl.tree;

import java.util.regex.*;

public class Util {
    private static String TAGTEST = "\\{%.+%}|\\{\\{.+}}";
    private static Pattern pTagTest = Pattern.compile(TAGTEST);
    private static String XVTEST = "\\{% *xv.+%}";
    private static Pattern pXvTest = Pattern.compile(XVTEST);
    private static String XVCELL = "^ *\\{% *xv.+%} *$";
    private static Pattern pXvCell = Pattern.compile(XVCELL);
    private static String BLOCKTEST = "\\{%.+%}";
    private static Pattern pBlockTest = Pattern.compile(BLOCKTEST);
    public static boolean hasTag(String txt) {
        return pTagTest.matcher(txt).find();
    }
    public static boolean hasXvTag(String txt) {
        return pXvTest.matcher(txt).find();
    }
    public static boolean isXvCell(String txt) {
        return pXvCell.matcher(txt).find();
    }
}
