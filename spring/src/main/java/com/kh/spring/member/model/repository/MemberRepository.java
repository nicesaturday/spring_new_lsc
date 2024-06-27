package com.kh.spring.member.model.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

@Repository
public class MemberRepository {
	public Member login(SqlSessionTemplate sqlSession , Member member) {
		return sqlSession.selectOne("memberMapper.login" , member);
	}

	public int insert(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.insert("memberMapper.insert" , member);
	}

	public int update(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.update("memberMapper.update",member);
	}

	public int delete(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.update("memberMapper.delete" , userId);
	}

	public int idCheck(SqlSessionTemplate sqlSession, String checkId) {
		return sqlSession.selectOne("memberMapper.idCheck" , checkId);
	}
	
	
}
