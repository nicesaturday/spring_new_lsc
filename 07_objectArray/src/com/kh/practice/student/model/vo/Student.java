package com.kh.practice.student.model.vo;

public class Student {
	private String name;
	private String subject;
	private int score;
	
	public Student() {}
	
	public Student(String name, String subject , int score) {
		this.name = name;
		this.subject = subject;
		this.score = score;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubject() {
		return this.subject;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return this.score;
	}

	
	public String inform() {
		return "Student [name=" + name + ", subject=" + subject + ", score=" + score + "]";
	}
	
	
}
