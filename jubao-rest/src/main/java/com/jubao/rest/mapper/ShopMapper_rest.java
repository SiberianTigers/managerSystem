package com.jubao.rest.mapper;

import org.apache.ibatis.annotations.Param;

import com.jubao.pojo.Shop;

/***
 * 店铺
 * @author 12146
 *
 */
public interface ShopMapper_rest {

	/***
	 * 根据店铺id查询出店铺和店铺
	 * @param sid
	 * @return
	 */
	public Shop findByIdShop(@Param("sid")int sid,@Param("pageSize")int pageSize);
	
}
