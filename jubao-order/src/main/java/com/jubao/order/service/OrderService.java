package com.jubao.order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.common.utis.JubaoResult;
import com.jubao.order.utils.Cart;
import com.jubao.pojo.User;
import com.jubao.pojo.UserAddress;

/**
 * 订单信息
 * @author 12146
 *
 */
public interface OrderService {

	
	 /***
	  * 显示用户收获地址
	  */
	public  List<UserAddress> getUserAddress(HttpServletRequest request);
	
	
	
	/***
     * 获取购物车
     * @param request
     * @return
     */
	public Cart getCart(HttpServletRequest request);
	
	
	  /***
	   * 	 创建订单
	   * @param order
	   * @return
	   */
	  public JubaoResult addOrder(HttpServletRequest request,Integer address_check);
	 
	  /***
	   * 获取用户
	   * @param request
	   * @return
	   */
	  public User checkLogin(HttpServletRequest request);
	
	
}
