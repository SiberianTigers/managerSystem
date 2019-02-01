package com.jubao.cart.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.common.utis.JubaoResult;
import com.jubao.pojo.util.Item;

/***
 * 改进版购物车
 * 
 * @author 12146
 *
 */
public class Carts {

	/***
	 * 店铺id为主键进行存储
	 * 
	 * 购物车集合
	 */

	private Map<Long, List<CartItem>> cartList = new HashMap<Long, List<CartItem>>();

	private double price;// 价格

	private int count;// 总数量

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
		System.out.println("--------" + item.getSid());
		item.setItemType(itemType);
		if (cartList.containsKey(item.getSid())) {// 判断店铺id是否位一样
			// 根据店铺id取出商品集合
			List<CartItem> cartItem = cartList.get(item.getSid());

			if (cartItem.contains(item)) {
				// 判断商品的id是不是一样，还有商品的规格是不是一样

				for (CartItem items : cartItem) {

					if (items.equals(item)) {
						if (items.getNum() + num > item.getNum()) {
							return JubaoResult.build(400, "商品库存不足");
						}
						System.out.println("--------商品一樣----------" + items);
						items.setNum(items.getNum() + num);
						return JubaoResult.ok(this);
					}

				}

			}

			if (num > item.getNum()) {
				return JubaoResult.build(400, "商品库存不足");
			}
			item.setImage(item.geImgList().get(0));// 设置商品图片为一张
			// 添加新商品项
			CartItem newCartItem = new CartItem(item, num);
			cartItem.add(newCartItem);
			return JubaoResult.ok(this);

		}

		// 店铺不一样

		if (num > item.getNum()) {
			return JubaoResult.build(400, "商品库存不足");
		}
		// 店铺id不 一样 则创建一个新的店铺项

		item.setImage(item.geImgList().get(0));// 设置商品图片为一张
		item.setImage(null);
		// 添加新商品项
		CartItem newCartItem = new CartItem(item, num);
		List<CartItem> cartList = new ArrayList<CartItem>();
		cartList.add(newCartItem);
		this.cartList.put(item.getSid(), cartList);
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
		JubaoResult jb = new JubaoResult();
		Item item = new Item();
		item.setId(pid);
		item.setItemType(itemType);
		item.setNum(num);
		if (cartList.containsKey(item.getSid())) {// 判断店铺id是否位一样
			// 根据店铺id取出商品集合
			List<CartItem> cartItem = cartList.get(item.getSid());

			if (cartItem.contains(item)) {
				// 判断商品的id是不是一样，还有商品的规格是不是一样

				System.out.println("修改商品数量" + num);
				for (CartItem items : cartItem) {
					if (items.equals(item)) {
						if (items.getNum() > num) {
							jb.setMsg("1");
						} else if (items.getNum() < num) {
							jb.setMsg("2");
						} else {
							jb.setMsg("0");
						}

						items.setNum(num);
						jb.setStatus(200);
						jb.setData(this);
						return jb;
					}
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
		JubaoResult jb = new JubaoResult();
		Item item = new Item();
		item.setId(pid);
		item.setItemType(itemType);
		if (cartList.containsKey(item.getSid())) {// 判断店铺id是否位一样
			// 根据店铺id取出商品集合
			List<CartItem> cartItem = cartList.get(item.getSid());

			if (cartItem.contains(item)) {
				// 判断商品的id是不是一样，还有商品的规格是不是一样
				for (CartItem items : cartItem) {
					if (items.equals(item)) {
						System.out.println("------删除购物车中的商品项---------" + pid);
						cartItem.remove(items);
						jb.setMsg(items.getNum() + "");
						jb.setStatus(200);
						jb.setData(this);
						return jb;
					}
				}

			}

		}

		return JubaoResult.build(400, "操作失败");

	}

	/***
	 * 统计购物车中的数量
	 * 
	 * @return
	 */
	public int countItemNumber() {
		int count = 0;
		for (Entry<Long, List<CartItem>> entry : cartList.entrySet()) {
			for (CartItem cartItem : entry.getValue()) {
				count += cartItem.getNum();
			}
		}
		this.count = count;
		return count;
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
		for (Entry<Long, List<CartItem>> entry : cartList.entrySet()) {
			for (CartItem cartItem : entry.getValue()) {
				price += cartItem.getPrice();
			}
		}
		this.price = price;
		return price;
	}

}
