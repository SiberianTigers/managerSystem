package com.jubao.cart.mapper;

import org.apache.ibatis.annotations.Param;

import com.jubao.pojo.util.Item;


/***
 * 购物车商品类
 * @author 12146
 *
 */
public interface CartItemMapper {

	
	public Item findItem(long pid);
	
	/***
	 * 增加商品库存
	 * @param pid
	 * @param num
	 * @return
	 */
	public int updateItemNumberAsc(@Param("pid")Long pid,@Param("num")int num);

	/***
	 * 减少商品库存
	 * @param pid
	 * @param num
	 * @return
	 */
	public int updateItemNumberdesc(@Param("pid")Long pid,@Param("num")int num);
}
