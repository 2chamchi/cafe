package com.errand.temp.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class PartyMemberMapper {

    @Id
    private Long mapperId;
    private Long partyId;
    private Long memberId;

    public PartyMemberMapper() {
    }

    public PartyMemberMapper(Long mapperId, Long partyId, Long memberId) {
        this.mapperId = mapperId;
        this.partyId = partyId;
        this.memberId = memberId;
    }
}
