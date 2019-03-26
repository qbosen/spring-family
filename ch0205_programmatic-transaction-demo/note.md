## ch0205_programmatic-transaction-demo

Spring 提供了一致的事务模型。核心接口：

PlatformTransactionManager, 定义了提交、回滚和获取状态。
对于不同的事务有不同的实现：
* DataSourceTransactionManager
* HibernateTransactionManager
* JtaTransactionManager

TransactionDefinition, 定义了事务的一些属性：
* Propagation
* Isolation
* Timeout
* Read-only status

### 事务的传播特性
| 传播性 | 值 | 描述 |
|---|---|---|
|0 |  PROPAGATION_REQUIRED      |当前有事务就⽤当前的，没有就用新的|
|1 |  PROPAGATION_SUPPORTS      |事务可有可无，不是必须的|
|2 |  PROPAGATION_MANDATORY     |当前一定要有事务，不然就抛异常|
|3 |  PROPAGATION_REQUIRES_NEW  |⽆论是否有事务，都起个新的事务|
|4 |  PROPAGATION_NOT_SUPPORTED |不支持事务，按⾮事务方式运行|
|5 |  PROPAGATION_NEVER         |不支持事务，如果有事务则抛异常|
|6 |  PROPAGATION_NESTED        |当前有事务就在当前事务⾥再起⼀个事务|

### 事务的隔离特性
| 隔离性 | 值 | 脏读 | 不可重复读 | 幻读|
|---|---|---|---|---|
|ISOLATION_READ_UNCOMMITTED |1|√|√|√|
|ISOLATION_READ_COMMITTED   |2|×|√|√|
|ISOLATION_REPEATABLE_READ  |3|×|×|√|
|ISOLATION_SERIALIZABLE     |4|×|×|×|


### 编程式事务
TransactionTemplate 直接继承自 TransactionDefinition，所以可以直接设置事务属性。
传入以下对象控制事务
* TransactionCallback
* TransactionCallbackWithoutResult

对 PlatformTransactionManager 可以传入 TransactionDefinition 进⾏设置。