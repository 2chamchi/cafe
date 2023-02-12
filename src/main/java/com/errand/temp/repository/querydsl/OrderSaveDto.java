package com.errand.temp.repository.querydsl;

import lombok.Data;

@Data
public class OrderSaveDto {
    private String orderName;
    private String orderDate;
    private Long customerId;
}
