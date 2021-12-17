
# xltpl
A Java library to generate xls/x files from a xls/x template.


## Get it

```xml
<dependency>
    <groupId>io.github.zhangyu836</groupId>
    <artifactId>xltpl</artifactId>
    <version>0.2.0</version>
</dependency>
```


## Use it

*   To use xltpl, you need to be familiar with the [syntax of jinja2 template](https://jinja.palletsprojects.com/).
*   Get a pre-written xls/x file as the template.
*   Put variables in the cells, such as : 

```jinja2
{{name}}
```
  
*   Put control statements in the cells :

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

> See [example](https://github.com/zhangyu836/xltpl4java/tree/main/example/src/main/java) .
 
## How it works

When xltpl reads a xls/x file, it creates a tree for each worksheet.  
And, each tree is translated to a jinja2 template with custom tags.  
When the template is rendered, jinja2 extensions of cumtom tags call corresponding tree nodes to write the xls/x file.



## Related
* [xtpl for python](https://github.com/zhangyu836/xltpl)

