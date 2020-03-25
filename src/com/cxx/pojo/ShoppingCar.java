package com.cxx.pojo;

public class ShoppingCar {
	// 菜品名称
	private String name;
	// 菜品单价
	private float price;
	// 总共多少份
	private int count=1;
	//购物车 总共多少钱
	private float sum;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	@Override
	public String toString() {
		return "ShoppingCar [name=" + name + ", price=" + price + ", count=" + count + ", sum=" + sum + "]";
	}
	
}
