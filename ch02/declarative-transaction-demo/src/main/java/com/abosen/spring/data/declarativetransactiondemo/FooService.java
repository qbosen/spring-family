package com.abosen.spring.data.declarativetransactiondemo;

public interface FooService {
    void insertRecord();
    void insertThenRollback() throws RollbackException;
}
