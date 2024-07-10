package com.kh.spring.mail;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
	
	@GetMapping("fild-send")
	public String sendMail() throws MessagingException{
		
		// html로 보내기 위해 쓰는 클래스
		MimeMessage message = sender.createMimeMessage();
		
		// 마임 메세지는 만들기 어려워서 Helper를 써야함
		MimeMessageHelper helper = new MimeMessageHelper(message , false , "UTF-8");
		
		helper.setSubject("파일 받으세요");
		helper.setText("내용 없음");
		
		
		DataSource source = new FileDataSource("C:\\Users\\user1\\Downloads\\IMG_1515.jpeg");
		helper.addAttachment(source.getName(), source);
		
		
		String[] toArr = {"reallynicesaturday@gmail.com" , "vjxj97@naver.com"};
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
