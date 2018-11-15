package com.jubao.order.mapper;

import com.jubao.order.pojo.Order;
import com.jubao.order.pojo.OrderDetail;

/***
 * 订单表
 * @author 12146
 *
 */
public interface OrderMapper {
	
	
	 //创建订单
	  public int create_Order(Order order);
	 
	  
	  //创建订单明细
	  public int create_order_detail(OrderDetail  orderDetail);
}
