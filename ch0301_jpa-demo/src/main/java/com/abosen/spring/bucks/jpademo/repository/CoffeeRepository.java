package com.abosen.spring.bucks.jpademo.repository;

import com.abosen.spring.bucks.jpademo.model.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}
