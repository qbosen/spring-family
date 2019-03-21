package com.abosen.spring.data.simplejdbcdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class FooDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    public void insertData() {
        // jdbcTemplate 插入
        Arrays.asList("a", "b").forEach(bar -> {
            jdbcTemplate.update("insert into foo (bar) values (?)", bar);
        });
        // jdbcTemplate 插入并获取主键
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (con -> {
                    PreparedStatement statement = con.prepareStatement(
                            "insert into foo (bar) values (?)", Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, "c");
                    return statement;
                }), holder);
        // 讲道理是3
        Assert.assertEquals(3, holder.getKey());


        // simpleJdbcInsert 插入
        HashMap<String, String> row = new HashMap<>(4);
        row.put("bar", "d");
        int execute = simpleJdbcInsert.execute(row);
        Assert.assertEquals(1, execute);

        // simpleJdbcInsert 插入并获取主键
        row.put("bar", "e");
        Number id = simpleJdbcInsert.executeAndReturnKey(row);
        // 讲道理是5
        Assert.assertEquals(5, id);

        // sqlParameterSource
        Foo foo = Foo.builder().bar("f").build();
        Number number = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(foo));
        Assert.assertEquals(6, number);

    }

    public void listData() {
        log.info("Count: {}",
                jdbcTemplate.queryForObject("select count(*) from foo", Long.class));

        List<String> list = jdbcTemplate.queryForList("select bar from foo", String.class);
        list.forEach(s -> log.info("Bar: {}", s));

        List<Foo> fooList = jdbcTemplate.query("select * from foo", new RowMapper<Foo>() {
            @Override
            public Foo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Foo.builder()
                        .id(rs.getLong("id"))
                        .bar(rs.getString("bar"))
                        .build();
            }
        });
        fooList.forEach(f -> log.info("Foo: {}", f));
    }
}
