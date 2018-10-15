package com.jubao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jubao.mapper.ShopMessageMapper;
import com.jubao.pojo.ShopMessage;
import com.jubao.service.ShopMessagerService;

@Service
public class ShopMessagerServiceImpl implements ShopMessagerService {

	@Resource
	private ShopMessageMapper shopMessageMapper;
	
	@Override
	public int addShopMessage(ShopMessage shopMessage) {
		// TODO Auto-generated method stub
		return shopMessageMapper.addShopMessage(shopMessage);
	}

	@Override
	public int BatchAdd(ShopMessage shopMessage, int[] arr) {
		// TODO Auto-generated method stub
		return shopMessageMapper.BatchAdd(shopMessage, arr);
	}

}
