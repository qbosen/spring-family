## ch0202_multi-datasource-demo

1. 屏蔽自动配置
``` java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class})
```
2. 手动注册 `DataSource, PlatformTransactionManager, JdbcTemplate`
3. 手动注册 `DataSourceInitializer` 以执行 `schema.sql`

### @Value() 的用法
```
@Value("normal")
private String normal; // 注入普通字符串

@Value("#{systemProperties['os.name']}")
private String systemPropertiesName; // 注入操作系统属性

@Value("${app.name}")
private String appName; // 注入配置属性: application.properties

@Value("#{ T(java.lang.Math).random() * 100.0 }")
private double randomNumber; //注入表达式结果

@Value("#{beanInject.another}")
private String fromAnotherBean; // 注入其他Bean属性：注入beanInject对象的属性another

@Value("classpath:com/hry/spring/configinject/config.txt")
private Resource resourceFile; // 注入文件资源

@Value("http://www.baidu.com")
private Resource testUrl; // 注入URL资源
```