package com.jubao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.common.utis.HttpClientUtil;
import com.common.utis.JubaoResult;
import com.jubao.portal.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

	@Value("${CONN_URL}")
	private String CONN_URL;

	@Override
	public JubaoResult getShopAndItem(int sid, int pageSize) {
		// TODO Auto-generated method stub

		Map param = new HashMap<String,String>();
		param.put("sid",sid+"");
		param.put("pageSize", pageSize+"");
		try {
			String result = HttpClientUtil.doPost(CONN_URL + "/shop/getShopAndItemInfo", param);
			if (!StringUtils.isBlank(result)) {
				JubaoResult jubaoResult = JSONObject.parseObject(result, JubaoResult.class);
				if (jubaoResult.getStatus() == 200) {
					return jubaoResult;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
