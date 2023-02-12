package com.errand.temp.repository.jpa;

import com.errand.temp.domain.Party;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@SpringBootTest
class JpaPartyV2RepositoryTest {

    @Autowired
    @Qualifier("jpaPartyV2Repository")
    private PartyRepository repository;

    @Test
    void save() {
        Party party = new Party("AAA");
        Party resultParty = repository.save(party);
        assertThat(resultParty.getPartyName()).isEqualTo(party.getPartyName());
    }

    @Test
    void update() {
        Party party = new Party("AAA");
        Party resultParty = repository.save(party);

        PartyUpdateDto updateDto = new PartyUpdateDto("BBB");
        repository.update(resultParty.getPartyId(), updateDto);

        Party findParty = repository.findById(resultParty.getPartyId()).get();
        assertThat(updateDto.getPartyName()).isEqualTo(findParty.getPartyName());
    }

    @Test
    void findById() {
        Party party = new Party("AAA");
        Party resultParty = repository.save(party);
        Party findParty = repository.findById(resultParty.getPartyId()).get();
        assertThat(resultParty).isEqualTo(findParty);
    }

    @Test
    void findAll() {
        Party party1 = new Party("AAA");
        Party party2 = new Party("BBB");
        Party resultParty1 = repository.save(party1);
        Party resultParty2 = repository.save(party2);

        PartySearchCond cond1 = new PartySearchCond();
        PartySearchCond cond2 = new PartySearchCond("AAA");

        List<Party> findList1 = repository.findAll(cond1);
        assertThat(2).isEqualTo(findList1.size());

        List<Party> findList2 = repository.findAll(cond2);
        assertThat(1).isEqualTo(findList2.size());
    }
}