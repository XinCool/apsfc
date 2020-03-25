package com.cxx.pojo;

import java.util.List;

import org.apache.catalina.User;

public class Orders {
	private int orderId;
	// 订购数量
	private String orderMenusum;
	// 订购时间
	private String orderTimes;
	// 是否配送
	private String orderDelivery;
	
	
	
	/*private int userId;
	private String userRealname;
	private String userPhone;
	private String userAddress;
	
	private int menusId;
	private String menusName;
	private String menusPricel;
	private String menusPrice;*/
	Menus menus;
	Users users;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderMenusum() {
		return orderMenusum;
	}
	public void setOrderMenusum(String orderMenusum) {
		this.orderMenusum = orderMenusum;
	}
	public String getOrderTimes() {
		return orderTimes;
	}
	public void setOrderTimes(String orderTimes) {
		this.orderTimes = orderTimes;
	}
	public String getOrderDelivery() {
		return orderDelivery;
	}
	public void setOrderDelivery(String orderDelivery) {
		this.orderDelivery = orderDelivery;
	}
	public Menus getMenus() {
		return menus;
	}
	public void setMenus(Menus menus) {
		this.menus = menus;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderMenusum=" + orderMenusum + ", orderTimes=" + orderTimes
				+ ", orderDelivery=" + orderDelivery +  ", menus=" + menus + ", users="
				+ users + "]";
	}
	
	
}
