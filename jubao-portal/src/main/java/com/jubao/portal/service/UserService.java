package com.jubao.portal.service;

import com.jubao.pojo.User;

/***
 * 用户类
 * @author 12146
 *
 */
public interface UserService {

	
	
	public   User getUserByToken(String token);
}
