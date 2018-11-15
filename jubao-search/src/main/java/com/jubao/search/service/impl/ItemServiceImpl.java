package com.jubao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.utis.JubaoResult;
import com.jubao.pojo.util.Item;
import com.jubao.search.mapper.ItemMapper;
import com.jubao.search.service.ItemService;
/***
 * 将数据导入solr集群
 * @author 12146
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private SolrServer solrServer;

	@Override
	public JubaoResult getProductList() {
		// TODO Auto-generated method stub
		 try { 
		List<Item>ItemList= itemMapper.getProductList();
		  
		System.out.println( ItemList.get(0).getSellPoint()+"-------商品买点");
		 for(Item item:ItemList){			 
			 //创建一个solr输入数据对象
			 SolrInputDocument solrinput=new SolrInputDocument();
			 solrinput.setField("id",item.getPid());
			 solrinput.setField("item_title",item.getTitle());
			 solrinput.setField("item_sell_point",item.getSellPoint());
			 solrinput.setField("item_price",item.getPrice());
			 solrinput.setField("item_title",item.getTitle());
			 solrinput.setField("item_image",item.getImage());
			 solrinput.setField("item_category_name",item.getCategoryName());
			 solrinput.setField("store_id",item.getSid());
			 solrinput.setField("store_name",item.getStoreName());
		  //写入索引库		
			 solrServer.add(solrinput);				
		 }	
		 //提交修改
		 solrServer.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return JubaoResult.build(500, org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(e)); 
			}

		 return JubaoResult.ok();
	}

}
