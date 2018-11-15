package com.jubao.shop.mapper;

import com.jubao.pojo.TemplateValue;

public interface TemplateValueMapper {

	/***
	 * 添加商品模板值
	 * @param templateValue
	 * @return
	 */
	public  int addTemplateValue(TemplateValue templateValue);
	
	/***
	 * 修改商品規格
	 */
	
	public int updateTemplateValue(TemplateValue templateValue);
	
	
	/***
	 * 刪除商品規格
	 */
	public  int deleteTemplateValue(int pid);
}
