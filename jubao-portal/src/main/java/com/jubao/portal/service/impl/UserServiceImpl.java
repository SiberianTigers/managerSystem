package com.jubao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.utis.HttpClientUtil;
import com.common.utis.JubaoResult;
import com.jubao.pojo.User;
import com.jubao.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Value("${LOGIN_LOCALHOST}")
	private String LOGIN_LOCALHOST;

	public String getLOGIN_LOCALHOST() {
		return LOGIN_LOCALHOST;
	}

	public void setLOGIN_LOCALHOST(String lOGIN_LOCALHOST) {
		LOGIN_LOCALHOST = lOGIN_LOCALHOST;
	}

	@Override
	public User getUserByToken(String token) {
		// TODO Auto-generated method stub
		try {
			Map param = new HashMap<String, String>();
			param.put("token", token);
			String result = HttpClientUtil.doPost(LOGIN_LOCALHOST + "checkSession_User", param);
			 System.out.println("--------LOGIN_LOCALHOST------------------"+result);
			if (!StringUtils.isBlank(result)) {
				
				JubaoResult jubaoResult = JSONObject.parseObject(result, JubaoResult.class);
				if (jubaoResult.getStatus() == 200) {
					User user = JSON.parseObject(jubaoResult.getData().toString(),User.class);
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
