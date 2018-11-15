package com.jubao.shop.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.common.pojo.ItemCatResult;
import com.common.utis.HttpClientUtil;
import com.common.utis.JubaoResult;
import com.jubao.shop.service.ProductCateService;

@Service
public class ProductCateServiceImpl implements ProductCateService {

	@Value("${CONN_URL}")
	private String CONN_URL;


	@Override
	public ItemCatResult getTreeCate() {
		// TODO Auto-generated method stub
		try {
			
			String result = HttpClientUtil.doGet(CONN_URL+"Categories/loadCateType");
              
			System.out.println("----getTreeCate-------" + result);
			if (!StringUtils.isBlank(result)) {

				JubaoResult jubaoResult = JSONObject.parseObject(result, JubaoResult.class);

				System.out.println("----getTreeCate-------" + jubaoResult.getStatus());
				if (jubaoResult.getStatus() == 200) {

					ItemCatResult itemCate =JSONObject.parseObject(jubaoResult.getData().toString(),ItemCatResult.class);
					
					System.out.println("----ItemCatResult itemCate = (ItemCatResult) jubaoResult.getData();-------" + itemCate);
					return itemCate;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

}
