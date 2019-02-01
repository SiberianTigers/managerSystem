package com.jubao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jubao.mapper.ProudctTemplateMapper;
import com.jubao.pojo.ProductTemplate;
import com.jubao.service.ProudctTemplateService;

@Service
public class ProudctTemplateServiceImpl implements ProudctTemplateService {

	
	@Autowired
	private ProudctTemplateMapper proudctTemplateMapper;
	
	
	@Override
	public int addTemplate(ProductTemplate proudctTemplate) {
		// TODO Auto-generated method stub  
		//根据f分类id删除模板
		   deleteTemplate(proudctTemplate.getCategoryid());		
		
		    //然后添加新模板
		   return proudctTemplateMapper.addTemplate(proudctTemplate);
	}


	
	
	/***
	 * 根据商品分类id 查询出模板
	 */
	@Override
	public ProductTemplate findTemplate(int cid) {
		// TODO Auto-generated method stub
		return proudctTemplateMapper.findTemplate(cid);
	}


	@Override
	public int deleteTemplate(int cid) {
		// TODO Auto-generated method stub
		return proudctTemplateMapper.deleteTemplate(cid);
	}

}
