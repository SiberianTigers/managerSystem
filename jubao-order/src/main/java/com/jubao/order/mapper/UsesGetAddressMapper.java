package com.jubao.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jubao.pojo.UserAddress;

/**
 *  用户收货地址
 * @author dell
 *
 */
public interface UsesGetAddressMapper {

	 /**
	  * 查询用户收货地址
	  * 
	  * 
	  */
	 public List<UserAddress> findByUseridAddress(Integer userid);
	 
}
