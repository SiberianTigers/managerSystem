package com.jubao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.common.utis.Constants;
import com.common.utis.CookieUtils;
import com.jubao.pojo.User;
import com.jubao.portal.service.UserService;
import com.jubao.portal.service.impl.UserServiceImpl;

/***
 * 拦截器
 * 
 * @author 12146
 *
 */
public class JubaoInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserServiceImpl UserService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("----------------");
		// TODO Auto-generated method stu
		/*
		 * try { // 获取到用户id String token = CookieUtils.getCookieValue(request,
		 * Constants.JB_TOKEN);
		 * 
		 * System.out.println(token+"=------------------");
		 * 
		 * // 去调用验证redis中的用户信息是否存在，判断用户是否登陆 User user =
		 * UserService.getUserByToken(token); if (user == null) {
		 * response.sendRedirect( UserService.getLOGIN_LOCALHOST() +
		 * "tologin?callBackUrl=" + request.getRequestURI()); return false; } }
		 * catch (Exception e) { // TODO: handle exception e.printStackTrace();
		 * }
		 */
		// 放行
		return true;
	}

}
