package com.jubao.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jubao.pojo.Product;
import com.jubao.pojo.Product_desc;
import com.jubao.pojo.TemplateValue;
import com.jubao.shop.mapper.ProductMapper;
import com.jubao.shop.mapper.Product_descMapper;
import com.jubao.shop.mapper.TemplateValueMapper;
import com.jubao.shop.service.ProudctService;

@Service
public class ProductServiceImpl implements ProudctService {

	@Autowired
	private ProductMapper productMapper;// 商品
	@Autowired
	private TemplateValueMapper templateValueMapper;// 商品规格值类
	@Autowired
	private Product_descMapper product_descMapper;// 商品描述

	@Override
	public boolean addProduct(Product product, TemplateValue templateValue, Product_desc product_desc) {
		// TODO Auto-generated method stub
		try {
			if (productMapper.addProduct(product) > 0) {
				templateValueMapper.addTemplateValue(templateValue);
				product_descMapper.addProduct_desc(product_desc);
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public List<Product> getByStatusProduct(Product product) {
		// TODO Auto-generated method stub

		return productMapper.getByStatusProduct(product);
	}

	@Override
	public boolean updateStatus(int pid,int status) {
		// TODO Auto-generated method stub
		if (productMapper.updateStatus(pid,status) > 0) {

			return true;
		}

		return false;
	}

	@Override
	public int updateProduct(Product product) {
		// TODO Auto-generated method stub
		return productMapper.updateProduct(product);
	}

	@Override
	public int deleteProduct(int pid) {
		// TODO Auto-generated method stub
		return productMapper.deleteProduct(pid);
	}

}
