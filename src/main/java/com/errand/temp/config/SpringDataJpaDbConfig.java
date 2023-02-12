package com.errand.temp.config;

import com.errand.temp.repository.jpa.SpringDataJpaPartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringDataJpaDbConfig {

    private final SpringDataJpaPartyRepository repository;
}
