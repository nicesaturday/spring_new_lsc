package com.ajaxpro.api.model;

public class SocialMember {
	private String id;
	private String nickName;
	private String thumbnailing;
	
	
	
	
	public SocialMember() {
		super();
	}
	public SocialMember(String id, String nickName, String thumbnailing) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.thumbnailing = thumbnailing;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getThumbnailing() {
		return thumbnailing;
	}
	public void setThumbnailing(String thumbnailing) {
		this.thumbnailing = thumbnailing;
	}
	
	
}
