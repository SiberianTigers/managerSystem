package com.jubao.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import com.jubao.pojo.util.Item;
import com.jubao.pojo.util.SearchResult;

public class solrTest {

	public void search() throws Exception {
		// TODO Auto-generated method stub
		// 返回值对象
		SearchResult result = new SearchResult();
		CloudSolrServer solrServer = new CloudSolrServer("47.107.36.50:2181,47.107.36.50:2182,47.107.36.50:2183");
		solrServer.setDefaultCollection("collection2");

		SolrQuery query = new SolrQuery();
		StringBuilder sb = new StringBuilder();

		sb.append("id:" + 1209986);

		query.setQuery(sb.toString());
		query.set("df", "item_keywords");

		// 设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		// 根据c查询条件查询索引库
		QueryResponse queryResponse = solrServer.query(query);
		// 去查询结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		// 取查询结果总数量
		result.setRecordCount(solrDocumentList.getNumFound());

		// 商品列表
		List<Item> itemList = new ArrayList<Item>();

		// 取高亮显示
		Map<String, Map<String, List<String>>> gaoliang = queryResponse.getHighlighting();

		// 取商品列表
		for (SolrDocument solrDocument : solrDocumentList) {

			System.out.println(solrDocument.get("id"));
			// 创建商品对象
			Item item = new Item();
			item.setPid((String) solrDocument.get("id"));
			// 取高亮显示的结果
			List<String> list = gaoliang.get(solrDocument.get("id")).get("item_title");
			String title = "";
			if (list != null && list.size() > 0) {
				title = list.get(0);
			} else {
				title = (String) solrDocument.get("item_title");
			}
			item.setTitle(title);
			item.setImage((String) solrDocument.get("item_image"));
			item.setPrice((long) solrDocument.get("item_price"));
	/*		item.setSell_point((String) solrDocument.get("item_sell_point"));
			item.setCategory_name((String) solrDocument.get("item_category_name"));*/

			// 添加的商品列表
			itemList.add(item);
		}
		solrServer.commit();
		for (Item item : itemList) {

			System.out.println(item.getTitle() + "---------" + item.getPrice());
		}

	}

	@Test
	public void search1() throws Exception {
		// TODO Auto-generated method stub

		CloudSolrServer solrServer = new CloudSolrServer("47.107.36.50:2181,47.107.36.50:2182,47.107.36.50:2183");
		solrServer.setDefaultCollection("collection2");

		SolrQuery query = new SolrQuery();
		StringBuilder sb = new StringBuilder();
		sb.append("id:" + 1209986);
		query.setQuery(sb.toString());
		query.set("df", "item_keywords");

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
			/*item.setSell_point((String) solrDocument.get("item_sell_point"));
			item.setCategory_name((String) solrDocument.get("item_category_name"));*/

			System.out.println(item.getTitle() + "---------" + item.getPrice());
		}
		solrServer.commit();

		return item;
	}
}
