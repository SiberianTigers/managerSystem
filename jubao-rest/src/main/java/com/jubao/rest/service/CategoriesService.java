package com.jubao.rest.service;

import java.util.List;

import com.common.pojo.ItemCatResult;



/***
 * 商品类目
 * @author 12146
 *
 */
public interface CategoriesService {

	/***
	 * 递归获取分类
	 * @return
	 */
	public List<?> getAllIntegerCategory(Integer id);
	
	
	/***
	 * 获取全部分类
	 */
	public String getItemCatResult();
	
	/***
	 *清除redis中的商品分类 缓存
	 * @return
	 */
	public long delRedisItemCate();
	
	
	
	/**
	 * 递归获取出 ---- 添加商品界面的   商品分类
	 */
	public List<?> getProductCate(Integer id); 
	
	
	
	/****
	 *  商品添加  ---   获取分类
	 * 
	 */
	public ItemCatResult  getCateResult();
	
	
}
