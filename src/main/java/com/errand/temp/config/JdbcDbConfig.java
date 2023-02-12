package com.errand.temp.config;

import com.errand.temp.repository.jdbc.CafeRepository;
import com.errand.temp.repository.jdbc.JdbcCafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcDbConfig {

    private final DataSource dataSource;

    @Bean(name = "jdbcCafeRepository")
    public CafeRepository jdbcCafeRepository() {
        return new JdbcCafeRepository(dataSource);
    }
}
