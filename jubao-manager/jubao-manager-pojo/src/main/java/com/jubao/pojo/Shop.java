package com.jubao.pojo;

import java.util.Date;;

/***
 * 店铺对象
 * 
 * @author 12146
 *
 */
public class Shop {

	private Integer id;  //主键
	private Integer storeOw; //店铺老板
	private Integer shopType;//店铺类型
	private String storeName;//店铺名称
	private String catename;// 店铺分类名称
	private Integer storeCategory;//店铺分类id
	private Integer storeStatus;//店铺状态  0 待审核   1.审核通过  . 2.屏蔽店铺 7天. 3.屏蔽14天    4.屏蔽21天   5 .封停店铺
	private Integer storeNumber;//店铺健康分值
	private Date storeCreateTime;//店铺创建时间
	private String storeAddress;//店铺所在地址
	private Integer storeUserNumber;//店铺用户评分
	private Float storePrice;//店铺注册押金
	private Float countPrice;//店铺营业额
	private Integer productNumber;//店铺商品销量
	private Integer phone;//联系电话
	private Long idCard;//身份证
	private String email;//电子邮箱
	private String owName;//申请者姓名
	
	private String shopDesc;//店铺简介
	
	private Integer shopStatus;//经营状态   1.正常  . 2.屏蔽店铺 7天. 3.屏蔽14天    4.屏蔽21天   5 .封停店铺
	
	private String vname;//经营状态名称
	
	private Integer points;//违规记分值
	
	
	
	public String getOwName() {
		return owName;
	}
	public void setOwName(String owName) {
		this.owName = owName;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Integer getShopStatus() {
		return shopStatus;
	}
	public void setShopStatus(Integer shopStatus) {
		this.shopStatus = shopStatus;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStoreOw() {
		return storeOw;
	}
	public void setStoreOw(Integer storeOw) {
		this.storeOw = storeOw;
	}
	public Integer getShopType() {
		return shopType;
	}
	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getCatename() {
		return catename;
	}
	public void setCatename(String catename) {
		this.catename = catename;
	}
	public Integer getStoreCategory() {
		return storeCategory;
	}
	public void setStoreCategory(Integer storeCategory) {
		this.storeCategory = storeCategory;
	}
	public Integer getStoreStatus() {
		return storeStatus;
	}
	public void setStoreStatus(Integer storeStatus) {
		this.storeStatus = storeStatus;
	}
	public Integer getStoreNumber() {
		return storeNumber;
	}
	public void setStoreNumber(Integer storeNumber) {
		this.storeNumber = storeNumber;
	}
	public Date getStoreCreateTime() {
		return storeCreateTime;
	}
	public void setStoreCreateTime(Date storeCreateTime) {
		this.storeCreateTime = storeCreateTime;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public Integer getStoreUserNumber() {
		return storeUserNumber;
	}
	public void setStoreUserNumber(Integer storeUserNumber) {
		this.storeUserNumber = storeUserNumber;
	}
	public Float getStorePrice() {
		return storePrice;
	}
	public void setStorePrice(Float storePrice) {
		this.storePrice = storePrice;
	}
	public Float getCountPrice() {
		return countPrice;
	}
	public void setCountPrice(Float countPrice) {
		this.countPrice = countPrice;
	}
	public Integer getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public Long getIdCard() {
		return idCard;
	}
	public void setIdCard(Long idCard) {
		this.idCard = idCard;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Shop [id=" + id + ", storeOw=" + storeOw + ", shopType=" + shopType + ", storeName=" + storeName
				+ ", catename=" + catename + ", storeCategory=" + storeCategory + ", storeStatus=" + storeStatus
				+ ", storeNumber=" + storeNumber + ", storeCreateTime=" + storeCreateTime + ", storeAddress="
				+ storeAddress + ", storeUserNumber=" + storeUserNumber + ", storePrice=" + storePrice + ", countPrice="
				+ countPrice + ", productNumber=" + productNumber + ", phone=" + phone + ", idCard=" + idCard
				+ ", email=" + email + ", getId()=" + getId() + ", getStoreOw()=" + getStoreOw() + ", getShopType()="
				+ getShopType() + ", getStoreName()=" + getStoreName() + ", getCatename()=" + getCatename()
				+ ", getStoreCategory()=" + getStoreCategory() + ", getStoreStatus()=" + getStoreStatus()
				+ ", getStoreNumber()=" + getStoreNumber() + ", getStoreCreateTime()=" + getStoreCreateTime()
				+ ", getStoreAddress()=" + getStoreAddress() + ", getStoreUserNumber()=" + getStoreUserNumber()
				+ ", getStorePrice()=" + getStorePrice() + ", getCountPrice()=" + getCountPrice()
				+ ", getProductNumber()=" + getProductNumber() + ", getPhone()=" + getPhone() + ", getIdCard()="
				+ getIdCard() + ", getEmail()=" + getEmail() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
