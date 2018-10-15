package com.jubao.mapper;

import org.apache.ibatis.annotations.Param;

import com.jubao.pojo.ShopMessage;

/***
 * 店铺私信
 * @author 12146
 *
 */
public interface ShopMessageMapper {

	/***
	 * 发送信息
	 * @param shopMessage
	 * @return
	 */
	public int addShopMessage(ShopMessage shopMessage);
	
	
	/***
	 * 批量添加信息
	 */
	
	public int BatchAdd(@Param("shopMessage")ShopMessage shopMessage,@Param("arr") int [] arr);
		
}
