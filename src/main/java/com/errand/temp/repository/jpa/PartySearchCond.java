package com.errand.temp.repository.jpa;

import lombok.Data;

@Data
public class PartySearchCond {
    private String partyName;

    public PartySearchCond(){

    }

    public PartySearchCond(String partyName){
        this.partyName = partyName;
    }
}
