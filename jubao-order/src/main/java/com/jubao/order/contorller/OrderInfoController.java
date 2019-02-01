package com.jubao.order.contorller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utis.JubaoResult;
import com.jubao.order.pojo.vo.OrderCustom;
import com.jubao.order.pojo.vo.OrderVo;
import com.jubao.order.service.OrderService;
import com.jubao.order.utils.AppOrderInfo;

@RequestMapping("orderInfo")
@Controller
public class OrderInfoController {

	@Autowired
	private OrderService orderService;

	/****
	 * 查询店铺查询订单
	 */
	@RequestMapping(value = "findOrderByShop", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult findOrderByShop(int sid, int status) {
		JubaoResult jubaoResult = null;
		System.out.println(sid + "-店铺id----查询店铺查询订单-------状态id--" + status);
		try {
			jubaoResult = orderService.findOrderByShop(sid, status);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jubaoResult;// 跳转到选择支付页面
	}

	/****
	 * 查询店铺查询订单
	 */
	@RequestMapping(value = "updateOrderStatus", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult updateOrderStatus(OrderVo orderVo) {
		System.out.println("-----查询店铺查询订单---------");
		JubaoResult jubaoResult = null;
		if (orderVo == null) {
			return jubaoResult.build(400, "请求信息错误,请重试");
		}
		try {
			jubaoResult = orderService.updateOrderStatus(orderVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jubaoResult;// 跳转到选择支付页面
	}

	/****
	 * 查询店铺查询订单数量
	 */
	@RequestMapping(value = "findShopOrderStatus", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult findShopOrderStatus(int shopid) {
		JubaoResult jubaoResult = null;
		System.out.println(shopid + "-----查询店铺查询订单数量---------");
		try {
			jubaoResult = orderService.findOrderStatusNumber(shopid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jubaoResult;// 跳转到选择支付页面
	}

	/****
	 * 查询店铺查询订单数量
	 */
	@RequestMapping(value = "findUserGetAddress", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult findUserGetAddress(int address) {
		JubaoResult jubaoResult = null;
		System.out.println(address + "-----查询店铺查询订单数量---------");
		try {
			jubaoResult = orderService.findUserGetAddress(address);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jubaoResult;// 跳转到选择支付页面
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
		OrderVo vo = new OrderVo();
		OrderCustom o = new OrderCustom();
		o.setStatus(1);
		if (appInfo.getPlayerId().indexOf("f") != -1) {
			System.out.println("--------合并订单--------");
			String str = appInfo.getPlayerId();
			String widstr = str.substring(0, str.indexOf("f"));
			Long wid = Long.parseLong(widstr);
			o.setWid(wid);
			vo.setOrderCustom(o);
			orderService.updateOrderStatus(vo);
		} else {
			System.out.println("--------单个订单--------");
			String str = appInfo.getPlayerId();
			Long id = Long.parseLong(str);
			o.setId(id);
			vo.setOrderCustom(o);
			orderService.updateOrderStatus(vo);
		}

		System.out.println("OKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOKOK");
		// 更改订单状态

	}

}
