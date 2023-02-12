package com.errand.temp.repository.jpa;

import com.errand.temp.domain.Party;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Slf4j
/**
 * @Repository
 * 1. 컴포넌트 스캔의 대상
 * 2. 예외 변환 AOP의 적용 대상
 *    Spring과 JPA를 함계 사용하는 경우 Spring은 JPA 예외 변환기 (PersistenceExceptionTranslator)를 등록한다.
 *    예외 변환 AOP 프록시는 JPA 관련 예외가 발생하면 JPA 예외 변환기를 통해 발생한 에외를 Spring 데이터 접근 예외로 변환한다.
 * ex) @Repository 에서 발생한 예외를 Spring 프록시 객체가 예외를 변환해서 서비스 계층으로 반환 한다.
 */
@Repository
// jpa 사용시 @Transactional 필수
// Service 계층에서 사용하는 것을 권장한다.
//@Transactional
public class JpaPartyRepository implements PartyRepository {

    private final EntityManager entityManager;

    public JpaPartyRepository(EntityManager em){
        this.entityManager = em;
    }

    @Override
    public Party save(Party party) {
        entityManager.persist(party);
        return party;
    }

    @Override
    public void update(Long partyId, PartyUpdateDto updateDto) {
        Party findParty = entityManager.find(Party.class, partyId);
        findParty.setPartyName(updateDto.getPartyName());
        // 로직 종료되면 자동 commit;
    }

    @Override
    public Optional<Party> findById(Long partyId) {
        Party party = entityManager.find(Party.class, partyId);
        return Optional.ofNullable(party);
    }

    @Override
    public List<Party> findAll(PartySearchCond searchCond) {
        // jpql : 엔티티 객체 대상의 쿼리 언어
        String jpql = "select p from Party p";
        // TODO: 동적 쿼리 문제는 Querydsl 을 활용해서 해결한다.
        // Querydsl 은 선택이 아닌 필수
        if (StringUtils.hasText(searchCond.getPartyName())){
            jpql += " where lower(party_name) like concat('%', lower(:partyName), '%')";
        }
        TypedQuery<Party> query = entityManager.createQuery(jpql, Party.class);
        if (StringUtils.hasText(searchCond.getPartyName())){
            query.setParameter("partyName", searchCond.getPartyName());
        }

        return query.getResultList();
    }
}
