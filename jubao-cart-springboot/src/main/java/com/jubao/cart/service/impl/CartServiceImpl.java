package com.jubao.cart.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.utis.Constants;
import com.common.utis.CookieUtils;
import com.common.utis.JubaoResult;
import com.jubao.cart.dao.JedisClient;
import com.jubao.cart.mapper.CartItemMapper;
import com.jubao.cart.service.CartService;
import com.jubao.cart.utils.Cart;
import com.jubao.pojo.util.Item;



@Service
/*@Transactional*/
public class CartServiceImpl implements CartService {

	@Autowired
	private CartItemMapper cartItemMapper;

	private JedisClient jedisClient;

	@Override
	public JubaoResult addItem(long pid, String itemInfo, int num, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		JubaoResult jb = null;
		try {
			Item item = cartItemMapper.findItem(pid);// 查询出商品
			System.out.println(item + "------商品信息----------");
			Cart cart = getCart(request);
			jb = cart.addCartItem(item, num, itemInfo);
			System.out.println(jb.getStatus());
			if (jb.getStatus() == 200) {
				cart.countItemNumber();// 设置购物车中的商品项数量
				cart.CartItemPrice();// 计算购物车总金额
				String cartStr = JSON.toJSONString(cart); // 转化为json对象存放到cookie
				// cartStr=URLEncoder.encode(cartStr,"utf-8");//防止中文乱码
				// Cookie cookie = new Cookie(Constants.CART, cartStr);
				CookieUtils.setCookie(request, response, Constants.CART, cartStr, true);
				// cookie.setMaxAge(3600 * 24);// 存放一个 24小时
				// response.addCookie(cookie);//將cookie加入到response
				cartItemMapper.updateItemNumberdesc(pid, num);// 修改商品库存
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
	public Cart getCart(HttpServletRequest request) {

		Cart cart = null;
		// 從cookie中獲取購物車
		String cartStr = CookieUtils.getCookieValue(request, Constants.CART, true);
		System.out.println(cartStr);
		if (StringUtils.isBlank(cartStr)) {// 如果cookie中沒有購物車則新建一個
			cart = new Cart();
			System.out.println("沒有購物車=--------------------");
		} else {// 有購物車則转为java对象
			System.out.println("有購物車=---------------------");
			cart = JSONObject.parseObject(cartStr, Cart.class);
		}
		return cart;
	}

	/***
	 * 修改购物车商品数量，和删除商品
	 */
	@Override
	public JubaoResult updateCartItem(long pid, int num, String itemType, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		JubaoResult jb = null;
		try {
			Cart cart = getCart(request);
			jb = cart.updateCartItem(pid, itemType, num);// 修改购物车
			if (jb.getStatus() == 200) {
				cart.countItemNumber();// 设置购物车中的商品项数量
				cart.CartItemPrice();// 计算购物车总金额
				String cartStr = JSON.toJSONString(cart); // 转化为json对象存放到cookie
				// cartStr=URLEncoder.encode(cartStr,"utf-8");//防止中文乱码
				// Cookie cookie = new Cookie(Constants.CART, cartStr);
				CookieUtils.setCookie(request, response, Constants.CART, cartStr, true);
				if (jb != null) {
					if (jb.getMsg().equals("1")) {
						// 减少
						cartItemMapper.updateItemNumberdesc(pid, num);
					} else if (jb.equals("2")) {
						// 增加
						cartItemMapper.updateItemNumberAsc(pid, num);
					}
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jb;
	}

	@Override
	public JubaoResult deleteCartItem(long pid, String itemType, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		JubaoResult jb = null;
		try {
			Cart cart = getCart(request);
			jb = cart.deleteCartItem(pid, itemType);// 删除购物车商品项
			if (jb.getStatus() == 200) {
				cart.countItemNumber();// 设置购物车中的商品项数量
				cart.CartItemPrice();// 计算购物车总金额
				String cartStr = JSON.toJSONString(cart); // 转化为json对象存放到cookie
				// cartStr=URLEncoder.encode(cartStr,"utf-8");//防止中文乱码
				// Cookie cookie = new Cookie(Constants.CART, cartStr);
				CookieUtils.setCookie(request, response, Constants.CART, cartStr, true);
				int num = Integer.parseInt(jb.getMsg());// 删除的商品数量
				// 修改商品库存
				cartItemMapper.updateItemNumberAsc(pid, num);
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
