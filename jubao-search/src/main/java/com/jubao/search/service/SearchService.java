package com.jubao.search.service;

import com.jubao.pojo.util.Item;
import com.jubao.pojo.util.SearchResult;

public interface SearchService {

	
	public SearchResult search(String queryString,String price,int page ,int rows)throws Exception;
	
	
	/***
	 * 根据商品id查询出商品
	 * @param id
	 * @return
	 */
	public Item searchById(Long id);
	
}
