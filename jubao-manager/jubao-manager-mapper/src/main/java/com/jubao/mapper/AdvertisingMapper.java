package com.jubao.mapper;

import java.util.List;

import com.jubao.pojo.AdvercateExtension;
import com.jubao.pojo.Advertising;

/***
 * 
 * @author 12146
 *
 */
public interface AdvertisingMapper {

	/***
	 * 添加
	 * @param advertising
	 * @return
	 */
	public int addAdvertising(Advertising advertising);
	
	
	/***
	 * 批量删除
	 */
	public int sumdelete(int[] array);
	
	
	/***
	 * 删除
	 */
	
	public int deleteAdvertising(int advertisingid);
	
	/***
	 * 修改
	 */
	public int updateAdvertising(Advertising advertising);
	
	
	/***
	 * 查询出广告图片
	 */
	public List<Advertising> getCateidAdvertising(Advertising Advertising);
	
	/**
	 *getCateGroupCount  图片分类  和 该分类下的图片数量   advercateExtension   扩展类  
	 */
	
	public List<AdvercateExtension> getCateGroupCount();
	
}
