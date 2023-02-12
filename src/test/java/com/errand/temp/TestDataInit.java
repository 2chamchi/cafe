package com.errand.temp;

import com.errand.temp.domain.Cafe;
import com.errand.temp.repository.jdbc.CafeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class TestDataInit {

    private final CafeRepository cafeRepository;

    public TestDataInit(@Qualifier("jdbcCafeRepository") CafeRepository cafeRepository){
        this.cafeRepository = cafeRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        createDbFile();
        initData();
    }

    public void initData(){
        log.info("Test Data Init");
        cafeRepository.save(new Cafe(1L, "TEST-Sample Cafe"));
        cafeRepository.save(new Cafe(2L, "TEST-StarBucks"));
        cafeRepository.save(new Cafe(3L, "TEST-이디야"));
        cafeRepository.save(new Cafe(4L, "TEST-cafeBene"));
    }

    public void createDbFile() {
        log.info("Test Create Db File");
        File file = new File("./test.mv.db");
        try {
            file.createNewFile();
        } catch (IOException e) {
            log.error("fail to sample db file");
        }
    }

    /**
     * Embedded H2 Database
     * Table 생성 필요 (schema.sql 반드시 파일명으로 생성, 고정값)
     * Spring Boot 는 기본 값으로 H2 Embedded 모드 사용
     * @Bean
     * @Profile("test")
     */
    /*
    public DataSource dataSource(){
        log.info("메모리 데잍베이스 초기화");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
    */

}
