package com.jubao.rest.service;

import java.util.List;

import com.jubao.pojo.AdvertisingCategory;

/***
 * 广告业务类
 * @author 12146
 *
 */
public interface AdvertisingAndCateService {

 
	 /**
	  *同步广告信息到缓存中
	  */
	 
	 public long delAdvertisingRedis();
	
		/***
		 * 首页广告   获取广告分类和该分类下的广告
		 *
		 *areaid  区域id
		 *
		 */
		public List<AdvertisingCategory> findAdvertisingCategoryAndAdvertising();
		
}
