package com.abosen.spring.data.redisdemo.service;

import com.abosen.spring.data.redisdemo.model.Coffee;
import com.abosen.spring.data.redisdemo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * @author qiubaisen
 * @date 2021/1/14
 */
@Slf4j
@Service
public class CoffeeService {
    private static final String CACHE_KEY = "springbucks-coffee";
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private RedisTemplate<String, Coffee> redisTemplate;

    public List<Coffee> findAllCoffee() {
        return coffeeRepository.findAll();
    }

    public Optional<Coffee> findOneCoffee(String name) {
        HashOperations<String, String, Coffee> hashOperations = redisTemplate.opsForHash();
        if (hashOperations.hasKey(CACHE_KEY, name)) {
            log.info("Get coffee {} from Redis.", name);
            return Optional.ofNullable(hashOperations.get(CACHE_KEY, name));
        }
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(
                Example.of(Coffee.builder().name(name).build(), matcher));
        log.info("Coffee Found: {}", coffee);
        if (coffee.isPresent()) {
            log.info("Put coffee {} to Redis.", name);
            hashOperations.put(CACHE_KEY, name, coffee.get());
            redisTemplate.expire(CACHE_KEY, 1, TimeUnit.MINUTES);
        }
        return coffee;
    }
}
