package com.jubao.pojo.util;
/**
 * 国家省市区县类
 * @author dell
 *
 */
public class CoutryAddress {

	private Integer areaId;//id
	
	private String areaCode;//行政区代码
	
	private String areaName;//地域名称
	
	private Integer level;//级别
	
	private String cityCode;//电话区号归属
	
	private Integer parentId;//父级id

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

  

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
}
