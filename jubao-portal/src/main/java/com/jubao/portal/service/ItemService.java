package com.jubao.portal.service;

import com.jubao.pojo.Product_desc;
import com.jubao.pojo.TemplateValue;

/***
 * 商品类
 * @author 12146
 *
 */
public interface ItemService {

	
	 /***
	  * 查询出商品规格
	  */
	public TemplateValue  findByIdTemplateValue(Long pid);
	
	
	
	/***
	 * 查询出商品描述
	 */
	public Product_desc findByIdProduct_desc(Long pid);
	
}
