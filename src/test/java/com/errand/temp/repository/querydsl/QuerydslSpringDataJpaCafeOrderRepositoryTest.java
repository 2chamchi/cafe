package com.errand.temp.repository.querydsl;

import com.errand.temp.domain.CafeOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class QuerydslSpringDataJpaCafeOrderRepositoryTest {


    @Autowired
    QuerydslSpringDataJpaOrderRepository repository;

    @Test
    void save() {
        LocalDateTime time = LocalDateTime.now();
        CafeOrder cafeOrder = new CafeOrder( "TEST-order", time, 11L);
        CafeOrder resultCafeOrder = repository.save(cafeOrder);
        assertThat(cafeOrder.getOrderName()).isEqualTo(resultCafeOrder.getOrderName());
    }

    @Test
    void update() {
        LocalDateTime time = LocalDateTime.now();
        CafeOrder cafeOrder = new CafeOrder( "TEST-order", time, 11L);
        CafeOrder resultCafeOrder = repository.save(cafeOrder);

        OrderUpdateDto updateDto = new OrderUpdateDto();
        updateDto.setOrderName("BBB");
        repository.update(resultCafeOrder.getOrderId(), updateDto);

        CafeOrder findOrder = repository.findById(resultCafeOrder.getOrderId()).get();
        assertThat(updateDto.getOrderName()).isEqualTo(findOrder.getOrderName());
    }

    @Test
    void findById() {
        LocalDateTime time = LocalDateTime.now();
        CafeOrder cafeOrder = new CafeOrder( "TEST-order", time, 11L);
        CafeOrder resultCafeOrder = repository.save(cafeOrder);

        CafeOrder findOrder = repository.findById(resultCafeOrder.getOrderId()).get();
        assertThat(cafeOrder.getOrderName()).isEqualTo(findOrder.getOrderName());
    }

    @Test
    void findAll_no_condition() {
        LocalDateTime time = LocalDateTime.now();
        CafeOrder cafeOrder1 = new CafeOrder( "TEST-order-AAA", time, 11L);
        CafeOrder cafeOrder2 = new CafeOrder( "TEST-order-BBB", time, 11L);
        CafeOrder resultCafeOrder1 = repository.save(cafeOrder1);
        CafeOrder resultCafeOrder2 = repository.save(cafeOrder2);

        List<CafeOrder> resultList = repository.findAll(new OrderSearchCond());
        assertThat(2).isEqualTo(resultList.size());
    }

    @Test
    void findAll_order_date() {
        LocalDateTime historyTime = LocalDateTime.of(2020, 1,3, 15, 9);
        LocalDateTime searchTime = LocalDateTime.of(2020, 1, 5, 1, 1);
        LocalDateTime now = LocalDateTime.now();
        CafeOrder cafeOrder1 = new CafeOrder( "TEST-order-AAA", historyTime, 11L);
        CafeOrder cafeOrder2 = new CafeOrder( "TEST-order-BBB", now, 11L);
        CafeOrder resultCafeOrder1 = repository.save(cafeOrder1);
        CafeOrder resultCafeOrder2 = repository.save(cafeOrder2);

        OrderSearchCond cond = new OrderSearchCond();
        cond.setOrderDate(searchTime);

        List<CafeOrder> resultList = repository.findAll(cond);
        assertThat(1).isEqualTo(resultList.size());
    }

    @Test
    void findAll_order_name() {
        LocalDateTime historyTime = LocalDateTime.of(2020, 1,3, 15, 9);
        LocalDateTime now = LocalDateTime.now();
        CafeOrder cafeOrder1 = new CafeOrder( "TEST-order-AAA", historyTime, 11L);
        CafeOrder cafeOrder2 = new CafeOrder( "TEST-order-BBB", now, 11L);
        CafeOrder resultCafeOrder1 = repository.save(cafeOrder1);
        CafeOrder resultCafeOrder2 = repository.save(cafeOrder2);

        OrderSearchCond cond = new OrderSearchCond();
        cond.setOrderName("AAA");

        List<CafeOrder> resultList = repository.findAll(cond);
        assertThat(1).isEqualTo(resultList.size());
    }

    @Test
    void findAll_order_name_order_date() {
        LocalDateTime historyTime = LocalDateTime.of(2020, 1,3, 15, 9);
        LocalDateTime searchTime = LocalDateTime.of(2020, 1, 5, 1, 1);
        LocalDateTime now = LocalDateTime.now();
        CafeOrder cafeOrder1 = new CafeOrder( "TEST-order-AAA", historyTime, 11L);
        CafeOrder cafeOrder2 = new CafeOrder( "TEST-order-BBB", now, 11L);
        CafeOrder resultCafeOrder1 = repository.save(cafeOrder1);
        CafeOrder resultCafeOrder2 = repository.save(cafeOrder2);

        OrderSearchCond cond = new OrderSearchCond();
        cond.setOrderDate(searchTime);
        cond.setOrderName("AAA");

        List<CafeOrder> resultList = repository.findAll(cond);
        assertThat(0).isEqualTo(resultList.size());
    }
}