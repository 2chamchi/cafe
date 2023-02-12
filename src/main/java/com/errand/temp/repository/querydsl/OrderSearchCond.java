package com.errand.temp.repository.querydsl;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSearchCond {
    private Long orderId;
    private String orderName;
    private Long customerId;
    private LocalDateTime orderDate;
}
