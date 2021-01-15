package com.abosen.spring.data.redisrepository.repository;

import com.abosen.spring.data.redisrepository.model.CoffeeCache;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author qiubaisen
 * @date 2021/1/16
 */
public interface CoffeeCacheRepository extends CrudRepository<CoffeeCache, Long> {
    Optional<CoffeeCache> findOneByName(String name);
}
