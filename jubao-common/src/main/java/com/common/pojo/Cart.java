package com.common.pojo;

import java.util.ArrayList;
import java.util.List;

import com.common.utis.JubaoResult;


/***
 * 购物车类
 * 
 * @author 12146
 *
 */
public class Cart {

	/*
	 * private List<CartItem> itemList = new ArrayList<CartItem>();// 商品
	 */
	private List<Store> storeList = new ArrayList<Store>();

	private double price;// 价格

	private int count;// 总数量

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Store> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Store> storeList) {
		this.storeList = storeList;
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
		for (Store store : storeList) { // 店铺

			if (store.getSid().longValue() == item.getSid().longValue()) { // 判断店铺id是否位一样

				for (CartItem citem : store.getItemList()) {

					System.out.println((citem.getItem().getId().longValue() == item.getId().longValue())
							+ "------for--------" + citem.getItemType().trim().equals(itemType.trim()));
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

				if (num > item.getNum()) {
					return JubaoResult.build(400, "商品库存不足");
				}
				item.setImage(item.geImgList().get(0));// 设置商品图片为一张
				// 添加新商品项
				CartItem cartItem = new CartItem(item, num, itemType);
				store.getItemList().add(cartItem);
				return JubaoResult.ok(this);
			}
		}

		if (num > item.getNum()) {
			return JubaoResult.build(400, "商品库存不足");
		}
		// 店铺id不 一样 则创建一个新的店铺项
		Store newStore = new Store();
		newStore.setSid(item.getSid()); // 店铺id
		newStore.setName(item.getStoreName());// 店铺名称

		item.setImage(item.geImgList().get(0));// 设置商品图片为一张
		// 添加新商品项
		CartItem cartItem = new CartItem(item, num, itemType);

		newStore.getItemList().add(cartItem);// 加入到店铺中商品项中

		storeList.add(newStore); // 将店铺加入到集合中
		System.out.println("--------商品不一樣----------");
		return JubaoResult.ok(this);
	}

	/****
	 * 修改購物車數量
	 * 
	 * @param pid
	 * @param itemType
	 * @param num
	 * @return
	 */
	public JubaoResult updateCartItem(long pid, String itemType, int num) {
		for (Store store : storeList) {

			for (CartItem citem : store.getItemList()) {

				if (citem.getItem().getId().longValue() == Long.valueOf(pid).longValue()
						&& citem.getItemType().trim().equals(itemType.trim())) {
					System.out.println("修改商品数量" + num);
					if (num > citem.getItem().getNum()) {
						return JubaoResult.build(400, "商品库存不足");
					}
					citem.setNum(num);
					return JubaoResult.ok(this);
				}
			}
		}
		return JubaoResult.build(400, "操作失败");
	}

	/***
	 * 刪除商品
	 * 
	 * @return
	 */
	public JubaoResult deleteCartItem(long pid, String itemType) {
		for (Store store : storeList) {
			for (CartItem citem : store.getItemList()) {

				if (citem.getItem().getId().longValue() == Long.valueOf(pid).longValue()
						&& citem.getItemType().trim().equals(itemType.trim())) {
					System.out.println("------删除购物车中的商品项---------" + pid);
					store.getItemList().remove(citem);
					return JubaoResult.ok(this);
				}
			}
		}
		return JubaoResult.build(400, "操作失败");
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
		for (Store item : storeList) {
			price += item.getPrice();
		}
		this.price = price;
		return price;
	}

}
