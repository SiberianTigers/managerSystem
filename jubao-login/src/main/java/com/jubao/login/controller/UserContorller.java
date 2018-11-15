package com.jubao.login.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utis.JubaoResult;
import com.jubao.login.pojo.Order;
import com.jubao.login.service.UserGetAddress;
import com.jubao.login.service.UserService;
import com.jubao.pojo.User;
import com.jubao.pojo.UserAddress;

@RequestMapping("user")
@Controller
public class UserContorller {

	@Autowired
	private UserService UserService;

	@Autowired
	private UserGetAddress UserGetAddress;

	/***
	 * 用户管理中心
	 * 
	 * @return
	 */
	@RequestMapping("userManager")
	public String userManager(HttpServletRequest request, Model model) {

		User user = UserService.checkLogin(request);

		request.getSession().setAttribute("user", user);

		return "user/userManager";
	}

	/***
	 * 跳转到用户收获地址页面
	 * 
	 * @return
	 */
	@RequestMapping("getAddress.html")
	public String getAddress(HttpServletRequest request, Model model) {

		return "user/MemberUserAddress";
	}

	/***
	 * 异步加载出用户的收获地址
	 * 
	 * @return
	 */
	@RequestMapping("ajaxUserAddress")
	public String ajaxUserAddress(HttpServletRequest request, Model model) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				List<UserAddress> userAddressList = UserGetAddress.findByUseridAddress(user.getUserid());
				model.addAttribute("userAddressList", userAddressList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "userMenu/userAddress";
	}

	/***
	 * 添加用户收获地址 ,produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8"
	 */
	@RequestMapping(value = "addUserGetAddress")
	@ResponseBody
	public JubaoResult addUserGetAddress(UserAddress UserAddress) {
		System.out.println(UserAddress);
		/*
		 * String params = java.net.URLDecoder.decode(UserAddress , "UTF-8");
		 * 
		 */ int result = UserGetAddress.userGetAddRess(UserAddress);
		if (result > 0) {
			return JubaoResult.ok();
		}
		return JubaoResult.ok();
	}
	
	/***
	 * 設置用戶的默認地址
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("setDefault")
	@ResponseBody
	public JubaoResult setDefault(@RequestParam("id")String id,HttpServletRequest request) {

		return  UserGetAddress.setDefaultAddress(id, request);
	}
	
	
	/***
	 * 查询用户订单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("findUserOrder.html")
	public String  findUserOrder(@RequestParam("status")int status,HttpServletRequest request,Model model) {
		System.out.println(status+"--------------------");
		//查询用户订单
         List<Order>OrderList=UserService.findUserOrderByStatus(status, request);
	
		    model.addAttribute("orderList",OrderList);
		   
		    return "user/userOrder";
	}
	
	
}
