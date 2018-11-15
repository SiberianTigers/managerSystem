package com.jubao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jubao.pojo.Product_desc;
import com.jubao.pojo.TemplateValue;
import com.jubao.rest.mapper.ItemMapper;
import com.jubao.rest.service.ItemService;
/***
 * 商品类
 * @author 12146
 *
 */
@Service(value="itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Override
	public TemplateValue findByIdTemplateValue(Long pid) {
		// TODO Auto-generated method stub
		return itemMapper.findByIdTemplateValue(pid);
	}
   
	 
	@Override
	public Product_desc findByIdProduct_desc(Long pid) {
		// TODO Auto-generated method stub
		return itemMapper.findByIdProduct_desc(pid);
	}

}
