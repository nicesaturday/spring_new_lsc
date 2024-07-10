package com.kh.spring.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailTest3 {
	
	
	@Autowired
	private JavaMailSenderImpl sender;
	
	@GetMapping("mime-send")
	public String sendMail() throws MessagingException{
		
		// html로 보내기 위해 쓰는 클래스
		MimeMessage message = sender.createMimeMessage();
		
		// 마임 메세지는 만들기 어려워서 Helper를 써야함
		MimeMessageHelper helper = new MimeMessageHelper(message , false , "UTF-8");
		
		helper.setSubject("형태가 있는 메일은 모양이 어떠한가?");
		
		
		String str = "고유한";
		helper.setText("<a href='http://localhost/spring/auth?str="+ str +">호호</a>" , true);
		String[] toArr = {"realnicesaturday@gmail.com" , "vjxj97@naver.com" ,"whddn131010@gmail.com"};
		helper.setTo(toArr);
		
		sender.send(message);
		
		return "redirect:/";
	}
	
	
	@GetMapping("auth")
	public String auth(String str) {
		if(str.equals("고유한")) {
			return "redirect:/";
		}else {
			return "return:/";
		}
	}
	
	
	
}
