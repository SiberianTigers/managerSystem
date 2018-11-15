package com.jubao.shop.mapper;

import com.jubao.pojo.ProductTemplate;
/***
 * 商品规格模板类
 * @author 12146
 *
 */
public interface TemplateModelMapper {

	
	public ProductTemplate  findByIdProductTemplate(int cid);
	
}
