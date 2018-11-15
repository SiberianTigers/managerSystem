package com.jubao.rest.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.common.utis.Constants;
import com.jubao.pojo.Advertising;
import com.jubao.pojo.AdvertisingCategory;
import com.jubao.rest.dao.JedisClient;
import com.jubao.rest.mapper.AdvertisingAndCateMapper;
import com.jubao.rest.service.AdvertisingAndCateService;

@Service
public class AdvertisingAndCateServiceImpl implements AdvertisingAndCateService {

	private Logger log = Logger.getLogger(AdvertisingAndCateServiceImpl.class);

	@Autowired
	private AdvertisingAndCateMapper AdvertisingAndCateMapper;

	@Autowired
	private JedisClient JedisClient;// 引入redis集群

	@Override
	public List<AdvertisingCategory> findAdvertisingCategoryAndAdvertising() {
		// TODO Auto-generated method stub
		List<AdvertisingCategory> advertisingCategoryList = null;
		try {
			// 去redis中查找广告
			String AdvertisingCategoryStr = JedisClient.get(Constants.ADVERTISING);

			if (!StringUtils.isBlank(AdvertisingCategoryStr)) {
				// 如果广告存在则取出 转换为java对象
				advertisingCategoryList = JSONArray.parseArray(AdvertisingCategoryStr, AdvertisingCategory.class);

				System.out.println("取广告中---redis-----------------------"+advertisingCategoryList);
				
				return getResult(advertisingCategoryList);// 返回结果
			}
			// 不存在去數據庫查詢廣告 和廣告分類
			advertisingCategoryList = AdvertisingAndCateMapper.findAdvertisingCategoryAndAdvertising(1);
			// 将查询出大于当前时间的有效期广告存放到redis中
			String result = JSONArray.toJSONString(advertisingCategoryList);
			JedisClient.set(Constants.ADVERTISING, result);

			System.out.println("取广告中---Mysql-----------------------"+result);
			
			return getResult(advertisingCategoryList);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ArrayList<AdvertisingCategory>();
		}

	}

	/***
	 * 過濾有效的广告
	 * @param advertisingCategoryList
	 * @return
	 */
	public List<AdvertisingCategory> getResult(List<AdvertisingCategory> advertisingCategoryList) {
		// 过滤有效广告
		for (AdvertisingCategory cate : advertisingCategoryList) {
			// 将超过有效期的广告删除
			for (int x = 0; x < cate.getAdvertisingList().size(); x++) {// 这里遍历redis中的广告集合
				// 判断广告时间,是否生效，是否过期
				if (!filterEffectively(cate.getAdvertisingList().get(x).getAdvertisinStartTime(),
						cate.getAdvertisingList().get(x).getAdvertisinStart())) {
					  // 将不合格的广告剔除
					cate.getAdvertisingList().remove(cate.getAdvertisingList().get(x));
				}
			}
		}
		return advertisingCategoryList;
	}

	/***
	 * 判断广告有效期
	 * 
	 * @return
	 */
	public boolean filterEffectively(Date startDate, Date endDate) {
		Date nowDate = new Date();
		if (nowDate.getTime() >= startDate.getTime() && endDate.getTime() > nowDate.getTime()) {
			return true;
		}
		return false;
	}

	@Override
	public long delAdvertisingRedis() {
		// TODO Auto-generated method stub
		long count = 0;
		// 刪除缓存中的广告主键
		try {
			count = JedisClient.del(Constants.ADVERTISING);
			System.out.println(count + "------------刪除redis中的主键-------");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}

}
