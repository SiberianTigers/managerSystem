package com.jubao.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.common.utis.JubaoResult;
import com.jubao.login.service.UserService;
import com.jubao.login.util.SendMessageTextUtils;
import com.jubao.pojo.User;

@Controller
@RequestMapping("/userlogin")
public class loginContorller {

	@Autowired
	private UserService userService;

	/***
	 * 跳转到登陆页面
	 * 
	 * @return
	 */

	@RequestMapping("tologin")
	public String toLogin(@RequestParam(value = "callBackUrl", required = false) String callBackUrl,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		// 回调url

		model.addAttribute("callBackUrl", callBackUrl);

		Cookie[] cookie = request.getCookies();

		for (Cookie ck : cookie) {
			System.out.println(ck.getName()+"-------COOKIE  取值-----------");
			if (ck.getName().equals("userCode")) {
				model.addAttribute(ck.getName(), ck.getValue());
			} else if (ck.getName().equals("password")) {
				model.addAttribute(ck.getName(), ck.getValue());
				model.addAttribute("auto","checked='checked'");
			}
		}

		return "login";
	}

	/***
	 * 跳转到注册页面
	 */
	@RequestMapping("toRegister")
	public String toRegister() {

		return "registerpage";

	}

	/****
	 * 
	 * 验证用户登陆信息
	 */

	@RequestMapping(value = "checkUserInfo", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult checkUserInfo(@RequestParam("userCodeOrPhone") String userCodeOrPhone,
			@RequestParam("password") String password, @RequestParam(value = "auto", required = false) boolean auto,
			HttpServletRequest request, HttpServletResponse response) {

		System.out.println("-------Cookie保存---------" + auto);
		JubaoResult result = null;
		try {
			result = userService.findByUserCodeOrPhoneUser(userCodeOrPhone, password, auto, request, response);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JubaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}

	/****
	 * 拦截器使用 使用kookie获取用户信息
	 */

	@RequestMapping(value = "checkSession_User", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult checkSession_User(@RequestParam("token") String token) {

		System.out.println("cookies---------------" + token);
		JubaoResult result = null;
		try {
			result = userService.checkSession_User(token);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JubaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}

	}

	/****
	 * jsonp 请求用户信息
	 * 
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping(value = "/getUser/{token}")
	@ResponseBody
	public String getCategories(@PathVariable("token") String token, String callback) {
		String resultStr = null;
		try {
			JubaoResult result = userService.checkSession_User(token);

			if (result.getStatus() == 200) {
				User user = JSONObject.parseObject(result.getData().toString(), User.class);
				result.setData(user);
			}
			String jsonStr = JSONObject.toJSONString(result);
			// 拼接字符串
			resultStr = callback + "(" + jsonStr + ")";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultStr;

	}

	/***
	 * 跳转到注销用户
	 */
	@RequestMapping("sessionOut")
	public String sessionOut(HttpServletRequest request, HttpServletResponse response) {

		JubaoResult result = userService.sessionOut(request);

		return "redirect:/login/userlogin/tologin";
	}

	/**
	 * 验证手机号码是否已经注册: 如果已经注册则提示，该手机号码已被注册: 如果没有，就发送验证码到该手机号码上面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClientException
	 */
	@RequestMapping(value = "isPhone", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult isPhone(@RequestParam("phone") String phone, HttpServletRequest request) {
		try {
			if (userService.isPhone(phone)) {
				// 发送短信
				SendMessageTextUtils message = new SendMessageTextUtils();
				message.getRandomNumber();// 获取四位数验证码
				SendSmsResponse sendSmsResponse = message.sendSms(phone);// 调用发送短信
				if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
					// 请求成功
					System.out.println("发送成功" + message.getPhoenMessage());
					request.getSession().setAttribute(phone, message.getPhoenMessage());// 将手机号码和验证码存入session
					return JubaoResult.ok("发送成功,请注意查看手机");

				} else {
					System.out.println("发送失败！");
					return JubaoResult.build(400, sendSmsResponse.getMessage());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JubaoResult.build(400, "用户已存在");

	}

	/**
	 * 验证手机号码和 验证码是否一致
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkPhoneMessageInfo", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult checkPhoneMessageInfo(HttpServletRequest request, @RequestParam("phone") String phone,
			@RequestParam("phoneMessage") String phoneMessage) {

		Integer message = (Integer) request.getSession().getAttribute(phone);

		System.out.println(message + "=====8888" + "===phone:" + phone + "===Messages==" + phoneMessage);

		if (!StringUtils.isBlank(message.toString())) {
			if (phoneMessage.equals(message.toString())) {
				return JubaoResult.ok("验证成功");
			} else {
				return JubaoResult.build(400, "输入的验证码有误");
			}
		} else {
			return JubaoResult.build(400, "没有接受到验证码？填写正确的手机号码,点击获取验证码");
		}

	}

	/**
	 * 添加新用户 mybatis 实现
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addNewUser", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult addNewUser(HttpServletRequest request, @RequestParam("userPhone") String userPhone,
			@RequestParam("password") String password) {
		System.out.println("rrrrrrrrrrrrr" + userPhone + "bbbbbbbbbbbbbbbbbbbbbbb" + password);

		if (userService.addUser(userPhone, password)) {
			return JubaoResult.ok("注册成功");
		} else {
			return JubaoResult.build(400, "注册失败");
		}
	}
}
