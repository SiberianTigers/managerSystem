package com.jubao.search.mapper;

import java.util.List;

import com.jubao.pojo.util.Item;

/***
 * 搜索商品
 * @author 12146
 *
 */
public interface ItemMapper {

	/***
	 * 查询出所有的商品导入到solr集群
	 * @return
	 */
	public List<Item> getProductList();
}
