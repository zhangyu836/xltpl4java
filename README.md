
# xltpl  
使用 xls/x 文件作为模板来生成 xls/x 文件。 [English](README_EN.md)     

 
## 获取

```xml
<dependency>
    <groupId>io.github.zhangyu836</groupId>
    <artifactId>xltpl</artifactId>
    <version>0.3.0</version>
</dependency>
```

## 使用

*   要使用 xltpl，需要了解 [jinja2 模板的语法](http://docs.jinkan.org/docs/jinja2/templates.html) 。  
*   选择一个 xls/x 文件作为模板。  
*   在单元格中插入变量： 
```jinja2
{{name}}
```  

*   在单元格中插入控制语句：

```jinja2
{%- for row in rows %}
{% set outer_loop = loop %}{% for row in rows %}
Cell
{{outer_loop.index}}{{loop.index}}
{%+ endfor%}{%+ endfor%}
```

* Java code
```java
import io.github.zhangyu836.xltpl.BookWriter;
```

```java
BookWriter bookWriter = new BookWriter();
bookWriter.load(fileName);
Map<String, Object> context = new HashMap<>();
context.put("name", "Hello Wizard");
context.put("address", "Somewhere over the rainbow");
context.put("date", new Date());
context.put("items", items);
bookWriter.renderSheet(context);
bookWriter.save(outFileName);
```

> 参见 [示例](https://github.com/zhangyu836/xltpl4java/tree/main/example/src/main/java) 。

## 实现方法

xls/x 文件的每个工作表会被转换为一棵树。  
树会被转换为带有自定义 tag 的 jinja2 模板。  
渲染模板时，自定义 tag 所对应的 jinja2 扩展调用相应的树节点来写入 xls/x 文件。


## 相关
* [xtpl for python](https://github.com/zhangyu836/xltpl)
