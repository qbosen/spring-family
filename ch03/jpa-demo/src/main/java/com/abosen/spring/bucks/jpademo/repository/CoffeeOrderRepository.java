package com.abosen.spring.bucks.jpademo.repository;

import com.abosen.spring.bucks.jpademo.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
}
