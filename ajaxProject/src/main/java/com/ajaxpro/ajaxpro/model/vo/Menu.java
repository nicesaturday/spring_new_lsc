package com.ajaxpro.ajaxpro.model.vo;


public class Menu {
	private int menuNumber;
	private String menuName;
	private int price;
	private String meterial;
	public Menu(int menuNumber, String menuName, int price, String meterial) {
		super();
		this.menuNumber = menuNumber;
		this.menuName = menuName;
		this.price = price;
		this.meterial = meterial;
	}
	public int getMenuNumber() {
		return menuNumber;
	}
	public void setMenuNumber(int menuNumber) {
		this.menuNumber = menuNumber;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMeterial() {
		return meterial;
	}
	public void setMeterial(String meterial) {
		this.meterial = meterial;
	}
	@Override
	public String toString() {
		return "Menu [menuNumber=" + menuNumber + ", menuName=" + menuName + ", price=" + price + ", meterial="
				+ meterial + "]";
	}
	
	
	
}
