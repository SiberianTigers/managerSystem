package com.jubao.order.contorller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.pojo.Cart;
import com.common.utis.JubaoResult;
import com.jubao.order.pojo.vo.OrderVo;
import com.jubao.order.service.OrderService;
import com.jubao.order.utils.AppOrderInfo;
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
	 * @throws UnsupportedEncodingException
	 */

	@RequestMapping(value = "dfkOrder", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	public String dfkOrder(@RequestParam("orderid") Long orderid) throws UnsupportedEncodingException {
		AppOrderInfo info = null;
		try {
			info = orderService.dfkOrder(orderid);
			info.setGoodsName(java.net.URLEncoder.encode(info.getGoodsName(), "UTF-8"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "redirect:/order/order/selectZf.html?playerId=" + info.getOrderId() + "&&appId=" + info.getAppId()
				+ "&&goodsPrice=" + getPrice(info.getGoodsPrice()) + "&&wx=1&&alipay=1&&succHide=1&&goodsName="
				+ info.getGoodsName() + "&&goodsId=1";// 跳转到选择支付页面
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



}
