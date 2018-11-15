package com.jubao.login.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.utis.JubaoResult;
import com.jubao.login.mapper.UsesGetAddressMapper;
import com.jubao.pojo.User;
import com.jubao.pojo.UserAddress;

@Service
public class UserGetAddressImpl implements com.jubao.login.service.UserGetAddress {

	@Autowired
	private UsesGetAddressMapper usesGetAddressMapper;

	@Override
	public int userGetAddRess(UserAddress address) {
		// TODO Auto-generated method stub

		address.setCreateTime(new Date());
		if(address.getIsdefault()==1){
			usesGetAddressMapper.updateDefaultAddress(address.getUserId());		
		}
		return usesGetAddressMapper.userGetAddRess(address);
	}

	@Override
	public List<UserAddress> findByUseridAddress(Integer userid) {
		// TODO Auto-generated method stub

		return usesGetAddressMapper.findByUseridAddress(userid);
	}

	@Override
	public JubaoResult setDefaultAddress(String addressid, HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {

				if (usesGetAddressMapper.updateDefaultAddress(user.getUserid()) > 0) {
					if (usesGetAddressMapper.setDefaultAddress(addressid) > 0) {
						return JubaoResult.ok();
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JubaoResult.build(400, "操作失敗");
	}

}
