package com.jubao.mapper;

import com.jubao.pojo.ShopRefused;

/***
 * 店铺审核拒绝信息
 * @author 12146
 *
 */
public interface ShopRefusedMapper {
   
	/***
	 * 添加拒绝信息
	 * @param shopRefused
	 * @return
	 */
	public int addShopRefused(ShopRefused shopRefused);
}
