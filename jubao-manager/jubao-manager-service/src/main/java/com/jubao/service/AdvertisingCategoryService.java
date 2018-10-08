package com.jubao.service;

import java.util.List;

import com.jubao.pojo.AdvertisingCategory;

/***
 * ͼƬ����ҵ���
 * @author 12146
 *
 */
public interface AdvertisingCategoryService {

	/***
	 * ��ȡ���еķ���
	 * @return
	 */
	public List<AdvertisingCategory> getAllAdvertisingCategory(AdvertisingCategory advertisingCategory);
	/***
	 * ��ӷ���
	 * @return
	 */
	public int addAdvertisingCategory(AdvertisingCategory advertisingCategory);
	
	
	/***
	 * �߼�ɾ������
	 */
	public int updateAdvertisingCategory(AdvertisingCategory advertisingCategory);
	
	
	/**
	 * 
	 * ɾ������
	 * 
	 */
	public int deleteAdvertisingCategory(int id);
	
	
	
}
