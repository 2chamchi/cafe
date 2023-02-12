package com.errand.temp.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    // @, #, $, %, ^, &, /, \, 공백 입력 불가
    @Column(name = "member_name")
    private String memberName;

    public Member(){

    }

    public Member(Long memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }
}
