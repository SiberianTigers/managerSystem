package com.jubao.portal.service;

import com.jubao.pojo.util.Item;
import com.jubao.pojo.util.SearchResult;

/****
 * 商品查询业务类
 * @author 12146
 *
 */
public interface ItemSearchService{

	
    
	
	/***
	 * 搜索商品
	 * @return
	 */
	public SearchResult SearchItem(String queryString,String price,int page);
	
	
	
	/***
	 * 根据商品id查询出商品详情
	 */
	public Item searchByIdProduct(Long  pid);
	
}
