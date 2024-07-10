package com.kh.spring.member.model.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.CertVO;
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
	
	public int sendMail(SqlSessionTemplate sqlSession, CertVO cert) {
		return sqlSession.insert("memberMapper.sendMail" , cert);
	}

	public boolean validate(SqlSessionTemplate sqlSessionTemplate, CertVO cert) {
		
		boolean result = sqlSessionTemplate.selectOne("memberMapper.validate" , cert);
		
		if(result) {
			MemberRepository.delete(sqlSessionTemplate , cert);
		}
		
		
		
		
		
		return result;
	}
}
