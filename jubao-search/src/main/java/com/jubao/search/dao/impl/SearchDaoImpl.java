package com.jubao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import com.jubao.pojo.util.Item;
import com.jubao.pojo.util.SearchResult;
import com.jubao.search.dao.SearcheDao;

@Repository
public class SearchDaoImpl implements SearcheDao {

	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		// TODO Auto-generated method stub
	//返回值对象
		SearchResult result=new SearchResult();
		
		//根据c查询条件查询索引库
		QueryResponse queryResponse=solrServer.query(query);
		//去查询结果
		SolrDocumentList solrDocumentList=queryResponse.getResults();
		//取查询结果总数量
		result.setRecordCount(solrDocumentList.getNumFound());
		
		//商品列表
		List<Item>itemList=new ArrayList<Item>();
		
		//取高亮显示
		Map<String,Map<String,List<String>>> gaoliang=queryResponse.getHighlighting();
		
		//取商品列表
		for(SolrDocument solrDocument:solrDocumentList){
			//创建商品对象
			Item item=new Item();
			item.setPid((String)solrDocument.get("id"));
			//取高亮显示的结果
			List<String> list=gaoliang.get(solrDocument.get("id")).get("item_title");
			String title="";
			if(list!=null&&list.size()>0){
				title=list.get(0);
			}else{
				title=(String)solrDocument.get("item_title");
			}
			item.setTitle(title);
			item.setImage((String)solrDocument.get("item_image"));
			item.setPrice((long)solrDocument.get("item_price"));
			item.setSellPoint((String)solrDocument.get("item_sell_point"));
			item.setCategoryName((String)solrDocument.get("item_category_name"));
			item.setSid((long)solrDocument.get("store_id"));
			 item.setStoreName ((String)solrDocument.get("store_name"));
			//添加的商品列表
			itemList.add(item);
		}
		result.setItemList(itemList);
		
		return result;
	}

	@Override
	public Item searchById(SolrQuery  query) throws Exception {
		// TODO Auto-generated method stub
		// 创建商品对象
		Item item = new Item();
		// 根据c查询条件查询索引库
		QueryResponse queryResponse = solrServer.query(query);
		// 去查询结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		// 商品列表
		// 取商品列表
		for (SolrDocument solrDocument : solrDocumentList) {
			item.setPid((String) solrDocument.get("id"));
			item.setTitle((String) solrDocument.get("item_title"));
			item.setImage((String) solrDocument.get("item_image"));
			item.setPrice((long) solrDocument.get("item_price"));
			item.setSellPoint((String) solrDocument.get("item_sell_point"));
			item.setSid((long)solrDocument.get("store_id"));
			item.setCategoryName((String) solrDocument.get("item_category_name"));

			System.out.println(item.getTitle() + "---------" + item.getPrice());
		}
		solrServer.commit();

		return item;	
	}

}
