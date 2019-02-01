package com.jubao.service;

import com.jubao.pojo.ProductTemplate;
/***
 * 商品规格模板
 * @author 12146
 *
 */
public interface ProudctTemplateService {

	

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
