package com.jubao.service;

import com.jubao.pojo.Manager;

public interface loginService {
	//管理员登录验证
	public Manager managerLogin(String managerName,String managerPwd);
}
