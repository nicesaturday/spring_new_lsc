package com.ajaxpro.api.app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("foods")
public class FoodController {
	
	@GetMapping(value="/{pageNo}" , produces = "application/json; charset=UTF-8")
	public String foods(@PathVariable int pageNo) throws IOException {
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("https://apis.data.go.kr/6260000/FoodService/getFoodKr");
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
