package com.abosen.spring.data.multidatasourcedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author qiubaisen
 * @date 2019-03-20
 */

@Component
@Slf4j
public class MultiDataSourceTest implements CommandLineRunner {
    @Autowired
    private JdbcTemplate fooJdbcTemplate;
    @Autowired
    private JdbcTemplate barJdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        fooTest();
        barTest();
    }

    private void barTest() {
        barJdbcTemplate.queryForList("select * from BAR").forEach(row -> log.info(row.toString()));
    }

    private void fooTest(){
        fooJdbcTemplate.queryForList("select * from FOO").forEach(row -> log.info(row.toString()));
    }
}
