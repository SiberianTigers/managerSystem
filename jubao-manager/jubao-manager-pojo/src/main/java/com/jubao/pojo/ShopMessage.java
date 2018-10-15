package com.jubao.pojo;

import java.util.Date;

/***
 * 店铺私信
 * @author 12146
 *
 */
public class ShopMessage {

	private Integer id;
	 private Integer shopid;
	 private String topic;
	  private Integer managerid;
	  private String privateInfo;
	  private Date sendDate;
	  private Integer status;
	  
	  private String array;
	  
	  
	  
	  
	public String getArray() {
		return array;
	}
	public void setArray(String array) {
		this.array = array;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getShopid() {
		return shopid;
	}
	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Integer getManagerid() {
		return managerid;
	}
	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}
	public String getPrivateInfo() {
		return privateInfo;
	}
	public void setPrivateInfo(String privateInfo) {
		this.privateInfo = privateInfo;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ShopMessage [id=" + id + ", shopid=" + shopid + ", topic=" + topic + ", managerid=" + managerid
				+ ", privateInfo=" + privateInfo + ", sendDate=" + sendDate + ", status=" + status + ", array=" + array
				+ "]";
	}

	
	
}
