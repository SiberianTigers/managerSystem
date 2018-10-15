package com.jubao.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.common.utis.JubaoResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jubao.pojo.Shop;
import com.jubao.pojo.ShopCateExtension;
import com.jubao.pojo.ShopMessage;
import com.jubao.service.ShopMessagerService;
import com.jubao.service.ShopService;

/***
 *  店鋪管理模塊
 * @author 12146
 *
 */
@Controller
@RequestMapping(value="/Shop")
public class ShopController {

  
	@Autowired
	 private ShopService ShopServiceImpl;
	
	@Autowired
	private ShopMessagerService shopMessagerService;
	/***
	 * 跳转到店铺列表页面
	 * @return
	 */
	@RequestMapping(value="toShop_list.html",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	public String toShop_list(Model model,
			@RequestParam(value="cateid",defaultValue="0")Integer cateid,
		    @RequestParam(value="pageIndex",defaultValue="1")Integer pageIndex,
		    @RequestParam(value="shopName",required=false)String shopName,
		    @RequestParam(value="pageSize",defaultValue="5")Integer pageSize,
		    Shop shop){
		
		System.out.println(pageSize+"=");
		
		//查询出分类
	    List<ShopCateExtension>shopCateList=ShopServiceImpl.getShopCate();
	    
	    int cateSum=0;
	    for(ShopCateExtension s:shopCateList){
	    	cateSum+=s.getShopcount();
		  }
	    
		PageHelper.startPage(pageIndex,pageSize); //分页信息
		
		System.out.println(shop+"===========店铺分类=====");
		
		if(shop==null){	
	    shop=new Shop();
	   }
		
	   if(cateid!=0){ //如果分类id不是0就安装分类id查询值，如果分类id是0就查询全部
		   shop.setStoreCategory(cateid);
	   }
	  
	   if(shopName!=null&&!"".equals(shopName)){
		   try {
		 shopName = new String(shopName.getBytes("iso8859-1"),"utf-8");
		 shop.setStoreName(shopName);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	    //设置查询店铺状态为审核已通过
	   shop.setStoreStatus(1);
	   
	   //查询出店铺信息
	  List<Shop>shopList=ShopServiceImpl.selectShop(shop);
	  
	  
	  
	  
	  //分页对象
	  PageInfo<Shop> pageInfo=new PageInfo<Shop>(shopList); 	 
  
	   model.addAttribute("shopCateList",shopCateList);
	   model.addAttribute("pageInfo",pageInfo);
	   model.addAttribute("shop",shop);
	   model.addAttribute("cateSum",cateSum);
	   model.addAttribute("pageSize",pageSize);
		return "Shop_list";
	}

	
	/**
	 * 给店铺发送私信
	 */
	@RequestMapping(value="shopMessage",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public JubaoResult shopMessage(ShopMessage shopMessage){
		
		System.out.println(shopMessage+"===发送店铺私信");
		
		if(shopMessage==null){
			 return	JubaoResult.build(400,"表单信息不完整");
		}
		
		shopMessage.setManagerid(1); //设置信息发送者
		shopMessage.setSendDate(new Date());//发送时间
		shopMessage.setStatus(1);//信息状态为未读
			
		if(shopMessagerService.addShopMessage(shopMessage)>0){
			
			return JubaoResult.ok();
		}		
	 return	JubaoResult.build(400,"信息添加失败");
	}
	
	/***
	 * 给店铺发送私信
	 */
	@RequestMapping(value="BatchAdd",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public JubaoResult BatchAdd(ShopMessage shopMessage){
		
		System.out.println(shopMessage+"===发送店铺私信");
		System.out.println(shopMessage.getArray()+"===发送店铺私信");
		if(shopMessage==null){
			 return	JubaoResult.build(400,"表单信息不完整");
		}else if(shopMessage.getArray()==null){
			 return	JubaoResult.build(400,"表单信息不完整");
		}
		
		JSONArray jsonArray=JSONArray.parseArray(shopMessage.getArray());
		int [] arr=JSONArray.toJavaObject(jsonArray, int[].class);
		
		for(int x=0;x<arr.length;x++){
			
			System.out.println(arr[x]);
		}
		
		
		shopMessage.setManagerid(1); //设置信息发送者
		shopMessage.setSendDate(new Date());//发送时间
		shopMessage.setStatus(1);//信息状态为未读
			
		if(shopMessagerService.BatchAdd(shopMessage, arr)>0){
			
			return JubaoResult.ok();
		}		
	 return	JubaoResult.build(400,"信息添加失败");
	}
}
