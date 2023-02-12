package com.errand.temp.repository.jpa;

import lombok.Data;

@Data
public class PartyUpdateDto {
    private String partyName;

    public PartyUpdateDto(){

    }

    public PartyUpdateDto(String partyName){
        this.partyName = partyName;
    }
}
