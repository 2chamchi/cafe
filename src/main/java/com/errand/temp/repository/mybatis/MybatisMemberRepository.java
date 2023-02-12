package com.errand.temp.repository.mybatis;

import com.errand.temp.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MybatisMemberRepository implements MemberRepository {

    private final MemberMapper memberMapper;


    @Override
    public Member save(Member member) {
        memberMapper.save(member);
        return member;
    }

    @Override
    public void update(Long memberId, MemberUpdateDto updateDto) {
        memberMapper.update(memberId, updateDto);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return memberMapper.findById(memberId);
    }

    @Override
    public List<Member> findAll(MemberSearchCond searchCond) {
        return memberMapper.findAll(searchCond);
    }
}
