package com.jubao.controller;



import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class IndexController {
   
	//日志记录
	  private  Logger logger =Logger.getLogger(IndexController.class);
		
	/***
	 * 跳转到系统管理界面页
	 * @return
	 */
	@RequestMapping(value="/index")
	public String toLogin(){
		
		System.out.println("====================================");
		
		return "index";
	}
	
	/***
	 * 跳转到 系统首页
	 * @return
	 */
	
	@RequestMapping("/home")
	public String showHome(){
		
		
		return "home";
	}

	/***
	 * 跳转到商品分类管理界面
	 * @return
	 */
	 @RequestMapping("/Category_Manage")
	public String toCategoryType(){
		
		
		return "Category_Manage";
	}
	/***
	 * 跳转到商品分类添加
	 */
	 @RequestMapping("product-category-add")
	 public String toCategoryTypeAdd(){
		 
		 
		 
		 return "product-category-add";
	 }
	 
	
}
