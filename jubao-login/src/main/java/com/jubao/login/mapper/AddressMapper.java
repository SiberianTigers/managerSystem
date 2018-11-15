package com.jubao.login.mapper;

import java.util.List;

import com.jubao.pojo.util.CoutryAddress;

/**
 *  全国地址
 * @author dell
 *
 */
public interface AddressMapper {

	/**
	 * s根据父级id查询到地址
	 * @param parentId
	 * @return
	 */
	public List<CoutryAddress>getAddress(String parentId);
	
	

	


	 
}
