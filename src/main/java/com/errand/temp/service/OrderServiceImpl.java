package com.errand.temp.service;

import com.errand.temp.domain.CafeOrder;
import com.errand.temp.repository.querydsl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements CafeOrderService {

    /**
     * TODO:
     * 간단한 쿼리 insert, update, delete -> Spring Data Jpa
     * 복잡한 쿼리 select -> Querydsl
     */
    private final SpringDataJpaOrderRepository jpaOrderRepository;
    private final QuerydslSpringDataJpaOrderRepository query;


    @Override
    public CafeOrder save(OrderSaveDto saveDto) {
        CafeOrder order = new CafeOrder();
        order.setOrderName(saveDto.getOrderName());
        order.setOrderDate(LocalDateTime.now());
        order.setCustomerId(0L);
        return jpaOrderRepository.save(order);
    }

    @Override
    public void update(Long orderId, OrderUpdateDto updateDto) {
        CafeOrder findOrder = jpaOrderRepository.findById(orderId).get();
        findOrder.setOrderName(updateDto.getOrderName());
    }

    @Override
    public Optional<CafeOrder> findById(Long orderId) {
        return jpaOrderRepository.findById(orderId);
    }

    @Override
    public List<CafeOrder> findByAll(OrderSearchCond searchCond) {
        return query.findAll(searchCond);
    }
}
