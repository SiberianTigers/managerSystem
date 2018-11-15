package com.jubao.login.service;

import java.util.List;

import com.jubao.pojo.util.CoutryAddress;

public interface AddresService {
	/**
	 * s根据父级id查询到地址
	 * @param parentId
	 * @return
	 */
	public List<CoutryAddress>getAddress(String parentId);
}
