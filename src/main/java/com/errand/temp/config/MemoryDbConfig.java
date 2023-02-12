package com.errand.temp.config;

import com.errand.temp.repository.jdbc.CafeRepository;
import com.errand.temp.repository.memory.MemoryCafeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MemoryDbConfig {
    @Bean("memoryCafeRepository")
    @Primary
    public CafeRepository cafeRepository(){
        return new MemoryCafeRepository();
    }

}
