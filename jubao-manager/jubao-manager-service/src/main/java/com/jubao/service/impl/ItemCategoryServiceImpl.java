package com.jubao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jubao.mapper.ItemCategoryMapper;
import com.jubao.pojo.ItemCategory;
import com.jubao.service.ItemCategoryService;

/***
 * 商品分类业务类
 * 
 * @author 12146
 *
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

	@Autowired
	private ItemCategoryMapper itemCategoryMapper;

	@Override
	public List<ItemCategory> getAllIntegerCategory(Integer id) {
		// TODO Auto-generated method stub

		return itemCategoryMapper.getAllIntegerCategory(id);
	}

	@Override
	public int addCate(ItemCategory category) {

		// TODO Auto-generated method stub
		return itemCategoryMapper.addCate(category);
	}

	@Override
	public int deleteCate(int id) {
		// TODO Auto-generated method stub
		return itemCategoryMapper.deleteCate(id);
	}

	@Override
	public int updateCate(ItemCategory category) {
		// TODO Auto-generated method stub
		return itemCategoryMapper.updateCate(category);
	}

	@Override
	public Integer selectSortIndex(int id) {
		// TODO Auto-generated method stub
		return itemCategoryMapper.selectSortIndex(id);
	}

	@Override
	public int deleteCateChile(int id) {
		// TODO Auto-generated method stub
		return itemCategoryMapper.deleteCateChile(id);
	}

}
