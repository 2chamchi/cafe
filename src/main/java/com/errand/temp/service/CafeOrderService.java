package com.errand.temp.service;

import com.errand.temp.domain.CafeOrder;
import com.errand.temp.repository.jdbc.CafeSaveDto;
import com.errand.temp.repository.jdbc.CafeSearchCond;
import com.errand.temp.repository.jdbc.CafeUpdateDto;
import com.errand.temp.repository.querydsl.OrderSaveDto;
import com.errand.temp.repository.querydsl.OrderSearchCond;
import com.errand.temp.repository.querydsl.OrderUpdateDto;

import java.util.List;
import java.util.Optional;

public interface CafeOrderService {
    CafeOrder save(OrderSaveDto saveDto);
    void update(Long orderId, OrderUpdateDto updateDto);

    Optional<CafeOrder> findById(Long orderId);
    List<CafeOrder> findByAll(OrderSearchCond searchCond);
}
