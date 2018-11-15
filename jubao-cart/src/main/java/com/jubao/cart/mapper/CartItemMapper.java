package com.jubao.cart.mapper;

import com.jubao.pojo.util.Item;
/***
 * 购物车商品类
 * @author 12146
 *
 */
public interface CartItemMapper {

	
	public Item findItem(long pid);
}
