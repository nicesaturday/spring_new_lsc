package com.ajaxpro.api.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.ajaxpro.api.model.vo.AirVo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


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
		
		
		String response = "";
		while(true) {
			
			String res = br.readLine();
			
			if(res != null) {
				response += res;
			} else {
				break;
			}
			
			
			
		}
		
		
		JsonObject jsonObj = JsonParser.parseString(response).getAsJsonObject().getAsJsonObject("response").getAsJsonObject("body");
		int totalCount = jsonObj.get("totalCount").getAsInt();
		
		JsonArray items = jsonObj.getAsJsonArray("items");
		
		
		
		System.out.println(response);
		System.out.println(jsonObj);
		System.out.println(totalCount);
		System.out.println(items);
		
		
		
		
		
		List<AirVo> alist =  new ArrayList<AirVo>();
		
		
		for(int i = 0; i < items.size(); i++) {
			JsonObject item = items.get(i).getAsJsonObject();
			AirVo airvo = new AirVo();
			airvo.setPm10Value(item.get("pm10Value").getAsString());
			airvo.setNo2Grade(item.get("no2Grade").getAsString());
			airvo.setO3Grade(item.get("03Grade").getAsString());
			airvo.setNo2Value(item.get("no2Value").getAsString());
		}
		
		br.close();
		urlConnection.disconnect();
		
		
		
		
		
		
		
	}
}
