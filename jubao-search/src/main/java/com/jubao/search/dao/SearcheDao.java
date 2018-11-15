package com.jubao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.jubao.pojo.util.Item;
import com.jubao.pojo.util.SearchResult;

/***
 * 搜索结果
 * @author 12146
 *
 */
public interface SearcheDao {
    
	/***
	 * 到solr中搜索结果
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public SearchResult search(SolrQuery query)throws Exception;
	
	
	/***
	 * 根据id去索引库查找商品
	 */
	public Item searchById(SolrQuery query)throws Exception;
	
}
