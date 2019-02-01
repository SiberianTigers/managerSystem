package com.jubao.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jubao.order.pojo.Order;
import com.jubao.order.pojo.OrderDetail;
import com.jubao.order.pojo.vo.OrderCustom;
import com.jubao.order.pojo.vo.OrderVo;
import com.jubao.order.pojo.vo.UserOrderStatusInfo;

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

  
	  //按照订单id查询出订单
	  Order findOrderById(Long orderid);

	  /***
	   * 根据店铺id查找订单
	   */
	  List<OrderCustom> findOrderByShop(@Param("sid")int sid,@Param("status")int status);
	  
	  
	  
	  /***
	   * 修改订单状态
	   */
	  int updateOrderStatus(OrderVo order);
	  
	  
	  /***
	   * 查询出店铺的订单状态数量
	   */
	  List<UserOrderStatusInfo> findOrderStatusNumber(int shopid);
	  
	  /***
	   * 查询用户收获地址
	   */
	  OrderCustom findUserGetAddress(int address);  
	  
}
