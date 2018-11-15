package com.jubao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.pojo.ItemCatResult;
import com.common.pojo.ItemCate;
import com.common.utis.Constants;
import com.common.utis.ZtreeUtis;
import com.jubao.mapper.ItemCategoryMapper;
import com.jubao.pojo.ItemCategory;
import com.jubao.rest.dao.JedisClient;
import com.jubao.rest.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired
	private ItemCategoryMapper ItemCategoryMapper;

	@Autowired
	private JedisClient JedisClient;

	@Override
	public String getItemCatResult() {
		// TODO Auto-generated method stub
		ItemCatResult result = null;
		String cateInfo = null;
		try {
			cateInfo = JedisClient.get(Constants.CATEINFO);// 从redis中取出数据

			if (!StringUtils.isBlank(cateInfo)) { // 判断数据是否为空
				System.out.println("==========================进入redis中");
				return cateInfo; // 不为空则返回 取出的json字符串
			}
			System.out.println("==========================进入数据中");
			// 这里 说明redis中为空， 取数据库取出。
			result = new ItemCatResult();
			result.setData(getAllIntegerCategory(0));// 使用递归取出商品分类
			cateInfo = JSON.toJSONString(result);// 将取出的数据转换为json字符串
			JedisClient.set(Constants.CATEINFO, cateInfo); // 将数据存放到redis中
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cateInfo;
	}

	@Override
	public List<?> getAllIntegerCategory(Integer id) {
		// TODO Auto-generated method stub

		// 查询出父类
		List<ItemCategory> cateParentList = ItemCategoryMapper.getAllIntegerCategory(id);

		List cateList = new ArrayList();
		for (ItemCategory cate : cateParentList) {
			// 判断是否父类
			if (cate.getIsParent() == 1) {
				ItemCate item = new ItemCate();

				item.setId(cate.getId());
				item.setUrl("/category/" + cate.getId() + ".html");
				item.setName(cate.getName());
				if (cate.getParentId() == 0) {
					item.setCateImg(cate.getCateImg());
				}
				// 递归调用
				item.setItem(getAllIntegerCategory(cate.getId()));
				cateList.add(item);
			} else {
				// 如果不是父类
				String cateItem = "/item/" + cate.getId() + ".html|" + cate.getName();
				cateList.add(cateItem);
			}
		}
		return cateList;

	}

	@Override
	public long delRedisItemCate() {
		// TODO Auto-generated method stub
		long count = 0;
		// 刪除缓存中的广告主键
		try {
			count = JedisClient.del(Constants.CATEINFO);
			System.out.println(count + "------------刪除redis中商品分类 的主键-------");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<?> getProductCate(Integer id) {
		// TODO Auto-generated method stub
		// 查询出父类
		List<ItemCategory> cateParentList = ItemCategoryMapper.getAllIntegerCategory(id);

		List cateList = new ArrayList();
		for (ItemCategory cate : cateParentList) {
			// 判断是否父类
			if (cate.getIsParent() == 1) {
				ZtreeUtis item = new ZtreeUtis();
				item.setName(cate.getName());
				item.setId(cate.getId());
				item.setParent(cate.getIsParent());
				item.setChildren(getProductCate(cate.getId()));
				cateList.add(item);
			} else {
				ZtreeUtis z = new ZtreeUtis();
				z.setName(cate.getName());
				z.setId(cate.getId());
				z.setParent(cate.getIsParent());
				cateList.add(z);
			}
			// 递归调用
		}
		return cateList;
	}

	@Override
	public ItemCatResult getCateResult() {
		// TODO Auto-generated method stub
		ItemCatResult item = null;
		try {
			String result = JedisClient.get("zTreeCate");

			if (!StringUtils.isBlank(result)) {
				item = JSONArray.parseObject(result, ItemCatResult.class);
				return item;
			}

			item = new ItemCatResult();
			item.setData(getProductCate(0));
			String resultStr = JSONObject.toJSONString(item);
			JedisClient.set("zTreeCate", resultStr);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return item;
	}

}
