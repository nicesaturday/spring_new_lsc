package com.ajaxpro.api.app.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ajaxpro.api.model.SocialMember;
import com.ajaxpro.api.model.service.KaKaoService;

@Controller
public class KakaoLoginController {
	
	
	@Autowired
	private KaKaoService kaKaoService;
	
	
	@GetMapping("oauth")
	public void socialLogin(String code , HttpSession session) throws IOException, ParseException {
		
		System.out.println(code);
		String accessToken = kaKaoService.getToken(code);
		session.setAttribute("accessToken", accessToken);
		
		SocialMember sm = kaKaoService.getUserInfo(accessToken);
		
		System.out.println(sm);
	}
	
	@GetMapping("logout")
	public void logout(HttpSession session) throws IOException {
		
		
		String accessToken = (String) session.getAttribute("accressToken");
		
		String responseData = kaKaoService.logout(accessToken);
	}
	
	
	
	
}
