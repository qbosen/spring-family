package com.abosen.spring.data.redisrepository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @author qiubaisen
 * @date 2021/1/16
 */
@RedisHash(value = "springbucks-coffee", timeToLive = 60)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeCache {
    @Id
    private Long id;
    @Indexed
    private String name;
    private Money price;
}
