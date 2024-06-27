package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.repository.MemberRepository;
import com.kh.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final SqlSessionTemplate sqlSessionTemplate;
	private final MemberRepository memberRepository;
	
	@Override
	public Member login(Member member) {
		return memberRepository.login(sqlSessionTemplate, member);
	}

	@Override
	public int insert(Member member) {
		return memberRepository.insert(sqlSessionTemplate, member);
	}

	@Override
	public int update(Member member) {
		return memberRepository.update(sqlSessionTemplate , member);
	}

	@Override
	public int delete(String userId) {
		return memberRepository.delete(sqlSessionTemplate , userId);
	}

	@Override
	public int idCheck(String checkId) {
		return memberRepository.idCheck(sqlSessionTemplate , checkId);
	}
	
	
	
}
