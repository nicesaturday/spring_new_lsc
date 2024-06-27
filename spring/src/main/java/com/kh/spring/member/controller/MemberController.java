package com.kh.spring.member.controller;




import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	
	
	private final MemberService memberService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/login.do")
	public ModelAndView login(Member member,
						ModelAndView mv,
						HttpSession session) {
		

		
		log.info("회원이 입력한 아이디 값 : {}" , member.getUserId());
		log.info("회원이 입력한 비밀번호 값 : {}" , member.getUserPwd());
		
		
	
		Member getMember = memberService.login(member);
		
		
		if((getMember != null) && bCryptPasswordEncoder.matches(member.getUserPwd(), getMember.getUserPwd())) {
			log.info("받음 : {}" , getMember);
			session.setAttribute("getMember", getMember);
			mv.setViewName("redirect:/");
		} else {
			mv.addObject("errorMsg" , "로그인 실패")
			.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@GetMapping("/{id}")
	public void restTest(@PathVariable String id) {
		log.info("앞단의 id : {}" , id);
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("getMember");
		return "redirect:/";
	}
	
	@GetMapping("/enroll")
	public String enrollForm() {
		return "member/enrollForm";
	}
	
	@PostMapping("/postEnroll")
	public String erollForm(Model model , Member member) {
		log.info("회원 가입 : {}" , member);
		
		String encPwd = bCryptPasswordEncoder.encode(member.getUserPwd());
		member.setUserPwd(encPwd);
		log.info("암호화?? : {}", encPwd);
		
		
		String view  = "";
		if(memberService.insert(member) > 0) {
			view = "redirect:/";
		} else {
			model.addAttribute("errormsg" , "가입 실패");
			view = "common/errorPage";
		}
		
		
		return view;
	}
	
	@GetMapping("/mypage")
	public String mypage(Model model ) {
		return "member/mypage";
	}
	
	
	@PostMapping("/update")
	public String update(Member member,
						 HttpSession session,
						 Model model,
						 RedirectAttributes redirect) {
		
		log.info("수정 요청 멤버 : {}", member);
		
		String view = "";
		if(memberService.update(member) > 0) {
			session.setAttribute("getMember",memberService.login(member));
			redirect.addFlashAttribute("success" , "success");
			view = "redirect:/member/mypage";
		} else {
			model.addAttribute("errorMsg", "수정 실패");
			view = "common/errorPage";
		}
		return view;
	}
	
	@PostMapping("/delete")
	public String delete(String userId , HttpSession session , String userPwd , RedirectAttributes redirect) {
		
		log.info("비밀번호 : {}",userPwd);
		String enc = ((Member)session.getAttribute("getMember")).getUserPwd();
		
		  if(bCryptPasswordEncoder.matches(userPwd, enc)) {
			  log.info("디크립트 완료");
			  if(memberService.delete(userId) > 0) {
				  log.info("ㅇdb완료");
				  session.removeAttribute("getMember"); 
				  session.setAttribute("successDelete", "successDelete");
			  } 
		  } else {
			  redirect.addFlashAttribute("failsDelete" , "failsDelete");
		  }
		  
		  
		 
		return "redirect:/";
	}
	
	
	@ResponseBody
	@GetMapping("/idCheck")
	public String checkId(String checkId) {
		
		
		return memberService.idCheck(checkId) > 0 ? "NNNNN" : "NNNNY";
	}
	
	
	
	
	
	
	
	
	
	
}
