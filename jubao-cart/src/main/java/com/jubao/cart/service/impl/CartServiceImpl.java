package com.jubao.cart.service.impl;

import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utis.Constants;
import com.common.utis.CookieUtils;
import com.common.utis.JubaoResult;
import com.jubao.cart.dao.JedisClient;
import com.jubao.cart.mapper.CartItemMapper;
import com.jubao.cart.service.CartService;
import com.jubao.cart.utils.Cart;
import com.jubao.cart.utils.CartItem;
import com.jubao.pojo.util.Item;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartItemMapper cartItemMapper;

	@Autowired
	private JedisClient jedisClient;

	@Override
	public JubaoResult addItem(long pid, String itemInfo, int num, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		JubaoResult jb = null;
		try {
			Item item = cartItemMapper.findItem(pid);// 查询出商品
			System.out.println(item + "------商品信息----------");
			Cart cart = getCart(request, response);
			jb = cart.addCartItem(item, num, itemInfo);
			System.out.println(jb.getStatus());
			if (jb.getStatus() == 200) {
				cart.countItemNumber();// 设置购物车中的商品项数量
				cart.CartItemPrice();// 计算购物车总金额
				Object o = JSONObject.toJSON(cart);
				// 转化为json对象存放到cookie
				System.out.println(o + "----------vvvvvvvvvvvvvvvvv---");
				jedisClient.set(cart.getUser_key(), o.toString());
				// cartItemMapper.updateItemNumberdesc(pid, num);// 修改商品库存
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jb;
	}

	/***
	 * 獲取購物車
	 * 
	 * @return
	 */
	public Cart getCart(HttpServletRequest request, HttpServletResponse response) {
		Cart cart = null;

	//	String token = CookieUtils.getCookieValue(request, Constants.JB_TOKEN);
		// 從cookie中獲取購物車
		String user_key = CookieUtils.getCookieValue(request, Constants.USER_KEY, true);
		//if (StringUtils.isBlank(token)) {
			System.out.println(user_key);
			if (StringUtils.isBlank(user_key)) {// 如果cookie中沒有購物車則新建一個
				UUID uuid = UUID.randomUUID(); // 生成一个uuid 代表用户的标记
				CookieUtils.setCookie(request, response, Constants.USER_KEY, uuid.toString(), true);// 将uuid存入到/
				cart = new Cart();
				cart.setUser_key(uuid.toString());
				String cartStr = JSONArray.toJSONString(cart); // 转化为json对象存放到cookie
				jedisClient.set(cart.getUser_key(), cartStr);
				System.out.println("沒有購物車=--------------------" + cartStr);
			} else {// 有購物車則转为java对象
				// 到redis中拿出
				String rediscart = jedisClient.get(user_key);
				System.out.println("有購物車=---------------------" + rediscart);
				cart = JSONObject.parseObject(rediscart, Cart.class);
				// 设置session的过期时间
				jedisClient.expire(user_key, 3600);
			}
	/*	} else {
			Integer index = (Integer) request.getSession().getAttribute("index");
			if (index == 1) {
				request.getSession().setAttribute("index", 2);
				// 已经登录
				// 先去redis中取出购物车
				String redisCart = jedisClient.get(Constants.CART + token);

				if (StringUtils.isBlank(redisCart)) {
					// 到redis中拿出
					String rediscart = jedisClient.get(user_key);
					jedisClient.set(Constants.CART + token, rediscart);
					jedisClient.del(user_key); // 删除临时的
					cart = JSONObject.parseObject(rediscart, Cart.class);
					return cart;
				} else {
					// 登录状态时的购物车
					cart = JSONObject.parseObject(redisCart, Cart.class);// 将redis中取出的购物车转为对象
					// 同时取出未登录状态的购物车
					String lscart = jedisClient.get(user_key);
					Cart wdlcart = JSONObject.parseObject(lscart, Cart.class);

					// 将两个状态合并为登录时的购物车
					for (Entry<Long, List<CartItem>> cart1 : wdlcart.getCartMap().entrySet()) {

						if (cart.getCartMap().containsKey(cart1.getKey())) {

							List<CartItem> c1CartItem = cart.getCartMap().get(cart1.getKey());
							List<CartItem> c2CartItem = wdlcart.getCartMap().get(cart1.getKey());

							for (int x = 0; x < c1CartItem.size(); x++) {

								if (!c1CartItem.get(x).equals(c2CartItem.get(x))) {
									c1CartItem.add(c2CartItem.get(x));
								}

							}

						} else {
							cart.getCartMap().put(cart1.getKey(), cart1.getValue());
						}

					}
					return cart;
				}
			} else {
				// 先去redis中取出购物车
				String redisCart = jedisClient.get(Constants.CART + token);
				// 登录状态时的购物车
				cart = JSONObject.parseObject(redisCart, Cart.class);// 将redis中取出的购物车转为对象
				return cart;
			}
		}

		return cart;*/
			return cart;
	}

	/***
	 * 修改购物车商品数量，和删除商品
	 */
	@Override
	public JubaoResult updateCartItem(long pid, int num, String itemType, long sid, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		JubaoResult jb = null;
		try {
			Cart cart = getCart(request, response);
			jb = cart.updateCartItem(pid, itemType, num, sid);// 修改购物车
			System.out.println(jb.getStatus() + "---修改状态---");
			if (jb.getStatus() == 200) {
				cart.countItemNumber();// 设置购物车中的商品项数量
				cart.CartItemPrice();// 计算购物车总金额
				Object o = JSONObject.toJSON(cart);
				// 转化为json对象存放到cookie
				System.out.println(o + "----------vvvvvvvvvvvvvvvvv---");
				jedisClient.set(cart.getUser_key(), o.toString());
				if (jb != null) {
					if (jb.getMsg().equals("1")) {
						// 减少
						// cartItemMapper.updateItemNumberdesc(pid, num);
					} else if (jb.equals("2")) {
						// 增加
						// cartItemMapper.updateItemNumberAsc(pid, num);
					}
					return jb;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jb;
	}

	@Override
	public JubaoResult deleteCartItem(long pid, String itemType, long sid, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		JubaoResult jb = null;
		try {
			Cart cart = getCart(request, response);
			jb = cart.deleteCartItem(pid, itemType, sid);// 删除购物车商品项
			if (jb.getStatus() == 200) {
				cart.countItemNumber();// 设置购物车中的商品项数量
				cart.CartItemPrice();// 计算购物车总金额
				Object o = JSONObject.toJSON(cart);
				// 转化为json对象存放到cookie
				System.out.println(o + "----------vvvvvvvvvvvvvvvvv---");
				jedisClient.set(cart.getUser_key(), o.toString());
				// 修改商品库存
				// cartItemMapper.updateItemNumberAsc(pid, num);
				return jb;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jb;
	}

	/****
	 * 將購物車存放到redis中
	 * 
	 * @param cart
	 * @param request
	 */

	public void setCartRedis(Cart cart, HttpServletRequest request) {
		boolean flag = false;

		String token = CookieUtils.getCookieValue(request, Constants.JB_TOKEN);
		if (!StringUtils.isBlank(token)) {
			String result = jedisClient.get(Constants.CART + token);

			if (StringUtils.isBlank(result)) {
				String cartStr = JSON.toJSONString(cart); // 转化为json对象存放到cookie
				jedisClient.set(Constants.CART + token, cartStr);
				return;
			}

			Cart redisCart = JSONObject.parseObject(result, Cart.class);

			if (flag) {
				String cartStr = JSON.toJSONString(redisCart); // 转化为json对象存放到cookie
				jedisClient.set(Constants.CART + token, cartStr);
			}
		}

	}
}
