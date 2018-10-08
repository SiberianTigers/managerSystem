package com.jubao.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utis.JubaoResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jubao.pojo.AdvertisingCategory;
import com.jubao.service.AdvertisingCategoryService;

@Controller
@RequestMapping(value = "/AdvertisingCateGory")
public class AdvertisingCateGoryController {

	@Autowired
	private AdvertisingCategoryService advertisingCategoryService;


	/***
	 * 添加图片分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addAdvertisingCate",method=RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult addAdvertisingCate(AdvertisingCategory advertisingCategory,HttpServletRequest request) {

		System.out.println(advertisingCategory + "=============");
		if (advertisingCategory == null) {
			return JubaoResult.build(500, "表单为空");
		}

		advertisingCategory.setAdvertisinCreateTime(new Date());// 添加时间
		// 添加操作
		if (advertisingCategoryService.addAdvertisingCategory(advertisingCategory) > 0) {
			return JubaoResult.ok();
		}
		return JubaoResult.build(500, "添加错误");
	}

	/***
	 * 查找全部图片分类
	 * produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"
	 * @return
	 */
	@RequestMapping(value = "/Sort_ads.html")
	public String getAllAdvertisingCate(AdvertisingCategory advertisingCategory,
			@RequestParam(value = "startIndex", defaultValue = "1") Integer startIndex,
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize, Model Model) {

		System.out.println(startIndex + "=============" + pageSize + "=========" + advertisingCategory);

		PageHelper.startPage(startIndex, pageSize);// startIndex 从第几条数据取值
													// pageSize :每页显示几条数据

		List<AdvertisingCategory> advertisingCategoryList = advertisingCategoryService
				.getAllAdvertisingCategory(advertisingCategory);

		// 分页信息和 数据
		PageInfo<AdvertisingCategory> pageInfo = new PageInfo<AdvertisingCategory>(advertisingCategoryList);

		Model.addAttribute("pageInfo", pageInfo);
		Model.addAttribute("startIndex", startIndex);
		Model.addAttribute("advertisingCategory", advertisingCategory);

		return "Sort_ads";
	}
	/***
	 *修改图片分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateAdvertisingCate",method=RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult updateAdvertisingCate(AdvertisingCategory advertisingCategory) {

		System.out.println(advertisingCategory + "=============");
     
		
		if (advertisingCategory == null) {
			return JubaoResult.build(500, "表单为空");
		}
		   //根据id查询出该分类的对象
		   List<AdvertisingCategory> aList=advertisingCategoryService.getAllAdvertisingCategory( new AdvertisingCategory(advertisingCategory.getCategoryId()));
		
		   if(aList!=null &&aList.size()>0){//判断那些字段被修改过的
			   
			   AdvertisingCategory  aInfo=aList.get(0);
			   
			   //判断分类名称有没有被修改过
			   if(aInfo .getCategoryName().equals(advertisingCategory.getCategoryName())){
				   advertisingCategory.setCategoryName(null);
			   }
			   if(aInfo.getCategoryStatus()==advertisingCategory.getCategoryStatus()||aInfo.getCategoryStatus().equals(advertisingCategory.getCategoryStatus())){
				   advertisingCategory.setCategoryStatus(null);
			   }
			   if(aInfo.getCategoryPirce()==advertisingCategory.getCategoryPirce()||aInfo.getCategoryPirce().equals(advertisingCategory.getCategoryPirce())){
				   advertisingCategory.setCategoryPirce(null);
			   }
			   if(aInfo.getAdvertisinNumber()==advertisingCategory.getAdvertisinNumber()||aInfo.getAdvertisinNumber().equals(advertisingCategory.getAdvertisinNumber())){
				   advertisingCategory.setAdvertisinNumber(null);
			   }
			   if(aInfo.getAdvertisinWidet()==advertisingCategory.getAdvertisinWidet()||aInfo.getAdvertisinWidet().equals(advertisingCategory.getAdvertisinWidet())){
				   advertisingCategory.setAdvertisinWidet(null);
			   }
			   if(aInfo.getAdvertisinHight()==advertisingCategory.getAdvertisinHight()||aInfo.getAdvertisinHight().equals(advertisingCategory.getAdvertisinHight())){
				   advertisingCategory.setAdvertisinHight(null);
			   }
			   if(aInfo.getCategoryDsc().equals(advertisingCategory.getCategoryDsc())){
				   advertisingCategory.setCategoryDsc(null);
			   }
			   
		   }
		   
		// 添加操作
		if (advertisingCategoryService.updateAdvertisingCategory(advertisingCategory) > 0) {
			return JubaoResult.ok();
		}
		return JubaoResult.build(500, "修改错误");
	}
	
	
	
	/***
	 *修改图片分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateCateStatus",method=RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult updateCateStatus(@RequestParam(value="id",required=false)Integer id,@RequestParam(value="status",defaultValue="1")Integer status) {

		System.out.println(id + "============="+status);
		// 添加操作
		if (advertisingCategoryService.updateAdvertisingCategory(new AdvertisingCategory(id,status)) > 0) {
			return JubaoResult.ok();
		}
		return JubaoResult.build(500, "修改错误");
	}
	
	/***
	 *删除图片分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteCate",method=RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult deleteCate(@RequestParam(value="id",required=false)Integer id) {

		System.out.println(id + "=============");
		// 添加操作
		if (advertisingCategoryService.deleteAdvertisingCategory(id)> 0) {
			return JubaoResult.ok();
		}
		return JubaoResult.build(500, "修改错误");
	}
	
	
	
}
