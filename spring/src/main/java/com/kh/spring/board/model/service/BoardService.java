package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;

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
	
	List<Board> selectImages();
	
	List<Reply> selectReply(int boardNo);
	
	int insertReply(Reply reply);
	
	Board boardAndReply(int boardNo);
	
	
	List<Board> findTopBoard();
	
	

}
