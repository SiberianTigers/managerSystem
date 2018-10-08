package com.jubao.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.common.utis.JubaoResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jubao.pojo.AdvercateExtension;
import com.jubao.pojo.Advertising;
import com.jubao.pojo.AdvertisingCategory;
import com.jubao.service.AdvertisingCategoryService;
import com.jubao.service.AdvertisingService;
import com.jubao.service.PictureService;

@Controller
@RequestMapping(value = "/Advertising")
public class AdvertisingController extends BaseController {

	@Autowired
	private AdvertisingCategoryService advertisingCategoryService;

	@Autowired
	private AdvertisingService advertisingService;
	@Autowired
	private PictureService pictureService;

	private static final String  locahast="http://119.29.195.240";
	
	/***
	 * 跳转到添加图片管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/advertising")
	public String toImgManager(Model model,@RequestParam(value="cateid",defaultValue="0")Integer cateid,@RequestParam(value="pageIndex",defaultValue="1")Integer pageIndex) {		
		//查询出广告分类信息  以及广告数量
		List<AdvercateExtension>adverCateList=advertisingService.getCateGroupCount();
		int count=0;
		for(AdvercateExtension cate:adverCateList){//统计有效总数
	       count+=cate.getEffectiveNumber();
		}
		PageHelper.startPage(pageIndex,5);// startIndex 从第几条数据取值
		// pageSize :每页显示几条数据
		
		Advertising adver=new Advertising();
		if(cateid !=0){
		 	adver.setCategoryId(cateid);	
		}
		
		
		//查询出广告
	    List<Advertising> advertisingList=advertisingService.getCateidAdvertising(adver);
		
	    PageInfo<Advertising> pageInfo=new PageInfo<Advertising>(advertisingList);
	    
	    
	    
	    
	    System.out.println(locahast+"总分页数==size"+pageInfo.getSize()+"==pageSize"+pageInfo.getPageSize());
		
		model.addAttribute("adverCateList",adverCateList);		
		model.addAttribute("cateSum",count);
		model.addAttribute("advertisingList",advertisingList);
		model.addAttribute("locahast",locahast);//图片存放主机ip
		model.addAttribute("cateid",cateid);
		model.addAttribute("pageIndex",pageIndex);
		model.addAttribute("pageInfo",pageInfo);
		
		return "advertising";
	}
  /***
   * 加载广告分类
   * @return
   */
	@RequestMapping(value = "loadCate", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult loadCate() {
		List<AdvertisingCategory> cateList = advertisingCategoryService
				.getAllAdvertisingCategory(new AdvertisingCategory(null,1));
		if (cateList != null) {
			return JubaoResult.ok(cateList);
		} else {
			return JubaoResult.build(400, "加载分类失败");
		}
	}

	/***
	 * 添加广告
	 * 
	 * @return
	 */
	@RequestMapping(value = "addAdvertising", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public JubaoResult addAdvertising(Advertising advertising,
			@RequestParam(value = "imgInfo", required = false) MultipartFile fileInfo, HttpServletRequest request) {

		System.out.println("添加广告=============="+advertising);
		// 上傳圖片
		Map<String, String> resultMap = pictureService.PictureLoad(fileInfo);
    
		  if(resultMap.get("Info")=="1"){
			    advertising.setAdvertisinUrl(resultMap.get("url"));// 图片存放路径	  
		  }else{ 
				return JubaoResult.build(400,resultMap.get("message"));
		  }
		  
		if (advertising == null) {
			return JubaoResult.build(400, "表单数据为空");
		}
		
	   //获取到广告过期时间
		advertising.setAdvertisinStart(pictureService.getEndDateTime(advertising.getAdvertisinTime(),advertising.getAdvertisinStartTime().getTime()));
		advertising.setAdvertisinCreate(new Date());// 创建时间
		advertising.setAdvertisinUserType(1);// 设置用户类型
		advertising.setAdvertisinUserId(1);

		if(advertisingService.addAdvertising(advertising)>0){
			return JubaoResult.ok();
		}
		return JubaoResult.build(400, "添加失敗");
	}
	
	  /***
	   * 修改广告状态
	   * @return
	   */
		@RequestMapping(value = "/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8",method=RequestMethod.POST)
		@ResponseBody
		public JubaoResult updateStatus(@RequestParam(value="/vid")Integer vid,@RequestParam(value="status",defaultValue="1")Integer status) {

			    if(advertisingService.updateAdvertising(new Advertising(vid,status))>0){
					return JubaoResult.ok();
			    }
				return JubaoResult.build(400,"修改失败");
		}
}
