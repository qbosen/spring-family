package com.abosen.spring.data.errorcodedemo;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.sql.SQLException;

/**
 * @author qiubaisen
 * @date 2019-04-11
 */
public class CustomSQLErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator {
    public static final String PREFIX = "CUSTOM TRANSLATOR >>> ";

    @Override
    protected DataAccessException customTranslate(String task, String sql, SQLException sqlEx) {
        if (sqlEx.getErrorCode() == 23001 || sqlEx.getErrorCode() == 23505) {
            return new CustomDuplicatedKeyException(PREFIX + sqlEx.getMessage());
        }
        return super.customTranslate(task, sql, sqlEx);
    }
}
