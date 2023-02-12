package com.errand.temp.repository.querydsl;

import com.errand.temp.domain.CafeOrder;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    CafeOrder save (CafeOrder cafeOrder);
    void update (Long orderId, OrderUpdateDto updateDto);
    Optional<CafeOrder> findById (Long orderId);
    List<CafeOrder> findAll (OrderSearchCond searchCond);
}
