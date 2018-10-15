package com.jubao.service;

import java.util.List;

import com.jubao.pojo.Shop;
import com.jubao.pojo.ShopCateExtension;
import com.jubao.pojo.ShopRefused;

public interface ShopService {
	 /**
	  * 查询出已有店铺分类及该分类下的店铺数量
	  * @return
	  */
	public List<ShopCateExtension>  getShopCate();
	
	
	 
	/***
	 * 根据店铺名称或  分类查询出店铺
	 * @param shop
	 * @return
	 */
	public List<Shop> selectShop(Shop shop);
	

	/***
	 * 查詢出待审核店铺数量
	 */
	public int auditShop();
	
	
	/***
	 * 批准店铺
	 */
	public boolean addapprovalShop(int id,int storeStatus);
	
	
	
	/***
	 * 拒绝店铺
	 */
	public boolean addRefusedShop(int id,int storeStatus,ShopRefused shopRefused);
	
}
