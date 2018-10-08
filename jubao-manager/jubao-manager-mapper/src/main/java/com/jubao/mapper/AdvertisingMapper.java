package com.jubao.mapper;

import java.util.List;

import com.jubao.pojo.AdvercateExtension;
import com.jubao.pojo.Advertising;

/***
 * ͼƬ����
 * @author 12146
 *
 */
public interface AdvertisingMapper {

	/***
	 * ��Ӳ���
	 * @param advertising
	 * @return
	 */
	public int addAdvertising(Advertising advertising);
	
	
	/***
	 * ����ɾ��
	 */
	public int sumdelete(int[] advertisingid);
	
	
	/***
	 * ����ɾ��
	 */
	
	public int deleteAdvertising(int advertisingid);
	
	/***
	 * �޸�
	 */
	public int updateAdvertising(Advertising advertising);
	
	
	/***
	 * ���ݷ���id��ѯ�������Ϣ
	 */
	public List<Advertising> getCateidAdvertising(Advertising Advertising);
	
	/**
	 *getCateGroupCount  �����ѯ�����ķ��࣬�͸÷��������    advercateExtension  ��������չ�� 
	 */
	
	public List<AdvercateExtension> getCateGroupCount();
	
}
