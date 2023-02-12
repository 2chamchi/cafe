package com.errand.temp.repository.jpa;

import com.errand.temp.domain.Party;
import com.errand.temp.domain.PartyMemberMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 간단한 쿼리만 자동 생성 쿼리를 사용한다.
 */
public interface SpringDataJpaPartyRepository extends JpaRepository<Party, Long> {
    List<Party> findByPartyNameLike(String partyName);
}
