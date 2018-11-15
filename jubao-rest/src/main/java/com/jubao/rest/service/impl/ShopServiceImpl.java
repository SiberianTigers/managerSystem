package com.jubao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jubao.pojo.Shop;
import com.jubao.rest.mapper.ShopMapper_rest;
import com.jubao.rest.service.ShopService;
@Service
public class ShopServiceImpl implements ShopService{

	@Autowired
	private ShopMapper_rest shopMapper_rest;
	@Override
	public Shop findByIdShop(int sid, int pageSize) {
		// TODO Auto-generated method stub
		return shopMapper_rest.findByIdShop(sid, pageSize);
	}

}
