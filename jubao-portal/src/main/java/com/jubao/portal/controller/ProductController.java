package com.jubao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jubao.pojo.Product_desc;
import com.jubao.pojo.TemplateValue;
import com.jubao.pojo.util.Item;
import com.jubao.portal.service.ItemSearchService;
import com.jubao.portal.service.ItemService;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ItemSearchService itemSearchService;

	@Autowired
     private ItemService itemService;
	/****
	 * 显示商品详情
	 * 
	 * @return
	 */

	@RequestMapping("product_details.html")
	public String Product_details(@RequestParam("pid") Long pid, Model model) {

		try {
			// 查询出商品信息
			Item item = itemSearchService.searchByIdProduct(pid);

			// 查询出商品规格
			TemplateValue templateValue = itemService.findByIdTemplateValue(pid);
			// 查询出商品描述信息
			Product_desc product_desc = itemService.findByIdProduct_desc(pid);
			model.addAttribute("Item", item);
			model.addAttribute("temp", templateValue);
			model.addAttribute("desc", product_desc);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "Product";
	}

}
