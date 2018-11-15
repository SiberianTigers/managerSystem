package com.jubao.shop.service;

import java.util.List;

import com.jubao.pojo.Product;
import com.jubao.pojo.Product_desc;
import com.jubao.pojo.TemplateValue;

public interface ProudctService {
	
	/***
	 * 增加商品
	 * @param product
	 * @return
	 */
	public boolean addProduct(Product product,TemplateValue templateValue,Product_desc product_desc);
	
	
	
	/***
	 * 按条件查询出宝贝
	 */
	public List<Product> getByStatusProduct(Product product);
	
	/***
	 * 修改商品状态
	 */
	public boolean updateStatus(int pid,int status);
	
	
	
	/***
	 * 修改商品
	 */
	public int updateProduct(Product product);

	/***
	 *刪除商品
	 */
	public int deleteProduct(int pid);
	
}

