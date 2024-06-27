package com.ajaxpro.ajaxpro;

import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajaxpro.ajaxpro.model.vo.Menu;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	//이거 이제 안씀
//	@GetMapping("ajax1")
//	public void calSum(String menu,
//						 
//						 HttpServletResponse response) throws Exception {
//		
//	
//		response.setContentType("text/html , charset=UTF-8");
//		response.getWriter().print(32);
//		logger.info("메뉴: {}" , menu);
//	}
	
	
	//produces에다가 반환인코딩 방식 설정.
	@ResponseBody
	@GetMapping(value="ajax1",produces="text/html; charset=UTF-8")
	public String calSum(String menu)  {
		
	
		
		logger.info("메뉴: {}" , menu);
		return "umhaha";
	}
	
	
	@GetMapping("responseData")
	public void  test(HttpServletResponse response) {
		
	}
	
	
	@ResponseBody
	@GetMapping(value="ajax2",produces="text/html; charset=UTF-8")
	public String selectMenu(String menu) {
		
		Menu menuone = new Menu(1, "짜장면", 5000, "춘장");
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		sb.append(" \'menuNumber \' :");
		sb.append(menuone.getMenuNumber());
		sb.append(",");
		sb.append(" \'menuName \':");
		sb.append(menuone.getMenuName());
		sb.append(",");
		sb.append(" \'price \' :");
		sb.append(menuone.getPrice());
		sb.append(",");
		sb.append("\'meterial \' :");
		sb.append(menuone.getMeterial());
		sb.append("}");
		
		return sb.toString();
	}
	
}
