package com.ajaxpro.api.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.ajaxpro.api.model.SocialMember;

@Service
public class KaKaoService {
	
	
	public String getToken(String code) throws IOException, ParseException {
		String urlstr = "https://kauth.kakao.com/oauth/token";
		URL url = new URL(urlstr);
		
		HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
		
		
		urlConnection.setRequestMethod("POST");
		urlConnection.setDoOutput(true);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("client_id=2901854041e3d8507e06378aa5f59b72");
		sb.append("&grant_type=authorization_code");
		sb.append("&redirect_uri=http://localhost/ajaxpro/oauth");
		sb.append("&code=");
		sb.append(code);
		System.out.println("이것임 : "  + sb.toString());
		bw.write(sb.toString());
		bw.flush();
		
		System.out.println(urlConnection.getResponseCode());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		
		String line = "";
		String responseData = "";
		
		while((line = br.readLine()) != null) {
			responseData += line;
		}
		
		System.out.println(responseData);
		
		JSONParser parser = new JSONParser();
		JSONObject element = (JSONObject)parser.parse(responseData);
		
		
		String accessToken = element.get("accress_token").toString();
		
		br.close();
		bw.close();
		
		return accessToken;
		
		
	}

	public String logout(String accessToken) throws IOException {
		
		String logoutUrl = "https://kapi.kakao.com/v1/user/logout";
		
		URL urll = new URL(logoutUrl);
		HttpURLConnection con = (HttpURLConnection) urll.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", "Bearer" + accessToken);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String responseData = "";
		String line = "";
		while((line = br.readLine()) != null) {
			responseData = line;
		}
		
		return responseData;
		
	}

	public SocialMember getUserInfo(String accessToken) throws ParseException {
		
		String userInfoUrl = "https://kapi.kakao.com/v2/user/me";
		
		SocialMember sm1 = null;
		
		try {
			URL url = new URL(userInfoUrl);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Authorization", "Bearer " + accessToken);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			
			String responseData = br.readLine();
			
			JSONObject responseobj = (JSONObject) new JSONParser().parse(responseData);
			JSONObject propObj = (JSONObject) responseobj.get("properties");
			
			sm1 = new SocialMember();
			sm1.setId(responseobj.get("id").toString());
			sm1.setNickName(propObj.get("nickName").toString());
			sm1.setThumbnailing(propObj.get("thumbnail_image").toString());
			
			
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return sm1;
	}
}
