package com.ajaxpro.api.app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/beach")
public class BeachController {
	
	
	@GetMapping(produces="application/json; charset=UTF-8")
	public String info(int pageNo) throws IOException{
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("http://apis.data.go.kr/6260000/BusanBeachInfoService/getBeachInfo");
		sb.append("?serviceKey=" + AirController.key);
		sb.append("&pageNo=" + pageNo);
		sb.append("&numOfRows=10");
		sb.append("&resultType=json");
		
		String url = sb.toString();
		
		System.out.println(url);
		
		URL requestUrl = new URL(url);
		
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		
		
		urlConnection.setRequestMethod("GET");
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		
		
		try {
			  return br.readLine();
			} finally {
				br.close();
				urlConnection.disconnect();
				System.out.println("완료??");
			}
		
		
		
	}
}
