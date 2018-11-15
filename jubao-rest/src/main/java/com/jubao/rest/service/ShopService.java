package com.jubao.rest.service;

import org.apache.ibatis.annotations.Param;

import com.jubao.pojo.Shop;

public interface ShopService {

	
	/***
	 * 根据店铺id查询出店铺和店铺
	 * @param sid
	 * @return
	 */
	public Shop findByIdShop(int sid,int pageSize);
}
