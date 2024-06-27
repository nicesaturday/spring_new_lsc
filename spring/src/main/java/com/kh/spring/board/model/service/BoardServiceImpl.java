package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.repository.BoardRepository;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;
	private final SqlSessionTemplate sessionTemplate;
	
	@Override
	public int boardCount() {
		return BoardRepository.boardCount(sessionTemplate);
	}

	@Override
	public List<Board> findAll(Map<String , Integer> map) {
		return boardRepository.findAll(sessionTemplate , map);
	}

	@Override
	public int searchCount(Map<String , String> map) {
		return boardRepository.searchCount(sessionTemplate , map);
	}
	
	@Override
	public List<Board> findByconditionAndKeyword(Map<String, String> map, RowBounds rowBounds) {
		return boardRepository.findByconditionAndKeyword(sessionTemplate , map , rowBounds);
	}
	

	@Override
	public List<Board> searchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Board board) {
		return boardRepository.insert(sessionTemplate , board);
	}

	@Override
	public int increaseCount(int boardNo) {
		return boardRepository.increaseCount(sessionTemplate , boardNo);
	}

	@Override
	public Board findById(int boardNo) {
		return boardRepository.findById(sessionTemplate , boardNo);
	}

	@Override
	public int delete(int boardNo) {
		return boardRepository.delete(sessionTemplate , boardNo);
	}

	@Override
	public int update(Board board) {
		return boardRepository.update(sessionTemplate , board);
	}

	@Override
	public List<Board> selectImages() {
		return boardRepository.selectImages(sessionTemplate);
	}

	@Override
	public List<Reply> selectReply(int boardNo) {
		return boardRepository.selectReply(sessionTemplate , boardNo);
	}

	@Override
	public int insertReply(Reply reply) {
		return boardRepository.insertReply(sessionTemplate , reply);
	}
	
	

	
	
}
