package com.jubao.order.utils;

import java.util.ArrayList;
import java.util.List;

import com.common.utis.JubaoResult;
import com.jubao.pojo.util.Item;

/***
 * 购物车类
 * 
 * @author 12146
 *
 */
public class Cart {

	private List<CartItem> itemList = new ArrayList<CartItem>();// 商品

	private double price;// 价格

	private int count;// 总数量

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/***
	 * 添加商品到购物车中
	 * 
	 * @return
	 * 
	 * 		1.先去redis中取购物车 有就取出， 没有就新建一个，
	 * 
	 * 
	 */
	public JubaoResult addCartItem(Item item, int num, String itemType) {
		// 遍历购物车项目
		for (CartItem citem : itemList) {
			System.out.println((citem.getItem().getId().longValue() == item.getId().longValue()) + "------for--------"
					+ citem.getItemType().trim().equals(itemType.trim()));
			// 判断商品的id是不是一样，还有商品的规格是不是一样
			if (citem.getItem().getId().longValue() == item.getId().longValue()
					&& citem.getItemType().trim().equals(itemType.trim())) {
				if (citem.getNum() + num > item.getNum()) {
					return JubaoResult.build(400, "商品库存不足");
				}
				System.out.println("--------商品一樣----------" + citem);
				citem.setNum(citem.getNum() + num);
				return JubaoResult.ok(this);
			}
		}

		item.setImage(item.geImgList().get(0));// 设置商品图片为一张
		// 添加新商品项
		CartItem cartItem = new CartItem(item, num, itemType);
		itemList.add(cartItem);// 加入购物车中
		System.out.println("--------商品不一樣----------");
		return JubaoResult.ok(this);
	}

	/****
	 * 修改購物車數量
	 * @param pid
	 * @param itemType
	 * @param num
	 * @return
	 */
	public JubaoResult updateCartItem(long pid, String itemType, int num) {
		for (CartItem citem : itemList) {
			if (citem.getItem().getId().longValue() == Long.valueOf(pid).longValue()
					&& citem.getItemType().trim().equals(itemType.trim())) {
				System.out.println("修改商品数量"+num);
				if(num>citem.getItem().getNum()){
					 return JubaoResult.build(400,"商品库存不足");
				}
				citem.setNum(num);
				return JubaoResult.ok(this);
			}
		}
          return JubaoResult.build(400,"操作失败");
	}

	
	/***
	 * 刪除商品
	 * @return
	 */
	public JubaoResult deleteCartItem(long pid, String itemType){
		
		
		for (CartItem citem : itemList) {
			
			if (citem.getItem().getId().longValue() == Long.valueOf(pid).longValue()
					&& citem.getItemType().trim().equals(itemType.trim())) {
				 System.out.println("------删除购物车中的商品项---------"+pid);  
				itemList.remove(citem);
				return JubaoResult.ok(this);
			}			
		}
		return JubaoResult.build(400,"操作失败");
	}
	
	
	
	
	
	public List<CartItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CartItem> itemList) {
		this.itemList = itemList;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// 计算总价格价格
	public double CartItemPrice() {
		double price = 0;
		for (CartItem item : itemList) {
			price += item.getPrice();
		}
		this.price = price;
		return price;
	}

}
