package com.jubao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jubao.mapper.loginMapper;
import com.jubao.pojo.Manager;
import com.jubao.service.loginService;

@Service
public class loginServiceImpl implements loginService {

	@Autowired
	private loginMapper loginmapper;
	
	@Override
	public Manager managerLogin(String Name, String managerPwd) {
		System.out.println("进入service");
		Manager mn=loginmapper.managerLogin(Name);
		if(mn!=null && mn.getManagerPwd().equals(managerPwd)){
			return mn;
		}
		return null;
	}

}
