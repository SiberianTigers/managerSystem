package com.jubao.cart.utils;

import com.jubao.pojo.util.Item;

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

	public CartItem(Item item, int num) {
		super();
		this.item = item;
		this.num = num;
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

	public double getPrice() {// 计算价格
		if (item != null) {
			this.price = item.getPrice() * num;
		}
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj instanceof Item)) {
			return false;
		}
		Item additem = (Item) obj;// 将传递进来的对象转为item商品对象

		System.out.println("-additem--比较--");

		System.out.println(((additem.getId().intValue() == this.item.getId().intValue())
				&& (additem.getItemType().equals(this.item.getItemType()))) == true ? true : false);

		return ((additem.getId().intValue() == this.item.getId().intValue())
				&& (additem.getItemType().equals(this.item.getItemType()))) == true ? true : false;
	}

}
