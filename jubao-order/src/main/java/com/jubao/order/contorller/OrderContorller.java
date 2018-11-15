package com.jubao.order.contorller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.common.utis.JubaoResult;
import com.jubao.order.service.OrderService;
import com.jubao.order.utils.AppOrderInfo;
import com.jubao.order.utils.Cart;
import com.jubao.pojo.UserAddress;

@RequestMapping("order")
@Controller
public class OrderContorller {

	@Autowired
	private OrderService orderService;

	/***
	 * 显示购物车下一步 确认订单
	 * 
	 * @return
	 */
	@RequestMapping("confirm_order.html")
	public String confirmItemOrder(HttpServletRequest request, Model model) {
		try {
			// 获取用户的收获地址
			List<UserAddress> userAddressList = orderService.getUserAddress(request);
			if (userAddressList != null) {
				model.addAttribute("address", userAddressList);
			}
			// 获取用户购物车
			Cart cart = orderService.getCart(request);
			model.addAttribute("cart", cart);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "BuyCarInfo2";
	}

	/**
	 * 创建订单s
	 */
	@RequestMapping(value = "create_Order", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	public String create_Order(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("address") Integer address_check) throws ServletException, IOException {
		JubaoResult jb = null;
		try {
			jb = orderService.addOrder(request, address_check);
			if (jb.getStatus() == 200) {
				AppOrderInfo info = (AppOrderInfo) jb.getData();
				info.setGoodsName(java.net.URLEncoder.encode(info.getGoodsName(), "UTF-8"));
				return "redirect:/order/order/selectZf.html?playerId=" + info.getOrderId() + "&&appId="
						+ info.getAppId() + "&&goodsPrice=" + getPrice(info.getGoodsPrice())
						+ "&&wx=1&&alipay=1&&succHide=1&&goodsName=" + info.getGoodsName() + "&&goodsId=1";// 跳转到选择支付页面
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "redirect:/order/order/confirm_order.html";
	}

	/****
	 * 计算商品价格
	 * 
	 * @param price
	 * @return
	 */
	public int getPrice(double price) {

		return (int) price * 100;
	}

	/***
	 * 跳转到选择支付页面
	 * 
	 * @return
	 */

	@RequestMapping(value = "selectZf.html", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	public String selectZf() {

		return "/pay-page/ddpay";// 跳转到选择支付页面
	}

	/***
	 * 支付成功回调页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("OK_goView")
	public void OK_goView(HttpServletRequest request, HttpServletResponse response, AppOrderInfo appInfo)
			throws IOException {

		/*
		 * String payTo = request.getParameter("payTo");// 支付方式: String orderId
		 * = request.getParameter("orderId");// 支付订单号 String playerId =
		 * request.getParameter("playerId");// 支付订单id String goodsId =
		 * request.getParameter("goodsId");// 商品id String sign =
		 * request.getParameter("sign");//
		 */

		System.out.println(appInfo.getPayTo() + "===" + appInfo.getOrderId() + "=====" + appInfo.getPlayerId() + "===="
				+ appInfo.getGoodsId() + "===" + appInfo.getSign());

		System.out.println("OKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOK");
		// 更改订单状态
       
	}

}
