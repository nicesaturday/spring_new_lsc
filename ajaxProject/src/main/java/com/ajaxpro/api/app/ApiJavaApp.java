package com.ajaxpro.api.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class ApiJavaApp {
	
	
	public final static String key = "oHfkaY88BRbD4M%2Fyx0m%2FtGQPCrjTmC97aKXu0LTqSYr8GwNqviM39OcSFzo00QDQ%2F8wqx98BelfUj%2BrtOSwqyA%3D%3D";
	
	public static void main(String[] args) throws IOException   {
		System.out.println("hi");
		
		
		
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty");
		sb.append("?serviceKey=" + key);
		sb.append("&sidoName=");
		sb.append(URLEncoder.encode("서울", "UTF-8"));
		sb.append("&returnType=json");
		
		String url = sb.toString();
		
		System.out.println(url);
		
		URL requestUrl = new URL(url);
		
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		
		
		urlConnection.setRequestMethod("GET");
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		
		
		while(true) {
			
			String res = br.readLine();
			
			if(res != null) {
				System.out.println(res);
			} else {
				break;
			}
			
			
			
		}
		
		
		
		
		
	}
}
