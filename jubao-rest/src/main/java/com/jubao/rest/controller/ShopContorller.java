package com.jubao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utis.JubaoResult;
import com.jubao.pojo.Shop;
import com.jubao.rest.service.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopContorller {

	@Autowired
	private ShopService shopService;
	
	
	@RequestMapping(value="getShopAndItemInfo",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public JubaoResult getShopAndItemInfo(@RequestParam("sid")Integer sid,@RequestParam(value="pageSize",defaultValue="6")Integer pageSize){
		 
		try{
	        Shop shop= shopService.findByIdShop(sid, pageSize);
		    if(shop!=null){   	
		    	return JubaoResult.ok(shop);
		    }
		}catch (Exception e) {
			// TODO: handle exception
		  e.printStackTrace();
		}
	return JubaoResult.build(400,"找不到匹配数据");	
	}
}
