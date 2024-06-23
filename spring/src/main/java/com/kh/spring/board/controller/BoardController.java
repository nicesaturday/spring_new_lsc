package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.model.template.PageTemplate;
import com.kh.spring.common.model.vo.PageInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	
	private final BoardService boardService;
	
	@GetMapping("boardlist")
	public String forwarding(
			@RequestParam(value="page" , defaultValue = "1")
			int page ,
			Model model) {
		
		int listCount; 		//   
		int currentPage = page;
		int pageLimit = 10;
		int boardLimit = 10;
		
		listCount = boardService.boardCount();
		
		log.info("리스트 수 : {} , 지금  페이지 : {}" , listCount , currentPage);

		
		// 더블로 해서 

		
		PageInfo pageInfo = PageTemplate.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		int StartValue = (currentPage - 1) * boardLimit + 1;
		int endValue = StartValue + boardLimit - 1;
		
		map.put("startValue", StartValue);
		map.put("endValue", endValue);
		
		boardService.findAll(map);
		
		List<Board> boardList = boardService.findAll(map);
		
		log.info("조회된 게시글의 개수 : {}" , boardList.size());
		log.info("======================================");
		log.info("게시글 목록 : {}" , boardList);
		
		model.addAttribute("list" , boardList);
		model.addAttribute("pageInfo" , pageInfo);
		
		
		return "board/list";
	}
	
	
	@GetMapping("search")
	public String search(
						 Model model,
						 String condition , 
						 String keyword,
						 @RequestParam(value="page" , defaultValue = "1") int page) {
		
		
		
		
		log.info("검색 조건 : {}" , condition);
		log.info("검색 키워드 : {}" , keyword);
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		
		int searchCount = boardService.searchCount(map);
		int currentPage = page;
		int pageLimit = 3;
		int boardLimit = 3;
		
		log.info("검색 조건에 부합하는 행의 수 : {}" , searchCount);
		
		
		
		PageInfo pageInfo = PageTemplate.getPageInfo(searchCount, currentPage, pageLimit, boardLimit);
		
		
		
		RowBounds rowBounds = new RowBounds((currentPage- 1) * boardLimit,
				 								boardLimit);
		
		
		//Mybatis에서 페이징 처리를 위한 RowBounds 제공
		 
		
		
		List<Board> boardList = boardService.findByconditionAndKeyword(map, rowBounds);
		
		model.addAttribute("list",boardList);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("condition" , condition);
		model.addAttribute("keyword", keyword);
		

		
		
		return "board/list";
	}
	
	
	
	
	@GetMapping("boardForm")
	public String boardForm() {
		return "board/insertForm";
	}
	
	
	
	
	
	@PostMapping("insert")
	public String insert(Board board,
						 MultipartFile upfile,
						 Model model,
						 HttpSession session) {
		
		
		log.info("게시글 정보 : {}" , board);
		
		
		
		if(!upfile.getOriginalFilename().equals("")) {
			//저장될 파일명 바꾸기
			
			String originName = upfile.getOriginalFilename();
			
			int num = (int) (Math.random() * 900) + 100;
			
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			
			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
			
			
			String[] ext = (originName.split("\\."));
			
			
			String changeName = "KH_" + currentTime + "_" + num + "." + ext[1];
			log.info("파일명1 : {}" , ext[1]);
			log.info("파일명1 : {}" , changeName);
			try {
				upfile.transferTo(new File(savePath + changeName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			board.setOriginName(originName);
			board.setChangeName(savePath + changeName);
			log.info("파일명2 : {}" , savePath + changeName);
			if(boardService.insert(board) > 0) {
				
				session.setAttribute("alertMsg", "성공");
				
				return "redirect:boardlist";
			} else {
				model.addAttribute("alertMsg" , "실패");
				return "board/list";
			}
			
		    
		}
		
		
		
		
		
		return null;
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
