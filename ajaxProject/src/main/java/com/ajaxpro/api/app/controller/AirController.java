package com.ajaxpro.api.app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pollution")
public class AirController {
	
	public final static String key = "oHfkaY88BRbD4M%2Fyx0m%2FtGQPCrjTmC97aKXu0LTqSYr8GwNqviM39OcSFzo00QDQ%2F8wqx98BelfUj%2BrtOSwqyA%3D%3D";
	
	@GetMapping(produces="application/json; charset=UTF-8")
	public String airPollution(String sidoName) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		System.out.println(sidoName);
		sb.append("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty");
		sb.append("?serviceKey=" + key);
		sb.append("&sidoName=");
		sb.append(URLEncoder.encode(sidoName, "UTF-8"));
		sb.append("&returnType=json");
		
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
	
	@GetMapping(value="/xml-pollution" , produces="application/xml; charset=UTF-8")
	public String xmlPollution(String sidoName) throws IOException {
		
		
		
		StringBuilder sb = new StringBuilder();
		System.out.println("잘옴 : " +sidoName);
		sb.append("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty");
		sb.append("?serviceKey=" + key);
		sb.append("&sidoName=");
		sb.append(URLEncoder.encode(sidoName, "UTF-8"));
		sb.append("&returnType=xml");
		
		String url = sb.toString();
		
		System.out.println(url);
		
		URL requestUrl = new URL(url);
		
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		
		
		urlConnection.setRequestMethod("GET");
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		
		String responseData = "";
		String line;
		
		while((line = br.readLine()) != null) {
			responseData += line;
		}
		System.out.println(responseData);
		
		
		try {
		  return responseData;
		} finally {
			br.close();
			urlConnection.disconnect();
			System.out.println("완료??");
		}
	}
}
