package com.jubao.pojo;

import java.util.Date;
import java.util.List;

import com.jubao.pojo.util.Item;;

/***
 * 搴楅摵瀵硅薄
 * 
 * @author 12146
 *
 */
public class Shop {
    
	private Long id;  //涓婚敭
	private Long storeOw; //搴楅摵鑰佹澘
	private Integer shopType;//搴楅摵绫诲瀷
	private String storeName;//搴楅摵鍚嶇О
	private String catename;// 搴楅摵鍒嗙被鍚嶇О
	private Integer storeCategory;//搴楅摵鍒嗙被id
	private Integer storeStatus;//搴楅摵鐘舵��  0 寰呭鏍�   1.瀹℃牳閫氳繃  . 2.灞忚斀搴楅摵 7澶�. 3.灞忚斀14澶�    4.灞忚斀21澶�   5 .灏佸仠搴楅摵
	private Integer storeNumber;//搴楅摵鍋ュ悍鍒嗗��
	private Date storeCreateTime;//搴楅摵鍒涘缓鏃堕棿
	private String storeAddress;//搴楅摵鎵�鍦ㄥ湴鍧�
	private Integer storeUserNumber;//搴楅摵鐢ㄦ埛璇勫垎
	private Float storePrice;//搴楅摵娉ㄥ唽鎶奸噾
	private Float countPrice;//搴楅摵钀ヤ笟棰�
	private Integer productNumber;//搴楅摵鍟嗗搧閿�閲�
	private Integer phone;//鑱旂郴鐢佃瘽
	private Long idCard;//韬唤璇�
	private String email;//鐢靛瓙閭
	private String owName;//鐢宠鑰呭鍚�
	
	private String shopDesc;//搴楅摵绠�浠�
	
	private Integer shopStatus;//缁忚惀鐘舵��   1.姝ｅ父  . 2.灞忚斀搴楅摵 7澶�. 3.灞忚斀14澶�    4.灞忚斀21澶�   5 .灏佸仠搴楅摵
	
	private String vname;//缁忚惀鐘舵�佸悕绉�
	
	private Integer points;//杩濊璁板垎鍊�
	
	private String storeCategoryName;//经营分类名称
	
	private List<Item> itemList;//商品集合
	
	
	
	
	public String getStoreCategoryName() {
		return storeCategoryName;
	}
	public void setStoreCategoryName(String storeCategoryName) {
		this.storeCategoryName = storeCategoryName;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
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
   
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreOw() {
		return storeOw;
	}
	public void setStoreOw(Long storeOw) {
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
