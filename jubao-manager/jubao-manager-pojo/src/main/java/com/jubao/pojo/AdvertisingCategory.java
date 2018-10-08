package com.jubao.pojo;

import java.util.Date;

/***
 * 广告分类
 * @author 12146
 *
 */
public class AdvertisingCategory {

	
		private Integer	categoryId;// INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
		private String	categoryName;// VARCHAR(30) NOT NULL COMMENT '分类名',
		private String	categoryDsc; //VARCHAR(300) NOT NULL COMMENT '分类描述',
		private Integer	categoryStatus;// BIGINT NOT NULL COMMENT '分类状态',
		private Float	categoryPirce;// DECIMAL NOT NULL COMMENT '分类价格/每小时'
		private Integer advertisinWidet;// INT NOT NULL COMMENT '图片宽度',
		private Integer	advertisinHight;// INT NOT NULL COMMENT '图片高度',
		
		private Date advertisinCreateTime;//分类创建时间
		private Integer advertisinNumber;// 该分类存储广告数量
		
		public Date getAdvertisinCreateTime() {
			return advertisinCreateTime;
		}
		public void setAdvertisinCreateTime(Date advertisinCreateTime) {
			this.advertisinCreateTime = advertisinCreateTime;
		}
		public Integer getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(Integer categoryId) {
			this.categoryId = categoryId;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public String getCategoryDsc() {
			return categoryDsc;
		}
		public void setCategoryDsc(String categoryDsc) {
			this.categoryDsc = categoryDsc;
		}
		public Integer getCategoryStatus() {
			return categoryStatus;
		}
		public void setCategoryStatus(Integer categoryStatus) {
			this.categoryStatus = categoryStatus;
		}
		public Float getCategoryPirce() {
			return categoryPirce;
		}
		public void setCategoryPirce(Float categoryPirce) {
			this.categoryPirce = categoryPirce;
		}
		public Integer getAdvertisinWidet() {
			return advertisinWidet;
		}
		public void setAdvertisinWidet(Integer advertisinWidet) {
			this.advertisinWidet = advertisinWidet;
		}
		public Integer getAdvertisinHight() {
			return advertisinHight;
		}
		public void setAdvertisinHight(Integer advertisinHight) {
			this.advertisinHight = advertisinHight;
		}
		
		public Integer getAdvertisinNumber() {
			return advertisinNumber;
		}
		public void setAdvertisinNumber(Integer advertisinNumber) {
			this.advertisinNumber = advertisinNumber;
		}
		@Override
		public String toString() {
			return "AdvertisingCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDsc="
					+ categoryDsc + ", categoryStatus=" + categoryStatus + ", categoryPirce=" + categoryPirce
					+ ", advertisinWidet=" + advertisinWidet + ", advertisinHight=" + advertisinHight
					+ ", getCategoryId()=" + getCategoryId() + ", getCategoryName()=" + getCategoryName()
					+ ", getCategoryDsc()=" + getCategoryDsc() + ", getCategoryStatus()=" + getCategoryStatus()
					+ ", getCategoryPirce()=" + getCategoryPirce() + ", getAdvertisinWidet()=" + getAdvertisinWidet()
					+ ", getAdvertisinHight()=" + getAdvertisinHight() + ", getClass()=" + getClass() + ", hashCode()="
					+ hashCode() + ", toString()=" + super.toString() + "]";
		}
		public AdvertisingCategory(Integer categoryId) {
			super();
			this.categoryId = categoryId;
		}
		public AdvertisingCategory() {
			super();
		}
		public AdvertisingCategory(Integer categoryId, Integer categoryStatus) {
			super();
			this.categoryId = categoryId;
			this.categoryStatus = categoryStatus;
		}
	
		
		


	
}
