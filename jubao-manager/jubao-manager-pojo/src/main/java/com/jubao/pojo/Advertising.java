package com.jubao.pojo;
/***
 * ���ͼƬ��
 * @author 12146
 *
 */

import java.util.Date;

public class Advertising {
	private Integer advertisinId;  //����
	private Integer categoryId;// INT NOT NULL COMMENT'����id'
	private String advertisinUrl;// VARCHAR(500) NOT NULL COMMENT '���ͼƬ���·��',
	private String advertisinToUrl; // VARCHAR(500) NOT NULL COMMENT '���ӵ�ַ',
	private Integer advertisinStatus;// INT NOT NULL COMMENT '���ͼƬ״̬/1.��Ч 2.����',
	private Integer advertisinTime;// INT NOT NULL COMMENT '���ʱ��Ͷ��/Сʱ',
	private Date advertisinCreate;// DATETIME NOT NULL COMMENT '��洴��ʱ��',
	private Date advertisinStartTime;// DATETIME NOT NULL COMMENT '��濪ʼʱ��',
	private Date advertisinStart;// DATETIME NOT NULL COMMENT '������ʱ��',
	private Integer advertisinUserType;// INT NOT NULL COMMENT'���Ͷ���û�����
										// 1.��̨����Ա,2.���� ',
	private Integer advertisinUserId;// INT NOT NULL COMMENT '���Ͷ����id'

	private String categoryName;//��������
	
	private String dateInfo;  //接受字符串类型的 广告开始 临时日期
	
	
	
	
	public String getDateInfo() {
		return dateInfo;
	}

	public void setDateInfo(String dateInfo) {
		this.dateInfo = dateInfo;
	}

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
