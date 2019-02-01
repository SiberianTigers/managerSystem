package com.jubao.mapper;

import java.util.List;

import com.jubao.pojo.ProductTemplate;

/***
 * 商品规格模板
 * @author 12146
 *
 */
public interface ProudctTemplateMapper {

	/****
	 * 添加模板
	 * @return
	 */
	public int addTemplate(ProductTemplate proudctTemplate);
	
	
	/***
	 * 查询出商品规格模板
	 * 
	 */
	public ProductTemplate findTemplate(int cid);
	
	/***
	 * 根据 分类id删除
	 * 
	 */
	
	public int deleteTemplate(int cid);
	
	
	
}
