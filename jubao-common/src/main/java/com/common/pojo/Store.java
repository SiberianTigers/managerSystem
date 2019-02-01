package com.common.pojo;

import java.util.ArrayList;
import java.util.List;

/***
 * 店铺
 * @author 12146
 *
 */
public class Store {

	private List<CartItem> itemList = new ArrayList<CartItem>();// 商品
	
	private Long sid;//店铺id
	
	private String name;//店铺名称
	
	private double price;//店铺合计价格

	
	// 计算总价格价格
	public double StoreItemPrice() {
		double price = 0;
		for (CartItem item : itemList) {
			price += item.getPrice();
		}
		this.price = price;
		return price;
	}
	
	
	
	public List<CartItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CartItem> itemList) {
		this.itemList = itemList;
	}



	public Long getSid() {
		return sid;
	}



	public void setSid(Long sid) {
		this.sid = sid;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		
		return StoreItemPrice();
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
	
}
