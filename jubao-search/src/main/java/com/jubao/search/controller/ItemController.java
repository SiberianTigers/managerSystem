package com.jubao.search.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utis.JubaoResult;
import com.jubao.pojo.util.Item;
import com.jubao.pojo.util.SearchResult;
import com.jubao.search.service.ItemService;
import com.jubao.search.service.SearchService;

@Controller
@RequestMapping(value = "item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private SearchService searchService;

	/****
	 * 导入数据到solr索引库
	 * 
	 * @return
	 */

	@RequestMapping(value = "uploadItemThisSolr")
	@ResponseBody
	public JubaoResult uploadItemThisSolr() {

		return itemService.getProductList();
	}

	/****
	 * 从索引库查找数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult search(@RequestParam("queryString") String queryString,
			@RequestParam(value = "price", required = false) String price,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "60") Integer rows) {
		SearchResult result = null;
		try {
			queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");

			result = searchService.search(queryString, price, page, rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JubaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		return JubaoResult.ok(result);
	}

	/****
	 * 从索引库根据id查找商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "searchById", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult searchById(@RequestParam("pid") Long pid) {
		Item result = null;
		try {
			result = searchService.searchById(pid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JubaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		return JubaoResult.ok(result);
	}
}
