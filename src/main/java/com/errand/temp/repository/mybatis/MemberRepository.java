package com.errand.temp.repository.mybatis;

import com.errand.temp.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    void update(Long memberId, MemberUpdateDto updateDto);
    Optional<Member> findById(Long memberId);
    List<Member> findAll(MemberSearchCond searchCond);
}
