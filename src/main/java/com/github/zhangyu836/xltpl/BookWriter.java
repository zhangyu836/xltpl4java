package com.github.zhangyu836.xltpl;

import com.github.zhangyu836.xltpl.tree.NodzMap;
import com.github.zhangyu836.xltpl.tree.Tree;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class BookWriter {
    private Workbook workbook;
    private final HashMap<String, SheetResource> sheetResourceByName = new HashMap<>();
    private final HashMap<Integer, SheetResource> sheetResourceByIndex = new HashMap<>();
    private final HashMap<String, SheetWriter> sheetWriterByName = new HashMap<>();
    private Jinja jinja;

    public void load(String filename) {
        try {
            FileInputStream file = new FileInputStream(filename);
            load(file);
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void load(FileInputStream file) {
        try {
            workbook = WorkbookFactory.create(file);
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            int index = 0;
            while (sheetIterator.hasNext() ){
                Sheet sheet = sheetIterator.next();
                Tree tree = new Tree(index);
                SheetResource sheetResource = tree.build(sheet);
                sheetResourceByIndex.put(index, sheetResource);
                sheetResourceByName.put(sheet.getSheetName(), sheetResource);
                index++;
            }
            for(int i = index-1; i >= 0; i--){
                workbook.removeSheetAt(i);
            }
            jinja = new Jinja();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private SheetResource getSheetResource(Map<String, Object> map) {
        String tplName = (String) map.get("tplName");
        if (tplName!=null){
            SheetResource sheetResource = sheetResourceByName.get(tplName);
            if(sheetResource !=null) {
                return sheetResource;
            }
        }
        Object index = map.get("tplIndex");
        int tplIndex = 0;
        try {
            tplIndex = (int) index;
        } catch (Exception e) {
        }
        return sheetResourceByIndex.get(tplIndex);
    }

    private String getSheetName(Map<String, Object> map) {
        String sheetName = (String) map.get("sheetName");
        if (sheetName!=null){
            return sheetName;
        }
        for(int i = 0; i < 9999; i++) {
            sheetName = String.format("sheet%d", i);
            if(!sheetWriterByName.containsKey(sheetName)) {
                return sheetName;
            }
        }
        return "XLSheet";
    }

    private SheetWriter getSheetWriter(Workbook workbook, SheetResource sheetResource, String sheetName) {
        SheetWriter sheetWriter = sheetWriterByName.get(sheetName);
        if (sheetWriter==null) {
            sheetWriter = new SheetWriter(workbook, sheetResource, sheetName);
            sheetWriterByName.put(sheetName, sheetWriter);
        }
        sheetResource.sheetTree.setSheetWriter(sheetWriter);
        NodzMap.setCurrentNodz(sheetResource.sheetTree);
        return sheetWriter;
    }

    public void renderSheet(Map<String, Object> map) {
        SheetResource sheetResource = getSheetResource(map);
        String sheetName = getSheetName(map);
        SheetWriter sheetWriter = getSheetWriter(workbook, sheetResource, sheetName);
        String template = sheetResource.tpl;
        jinja.jinjava.render(template, map);
        sheetWriter.collectRange();
    }

    public void renderSheets(ArrayList<Map<String, Object>> mapList) {
        for(Map<String, Object> map: mapList) {
            renderSheet(map);
        }
    }

    private void clearSheets() {
        for(int i = sheetWriterByName.size()-1; i >= 0; i--){
            workbook.removeSheetAt(i);
        }
        sheetWriterByName.clear();
    }

    public void save(String filename) {
        try
        {
            FileOutputStream file = new FileOutputStream(filename);
            save(file);
            file.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void save(FileOutputStream file) {
        try
        {
            workbook.write(file);
            clearSheets();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
