## ch0206_declarative-transaction-demo

### 基于注解的配置⽅方式

##### 开启事务注解的⽅方式
1. @EnableTransactionManagement
2. \<tx:annotation-driven/>
3. 配置选项
    1. proxyTargetClass
    代理方式 cglib/jdk, mode必须为proxy模式
    2. mode
    增强方式 proxy/aspectj-weaving, proxy方式下通过aop无法增强本地方法调用,
    需要获取自身代理执行。
    3. order

##### @Transactional 相关配置
1. transactionManager
2. propagation
3. isolation
4. timeout
5. readOnly 一个只读标记，需要事务管理器进行具体支持
6. rollbackFor/noRollbackFor 针对哪些异常(不)进行回滚