package com.jubao.login.controller;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utis.JubaoResult;
import com.jubao.login.service.AddresService;
import com.jubao.pojo.util.CoutryAddress;

@RequestMapping("address")
@Controller
public class AddressContorller {

	@Autowired
	private AddresService addresService;

	/***
	 * 获取国家地址
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "getAddress", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult getAddress(@RequestParam(value = "parentId", defaultValue = "0") String parentId) {

		try {
			List<CoutryAddress> coutryAddressList = addresService.getAddress(parentId);
			if (coutryAddressList != null && coutryAddressList.size() > 0) {

				return JubaoResult.ok(coutryAddressList);
			} else {
				return JubaoResult.build(400, "加载地址失败");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JubaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}

}
