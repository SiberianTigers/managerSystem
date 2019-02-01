package com.jubao.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jubao.pojo.ItemCategory;

public interface ItemCategoryService {

	/***
	 * 获取所有分类
	 * @return
	 */
	public List<ItemCategory> getAllIntegerCategory(Integer id);
	
	
	/***
	 * 新增商品分类
	 */
	
	public int addCate(ItemCategory category);
	
	/***
	 * 删除商品分类
	 */
	
	public int  deleteCate(int id);
	

	/**
	 * 删除子分类
	 * @param id
	 * @return
	 */
	public int deleteCateChile(int id);
	/***
	 * 修改商品分类
	 */
	public int updateCate(ItemCategory category);
	  /**
	   *  查询 出   sort_order  字段 最大排序  
	   */
		
	public Integer selectSortIndex(int id);
	/***
	 * 
	 *修改图片log
	 * @return
	 */
	public int updateCatelog(@Param("cateImg")String cateImg,@Param("id")Integer id);
}
