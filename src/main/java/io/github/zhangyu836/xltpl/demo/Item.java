package io.github.zhangyu836.xltpl.demo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class Item {
    String name;
    String category;
    Double price;
    Integer count;
    Date date;

    public static ArrayList<Item> getItems() {
        ArrayList<Item> arrayList = new ArrayList<>();
        Item item = new Item();
        item.setName("萝卜");
        item.setCategory("蔬菜");
        item.setPrice(1.11);
        item.setCount(5);
        item.setDate(new Date());
        arrayList.add(item);
        item = new Item();
        item.setName("苹果");
        item.setCategory("水果");
        item.setPrice(2.22);
        item.setCount(4);
        item.setDate(new Date());
        arrayList.add(item);
        item = new Item();
        item.setName("香蕉");
        item.setCategory("水果");
        item.setPrice(3.33);
        item.setCount(3);
        item.setDate(new Date());
        arrayList.add(item);
        item = new Item();
        item.setName("白菜");
        item.setCategory("蔬菜");
        item.setPrice(1.11);
        item.setCount(2);
        item.setDate(new Date());
        arrayList.add(item);
        item = new Item();
        item.setName("凤梨");
        item.setCategory("水果");
        item.setPrice(5.55);
        item.setCount(1);
        item.setDate(new Date());
        arrayList.add(item);
        return arrayList;
    }
}
