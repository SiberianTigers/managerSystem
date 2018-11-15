package com.jubao.login.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.utis.Constants;
import com.common.utis.CookieUtils;
import com.common.utis.JubaoResult;
import com.common.utis.SecurityUtils;
import com.jubao.login.dao.JedisClient;
import com.jubao.login.mapper.UserMapper;
import com.jubao.login.pojo.Order;
import com.jubao.login.service.UserService;
import com.jubao.login.util.SendMessageTextUtils;
import com.jubao.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${SESSION_USER_TIME}") // session过期时间
	private int SESSION_USER_TIME;

	@Override
	public JubaoResult findByUserCodeOrPhoneUser(String userCodeOrPhone, String password, boolean auto,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			user = userMapper.findByUserCodeOrPhoneUser(userCodeOrPhone);

			if (user == null) {
				return JubaoResult.build(400, "没有该用户");
			}
			if (!user.getPassword().equals(SecurityUtils.md5Hex(password))) {
				return JubaoResult.build(400, "用户名或密码错误");
			}

			user.setPassword(null);// 将用户密码置空

			// 到这里表示用户密码正确 将用户存放到redis中
			jedisClient.set(Constants.SESSION_USER + ":" + user.getUserid(), JSON.toJSONString(user));
			// 设置session的过期时间
			jedisClient.expire(Constants.SESSION_USER + ":" + user.getUserid(), SESSION_USER_TIME);

			/*
			 * //将用户存放到login中的session，这样可以不用每次取redis中取出
			 * request.getSession().setAttribute(Constants.SESSION_USER,user);
			 */

			// 将用户id存放到kookie中

			// 添加写cookie的逻辑.cookie的有效期是关闭浏览器就失效
			CookieUtils.setCookie(request, response, Constants.JB_TOKEN, user.getUserid().toString());

			if (auto) {// 將用戶保存到cookie中下次登陸不必反復輸入密碼
				Cookie cookie1 = new Cookie("userCode", userCodeOrPhone);
				Cookie cookie2 = new Cookie("password", password);
				cookie1.setMaxAge((3600 * 24) * 7); // 保存一個星期
				cookie2.setMaxAge((3600 * 24) * 7); // 保存一個星期
				response.addCookie(cookie1);
				response.addCookie(cookie2);
			} else {// 不保存cookie
				Cookie cookie1 = new Cookie("userCode", "");
				Cookie cookie2 = new Cookie("password", "");
				cookie1.setMaxAge(0); // 清除
				cookie2.setMaxAge(0); // 清除
				response.addCookie(cookie1);
				response.addCookie(cookie2);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JubaoResult.ok(user.getUserid());
	}

	@Override
	public JubaoResult checkSession_User(String token) {
		// TODO Auto-generated method stub
		String result = null;
		try {
			// 取出redis中的用户信息
			result = jedisClient.get(Constants.SESSION_USER + ":" + token);

			System.out.println(result + "----------SESSION_USER------------");

			if (StringUtils.isBlank(result)) {
				return JubaoResult.build(400, "此session已过期，请重新登陆");
			}
			// 更新过期时间
			jedisClient.expire(Constants.SESSION_USER + ":" + token, SESSION_USER_TIME);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		/* User user= JSON.parseObject(result, User.class); */
		// 返回用户信息
		return JubaoResult.ok(result);
	}

	@Override
	public JubaoResult sessionOut(HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			String token = CookieUtils.getCookieValue(request, Constants.JB_TOKEN);
			long info = jedisClient.del(Constants.SESSION_USER + ":" + token);
			System.out.println("------注销---------" + token + "-----------" + info);
			return JubaoResult.ok();
		} catch (Exception e) {
			// TODO: handle exception
			JubaoResult.build(500, "注销失败");
		}
		return null;
	}

	@Override
	public boolean isPhone(String phone) {
		// TODO Auto-generated method stub

		int info = userMapper.isPhone(phone);

		System.out.println("查询判断结果" + info);
		if (info > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean addUser(String userPhone, String password) {
		// TODO Auto-generated method stub
		try {
			User user = new User();
			user.setPhone(userPhone);
			user.setPassword(SecurityUtils.md5Hex(password));// MD5加密
			SendMessageTextUtils st = new SendMessageTextUtils();
			st.getRandomNumber();
			String newName = "jb" + userPhone.substring(4) + "_" + st.getPhoenMessage();
			System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyy" + newName);
			user.setUserCode(newName);
			user.setNickName(newName);
			user.setMyImgage("timgxx.jpg");
			user.setCreateTime(new Date());

			int info = userMapper.addUser(user);

			if (info > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User checkLogin(HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			String token = CookieUtils.getCookieValue(request, Constants.JB_TOKEN);
			if (!StringUtils.isBlank(token)) {
				String userStr = jedisClient.get(Constants.SESSION_USER + ":" + token);
				if (!StringUtils.isBlank(userStr)) {
					User user = JSON.parseObject(userStr, User.class);
					return user;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Order> findUserOrderByStatus(int status, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<Order> orderList = null;
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				orderList = userMapper.findUserOrderByStatus(status, user.getUserid());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return orderList;
	}

}
