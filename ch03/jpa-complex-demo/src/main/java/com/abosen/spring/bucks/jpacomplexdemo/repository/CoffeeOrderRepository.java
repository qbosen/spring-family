package com.abosen.spring.bucks.jpacomplexdemo.repository;

import com.abosen.spring.bucks.jpacomplexdemo.model.CoffeeOrder;

import java.util.List;

public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomerOrderById(String customer);
    List<CoffeeOrder> findByItems_Name(String name);
}
