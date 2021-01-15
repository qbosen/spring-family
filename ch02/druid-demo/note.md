## ch0203_druid-demo
[wiki](https://github.com/alibaba/druid/wiki/%E9%A6%96%E9%A1%B5)

### 功能点
1. 详细的监控
2. ExceptionSorter，针对主流数据库的返回码都有⽀持
3. SQL 防注⼊
4. 内置加密配置
5. 众多扩展点，⽅便进行定制

### 配置
1. 直接通过配置 `DruidDataSource`
2. 通过 `druid-spring-boot-starter`
直接在`application` 配置中配置 `spring.datasource.druid.*`
3. 通过 `spring.datasource.druid.stat-view-servlet.*` 配置页面监控等

### 使用密码加密
1. 进入druid jar包所在目录
2. 执行 `java -cp druid.jar com.alibaba.druid.filter.config.ConfigTools <password>`
```
$ java -cp druid-1.1.14.jar com.alibaba.druid.filter.config.ConfigTools pwd
privateKey:MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEA...
publicKey:MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANnZDAfPXKfGC64wm...
password:ItzTOJZ9BAaaUYWTIXvVcxgLeFFOpMzYKajgHD/8DTvvXOOolO...
```

### 通过filter实现扩展
1. 继承 `com.alibaba.druid.filter.FilterEventAdapter` 重写锚点方法自定义filter
2. 在 `META-INF/druid-filter.properties` 中注册 filter
3. 在配置中启用该 filter