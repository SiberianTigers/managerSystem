package com.jubao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jubao.pojo.Shop;
import com.jubao.pojo.ShopCateExtension;

/**
 * 店铺管理持久层
 * 
 * @author 12146
 *
 */
public interface ShopMapper {

	/**
	 * 查询出已有店铺分类及该分类下的店铺数量
	 * 
	 * @return
	 */
	public List<ShopCateExtension> getShopCate();

	/***
	 * 根据店铺名称或 分类查询出店铺
	 * 
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
	public int approvalShop(@Param("id")int id,@Param("storeStatus")int storeStatus);
}
