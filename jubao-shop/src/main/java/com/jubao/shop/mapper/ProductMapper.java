package com.jubao.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jubao.pojo.Product;

/**
 * 商品表
 * @author 12146
 *
 */
public interface ProductMapper {

	/***
	 * 增加商品
	 * @param product
	 * @return
	 */
	public int addProduct(Product product);
	
	
	/***
	 * 按条件查询出宝贝
	 */
	public List<Product> getByStatusProduct(Product product);
	
	/***
	 * 根据商品id修改商品的状态
	 * 
	 */
	public int updateStatus(@Param("pid")int pid,@Param("status")int status);
	
	
	/***
	 * 修改商品
	 */
	public int updateProduct(Product product);
	
	
	/***
	 *刪除商品
	 */
	public int deleteProduct(int pid);
	
	
}
