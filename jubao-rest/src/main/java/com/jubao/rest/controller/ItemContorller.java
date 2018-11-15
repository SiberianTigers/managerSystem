package com.jubao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utis.JubaoResult;
import com.jubao.pojo.Product_desc;
import com.jubao.pojo.TemplateValue;
import com.jubao.rest.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemContorller {

	@Autowired
	private ItemService itemService;

	/***
	 * 获取商品规格
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "getTemplateValue", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult getTemplateValue(@RequestParam("pid") Long pid) {

		try {
			TemplateValue templateValue = itemService.findByIdTemplateValue(pid);
			if (templateValue != null) {
				return JubaoResult.ok(templateValue);
			}
			return JubaoResult.build(400, "未找到匹配的数据");
		} catch (Exception e) {
			// TODO: handle exception
			return JubaoResult.build(500, e.getMessage());

		}

	}

	/***
	 * 获取商品描述
	 */
	@RequestMapping(value = "getProduct_desc", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult getProduct_desc(@RequestParam("pid") Long pid) {
		try {
			Product_desc product_desc = itemService.findByIdProduct_desc(pid);
			if (product_desc != null) {
                
				System.out.println("----商品描述---"+product_desc.getItemdesc());
				
				return JubaoResult.ok(product_desc);
			}
			return JubaoResult.build(400, "未找到匹配的数据");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JubaoResult.build(500, e.getMessage());
		}
	}

}
