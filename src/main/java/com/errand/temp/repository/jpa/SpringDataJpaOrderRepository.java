package com.errand.temp.repository.jpa;

import com.errand.temp.domain.CafeOrder;
import com.errand.temp.repository.querydsl.OrderRepository;
import com.errand.temp.repository.querydsl.OrderSearchCond;
import com.errand.temp.repository.querydsl.OrderUpdateDto;

import java.util.List;
import java.util.Optional;

public class SpringDataJpaOrderRepository implements OrderRepository {

    @Override
    public CafeOrder save(CafeOrder cafeOrder) {

        return null;
    }

    @Override
    public void update(Long orderId, OrderUpdateDto updateDto) {

    }

    @Override
    public Optional<CafeOrder> findById(Long orderId) {
        return Optional.empty();
    }

    @Override
    public List<CafeOrder> findAll(OrderSearchCond searchCond) {
        return null;
    }
}
