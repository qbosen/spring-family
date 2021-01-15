## ch0201_datasource-demo

### springboot的自动配置
1. `management.endpoints.web.exposure.include=*` 对外暴露`actuator`的所有节点
   可以通过访问 http://localhost:8080/actuator/health 或者 http://localhost:8080/actuator/beans
   等节点获取应用信息。

2. `spring.output.ansi.enabled=ALWAYS` 对console着色，test-console默认是没有颜色的

3. `spring.datasource.schema/data` 指定数据库初始化行为。
   **但是只有在自动配置过程中才会自动执行初始化过程**。参见
   [ch0202](../ch0202_multi-datasource-demo/note.md)