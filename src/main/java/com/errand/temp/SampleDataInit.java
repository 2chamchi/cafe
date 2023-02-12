package com.errand.temp;

import com.errand.temp.domain.Cafe;
import com.errand.temp.domain.CafeOrder;
import com.errand.temp.repository.jdbc.CafeRepository;
import com.errand.temp.repository.querydsl.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class SampleDataInit {

    @Autowired
    @Qualifier("jdbcCafeRepository")
    private final CafeRepository cafeRepository;

    @Autowired
    @Qualifier("querydslSpringDataJpaOrderRepository")
    private final OrderRepository orderRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        createDbFile();
        initCafeData();
        initOrderData();
    }

    public void createDbFile() {
        log.info("Sample Create Db File");
        File file = new File("./sample.mv.db");
        try {
            file.createNewFile();
        } catch (IOException e) {
            log.error("fail to sample db file");
        }
    }

    public void initCafeData(){
        log.info("Sample Cafe Data Init");
        cafeRepository.save(new Cafe(1L, "Sample Cafe"));
        cafeRepository.save(new Cafe(2L, "StarBucks"));
        cafeRepository.save(new Cafe(3L, "이디야"));
        cafeRepository.save(new Cafe(4L, "cafebene"));
    }

    public void initOrderData(){
        log.info("Sample Order Data Init");
        LocalDateTime time1 = LocalDateTime.of(2020, 1, 1, 1, 13);
        LocalDateTime time2 = LocalDateTime.of(2021, 5, 5, 4, 23);
        LocalDateTime time3 = LocalDateTime.of(2022, 9, 9, 8, 33);
        LocalDateTime time4 = LocalDateTime.of(2023, 1, 11, 1, 43);
        orderRepository.save(new CafeOrder("TEST-order-001", time1, 0L));
        orderRepository.save(new CafeOrder("TEST-CCC-001", time2, 0L));
        orderRepository.save(new CafeOrder("TEST-order-AAA", time3, 0L));
        orderRepository.save(new CafeOrder("TEST-CCC-002", time4, 0L));
    }
}
