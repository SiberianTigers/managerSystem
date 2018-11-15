package com.jubao.login.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.utis.JubaoResult;
import com.jubao.login.pojo.Order;
import com.jubao.pojo.User;

public interface UserService {

	
	/***
	 * 根据用户账号，或手机号查询用户
	 * @return
	 */
	public JubaoResult findByUserCodeOrPhoneUser(String userCodeOrPhone,String password,boolean auto,HttpServletRequest request,HttpServletResponse response);
	
	
	
	/***
	 * 验证用户是否登陆过
	 */
	
	public JubaoResult checkSession_User(String token);
	
	/***
	 * 注销用户
	 */
	public JubaoResult sessionOut(HttpServletRequest request);
	
	/***
	 *根据手机号来查询，用户是否存在
	 */
	public boolean isPhone(String phone);
	
	/***
	 * 取出user用户
	 */
	public User checkLogin(HttpServletRequest request);
	
	
	/****
	 * 添加用户
	 */
	public boolean addUser(String userPhone,String password);
	
	
	
	
	
	/***
	 * 获取用户订单
	 * 
	 * 1  已付款
	 * 
	 * 0 未付款
	 */
	List<Order> findUserOrderByStatus(int status,HttpServletRequest request);
}
