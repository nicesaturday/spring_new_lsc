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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
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
		
		if(!"".equals(upfile.getOriginalFilename())) {
			//저장될 파일명 바꾸기
			
			
			
			board.setOriginName(upfile.getOriginalFilename());
			board.setChangeName(saveFile(upfile, session));
			if(boardService.insert(board) > 0) {
				
				session.setAttribute("alertMsg", "성공");
				
				return "redirect:boardlist";
			} else {
				model.addAttribute("alertMsg" , "실패");
				return "board/list";
			}

		} else {
			if(boardService.insert(board) > 0) {
				session.setAttribute("alertMsg", "성공");
				
				return "redirect:boardlist";
			} else {
				model.addAttribute("alertMsg" , "실패");
				return "board/list";
			}
		}
	
	
	}
	
	
	@GetMapping("board-detail")
	public ModelAndView findById(int boardNo , Model model ,  ModelAndView mv) {
		
		log.info("보드엔오 : {}", boardNo);
		
		log.info("보드 : {}" , boardService.findById(boardNo));
		if(boardService.increaseCount(boardNo) > 0) {
			/*
			 * model.addAttribute("board",boardService.findById(boardNo)); return
			 * "board/boardDetail";
			 */
			
			  mv.addObject("board" , boardService.findById(boardNo))
			  .setViewName("board/boardDetail");
			 
		} else {
			mv.addObject("errorMsg" , "게시글 조회에 실패했습니다.").setViewName("common/errorPage");
		}
		
		
		
		return mv;
	}
	
	
	
	
	@PostMapping("boardDelete")
	public String deleteById(int boardNo,
							 String filePath,
							 HttpSession session,
							 Model model) {
		
		
		
		if(boardService.delete(boardNo) > 0) {
			
			if(!"".equals(filePath)) {
				new File(session.getServletContext().getRealPath(filePath)).delete();
			}
			
			session.setAttribute("alertMsg", "게시글 삭제 성공");
			return "redirect:boardlist";
		} else {
			model.addAttribute("errorMsg" , "게시글 삭제 실패!");
			return "common/errorPage";
		}
		
	}
	
	
	@PostMapping("boardUpdate")
	public String update(Model model , int boardNo) {
		
		
		model.addAttribute("board" , boardService.findById(boardNo));
		return "board/boardUpdate";
	}
	
	
	
	
	
	@PostMapping("update")
	public String updateForm(ModelAndView mv , 
								   Board board,
								   HttpSession session,
								   MultipartFile reupFile) {
		
		
		if(!"".equals(reupFile.getOriginalFilename())) {
			
			board.setOriginName(reupFile.getOriginalFilename());
			board.setChangeName(saveFile(reupFile, session));
			
		} else {
			board.setOriginName(board.getOriginName());
			board.setChangeName(board.getChangeName());
		}
		
		
		
		
		
		if(boardService.update(board) > 0) {
			session.setAttribute("alertMsg", "완료");
			return "redirect:board-detail?boardNo=" + board.getBoardNo();
		} else {
			session.setAttribute("errorMsg", "실패");
			return "common/errorPage";
		}

	}
	
	
	
	public String saveFile(MultipartFile upfile , HttpSession session) {
		String originName = upfile.getOriginalFilename();
		
		int num = (int) (Math.random() * 900) + 100;
		
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
		
		log.info("세이브 파일 !@$!@$!@$@$!@{}" ,savePath);
	    String ext = originName.substring(originName.lastIndexOf("."));
		
		
		String changeName = "KH_" + currentTime + "_" + num + "." + ext;
		log.info("파일명1 : {}" , ext);
		log.info("파일명1 : {}" , changeName);
		try {
			//받은 로컬 파일 경로를 새 경로로 바꿔서 만들기(복제)
			//로컬에 같은 이름의 파일은 존재 할 수 없다.
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "resources/uploadFiles/" + changeName;
	}
	
	
	
	
	@GetMapping("image-board")
	public String images(Model model) {
		
		
		List<Board> images = boardService.selectImages();
		
		model.addAttribute("board" , boardService.selectImages());
		
		
		
		return "board/imageList";
	}
	
	
	
	
	@ResponseBody
	@GetMapping(value="reply" , produces = "application/json;charset=UTF-8")
	public String selectReply(int boardNo) {
		return new Gson().toJson(boardService.selectReply(boardNo));
	}
	
	
	@ResponseBody
	@PostMapping("reply")
	public String saveReply(Reply reply) {
		log.info("리플라이? : {}" , reply.toString());
		return boardService.insertReply(reply) > 0 ? "success" : "fail";
	}
	
	
	@ResponseBody
	@GetMapping(value="board-reply" , produces = "application/json;charset=UTF-8")
	public String boardAndReply(int boardNo) {
		return new Gson().toJson(boardService.boardAndReply(boardNo));
	}
	
	
	
	
	
	
}
