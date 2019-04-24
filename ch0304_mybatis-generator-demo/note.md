## ch0304_mybatis-generator-demo

#### 配置 Mybatis Generator
* generatorConfiguration
* context
    * jdbcConnection
    * javaModelGenerator
    * sqlMapGenerator
    * javaClientGenerator (ANNOTATEDMAPPER / XMLMAPPER / MIXEDMAPPER)
    简单的使用注解配置，复杂的(Example)使用xml配置
    * table

#### 内置插件都在 org.mybatis.generator.plugins 包中
* FluentBuilderMethodsPlugin
* ToStringPlugin
* SerializablePlugin
* RowBoundsPlugin
* ...

[mybatis xml configuration](http://www.mybatis.org/generator/configreference/xmlconfig.html)

#### PS
1. 需要注意配置中的路径是相对工作目录的，对于module工程需要将工作目录从 project 设置到 module
2. generatorConfig中的配置是有顺序的