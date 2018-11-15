package com.jubao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.common.pojo.ItemCatResult;
import com.common.utis.JubaoResult;
import com.jubao.portal.service.CategoriesService;
import com.jubao.portal.service.ShopService;


/***
 * 店铺
 * 
 * @author 12146
 *
 */
@Controller
@RequestMapping(value = "/shop")
public class ShopContorller {

	@Autowired
	private CategoriesService CategoriesService;

	@Autowired
	private ShopService ShopService;

	@RequestMapping(value = "/toShop.html")
	public String toShop() {

		return "shop";
	}

	/***
	 * 加载需要的页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ajaxView.html", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	public String ajaxView() {

		return "shop_public/productAdd";
	}

	/***
	 * 獲取 樹形菜單
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadCate.json", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String loadCate() {

		ItemCatResult item = CategoriesService.getTreeCate();

		return JSON.toJSONString(item.getData());
	}

	/***
	 * 異步加載店鋪信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadShop.json", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public JubaoResult loadShop(@RequestParam("sid") Integer sid) {

		JubaoResult result = ShopService.getShopAndItem(sid, 6);

		return result;
	}

}
