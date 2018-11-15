package com.jubao.portal.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.utis.HttpClientUtil;
import com.common.utis.JubaoResult;
import com.jubao.pojo.Advertising;
import com.jubao.pojo.AdvertisingCategory;
import com.jubao.portal.service.AdvertisingService;
/****
 * 广告类
 * @author 12146
 *
 */
@Service
public class AdvertisingServiceImpl implements AdvertisingService {

	@Value("${CONN_URL}")
	private String CONN_URL;

	
	
	@Override
	public Map<String,AdvertisingCategory> findAdvertisingCategoryAndAdvertising() {
		// TODO Auto-generated method stub
		JubaoResult jb = null;
		Map<String,AdvertisingCategory> MapresultCategory = new HashMap<String, AdvertisingCategory>();
		try {
			// 创建一个httpclient对象
			 String result=HttpClientUtil.doPost(CONN_URL + "advertising/AdvertisingCategoryAndAdvertising");
			
			 //将json字符串转换为JSON对象
			JSON json = JSON.parseObject(result);
			//将JSON对象转换为   juresult对象
			jb = JSONObject.toJavaObject(json, JubaoResult.class);
		   System.out.println("======AdvertisingServiceImpl==================="+jb.getData()); 
			if (jb.getStatus() == 200) {
				//将json字符串转换为JSON对象			
			    List<AdvertisingCategory>advertisingCategoryList=JSONObject.parseArray(jb.getData().toString(),AdvertisingCategory.class);
			  
			    for(AdvertisingCategory adv:advertisingCategoryList){			
			    	System.out.println("-----------------------"+adv);
					MapresultCategory.put("Cate"+adv.getCategoryId(),adv);			
			    }		
			    return MapresultCategory;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return  new HashMap<String, AdvertisingCategory>();
	}

}
