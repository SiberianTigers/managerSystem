package com.jubao.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jubao.login.pojo.Order;
import com.jubao.pojo.User;

public interface UserMapper {

	/***
	 * 根据用户账号，或手机号查询用户
	 * @return
	 */
	public User findByUserCodeOrPhoneUser(String userCodeOrPhone);
	
	/***
	 * 根据用户手机号判断用户是否存在
	 */
	public int isPhone(String phone);
	
	
	/****
	 * 添加用户
	 */
	public int addUser(User user);
	
	
	/***
	 * 获取用户订单
	 * 
	 * 1  已付款
	 * 
	 * 0 未付款
	 */
	List<Order> findUserOrderByStatus(@Param("status")int status,@Param("userid")int userid);
	
}
