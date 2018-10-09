package com.jubao.service;

import java.util.List;

import com.jubao.pojo.AdvercateExtension;
import com.jubao.pojo.Advertising;

/***
 * ͼƬ����
 * @author 12146
 *
 */
public interface AdvertisingService {

	/***
	 * ��Ӳ���
	 * @param advertising
	 * @return
	 */
	public int addAdvertising(Advertising advertising);
	
	
	/***
	 * ����ɾ��
	 */
	public int deleteCount(int[] advertisingid);
	
	
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
	 * @param Advertising
	 * @return
	 */
	public List<Advertising> getCateidAdvertising(Advertising Advertising);
     /***
      * getCateGroupCount  �����ѯ�����ķ��࣬�͸÷��������    advercateExtension  ��������չ�� 
      * @param cateid
      * @return
      */
	public List<AdvercateExtension> getCateGroupCount();

}
