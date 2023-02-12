package com.errand.temp.repository.jpa;

import com.errand.temp.domain.Party;

import java.util.List;
import java.util.Optional;

public interface PartyRepository {
    Party save (Party party);
    void update (Long partyId, PartyUpdateDto updateDto);
    Optional<Party> findById (Long partyId);
    List<Party> findAll (PartySearchCond searchCond);
}
