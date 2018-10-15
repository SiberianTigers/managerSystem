package com.jubao.service;

import com.jubao.pojo.ShopRefused;
/**
 * 店铺申请拒绝信息
 * @author 12146
 *
 */
public interface ShopRefusedService {

	
	   
		/***
		 * 添加拒绝信息
		 * @param shopRefused
		 * @return
		 */
		public int addShopRefused(ShopRefused shopRefused);
}
