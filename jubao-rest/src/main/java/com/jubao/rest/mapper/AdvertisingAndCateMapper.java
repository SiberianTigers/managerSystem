package com.jubao.rest.mapper;

import java.util.List;

import com.jubao.pojo.AdvertisingCategory;

/***
 * 广告和广告分类
 * @author 12146
 *
 */
public interface AdvertisingAndCateMapper {

	/***
	 *获取广告分类和该分类下的广告
	 *
	 *areaid  区域id
	 *
	 */
	public List<AdvertisingCategory> findAdvertisingCategoryAndAdvertising(int areaid);
	
	
	
}
