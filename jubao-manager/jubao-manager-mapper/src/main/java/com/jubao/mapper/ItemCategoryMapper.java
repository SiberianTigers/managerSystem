package com.jubao.mapper;

import java.util.List;

import com.jubao.pojo.ItemCategory;

public interface ItemCategoryMapper {
  
	/***
	 * ��ȡ���з���
	 * @return
	 */
	public List<ItemCategory> getAllIntegerCategory(Integer id);
	
	/***
	 * ������Ʒ����
	 */
	
	public int addCate(ItemCategory category);
	
	/***
	 * ɾ����Ʒ����
	 */
	
	public int  deleteCate(int id);
	
	/**
	 * ɾ���ӷ���
	 * @param id
	 * @return
	 */
	public int deleteCateChile(int id);
	
	/***
	 * �޸���Ʒ����
	 */
	public int updateCate(ItemCategory category);

  /**
   *  ��ѯ ��   sort_order  �ֶ� �������  
   */
	
public Integer selectSortIndex(int id);

	
	
}
