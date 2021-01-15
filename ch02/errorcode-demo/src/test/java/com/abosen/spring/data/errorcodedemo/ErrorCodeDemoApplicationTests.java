package com.abosen.spring.data.errorcodedemo;

import org.hamcrest.core.IsNot;
import org.hamcrest.core.StringStartsWith;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErrorCodeDemoApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testThrowingCustomException() {
        exception.expect(CustomDuplicatedKeyException.class);
        exception.expectMessage(IsNot.not(StringStartsWith.startsWith(CustomSQLErrorCodesTranslator.PREFIX)));

        insertDuplicateKey();
    }

    @Test
    public void testCustomErrorCodesTranslator() {
        exception.expect(CustomDuplicatedKeyException.class);
        exception.expectMessage(StringStartsWith.startsWith(CustomSQLErrorCodesTranslator.PREFIX));

        SQLExceptionTranslator origin = jdbcTemplate.getExceptionTranslator();
        jdbcTemplate.setExceptionTranslator(new CustomSQLErrorCodesTranslator());
        insertDuplicateKey();
        jdbcTemplate.setExceptionTranslator(origin);
    }

    private void insertDuplicateKey() {
        jdbcTemplate.execute("insert into foo (id, bar) values (1, 'a')");
        jdbcTemplate.execute("insert into foo (id, bar) values (1, 'b')");
    }
}

