package com.jubao.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utis.JubaoResult;
import com.jubao.pojo.AdvertisingCategory;
import com.jubao.rest.service.AdvertisingAndCateService;
/***
 * 广告
 * @author 12146
 *
 */
@Controller
@RequestMapping(value = "/advertising")
public class AdvertisingController {

	@Autowired
	private AdvertisingAndCateService AdvertisingService;

	
	 /***
	  * 查询出广告及分类
	  * @return
	  */
	@RequestMapping(value = "AdvertisingCategoryAndAdvertising", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public JubaoResult getAdvertisingCategoryAndAdvertising() {
		try {
			List<AdvertisingCategory> AdvertisingCategoryList = AdvertisingService.findAdvertisingCategoryAndAdvertising();
			return JubaoResult.ok(AdvertisingCategoryList);
		} catch (Exception e) {
			// TODO: handle exception
			return JubaoResult.build(400, e.getMessage());
		}

	}
	
	
	/****
	 *  删除redis中的广告主键
	 * @return
	 */
	@RequestMapping(value="delRedisAdvertising",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public JubaoResult delRedisAdvertising() {
			  if(AdvertisingService.delAdvertisingRedis()!=0){
				  System.out.println("-----删除redis中的广告主键---------");
					return JubaoResult.ok();		  
			  }
			 return JubaoResult.build(400,"执行失败");	
	}
	
}
