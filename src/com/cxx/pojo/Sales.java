package com.cxx.pojo;

public class Sales {
	private String menusName;
	private int sumSales;
	public String getMenusName() {
		return menusName;
	}
	public void setMenusName(String menusName) {
		this.menusName = menusName;
	}
	public int getSumSales() {
		return sumSales;
	}
	public void setSumSales(int sumSales) {
		this.sumSales = sumSales;
	}
	@Override
	public String toString() {
		return "Sales [menusName=" + menusName + ", sumSales=" + sumSales + "]";
	}
	
}
