package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.repository.BoardRepository;
import com.kh.spring.board.model.vo.Board;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Board findById(int boardNo) {
		return null;
	}

	@Override
	public int delete(int boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
