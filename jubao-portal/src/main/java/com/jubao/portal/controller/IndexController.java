package com.jubao.portal.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.common.utis.Constants;
import com.common.utis.CookieUtils;
import com.jubao.pojo.AdvertisingCategory;
import com.jubao.pojo.User;
import com.jubao.portal.service.AdvertisingService;
import com.jubao.portal.service.impl.UserServiceImpl;
import com.jubao.portal.utils.Cart;

@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private AdvertisingService AdvertisingService;

	@Autowired
	private UserServiceImpl UserService;

	/****
	 * 跳转到首页
	 * 
	 * @return
	 */

	@RequestMapping(value = { "main.html", "/" })
	public String toIndex(Model model, HttpServletRequest request) {
		try {
			// 查询出 首页 分类以及广告
			Map<String, AdvertisingCategory> advertisingCategoryMap = AdvertisingService
					.findAdvertisingCategoryAndAdvertising();

			model.addAttribute("CategoryMap", advertisingCategoryMap);

			Cart cart = getCart(request);

			request.getSession().setAttribute("cart", cart);// 将购物车存放到页面中

			// 获取到用户id
			String token = CookieUtils.getCookieValue(request, Constants.JB_TOKEN);

			System.out.println(token + "=------------------");
			if (!StringUtils.isBlank(token)) {
				// 去调用验证redis中的用户信息是否存在，判断用户是否登陆 User user =
				User user = UserService.getUserByToken(token);
				request.getSession().setAttribute(Constants.SESSION_USER, user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "main";
	}

	/***
	 * 獲取購物車
	 * 
	 * @return
	 */
	public Cart getCart(HttpServletRequest request) {
		Cart cart = null;
		// 從cookie中獲取購物車
		String cartStr = CookieUtils.getCookieValue(request, Constants.CART, true);
		System.out.println(cartStr);
		if (StringUtils.isBlank(cartStr)) {// 如果cookie中沒有購物車則新建一個
			cart = new Cart();
			System.out.println("沒有購物車=--------------------");
		} else {// 有購物車則转为java对象
			System.out.println("有購物車=---------------------");
			cart = JSONObject.parseObject(cartStr, Cart.class);
		}
		return cart;
	}

}
