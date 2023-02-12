package com.errand.temp.service;

import com.errand.temp.domain.Party;
import com.errand.temp.repository.jpa.PartySaveDto;
import com.errand.temp.repository.jpa.PartySearchCond;
import com.errand.temp.repository.jpa.PartyUpdateDto;

import java.util.List;
import java.util.Optional;

public interface PartyService {
    Party save(PartySaveDto saveDto);
    void update(Long partyId, PartyUpdateDto updateDto);
    Optional<Party> findById(Long partyId);
    List<Party> findByAll(PartySearchCond cond);
}
