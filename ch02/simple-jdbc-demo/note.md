## ch0204_simple-jdbc-demo

1. `SimpleJdbcInsert`： 用于插入数据，根据数据库元数据进行插入数据，本类用于简化插入操作，
提供三种类型方法：`execute` 方法用于普通插入、`executeAndReturnKey` 及
`executeAndReturnKeyHolder` 方法用于插入时获取主键值、executeBatch方法用于批处理。

2. `SimpleJdbcCall`： 用于调用存储过程及自定义函数，本类用于简化存储过程及自定义函数调用。

3. `jdbcTemplate` 获取生成的主键

4. `NamedParameterJdbcTemplate` 批处理： 支持命名参数批处理；

5. 通过 `SqlParameterSource` 自动生成参数