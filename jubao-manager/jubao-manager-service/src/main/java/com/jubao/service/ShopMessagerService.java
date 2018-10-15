package com.jubao.service;

import org.apache.ibatis.annotations.Param;

import com.jubao.pojo.ShopMessage;

public interface ShopMessagerService {
	/***
	 * 发送信息
	 * @param shopMessage
	 * @return
	 */
	public int addShopMessage(ShopMessage shopMessage);
	
	
	
	/***
	 * 批量添加信息
	 */
	
	public int BatchAdd(ShopMessage shopMessage, int [] arr);
}
