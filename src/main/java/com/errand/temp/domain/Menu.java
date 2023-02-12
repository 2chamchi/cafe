package com.errand.temp.domain;

import lombok.Data;

@Data
public class Menu {
    private Long id;
    private String name;
    private int price;
    private Long cafeId;

    public Menu() {
    }

    public Menu(Long id, int price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }
}
