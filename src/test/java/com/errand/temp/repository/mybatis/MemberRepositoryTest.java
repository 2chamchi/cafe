package com.errand.temp.repository.mybatis;

import com.errand.temp.domain.Cafe;
import com.errand.temp.domain.Member;
import com.errand.temp.repository.jdbc.CafeSearchCond;
import com.errand.temp.repository.jdbc.CafeUpdateDto;
import com.errand.temp.repository.memory.MemoryCafeRepository;
import com.errand.temp.repository.mybatis.MemberRepository;
import com.errand.temp.repository.mybatis.MemberSearchCond;
import com.errand.temp.repository.mybatis.MemberUpdateDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// Spring @Transactional 은 테스트 환경에서는 특별하게 동작한다. (강제 rollback)
@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        Member saveMember = new Member(0L, "jack");
        Member resultMember = memberRepository.save(saveMember);
        Member findMember = memberRepository.findById(resultMember.getMemberId()).get();
        assertThat(findMember).isEqualTo(resultMember);
    }

    // Spring @Commit 은 강제로 commit 수행
    //@Commit
    //@Transactional
    @Test
    void update() {
        save();
        String updateMemberName = "StartBucks";
        MemberSearchCond cond = new MemberSearchCond();
        List<Member> memberList = memberRepository.findAll(cond);
        Long updateMemberId = memberList.get(0).getMemberId();

        MemberUpdateDto updateDto = new MemberUpdateDto();
        updateDto.setMemberName(updateMemberName);
        memberRepository.update(updateMemberId, updateDto);
        Member findMember = memberRepository.findById(updateMemberId).get();
        assertThat(updateMemberName).isEqualTo(findMember.getMemberName());
    }

    @Test
    void findById() {
        Member resultMember = memberRepository.save(new Member(0L, "lee"));
        Member findMember = memberRepository.findById(resultMember.getMemberId()).get();
        assertThat(findMember).isEqualTo(resultMember);
    }

    @Test
    void findAll() {
        // TestDataInit + 추가 데이터
        Member member1 = new Member(1L, "park");
        Member member2 = new Member(2L, "jung");
        memberRepository.save(member1);
        memberRepository.save(member2);
        List<Member> memberList = memberRepository.findAll(new MemberSearchCond());
        assertThat(2).isEqualTo(memberList.size());
    }
}