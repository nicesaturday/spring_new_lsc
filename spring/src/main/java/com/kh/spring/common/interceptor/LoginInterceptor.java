package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request , HttpServletResponse response , Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		
		if(session.getAttribute("loginUser") != null) {
			return true;
		} else {
			
			session.setAttribute("alertMsg", "로그인 장난 ㄴㄴ");
			
			response.sendRedirect(request.getContextPath());
			
			return false;
		}
		
		
		// 자신을 반환?
		// return super.preHandle(request, response, handler);
	}
	
}
