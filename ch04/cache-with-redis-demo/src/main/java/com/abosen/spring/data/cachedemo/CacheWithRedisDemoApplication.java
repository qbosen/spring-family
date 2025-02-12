package com.abosen.spring.data.cachedemo;

import com.abosen.spring.data.cachedemo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

/**
 * @author qiubaisen
 * @date 2021/1/14
 */

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@EnableJpaRepositories
@EnableCaching(proxyTargetClass = true)
public class CacheWithRedisDemoApplication implements ApplicationRunner {
    @Autowired
    private CoffeeService coffeeService;

    public static void main(String[] args) {
        SpringApplication.run(CacheWithRedisDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Count: {}", coffeeService.findAllCoffee().size());
        for (int i = 0; i < 5; i++) {
            log.info("Reading from cache.");
            coffeeService.findAllCoffee();
        }
        Thread.sleep(5_000);
        log.info("Reading after refresh.");
        coffeeService.findAllCoffee().forEach(c -> log.info("Coffee {}", c.getName()));
    }
}
