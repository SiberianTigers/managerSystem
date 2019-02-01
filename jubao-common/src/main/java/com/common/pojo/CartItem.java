package com.common.pojo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/***
 * 购物车项
 * 
 * @author 12146
 *
 */
public class CartItem {

	private Item item;// 商品

	private int num;// 数量

	private double price;// 价格
	
	private String itemType;//商品规格


	public CartItem(Item item, int num, String itemType) {
		super();
		this.item = item;
		this.num = num;	
		this.itemType=itemType;
	}

	
	
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		
		this.itemType = itemType;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		
		this.num = num;
		getPrice();
	}
   
	public double getPrice() {//计算价格
		if (item != null) {
		 this.price = item.getPrice() * num;
		}
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
