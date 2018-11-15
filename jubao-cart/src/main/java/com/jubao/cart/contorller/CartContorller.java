package com.jubao.cart.contorller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.common.utis.Constants;
import com.common.utis.JubaoResult;
import com.jubao.cart.service.CartService;
import com.jubao.cart.service.UserService;
import com.jubao.cart.utils.Cart;
import com.jubao.pojo.User;

@Controller
@RequestMapping("cart")
public class CartContorller {

	@Autowired
	private CartService cartService;

	@Autowired
	private UserService userService;

	/***
	 * 添加商品
	 * 
	 * @param pid
	 * @param itemInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addItem", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String addItem(@RequestParam("pid") long pid, @RequestParam("typeItem") String typeItem,
			@RequestParam("num") int num, String callback, HttpServletRequest request, HttpServletResponse response) {
		JubaoResult result = null;
		try {
			typeItem = new String(typeItem.getBytes("ISO-8859-1"), "utf-8");
			System.out.println(pid + "---------" + "-----" + num + "-----" + "------" + typeItem);
			result = cartService.addItem(pid, typeItem, num, request, response);// 添加商品
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String resultStr = JSON.toJSONString(result);
		return callback + "(" + resultStr + ")";
	}

	/***
	 * 购物车显示
	 * 
	 * @return
	 */
	@RequestMapping("cartItemShow")
	public String cartItemShow(HttpServletRequest request, Model model) {

		try {
			Cart cart = cartService.getCart(request);
			model.addAttribute("cart", cart);
			User user = userService.checkLogin(request);
			request.getSession().setAttribute(Constants.SESSION_USER, user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "BuyCar";
	}

	/***
	 * 修改购物项
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateCartItem", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult updateCartItem(@RequestParam("pid") long pid, @RequestParam("typeItem") String typeItem,
			@RequestParam("num") int num, String callback, HttpServletRequest request, HttpServletResponse response) {
		JubaoResult jb = null;
		try {
			typeItem = new String(typeItem.getBytes("ISO-8859-1"), "utf-8");
			System.out.println(pid + "---------" + "-----" + num + "-----" + "------" + typeItem);
			jb = cartService.updateCartItem(pid, num, typeItem, request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jb;
	}

	/***
	 * 删除购物车项
	 */
	@RequestMapping(value = "deleteCartItem", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult deleteCartItem(@RequestParam("pid") long pid, @RequestParam("typeItem") String typeItem,
			String callback, HttpServletRequest request, HttpServletResponse response) {
		JubaoResult jb = null;
		try {
			typeItem = new String(typeItem.getBytes("ISO-8859-1"), "utf-8");
			System.out.println(pid + "---------" + "----- -----" + "------" + typeItem);
			jb = cartService.deleteCartItem(pid, typeItem, request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jb;
	}

}
