package io.github.zhangyu836.xltpl.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.github.zhangyu836.xltpl.BookWriter;

public class demo {
    public static void main(String[] args) {
        String fileNamex = "example.xlsx";
        String fileName = "example.xls";
        System.out.println("Hello xltpl");
        write(fileName);
        write(fileNamex);
    }

    public static void write(String fileName) {
        BookWriter bookWriter = new BookWriter();
        bookWriter.load(fileName);
        ArrayList<Item> items = Item.getItems();
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }

        Map<String, Object> contextCn = new HashMap<>();
        contextCn.put("name", "龙傲天");
        contextCn.put("address", "福建行中书省福宁州傲龙山庄");
        contextCn.put("fm", 1111);
        contextCn.put("date", new Date());
        contextCn.put("rows", items);
        contextCn.put("items", items);
        contextCn.put("xv", true);// xv mode

        Map<String, Object> contextEn = new HashMap<>();
        contextEn.put("name", "Hello Wizard");
        contextEn.put("address", "Somewhere over the rainbow");
        contextEn.put("fm", 1111);
        contextEn.put("date", new Date());
        contextEn.put("rows", items);
        contextEn.put("items", items);
        contextEn.put("tplIndex", 1);
        ArrayList<Map<String, Object>> contexList = new ArrayList<>();
        contexList.add(contextCn);
        contexList.add(contextEn);
        bookWriter.renderSheets(contexList);
        contextCn.put("sheetName", "cn");
        contextEn.put("sheetName", "en");
        contextCn.put("xv", false);
        bookWriter.renderSheet(contextCn);
        bookWriter.renderSheet(contextEn);
        //append
        contextCn.put("sheetName", "en");
        contextCn.put("xv", true);
        contextEn.put("sheetName", "cn");
        bookWriter.renderSheet(contextCn);
        bookWriter.renderSheet(contextEn);
        String outFileName = "out0." + extension;
        bookWriter.save(outFileName);
        bookWriter.renderSheet(contextCn);
        bookWriter.renderSheet(contextEn);
        contextCn.put("tplName", "list");
        contextCn.put("sheetName", "list");
        bookWriter.renderSheet(contextCn);
        outFileName = "out1." + extension;
        bookWriter.save(outFileName);
    }
}

