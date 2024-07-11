package com.kh.spring.socket.handler;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebSocketGroupServer extends TextWebSocketHandler {

	
	private Set<WebSocketSession> users = new CopyOnWriteArraySet<WebSocketSession>();
	
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("사용자 접속 !");
		users.add(session);
		log.info("{} 명 접속 중." , users.size());
		
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		TextMessage newMessage = new TextMessage((CharSequence) message.getPayload());
		
		for(WebSocketSession ws: users) {
			ws.sendMessage(newMessage);
		}
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		users.remove(session);
		log.info("사용자 접속 종료 | 현재 {}명 접속중입니다." , users.size());
	}
	
	
}
