## ch0303_mybatis-demo

对于sql要求更专业严格的公司，mybatis的使用更多一点。

#### 在 Spring 中使用 Mybatis
* [MyBatis Spring Adapter](https://github.com/mybatis/spring)
* [MyBatis Spring-Boot-Starter](https://github.com/mybatis/spring-boot-starter)

#### 配置
* `mybatis.mapper-locations` = classpath*:mapper/**/*.xml
* `mybatis.type-aliases-package` = 类型别名的包名
* `mybatis.type-handlers-package` = TypeHandler扫描包名
* `mybatis.configuration.map-underscore-to-camel-case` = true

Mybatis 中 insert/update/delete 返回的均是修改数据的 count