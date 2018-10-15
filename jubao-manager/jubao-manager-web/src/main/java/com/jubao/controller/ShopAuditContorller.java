package com.jubao.controller;
import java.util.Date;
import java.util.List;

/***
 * 店铺审核
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utis.JubaoResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jubao.pojo.Shop;
import com.jubao.pojo.ShopMessage;
import com.jubao.pojo.ShopRefused;
import com.jubao.service.ShopMessagerService;
import com.jubao.service.ShopService;

@Controller
@RequestMapping(value="/ShopsAudit")
public class ShopAuditContorller {

	@Autowired
	 private ShopService ShopServiceImpl;
	

	/***
	 * 跳转到店铺审核页面
	 * @return
	 */
	@RequestMapping(value="toShop_audit.html")
	public String toShop_audit(Model Model,@RequestParam(value="pageIndex",defaultValue="1")Integer pageIndex,@RequestParam(value="pageSize",defaultValue="5")Integer pageSize){
		
		PageHelper.startPage(pageIndex, pageSize);//分页信息
		
		
		Shop shop=new Shop();
		 //设置查询店铺状态为审核已通过
		   shop.setStoreStatus(2);
		   
		   //查询出店铺信息
		  List<Shop>shopList=ShopServiceImpl.selectShop(shop);
	
		  PageInfo<Shop> pageInfo=new PageInfo<Shop>(shopList);
		  
		  Model.addAttribute("shopList",shopList);
		  
		  Model.addAttribute("pageInfo",pageInfo);
		
		return "Shops_Audit";
	}
	
	/***
	 * 跳转到店铺审核页面   shopping_detailed.html
	 * @return
	 */
	@RequestMapping(value="loadShopInfo",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public Integer loadShopInfo(Model Model){
		

		return ShopServiceImpl.auditShop();//查询出待审核店铺数量
	}
	/***
	 * 跳转到店铺信息详细页面
	 * @return
	 */
	@RequestMapping(value="shopping_detailed.html")
	public String shopping_detailed(Model Model,Shop shop){
	 
		System.out.println(shop+"======");
	 
		  Model.addAttribute("shop",shop);
		
		return "shopping_detailed";
	}
	
	
	
	/***
	 * 批准店铺开店申请
	 * @return
	 */
	@RequestMapping(value="approval",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public JubaoResult approval(@RequestParam(value="shopid")Integer shopid){
		//将店铺状态修改为1  表示店铺 审核通过
		 if(ShopServiceImpl.addapprovalShop(shopid,1)){		 
			 return JubaoResult.ok();
		 }
		return JubaoResult.build(400,"执行错误");
	}
	
	

	/***
	 * 拒绝开店申请
	 * @return
	 */
	@RequestMapping(value="Refused",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public JubaoResult Refused(@RequestParam(value="shopid")Integer shopid,@RequestParam(value="contentInfo",required=false) String contentInfo, ShopRefused shopRefused){	
		System.out.println(shopid+"------"+shopRefused+"---------------"+contentInfo);
		
		if(shopRefused==null){
			return JubaoResult.build(400,"执行错误");
		}
		if(shopid == null){
			return JubaoResult.build(400,"执行错误");
		}
		
		shopRefused.setShopid(shopid);//设置店铺id
		shopRefused.setContent(contentInfo);//设置拒绝信息
		shopRefused.setCreateDate(new Date());//审批时间
		
		
		//将店铺状态修改为3 表示 拒绝 店铺
		 if(ShopServiceImpl.addRefusedShop(shopid, 3, shopRefused)){	
			 return JubaoResult.ok();
		 }
		return JubaoResult.build(400,"执行错误");
	}
}
