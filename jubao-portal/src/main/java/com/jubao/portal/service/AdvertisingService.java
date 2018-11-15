package com.jubao.portal.service;

import java.util.Map;

import com.jubao.pojo.AdvertisingCategory;

/***
 * 广告业务类
 * @author 12146
 *
 */
public interface AdvertisingService {

  
	 
	/****
	 * 查询广告分类和广告
	 * @return
	 */
	 public   Map<String,AdvertisingCategory>  findAdvertisingCategoryAndAdvertising();

	

	
	
}
