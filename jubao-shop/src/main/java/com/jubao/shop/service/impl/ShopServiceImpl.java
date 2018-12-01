package com.jubao.shop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.common.utis.HttpClientUtil;
import com.common.utis.JubaoResult;
import com.jubao.pojo.Shop;
import com.jubao.shop.mapper.UserShopMapper;
import com.jubao.shop.pojo.vo.OrderCustom;
import com.jubao.shop.pojo.vo.OrderVo;
import com.jubao.shop.pojo.vo.UserOrderStatusInfo;
import com.jubao.shop.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private UserShopMapper userShopMapper;

	private String ORDER_LOCATLHOST = "http://localhost:8088/order/orderInfo/";

	@Override
	public Shop findShopByUserId(int userid) {

		// TODO Auto-generated method stub
		return userShopMapper.findShopByUserid(userid);
	}

	@Override
	public List<OrderCustom> findOrderByShop(int sid, int status) {
		// TODO Auto-generated method stub findOrderByShop
		Map<String, String> param = new HashMap<String, String>();
		param.put("sid", sid + "");
		param.put("status", status + "");
		String result = null;
		try {
			result = HttpClientUtil.doPost(ORDER_LOCATLHOST + "findOrderByShop", param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (!StringUtils.isBlank(result)) {
			JubaoResult jubaoResult = JSON.parseObject(result, JubaoResult.class);
			if (jubaoResult != null) {
				if (jubaoResult.getStatus() == 200) {
					System.out.println(jubaoResult.getData() + "------findOrderByShop-----");
					List<OrderCustom> orderList = (List<OrderCustom>) jubaoResult.getData();
					return orderList;
				}

			}
		}
		return new ArrayList<OrderCustom>();
	}

	@Override
	public JubaoResult updateOrderStatus(OrderVo ordervo) {
		// TODO Auto-generated method stub updateOrderStatus
		Map<String, String> param = new HashMap<String, String>();
		param.put("orderCustom.id", ordervo.getOrderCustom().getId() + "");
		param.put("orderCustom.status", 2 + "");
		param.put("orderCustom.shippingname", ordervo.getOrderCustom().getShippingname());
		param.put("orderCustom.consigntime", new Date() + "");
		String result = null;
		try {
			result = HttpClientUtil.doPost(ORDER_LOCATLHOST + "updateOrderStatus", param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (!StringUtils.isBlank(result)) {
			JubaoResult jubaoResult = JSON.parseObject(result, JubaoResult.class);
			if (jubaoResult != null) {
				if (jubaoResult.getStatus() == 200) {
					System.out.println(jubaoResult.getData() + "------findOrderByShop-----");
					return jubaoResult;
				}

			}
		}
		return JubaoResult.build(400, "请求出错");
	}

	@Override
	public Map<String, String> findOrderStatusNumber(int shopid) {
		// TODO Auto-generated method stub findShopOrderStatus
		Map<String, String> param = new HashMap<String, String>();
		param.put("shopid", shopid + "");
		String result = null;
		Map<String, String> sresult = new HashMap<String, String>();
		sresult.put("dfk", "0");
		sresult.put("yfk", "0");
		sresult.put("yfh", "0");
		sresult.put("ywc", "0");
		try {
			result = HttpClientUtil.doPost(ORDER_LOCATLHOST + "findShopOrderStatus", param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (!StringUtils.isBlank(result)) {
			JubaoResult jubaoResult = JSON.parseObject(result, JubaoResult.class);
			if (jubaoResult != null) {
				if (jubaoResult.getStatus() == 200) {
					System.out.println(jubaoResult.getData() + "------findOrderByShop-----");
					List<UserOrderStatusInfo> statuslist = JSONArray.parseArray(jubaoResult.getData().toString(),
							UserOrderStatusInfo.class);
					System.out.println("----findOrderStatusNumber------------------" + statuslist);
					for (UserOrderStatusInfo slist : statuslist) {
						if (slist.getStatus() == 0) {
							sresult.put("dfk", slist.getNum() + "");
						} else if (slist.getStatus() == 1) {
							sresult.put("yfk", slist.getNum() + "");
						} else if (slist.getStatus() == 2) {
							sresult.put("yfh", slist.getNum() + "");
						} else if (slist.getStatus() == 3) {
							sresult.put("ywc", slist.getNum() + "");
						}
					}
				}

			}
		}
		return sresult;
	}

	@Override
	public OrderCustom findUserGetAddress(int address) {
		// TODO Auto-generated method stub
		Map<String, String> param = new HashMap<String, String>();
		param.put("address", address + "");
		String result = null;
		try {
			result = HttpClientUtil.doPost(ORDER_LOCATLHOST + "findUserGetAddress", param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (!StringUtils.isBlank(result)) {
			JubaoResult jubaoResult = JSON.parseObject(result, JubaoResult.class);
			if (jubaoResult != null) {
				if (jubaoResult.getStatus() == 200) {
					System.out.println(jubaoResult.getData() + "------findUserGetAddress-----");
					OrderCustom order = JSON.parseObject(jubaoResult.getData().toString(), OrderCustom.class);
					return order;
				}

			}
		}
		return new OrderCustom();
	}

}
