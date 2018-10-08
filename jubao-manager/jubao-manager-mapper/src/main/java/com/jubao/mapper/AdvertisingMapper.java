package com.jubao.mapper;

import java.util.List;

import com.jubao.pojo.AdvercateExtension;
import com.jubao.pojo.Advertising;

/***
 * 图片管理
 * @author 12146
 *
 */
public interface AdvertisingMapper {

	/***
	 * 添加操作
	 * @param advertising
	 * @return
	 */
	public int addAdvertising(Advertising advertising);
	
	
	/***
	 * 批量删除
	 */
	public int sumdelete(int[] advertisingid);
	
	
	/***
	 * 单个删除
	 */
	
	public int deleteAdvertising(int advertisingid);
	
	/***
	 * 修改
	 */
	public int updateAdvertising(Advertising advertising);
	
	
	/***
	 * 根据分类id查询出广告信息
	 */
	public List<Advertising> getCateidAdvertising(Advertising Advertising);
	
	/**
	 *getCateGroupCount  分组查询出广告的分类，和该分类的数量    advercateExtension  广告分类扩展类 
	 */
	
	public List<AdvercateExtension> getCateGroupCount();
	
}
