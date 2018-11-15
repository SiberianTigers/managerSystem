package com.jubao.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jubao.login.mapper.AddressMapper;
import com.jubao.login.service.AddresService;
import com.jubao.pojo.util.CoutryAddress;
/***
 * 国家地址
 * @author 12146
 *
 */
@Service
public class AddressServiceImpl implements AddresService {

	@Autowired
	private  AddressMapper addressMapper;
	@Override
	public List<CoutryAddress> getAddress(String parentId) {
		// TODO Auto-generated method stub
	
		return addressMapper.getAddress(parentId);
	}

}
