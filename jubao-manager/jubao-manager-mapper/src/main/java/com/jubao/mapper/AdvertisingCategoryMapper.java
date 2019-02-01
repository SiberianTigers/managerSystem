package com.jubao.mapper;

import java.util.List;

import com.jubao.pojo.AdvertisingCategory;

/***
 * 图片分类业务层
 * @author 12146
 *
 */
public interface AdvertisingCategoryMapper {

	/***
	 * 获取所有的分类
	 * @return
	 */
	public List<AdvertisingCategory> getAllAdvertisingCategory(AdvertisingCategory advertisingCategory);
	/***
	 * 添加分类
	 * @return
	 */
	public int addAdvertisingCategory(AdvertisingCategory advertisingCategory);
	
	
	/***
	 * 逻辑修改分类
	 */
	public int updateAdvertisingCategory(AdvertisingCategory advertisingCategory);
	
	
	/**
	 * 
	 * 删除分类
	 * 
	 */
	public int deleteAdvertisingCategory(int id);
	
	

}
