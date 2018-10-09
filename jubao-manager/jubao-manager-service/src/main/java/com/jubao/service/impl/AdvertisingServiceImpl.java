package com.jubao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jubao.mapper.AdvertisingMapper;
import com.jubao.pojo.AdvercateExtension;
import com.jubao.pojo.Advertising;
import com.jubao.service.AdvertisingService;

/***
 * ͼƬ����
 * @author 12146
 *
 */
@Service
public class AdvertisingServiceImpl implements AdvertisingService {

	
	@Autowired
	private AdvertisingMapper AdvertisingMapper;
	
	@Override
	public int addAdvertising(Advertising advertising) {
		// TODO Auto-generated method stub
		return AdvertisingMapper.addAdvertising(advertising);
	}

	@Override
	public int deleteCount(int[] advertisingid) {
		// TODO Auto-generated method stub
		return AdvertisingMapper.sumdelete(advertisingid);
	}

	@Override
	public int deleteAdvertising(int advertisingid) {
		// TODO Auto-generated method stub
		return AdvertisingMapper.deleteAdvertising(advertisingid);
	}

	@Override
	public int updateAdvertising(Advertising advertising) {
		// TODO Auto-generated method stub
		return AdvertisingMapper.updateAdvertising(advertising);
	}

	@Override
	public List<Advertising> getCateidAdvertising(Advertising Advertising) {
		// TODO Auto-generated method stub
		return AdvertisingMapper.getCateidAdvertising(Advertising);
	}

	@Override
	public List<AdvercateExtension> getCateGroupCount() {
		// TODO Auto-generated method stub
		return AdvertisingMapper.getCateGroupCount();
	}



	
}
