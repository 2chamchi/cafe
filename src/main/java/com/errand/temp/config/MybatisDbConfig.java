package com.errand.temp.config;

import com.errand.temp.repository.mybatis.MemberMapper;
import com.errand.temp.repository.mybatis.MemberRepository;
import com.errand.temp.repository.mybatis.MybatisMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MybatisDbConfig {

    private final MemberMapper memberMapper;
}
