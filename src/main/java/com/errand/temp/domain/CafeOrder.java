package com.errand.temp.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class CafeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String orderName;
    private LocalDateTime orderDate;
    private Long customerId;

    public CafeOrder() {
    }

    public CafeOrder(String orderName, LocalDateTime orderDate, Long customerId) {
        this.orderName = orderName;
        this.orderDate = orderDate;
        this.customerId = customerId;
    }
}
