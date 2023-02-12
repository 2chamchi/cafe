package com.errand.temp;

import com.errand.temp.repository.jdbc.CafeRepository;
import com.errand.temp.repository.querydsl.OrderRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication()
public class TempApplication {

	public static void main(String[] args) {
		SpringApplication.run(TempApplication.class, args);
	}

	@Bean
	@Profile("local")
	public SampleDataInit sampleDataInit(CafeRepository cafeRepository, OrderRepository orderRepository){
		return new SampleDataInit(cafeRepository, orderRepository);
	}

}
