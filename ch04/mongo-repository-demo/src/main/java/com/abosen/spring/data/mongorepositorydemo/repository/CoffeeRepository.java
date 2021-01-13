package com.abosen.spring.data.mongorepositorydemo.repository;

import com.abosen.spring.data.mongorepositorydemo.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author qiubaisen
 * @date 2021/1/13
 */
public interface CoffeeRepository extends MongoRepository<Coffee, String> {
    List<Coffee> findByName(String name);
}
