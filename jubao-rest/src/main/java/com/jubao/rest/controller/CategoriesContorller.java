package com.jubao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.pojo.ItemCatResult;
import com.common.utis.JubaoResult;
import com.jubao.rest.service.CategoriesService;

/***
 * 商品类型
 * 
 * @author 12146
 *
 */
@Controller
@RequestMapping("/Categories")
public class CategoriesContorller {

	@Autowired
	private CategoriesService categoriesService;

	@RequestMapping(value = "/getCategories", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getCategories(String callback) {

		String result = categoriesService.getItemCatResult();

		// 拼接字符串
		String resultStr = callback + "(" + result + ")";

		return resultStr;

	}

	/****
	 * 删除redis中的广告主键
	 * 
	 * @return
	 */
	@RequestMapping(value = "delRedisItemCate", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult delRedisItemCate() {
		if (categoriesService.delRedisItemCate() != 0) {
			System.out.println("-----删除redis中的广告主键---------");
			return JubaoResult.ok();
		}
		return JubaoResult.build(400, "执行失败");
	}

	/****
	 * 加载树形菜单 的商品分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadCateType", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult loadCateType(String callback) {

	/*	try {
			ItemCatResult result = categoriesService.getCateResult();

			JSON  jsonstr=(JSON) JSONArray.toJSON(result.getData());

			// 拼接字符串
			String resultStr = callback + "(" + jsonstr + ")";

			return JSON.toJSON(resultStr);
		} catch (Exception e) {
			// TODO: handle exception
			return JubaoResult.build(500, e.getMessage());
		}*/
		try {
			
			ItemCatResult result = categoriesService.getCateResult();
			return JubaoResult.ok(result);
			
		} catch (Exception e) {
			// TODO: handle exception
			return JubaoResult.build(500, e.getMessage());
		}
	}

}
