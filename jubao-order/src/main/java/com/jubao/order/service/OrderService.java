package com.jubao.order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.common.pojo.Cart;
import com.common.utis.JubaoResult;
import com.jubao.order.pojo.vo.OrderCustom;
import com.jubao.order.pojo.vo.OrderVo;
import com.jubao.order.pojo.vo.UserOrderStatusInfo;
import com.jubao.order.utils.AppOrderInfo;
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
	
	  /****
	 * 按照订单id查询出订单
	 */	   
	AppOrderInfo dfkOrder(Long orderid);
	
	
	
	  /***
	   * 根据店铺id查找订单
	   */
	   JubaoResult findOrderByShop(int sid,int status);
	   
		  /***
		   * 修改订单状态
		   */
	   JubaoResult updateOrderStatus(OrderVo order);
	   
	   /***
		   * 查询出店铺的订单状态数量
		   */
		  JubaoResult findOrderStatusNumber(int shopid);
		  
		  
		  
		  /***
		   * 查询用户收获地址
		   */
		  JubaoResult findUserGetAddress(int address);  
}
