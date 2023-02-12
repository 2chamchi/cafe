package com.errand.temp.config;

import com.errand.temp.repository.querydsl.OrderRepository;
import com.errand.temp.repository.querydsl.QuerydslSpringDataJpaOrderRepository;
import com.errand.temp.repository.querydsl.SpringDataJpaOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class CafeOrderConfig {

    private final EntityManager entityManager;
    private final SpringDataJpaOrderRepository jpaOrderRepository;

    @Bean(name = "querydslSpringDataJpaOrderRepository")
    public OrderRepository queryOrderRepository(){
        return new QuerydslSpringDataJpaOrderRepository(entityManager);
    }
}
