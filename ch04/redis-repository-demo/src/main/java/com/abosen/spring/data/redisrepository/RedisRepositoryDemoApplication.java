package com.abosen.spring.data.redisrepository;

import com.abosen.spring.data.redisrepository.converter.BytesToMoneyConverter;
import com.abosen.spring.data.redisrepository.converter.MoneyToBytesConverter;
import com.abosen.spring.data.redisrepository.model.Coffee;
import com.abosen.spring.data.redisrepository.service.CoffeeService;
import io.lettuce.core.ReadFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author qiubaisen
 * @date 2021/1/14
 */

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@EnableJpaRepositories
@EnableRedisRepositories
public class RedisRepositoryDemoApplication implements ApplicationRunner {
    @Autowired
    private CoffeeService coffeeService;

    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
    }

    @Bean
    public RedisCustomConversions redisCustomConversions() {
        return new RedisCustomConversions(
                Arrays.asList(new MoneyToBytesConverter(), new BytesToMoneyConverter()));
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisRepositoryDemoApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<Coffee> c = coffeeService.findSimpleCoffeeFromCache("mocha");
        log.info("Coffee {}", c);

        for (int i = 0; i < 5; i++) {
            c = coffeeService.findSimpleCoffeeFromCache("mocha");
        }

        log.info("Value from Redis: {}", c);
    }
}
