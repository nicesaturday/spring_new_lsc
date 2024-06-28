package com.kh.spring.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="boards" , produces = "application/json;charset=UTF-8")
public class BoardsController {
	
	
	
	
	private final BoardService boardService;
	
	
	
	@GetMapping
	public List<Board> findTopFiveBoard() {
		
		log.info("리스트 : {}" , boardService.findTopBoard().toString());
		
		return boardService.findTopBoard();
	}
	
	
	@GetMapping("/{boardNo}")
	public Board findByBoardNo(@PathVariable int boardNo) {
		log.info("패스베리 : {}" , boardNo);
		return boardService.findById(boardNo);
	}
	
	
	
	
	
	
	
	
	
	
}
