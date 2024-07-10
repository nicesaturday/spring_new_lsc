package com.kh.spring.mail;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailTest1 {
	
	
	public static JavaMailSenderImpl sender;
	
	public static void main(String[] args) {
		JavaMailSenderImpl impl = new JavaMailSenderImpl();
		
		Properties prop = new Properties();
		
		impl.setHost("smtp.gmail.com");
		impl.setPort(587);
		impl.setUsername("reallynicesaturday");
		impl.setPassword("vnaspssfgrvwwrpv");
		
		
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		
		impl.setJavaMailProperties(prop);
		
		sender = impl;
		
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setSubject("제목");
		
		message.setText("본문");
		
		
		// 내용
		message.setText("헤헤");
		
		String to = "받는 사람 메일 주소";
		
		
		String[] toArr = {"reallynicesaturday@gmail.com" };
		
		
		message.setTo(toArr);
		
		
		sender.send(message);
		
		
	}
}
