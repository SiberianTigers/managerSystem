package com.jubao.shop.service;

import java.util.List;

import com.common.utis.TtemplateUtil;
import com.jubao.pojo.Product;
import com.jubao.pojo.Product_desc;
import com.jubao.pojo.TemplateValue;
import com.jubao.shop.pojo.vo.ProductCustom;

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
	public List<ProductCustom> getByStatusProduct(Product product);
	
	/***
	 * 修改商品状态
	 */
	public boolean updateStatus(Long pid,int status);
	
	
	
	/***
	 * 修改商品
	 */
	public boolean updateProduct(Product product,TemplateValue temp,Product_desc desc);

	/***
	 *刪除商品
	 */
	public int deleteProduct(Long pid);
	
	
	
    /***
     * 根基商品id查询商品
     */
	
	ProductCustom findProductByPid(Long pid);
	
	
	/***
	 * 根据 商品id查询商品规格  个参数
	 */
	
	 List<TtemplateUtil> findTemplateValue(Long pid);
	
	/****
	 * 根据商品id查询商品描述
	 */
	Product_desc findItemByIdDesc(Long pid);
	
	
}

