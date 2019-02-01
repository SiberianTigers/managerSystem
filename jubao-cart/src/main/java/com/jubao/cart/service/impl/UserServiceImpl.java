package com.jubao.cart.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.utis.Constants;
import com.common.utis.CookieUtils;
import com.jubao.cart.dao.JedisClient;
import com.jubao.cart.service.UserService;
import com.jubao.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JedisClient jedisClient;

	@Override
	public User checkLogin(HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			String token = CookieUtils.getCookieValue(request, Constants.JB_TOKEN);
			if (!StringUtils.isBlank(token)) {
				String userStr = jedisClient.get(Constants.SESSION_USER + ":" + token);
				if (!StringUtils.isBlank(userStr)) {
					User user = JSON.parseObject(userStr, User.class);
				    request.getSession().setAttribute("index",1);
					return user;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
