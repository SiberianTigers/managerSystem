package com.jubao.shop.service;

import java.util.List;
import java.util.Map;

import com.common.utis.JubaoResult;
import com.jubao.pojo.Shop;
import com.jubao.shop.pojo.vo.OrderCustom;
import com.jubao.shop.pojo.vo.OrderVo;

/***
 * 商品業務類
 * 
 * @author 12146
 *
 */
public interface ShopService {

	/***
	 * 根據用戶的id查找店鋪
	 * 
	 * @param userid
	 * @return
	 */
	Shop findShopByUserId(int userid);

	/***
	 * 根据店铺id查找订单
	 */
	List<OrderCustom>  findOrderByShop(int sid, int status);

	/***
	 * 修改订单状态
	 */
	JubaoResult updateOrderStatus(OrderVo ordervo);

	/***
	 * 查询出店铺的订单状态数量
	 */
	Map<String,String> findOrderStatusNumber(int shopid);
	
	/***
	 * 查询用户收获地址
	 * @param address
	 * @return
	 */
	OrderCustom findUserGetAddress(int address);
   
}
