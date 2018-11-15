package com.jubao.order.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jubao.order.service.OrderService;
import com.jubao.pojo.User;

/***
 * 拦截器
 * 
 * @author 12146
 *
 */
public class JubaoInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private OrderService orderService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("-----订单拦截器----------");
		// TODO Auto-generated method stu
		
		  try { 

		 // 去调用验证redis中的用户信息是否存在，判断用户是否登陆 User user =
			User user=orderService.checkLogin(request);
		   if (user == null) {
		      response.sendRedirect("http://localhost:8086/login/userlogin/"+
						"tologin?callBackUrl=" + request.getRequestURI());
		     return false; 
		    }
		 
		 }
		  catch (Exception e) { // TODO: handle exception e.printStackTrace();
		 }
	
		// 放行
		return true;
	}

}
