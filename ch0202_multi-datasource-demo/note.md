## ch0202_multi-datasource-demo

1. 屏蔽自动配置
``` java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class})
```
2. 手动注册 `DataSource, PlatformTransactionManager, JdbcTemplate`
3. 手动注册 `DataSourceInitializer` 以执行 `schema.sql`