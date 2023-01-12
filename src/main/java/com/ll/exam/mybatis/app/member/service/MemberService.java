package com.ll.exam.mybatis.app.member.service;

import com.ll.exam.mybatis.app.member.dto.Member;
import com.ll.exam.mybatis.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMemberByUsername(String username) {
        return memberRepository.getMemberByUsername(username);
    }
}
