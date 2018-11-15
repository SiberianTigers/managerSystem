package com.jubao.shop.mapper;

import com.jubao.pojo.Product_desc;

/**
 * 商品描述表
 * @author 12146
 *
 */
public interface Product_descMapper {

	/***
	 * 增加商品描述
	 * @param product
	 * @return
	 */
	public int addProduct_desc(Product_desc product_desc);
	
	
	/***
	 * 修改商品描述
	 */
	
	public  int updateDesc(Product_desc Product_desc);
	
	/***
	 * 刪除商品詳細描述
	 */
	public int deleteDesc(int pid);
}
