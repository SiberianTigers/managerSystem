package com.jubao.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.utis.TtemplateUtil;
import com.jubao.pojo.Product;
import com.jubao.pojo.Product_desc;
import com.jubao.pojo.TemplateValue;
import com.jubao.shop.mapper.ProductMapper;
import com.jubao.shop.mapper.Product_descMapper;
import com.jubao.shop.mapper.TemplateValueMapper;
import com.jubao.shop.pojo.vo.ProductCustom;
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
	public List<ProductCustom> getByStatusProduct(Product product) {
		// TODO Auto-generated method stub

		return productMapper.getByStatusProduct(product);
	}

	@Override
	public boolean updateStatus(Long pid, int status) {
		// TODO Auto-generated method stub
		if (productMapper.updateStatus(pid, status) > 0) {

			return true;
		}

		return false;
	}

	@Override
	public int deleteProduct(Long pid) {
		// TODO Auto-generated method stub
		return productMapper.deleteProduct(pid);
	}

	@Override
	public ProductCustom findProductByPid(Long pid) {
		// TODO Auto-generated method stub

		return productMapper.findProductByPid(pid);
	}

	/****
	 * 商品规格参数
	 */
	@Override
	public List<TtemplateUtil> findTemplateValue(Long pid) {
		// TODO Auto-generated method stub
		List<TtemplateUtil> templateUtil=null;
		TemplateValue templateValue = templateValueMapper.findTemplateValue(pid);
        if(templateValue!=null){
		 templateUtil = JSON.parseArray(templateValue.getParamData(), TtemplateUtil.class);
         return  templateUtil;
        }
		return new ArrayList<TtemplateUtil>();
	}

	/****
	 * 商品描述
	 */

	@Override
	public Product_desc findItemByIdDesc(Long pid) {
		// TODO Auto-generated method stub
		return product_descMapper.findItemByIdDesc(pid);
	}

	@Override
	public boolean updateProduct(Product product, TemplateValue temp, Product_desc desc) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (productMapper.updateProduct(product) > 0) {
			try {
				TemplateValue tp=templateValueMapper.findTemplateValue(product.getPid());//查詢是否存在要修改的規格參數
				if(tp==null){
					templateValueMapper.addTemplateValue(temp); //增加
				}else{
					templateValueMapper.updateTemplateValue(temp);//修改
				}
				if (desc != null) {
					Product_desc ds = product_descMapper.findItemByIdDesc(product.getPid());//查詢是否存在要修改的商品描述
					if (ds == null) {
						product_descMapper.addProduct_desc(desc);//增加
					} else {
						product_descMapper.updateDesc(desc);//修改
					}
				}
				flag = true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}

}
