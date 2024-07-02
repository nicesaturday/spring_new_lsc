package com.ajaxpro.api.model.vo;

public class AirVo {
	private String pm10Value;
	private String o3Grade;
	private String no2Grade;
	private String no2Value;
	
	
	
	public AirVo() {
		super();
	}
	public AirVo(String pm10Value, String o3Grade, String no2Grade, String no2Value) {
		super();
		this.pm10Value = pm10Value;
		this.o3Grade = o3Grade;
		this.no2Grade = no2Grade;
		this.no2Value = no2Value;
	}
	public String getPm10Value() {
		return pm10Value;
	}
	public void setPm10Value(String pm10Value) {
		this.pm10Value = pm10Value;
	}
	public String getO3Grade() {
		return o3Grade;
	}
	public void setO3Grade(String o3Grade) {
		this.o3Grade = o3Grade;
	}
	public String getNo2Grade() {
		return no2Grade;
	}
	public void setNo2Grade(String no2Grade) {
		this.no2Grade = no2Grade;
	}
	public String getNo2Value() {
		return no2Value;
	}
	public void setNo2Value(String no2Value) {
		this.no2Value = no2Value;
	}
}
