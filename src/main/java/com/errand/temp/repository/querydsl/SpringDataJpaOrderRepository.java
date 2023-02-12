package com.errand.temp.repository.querydsl;

import com.errand.temp.domain.CafeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataJpaOrderRepository extends JpaRepository<CafeOrder, Long> {

    List<CafeOrder> findByOrderDateGreaterThan(LocalDateTime orderDate);
    List<CafeOrder> findByOrderNameLike(String orderName);

    List<CafeOrder> findByOrderNameLikeAndOrderDateGreaterThan(String orderName, LocalDateTime orderDate);
}
