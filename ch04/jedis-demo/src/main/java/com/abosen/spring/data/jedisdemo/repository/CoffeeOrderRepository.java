package com.abosen.spring.data.jedisdemo.repository;

import com.abosen.spring.data.jedisdemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author qiubaisen
 * @date 2021/1/14
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
