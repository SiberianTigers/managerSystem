package com.jubao.rest.mapper;

import com.jubao.pojo.Product_desc;
import com.jubao.pojo.TemplateValue;

/***
 * 商品类
 * @author 12146
 *
 */
public interface ItemMapper {

	

	public TemplateValue findByIdTemplateValue(Long pid);
	
	
	
	
	public Product_desc findByIdProduct_desc(Long pid);

	
}
