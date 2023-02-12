package com.errand.temp.config;

import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class JpaDbConfig {

    private final EntityManager entityManager;

    public JpaDbConfig (EntityManager entityManager){
        this.entityManager = entityManager;
    }
}
