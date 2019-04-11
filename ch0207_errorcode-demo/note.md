## ch0207_errorcode-demo

* Spring 会将数据操作的异常转换为 `DataAccessException`,
 无论使用何种数据访问方式，都能使⽤一样的异常

### Spring 怎么识别错误码的
* 通过 `SQLErrorCodeSQLExceptionTranslator` 解析错误码

### 如何定义 ErrorCode
* `org/springframework/jdbc/support/sql-error-codes.xml`,
spring定义了不同数据库错误码到 `DataAccessException` 异常的映射
* Classpath 下的 `sql-error-codes.xml`, 自定义错误码
* 继承 `SQLErrorCodeSQLExceptionTranslator`, 然后配置到 `jdbcTemplate`