package com.errand.temp.service;

import com.errand.temp.domain.Party;
import com.errand.temp.repository.jpa.PartyRepository;
import com.errand.temp.repository.jpa.PartySaveDto;
import com.errand.temp.repository.jpa.PartySearchCond;
import com.errand.temp.repository.jpa.PartyUpdateDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartyServiceImpl implements PartyService {

    /**
     * TODO:
     * 간단한 insert, update, delete -> Spring Data Jpa
     * 복잡한 조회 쿼리 -> Querydsl
     */
    private final PartyRepository partyRepository;

    public PartyServiceImpl(@Qualifier("jpaPartyV2Repository") PartyRepository repository){
        this.partyRepository = repository;
    }

    @Override
    public Party save(PartySaveDto saveDto) {
        Party party = new Party(saveDto.getPartyName());
        return partyRepository.save(party);
    }

    @Override
    public void update(Long partyId, PartyUpdateDto updateDto) {
        partyRepository.update(partyId, updateDto);
    }

    @Override
    public Optional<Party> findById(Long partyId) {
        return partyRepository.findById(partyId);
    }

    @Override
    public List<Party> findByAll(PartySearchCond cond) {
        return partyRepository.findAll(cond);
    }
}
