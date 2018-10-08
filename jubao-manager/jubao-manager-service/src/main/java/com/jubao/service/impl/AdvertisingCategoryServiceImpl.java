package com.jubao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jubao.mapper.AdvertisingCategoryMapper;
import com.jubao.pojo.AdvertisingCategory;
import com.jubao.service.AdvertisingCategoryService;

@Service
public class AdvertisingCategoryServiceImpl implements AdvertisingCategoryService {

	@Autowired
	private AdvertisingCategoryMapper advertisingCategoryMapper;

	@Override
	public List<AdvertisingCategory> getAllAdvertisingCategory(AdvertisingCategory advertisingCategory) {
		// TODO Auto-generated method stub
		return advertisingCategoryMapper.getAllAdvertisingCategory(advertisingCategory);
	}

	@Override
	public int addAdvertisingCategory(AdvertisingCategory advertisingCategory) {
		// TODO Auto-generated method stub
		return advertisingCategoryMapper.addAdvertisingCategory(advertisingCategory);
	}

	
	@Override
	public int updateAdvertisingCategory(AdvertisingCategory advertisingCategory) {
		// TODO Auto-generated method stub
		return advertisingCategoryMapper.updateAdvertisingCategory(advertisingCategory);
	}

	@Override
	public int deleteAdvertisingCategory(int id) {
		// TODO Auto-generated method stub
		return advertisingCategoryMapper.deleteAdvertisingCategory(id);
	}

}
