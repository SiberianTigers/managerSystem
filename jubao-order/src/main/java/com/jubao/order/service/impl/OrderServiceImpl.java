package com.jubao.order.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.utis.Constants;
import com.common.utis.CookieUtils;
import com.common.utis.JubaoResult;
import com.jubao.order.dao.JedisClient;
import com.jubao.order.mapper.OrderMapper;
import com.jubao.order.mapper.UsesGetAddressMapper;
import com.jubao.order.pojo.Order;
import com.jubao.order.pojo.OrderDetail;
import com.jubao.order.service.OrderService;
import com.jubao.order.utils.AppOrderInfo;
import com.jubao.order.utils.Cart;
import com.jubao.order.utils.CartItem;
import com.jubao.pojo.User;
import com.jubao.pojo.UserAddress;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private UsesGetAddressMapper usesGetAddressMapper;
	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private OrderMapper orderMapper;

	@Override
	public List<UserAddress> getUserAddress(HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			String token = CookieUtils.getCookieValue(request, Constants.JB_TOKEN);// 获取用户的token
			User user = checkLogin(request);
			request.getSession().setAttribute("user", user);
			if (!StringUtils.isBlank(token)) {
				Integer uid = Integer.parseInt(token);
				List<UserAddress> userAddress = usesGetAddressMapper.findByUseridAddress(uid);
				return userAddress;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	/***
	 * 获取用户信息
	 * 
	 * @param request
	 * @return
	 */
	public User checkLogin(HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			String token = CookieUtils.getCookieValue(request, Constants.JB_TOKEN);
			if (!StringUtils.isBlank(token)) {
				String userStr = jedisClient.get(Constants.SESSION_USER + ":" + token);
				if (!StringUtils.isBlank(userStr)) {

					User user = JSON.parseObject(userStr, User.class);
					System.out.println("-----------" + token + "------" + user);
					return user;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 獲取購物車
	 * 
	 * @return
	 */
	public Cart getCart(HttpServletRequest request) {

		Cart cart = null;
		try {
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
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cart;
	}

	@Override
	public JubaoResult addOrder(HttpServletRequest request, Integer address) {
		// TODO Auto-generated method stub
		int resultCount = 0;
		try {
			UUID uuid = UUID.randomUUID();
			// 获取用户
			User user = (User) request.getSession().getAttribute("user");
			if(user==null){
				return JubaoResult.build(400,"用户未登陆");
			}		
			// 获取购物车
			Cart cart = getCart(request);
			if(cart == null){
				return JubaoResult.build(400,"购物车为空");
			}
			
			AppOrderInfo info=new AppOrderInfo();
			info.setPlayerId(user.getUserid().toString());//用户id
			info.setGoodsPrice(cart.getPrice());//设置总金额去   钉钉 创建订单
			
			Order order = new Order();
			System.out.println("-------创建订单-----------" + uuid.toString() + "------------" + address);
			order.setOrderCode(uuid.toString());// 设置订单id
			order.setUserid(user.getUserid());// 设置用户名
			order.setPayment(cart.getPrice());// 订单金额
			order.setPost_fee(15);// 订单邮费
			order.setCreatetime(new Date());// 订单创建时间
			order.setAddressid(address);// 设置收获地址
			// 订单是否成功创建
			if (orderMapper.create_Order(order) > 0) {
				
				System.out.println("-------创建订单---------"+order.getId()+"---------返回id-----------------");
				info.setOrderId(order.getId().toString());
				// 添加订单明细 
				for (CartItem cartItem : cart.getItemList()) {
					OrderDetail detail = new OrderDetail();
					detail.setOrderid(order.getId());// 设置订单号
					detail.setItemid(cartItem.getItem().getId());// 设置商品号
					detail.setItemType(cartItem.getItemType());// 设置商品规格
					detail.setNum(cartItem.getNum());// 设置商品数量
					detail.setPicpath(cartItem.getItem().getImage());// 设置商品图片
					detail.setPrice(cartItem.getItem().getPrice());// 设置商品单价
					detail.setTotalfee(cartItem.getPrice());// 设置商品总价格
					detail.setTitle(cartItem.getItem().getTitle());// 商品标题
					detail.setStoreName(cartItem.getItem().getStoreName());//店铺名称
		            	
					info.setGoodsName(info.getGoodsName()+"+"+cartItem.getItem().getTitle());//設置商品名稱到 釘釘訂單對象
					if (orderMapper.create_order_detail(detail) > 0) {
						resultCount++;
					}
				}
			}
			
			if (cart.getItemList().size() == resultCount) {
				 return JubaoResult.ok(info);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	 return	JubaoResult.build(400, "创建订单失败");
	}

}
