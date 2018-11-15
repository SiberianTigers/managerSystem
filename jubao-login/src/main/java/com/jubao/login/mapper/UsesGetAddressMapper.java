package com.jubao.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jubao.pojo.UserAddress;

/**
 *  用户收货地址
 * @author dell
 *
 */
public interface UsesGetAddressMapper {
	/***
	 * 
	 * 添加用户地址
	 * 
	 */
	
	public int userGetAddRess(UserAddress address);
	
	/**
	 * 修改用户的默认地址为0
	 */
	public int updateDefaultAddress(int userid);
	 /**
	  * 查询用户收货地址
	  * 
	  * 
	  */
	 public List<UserAddress> findByUseridAddress(Integer userid);
	 
	 /**
	  * 设置用户默认地址
	  * @return
	  */
	 public int setDefaultAddress(String addressid);
	 
	 /**
	  * 删除地址
	  * @param addressid
	  * @return
	  */
	 public int deleteAddress(String addressid);
	 
	
	
	

	


	 
}
