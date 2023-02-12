package com.errand.temp.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cafeId;

    // camel <-> underscore 자동 변환
    @Column(name = "cafe_name", length = 255)
    private String cafeName;

    // 기본 생성자 필수
    public Cafe() {
    }

    public Cafe(Long cafeId, String cafeName) {
        this.cafeId = cafeId;
        this.cafeName = cafeName;
    }
}
