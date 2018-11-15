package com.jubao.search.service;

import com.common.utis.JubaoResult;

public interface ItemService {

	
	/***
	 * 查询出所有的商品导入到solr集群
	 * @return
	 */
	public JubaoResult getProductList();
}
