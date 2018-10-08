package com.jubao.pojo;
/***
 * 广告图片类
 * @author 12146
 *
 */

import java.util.Date;

public class Advertising {
	private Integer advertisinId;  //主键
	private Integer categoryId;// INT NOT NULL COMMENT'分类id'
	private String advertisinUrl;// VARCHAR(500) NOT NULL COMMENT '广告图片存放路径',
	private String advertisinToUrl; // VARCHAR(500) NOT NULL COMMENT '连接地址',
	private Integer advertisinStatus;// INT NOT NULL COMMENT '广告图片状态/1.有效 2.过期',
	private Integer advertisinTime;// INT NOT NULL COMMENT '广告时长投放/小时',
	private Date advertisinCreate;// DATETIME NOT NULL COMMENT '广告创建时间',
	private Date advertisinStartTime;// DATETIME NOT NULL COMMENT '广告开始时间',
	private Date advertisinStart;// DATETIME NOT NULL COMMENT '广告过期时间',
	private Integer advertisinUserType;// INT NOT NULL COMMENT'广告投放用户类型
										// 1.后台管理员,2.卖家 ',
	private Integer advertisinUserId;// INT NOT NULL COMMENT '广告投放人id'

	private String categoryName;//分类名称
	
	
	
	
	public Advertising() {
		super();
	}

	public Advertising(Integer advertisinId, Integer advertisinStatus) {
		super();
		this.advertisinId = advertisinId;
		this.advertisinStatus = advertisinStatus;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getAdvertisinId() {
		return advertisinId;
	}

	public void setAdvertisinId(Integer advertisinId) {
		this.advertisinId = advertisinId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getAdvertisinUrl() {
		return advertisinUrl;
	}

	public void setAdvertisinUrl(String advertisinUrl) {
		this.advertisinUrl = advertisinUrl;
	}


	public String getAdvertisinToUrl() {
		return advertisinToUrl;
	}

	public void setAdvertisinToUrl(String advertisinToUrl) {
		this.advertisinToUrl = advertisinToUrl;
	}

	public Integer getAdvertisinStatus() {
		return advertisinStatus;
	}

	public void setAdvertisinStatus(Integer advertisinStatus) {
		this.advertisinStatus = advertisinStatus;
	}

	public Integer getAdvertisinTime() {
		return advertisinTime;
	}

	public void setAdvertisinTime(Integer advertisinTime) {
		this.advertisinTime = advertisinTime;
	}

	public Date getAdvertisinCreate() {
		return advertisinCreate;
	}

	public void setAdvertisinCreate(Date advertisinCreate) {
		this.advertisinCreate = advertisinCreate;
	}

	public Date getAdvertisinStartTime() {
		return advertisinStartTime;
	}

	public void setAdvertisinStartTime(Date advertisinStartTime) {
		this.advertisinStartTime = advertisinStartTime;
	}

	public Date getAdvertisinStart() {
		return advertisinStart;
	}

	public void setAdvertisinStart(Date advertisinStart) {
		this.advertisinStart = advertisinStart;
	}

	public Integer getAdvertisinUserType() {
		return advertisinUserType;
	}

	public void setAdvertisinUserType(Integer advertisinUserType) {
		this.advertisinUserType = advertisinUserType;
	}

	public Integer getAdvertisinUserId() {
		return advertisinUserId;
	}

	public void setAdvertisinUserId(Integer advertisinUserId) {
		this.advertisinUserId = advertisinUserId;
	}

	@Override
	public String toString() {
		return "AdvertisingCategory [categoryId=" + categoryId + ", advertisinUrl=" + advertisinUrl
				+ "advertisinToUrl=" + advertisinToUrl + ", advertisinStatus="
				+ advertisinStatus + ", advertisinTime=" + advertisinTime + ", advertisinCreate=" + advertisinCreate
				+ ", advertisinStartTime=" + advertisinStartTime + ", advertisinStart=" + advertisinStart
				+ ", advertisinUserType=" + advertisinUserType + ", advertisinUserId=" + advertisinUserId + "]";
	}

}
