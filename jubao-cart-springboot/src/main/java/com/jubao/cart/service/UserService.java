package com.jubao.cart.service;

import javax.servlet.http.HttpServletRequest;

import com.jubao.pojo.User;

public interface UserService {
	
	 /***
	  * 取出登陆用户
	  * @param request
	  * @return
	  */
	public User checkLogin(HttpServletRequest request);
}
