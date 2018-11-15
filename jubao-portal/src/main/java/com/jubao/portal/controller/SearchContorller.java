package com.jubao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jubao.pojo.util.SearchResult;
import com.jubao.portal.service.ItemSearchService;

@Controller
@RequestMapping(value = "search")
public class SearchContorller {

	@Autowired
	private ItemSearchService itemSearchService;

	/***
	 * 查询商品
	 * 
	 * @param queryString
	 * @param page
	 * @return
	 */
	@RequestMapping(value="search_product.html",produces="text/html;charset=utf-8")
	public String productSearch(@RequestParam("queryString") String queryString,
								@RequestParam(value="price",required=false) String  price,                  
			@RequestParam(value = "pageIndex", defaultValue = "1") Integer page, Model model) {
		try {
			
			queryString=new String(queryString.getBytes("iso8859-1"),"utf-8");
			SearchResult result = itemSearchService.SearchItem(queryString,price, page);
			model.addAttribute("SearchResult",result);
			model.addAttribute("queryString",queryString);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "CategoryList";
	}

	
	
		
	
}
