package com.jubao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jubao.pojo.util.Item;
import com.jubao.pojo.util.SearchResult;
import com.jubao.search.dao.SearcheDao;
import com.jubao.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearcheDao searcheDao;

	@Override
	public SearchResult search(String queryString,String price, int page, int rows) throws Exception {
		// TODO Auto-generated method stub
		// 查询对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		StringBuilder sb=new StringBuilder();
		 sb.append("item_title:"+queryString);
		 if(queryString!=null&&price!=null&&!"".equals(price)){
		    String [] arr=price.split("-");
			 sb.append(" AND  item_price:[ "+arr[0]+"  TO  "+arr[1]+"] ");
		 }
		query.setQuery(sb.toString());
		// 设置分页 要跳转的页号减1 * 每页显示几条
		query.setStart((page - 1) * rows);
		// 设置每页显示几条数据
		query.setRows(rows);
		// 设置默认搜索域
		query.set("df", "item_keywords");

		
		
		// 设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");

		// 执行查询
		SearchResult searchResult = searcheDao.search(query);

		// 获取到数据总数量
		long dataCount = searchResult.getRecordCount();
		// 获取分页总数 总数据 /每页显示几条 =总分页
		long pageCount = dataCount / rows;
		if (dataCount % rows > 0) {
			pageCount++;
		}
		searchResult.setPageCount(pageCount);
		searchResult.setCurPage(page);

		return searchResult;

	}

	@Override
	public Item searchById(Long id) {
		// TODO Auto-generated method stub
		SolrQuery query = new SolrQuery();
		StringBuilder sb = new StringBuilder();
		sb.append("id:" + id);
		query.setQuery(sb.toString());	
		query.set("df", "item_keywords");
		Item item=null;

		try {
			 item=searcheDao.searchById(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

}
