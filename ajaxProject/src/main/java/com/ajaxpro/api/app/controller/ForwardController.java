package com.ajaxpro.api.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForwardController {
	
	
	@GetMapping("air")
	public String air() {
		return "air/air-pollution";
	}
	
	@GetMapping("busan")
	public String busan() {
		return "busan";
	}
	
	@GetMapping("shop")
	public String shop() {
		return "naver/shopping";
	}
	
	@GetMapping("map")
	public String map() {
		return "kakao/kakoMap";
	}
	
	@GetMapping("food")
	public String food() {
		return "busanMatjip";
	}
	
	
	
	
}



