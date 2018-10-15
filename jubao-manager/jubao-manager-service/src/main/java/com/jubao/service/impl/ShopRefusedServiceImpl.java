package com.jubao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jubao.mapper.ShopRefusedMapper;
import com.jubao.service.ShopRefusedService;

@Service
public class ShopRefusedServiceImpl implements ShopRefusedService {

	
	@Autowired
	private ShopRefusedMapper shopRefusedMapper;
	

	@Override
	public int addShopRefused(com.jubao.pojo.ShopRefused shopRefused) {
		// TODO Auto-generated method stub
		return shopRefusedMapper.addShopRefused(shopRefused);
	}

}
