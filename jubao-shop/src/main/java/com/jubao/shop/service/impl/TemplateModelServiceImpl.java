package com.jubao.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jubao.pojo.ProductTemplate;
import com.jubao.shop.mapper.TemplateModelMapper;
import com.jubao.shop.service.TemplateModelService;

@Service
public class TemplateModelServiceImpl implements TemplateModelService {

	@Autowired
	private TemplateModelMapper  TemplateModelMapper;
	
	
	@Override
	public ProductTemplate findByIdProductTemplate(int cid) {
		
		// TODO Auto-generated method stub
		return TemplateModelMapper.findByIdProductTemplate(cid);
	}

}
