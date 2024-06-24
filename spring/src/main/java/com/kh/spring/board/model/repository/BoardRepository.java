package com.kh.spring.board.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Board;

@Repository
public class BoardRepository {

	public static int boardCount(SqlSessionTemplate sessionTemplate) {
		return sessionTemplate.selectOne("boardMapper.boardCount");
	}

	public List<Board> findAll(SqlSessionTemplate sessionTemplate, Map<String, Integer> map) {
		return sessionTemplate.selectList("boardMapper.findAll",map);
	}

	public int searchCount(SqlSessionTemplate sessionTemplate, Map<String, String> map) {
		return sessionTemplate.selectOne("boardMapper.searchCount",map);
	}

	public List<Board> findByconditionAndKeyword(SqlSessionTemplate sessionTemplate, Map<String, String> map,
			RowBounds rowBounds) {
		return sessionTemplate.selectList("boardMapper.findByconditionAndKeyword" , map , rowBounds);
	}

	public int insert(SqlSessionTemplate sessionTemplate, Board board) {
		return sessionTemplate.insert("boardMapper.insert" , board);
	}

	public int increaseCount(SqlSessionTemplate sessionTemplate, int boardNo) {
		return sessionTemplate.update("boardMapper.increaseCount" , boardNo);
	}

	public Board findById(SqlSessionTemplate sessionTemplate, int boardNo) {
		return sessionTemplate.selectOne("boardMapper.findById" , boardNo);
	}

	public int delete(SqlSessionTemplate sessionTemplate, int boardNo) {
		return sessionTemplate.update("boardMapper.delete" , boardNo);
	}

	public int update(SqlSessionTemplate sessionTemplate, Board board) {
		return sessionTemplate.update("boardMapper.update" , board);
	}

	
	
	

}
