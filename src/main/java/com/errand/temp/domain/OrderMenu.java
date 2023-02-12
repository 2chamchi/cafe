package com.errand.temp.domain;

import lombok.Data;

@Data
public class OrderMenu {
    private Long orderMenuId;
    private Long orderId;
    private Long menuId;
    private Long memberId;
    private String comment;

    public OrderMenu() {
    }

    public OrderMenu(Long orderMenuId, Long orderId, Long menuId, Long memberId) {
        this.orderMenuId = orderMenuId;
        this.orderId = orderId;
        this.menuId = menuId;
        this.memberId = memberId;
    }

    public OrderMenu(Long orderMenuId, Long orderId, Long menuId, Long memberId, String comment) {
        this.orderMenuId = orderMenuId;
        this.orderId = orderId;
        this.menuId = menuId;
        this.memberId = memberId;
        this.comment = comment;
    }
}
