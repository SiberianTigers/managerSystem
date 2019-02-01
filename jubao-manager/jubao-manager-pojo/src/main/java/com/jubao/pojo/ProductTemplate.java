package com.jubao.pojo;

import java.util.Date;

/***
 * 商品分类模板
 * 
 * @author 12146
 *
 */

public class ProductTemplate {

	private Integer id;
	private Integer categoryid;
	private String paramData;
	private Date createTime;
	private Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}
	public String getParamData() {
		return paramData;
	}
	public void setParamData(String paramData) {
		this.paramData = paramData;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "ProductTemplate [id=" + id + ", categoryid=" + categoryid + ", paramData=" + paramData + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", getId()=" + getId() + ", getCategoryid()="
				+ getCategoryid() + ", getParamData()=" + getParamData() + ", getCreateTime()=" + getCreateTime()
				+ ", getUpdateTime()=" + getUpdateTime() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	

}
