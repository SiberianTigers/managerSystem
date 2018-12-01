package com.jubao.shop.mapper;

import com.jubao.pojo.Shop;

/***
 * 店鋪  dao
 * @author 12146
 *
 */
public interface UserShopMapper {

	/***
	 * 根據用戶id查找店鋪
	 * @param userid
	 * @return
	 */
	Shop findShopByUserid(int userid);
}
