package com.errand.temp.repository.jpa;

import com.errand.temp.domain.Party;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class JpaPartyV2Repository implements PartyRepository {

    private final SpringDataJpaPartyRepository repository;

    @Override
    public Party save(Party party) {
        return repository.save(party);
    }

    @Override
    public void update(Long partyId, PartyUpdateDto updateDto) {
        Party findParty = repository.findById(partyId).orElseThrow(null);
        findParty.setPartyName(updateDto.getPartyName());
    }

    @Override
    public Optional<Party> findById(Long partyId) {
        return repository.findById(partyId);
    }

    @Override
    public List<Party> findAll(PartySearchCond searchCond) {
        List<Party> resultList;
        if (StringUtils.hasText(searchCond.getPartyName())) {
            /**
             * TODO: hibernate 5.6.6 ~ 5.6.7 bug: Like condition
             */
            resultList = repository.findByPartyNameLike(searchCond.getPartyName());
        } else {
            resultList = repository.findAll();
        }
        return resultList;
    }
}
