package com.cxx.pojo;

public class Menus {
	private int menusId;
	private String menusName;
	private int typeId;
	private String menusBurden;
	private String menusBrief;
	private String menusPrice;
	private String menusSums;
	private String menusPricel;
	private String menusSumsl;
	private String menusImgpath;
	private String typeName;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getMenusId() {
		return menusId;
	}
	public void setMenusId(int menusId) {
		this.menusId = menusId;
	}
	public String getMenusName() {
		return menusName;
	}
	public void setMenusName(String menusName) {
		this.menusName = menusName;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getMenusBurden() {
		return menusBurden;
	}
	public void setMenusBurden(String menusBurden) {
		this.menusBurden = menusBurden;
	}
	public String getMenusBrief() {
		return menusBrief;
	}
	public void setMenusBrief(String menusBrief) {
		this.menusBrief = menusBrief;
	}
	public String getMenusPrice() {
		return menusPrice;
	}
	public void setMenusPrice(String menusPrice) {
		this.menusPrice = menusPrice;
	}
	public String getMenusSums() {
		return menusSums;
	}
	public void setMenusSums(String menusSums) {
		this.menusSums = menusSums;
	}
	public String getMenusPricel() {
		return menusPricel;
	}
	public void setMenusPricel(String menusPricel) {
		this.menusPricel = menusPricel;
	}
	public String getMenusSumsl() {
		return menusSumsl;
	}
	public void setMenusSumsl(String menusSumsl) {
		this.menusSumsl = menusSumsl;
	}
	public String getMenusImgpath() {
		return menusImgpath;
	}
	public void setMenusImgpath(String menusImgpath) {
		this.menusImgpath = menusImgpath;
	}
	@Override
	public String toString() {
		return "Menus [menusId=" + menusId + ", menusName=" + menusName + ", typeId=" + typeId + ", menusBurden="
				+ menusBurden + ", menusBrief=" + menusBrief + ", menusPrice=" + menusPrice + ", menusSums=" + menusSums
				+ ", menusPricel=" + menusPricel + ", menusSumsl=" + menusSumsl + ", menusImgpath=" + menusImgpath
				+ ", typeName=" + typeName + "]";
	}
	
	
}
