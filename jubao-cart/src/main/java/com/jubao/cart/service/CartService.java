package com.jubao.cart.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.utis.JubaoResult;
import com.jubao.cart.utils.Cart;


public interface CartService {

	/***
	 * 添加商品到购物车
	 * @param pid
	 * @param itemInfo
	 * @return
	 */
	public JubaoResult  addItem(long pid,String itemInfo,int num,HttpServletRequest request,HttpServletResponse response);
	
	
     /***
      * 获取购物车
      * @param request
      * @return
      */
	public Cart getCart(HttpServletRequest request,HttpServletResponse response);
	
	/***
	 * 修改购物车项 数量
	 * @param pid
	 * @param num
	 * @return
	 */
	public JubaoResult updateCartItem(long pid,int num,String itemType,long sid,HttpServletRequest request,HttpServletResponse response);
	
	
	/***
	 * 删除购物车项
	 * @param pid
	 * @param num
	 * @return
	 */
	public JubaoResult deleteCartItem(long pid,String itemType,long sid,HttpServletRequest request,HttpServletResponse response);
}
