package com.jubao.shop.service;

import javax.servlet.http.HttpServletRequest;

import com.jubao.pojo.User;

public interface UserService {
 
	/***
	 * 获取用户
	 * @param request
	 * @return
	 */
	public User checkLogin(HttpServletRequest request);
}
