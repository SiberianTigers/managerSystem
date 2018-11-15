package com.jubao.portal.service;

import com.common.pojo.ItemCatResult;

/***
 * 商品类目
 * @author 12146
 *
 */
public interface CategoriesService {

	/***
	 * 获取全部分类
	 */
	public ItemCatResult getItemCatResult();


	
	/***
	 * 获取树形菜单格式的 商品分类
	 * 
	 * 
	 */
	public  ItemCatResult getTreeCate();
	
}
