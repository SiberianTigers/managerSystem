package com.jubao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jubao.pojo.Manager;
import com.jubao.service.loginService;

@Controller
public class loginController {
	
	@Autowired
	private loginService lservice;
	
	/**
	 * 登陆验证
	 * @param name 网页中输入的用户名
	 * @param pwd 输入的密码
	 * @return
	 */
	@RequestMapping("/loginvalidate")
	public String mapperLogin(String name,String pwd){
		System.out.println("============================测试");
		Manager manager=lservice.managerLogin(name, pwd);
		if(manager!=null){
			System.out.println("登录用户存在");
			return "index";
		}
		
		return "login";
	}
}
