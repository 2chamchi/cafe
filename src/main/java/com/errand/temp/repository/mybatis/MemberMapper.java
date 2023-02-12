package com.errand.temp.repository.mybatis;

import com.errand.temp.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    void save(Member member);

    // 파라미터가 2개 이상이면 @Param 필요
    void update(@Param("memberId") Long memberId, @Param("updateParam") MemberUpdateDto updateDto);
    Optional<Member> findById(@Param("memberId") Long memberId);
    List<Member> findAll(MemberSearchCond searchCond);
}
