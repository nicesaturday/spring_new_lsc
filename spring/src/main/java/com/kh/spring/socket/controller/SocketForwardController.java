package com.kh.spring.socket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SocketForwardController extends Object {
	
	
	
	@GetMapping("connect-form")
	public String connect() {
		return "socket/connect";
	}
	
	@GetMapping("group-chat")
	public String group() {
		return "socket/groupChatting";
	}
	
}
