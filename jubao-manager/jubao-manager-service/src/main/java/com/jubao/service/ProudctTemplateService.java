package com.jubao.service;

import com.jubao.pojo.ProductTemplate;
/***
 * ��Ʒ���ģ��
 * @author 12146
 *
 */
public interface ProudctTemplateService {

	

	/****
	 * ���ģ��
	 * @return
	 */
	public int addTemplate(ProductTemplate proudctTemplate);
	/***
	 * ��ѯ����Ʒ���ģ��
	 * 
	 */
	public ProductTemplate findTemplate(int cid);
	
	
	/***
	 * ���� ����idɾ��
	 * 
	 */
	
	public int deleteTemplate(int cid);
	
}
