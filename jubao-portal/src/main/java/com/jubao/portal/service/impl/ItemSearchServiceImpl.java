package com.jubao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utis.HttpClientUtil;
import com.common.utis.JubaoResult;
import com.jubao.pojo.util.Item;
import com.jubao.pojo.util.SearchResult;
import com.jubao.portal.service.ItemSearchService;

/***
 * 商品搜索类
 * 
 * @author 12146
 *
 */
@Service
public class ItemSearchServiceImpl implements ItemSearchService {

	@Value("${SEARCH_LOCALHAST_URL}")
	private String SEARCH_LOCALHAST_URL;
	@Value("${ITEM_SIZE}")
	private int ITEM_SIZE;

	@Override
	public SearchResult SearchItem(String queryString, String price, int page) {
		// TODO Auto-generated method stub
		try {
			Map param = new HashMap<String, String>();
			param.put("queryString", queryString);
			param.put("price", price);
			param.put("page", page + "");
			param.put("size", ITEM_SIZE + "");
			// 调用search服务层查询
			String result = HttpClientUtil.doGet(SEARCH_LOCALHAST_URL + "search", param);

			// 把json对象转换为java对象
			JubaoResult jubaoResult = JSONObject.parseObject(result, JubaoResult.class);
			System.out.println("把json转换为java对象" + jubaoResult.getData() + "------------");
			if (jubaoResult.getStatus() == 200) {
				SearchResult searchResult = JSONObject.parseObject(jubaoResult.getData().toString(),
						SearchResult.class);
				return searchResult;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Item searchByIdProduct(Long pid) {
		// TODO Auto-generated method stub
		try {
			Map param = new HashMap<String, String>();
			param.put("pid", pid + "");
			// 调用search服务层查询
			String result = HttpClientUtil.doGet(SEARCH_LOCALHAST_URL + "searchById", param);

			// 把json对象转换为java对象
			JubaoResult jubaoResult = JSONObject.parseObject(result, JubaoResult.class);
			System.out.println("把json转换为java对象" + jubaoResult.getData() + "------------");
			if (jubaoResult.getStatus() == 200) {
				if (jubaoResult.getData().toString() != null && !"".equals(jubaoResult.getData().toString())) {
					Item item = JSONObject.parseObject(jubaoResult.getData().toString(), Item.class);

					return item;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
