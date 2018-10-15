package com.jubao.pojo;

import java.util.Date;

/***
 * 店铺拒绝信息
 * @author 12146
 *
 */
public class ShopRefused {

	  private Integer id;
	  private Integer shopid;
	  private String content;
	  private Date createDate;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "ShopRefused [id=" + id + ", shopid=" + shopid + ", content=" + content + ", createDate=" + createDate
				+ "]";
	}


}
