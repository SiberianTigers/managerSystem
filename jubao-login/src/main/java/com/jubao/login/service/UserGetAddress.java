package com.jubao.login.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.common.utis.JubaoResult;
/***
 * 用户收获地址
 */
import com.jubao.pojo.UserAddress;

public interface UserGetAddress {
	/***
	 * 
	 * 添加地址
	 * 
	 */
	
	public int userGetAddRess(UserAddress address);
	
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
	 public JubaoResult setDefaultAddress(String addressid,HttpServletRequest request);

}
