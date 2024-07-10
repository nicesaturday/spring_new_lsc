package com.kh.spring.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailTest2 {
	
	@Autowired
	private JavaMailSenderImpl sender;
	
	
	
	@GetMapping("mail-test")
	public String mail() {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setSubject("얍");
		message.setText("후후");
		String[] toArr = {"reallynicesaturday@gmail.com" , "vjxj97@naver.com" ,"whddn131010@gmail.com"};
		
		message.setTo(toArr);
		
		sender.send(message);
		
		
		return "redirect:/";
	}
}
