package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.kh.spring.board.model.vo.Board;

public interface BoardService {
	int boardCount();
	List<Board> findAll(Map<String, Integer> map);
	int searchCount(Map<String , String> map);
	
	List<Board> findByconditionAndKeyword(Map<String , String> map , RowBounds rowBounds);
	
	List<Board> searchAll();
	int insert(Board board);
	
	
	
	int increaseCount(int boardNo);
	Board findById(int boardNo);
	
	int delete(int boardNo);
	
	int update(Board board);
	
	

}
