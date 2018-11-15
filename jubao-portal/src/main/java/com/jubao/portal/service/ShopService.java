package com.jubao.portal.service;

import com.common.utis.JubaoResult;

public interface ShopService {

	/***
	 * 獲取店鋪信息和指定的商品數据
	 * @return
	 */
	public JubaoResult getShopAndItem(int sid,int pageSize);
	
}
