package com.jubao.pojo;

import java.util.Date;

/***
 * ÉÌÆ·ÃèÊö
 * @author 12146
 *
 */
public class Product_desc {

	 private Long itemid;
	  private String itemdesc;
	  private Date created;
	  private Date updated;

	
	public Long getItemid() {
		return itemid;
	}
	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}
	public String getItemdesc() {
		return itemdesc;
	}
	public void setItemdesc(String itemdesc) {
		this.itemdesc = itemdesc;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	@Override
	public String toString() {
		return "Product_desc [itemid=" + itemid + ", itemdesc=" + itemdesc + ", created=" + created + ", updated="
				+ updated + ", getItemid()=" + getItemid() + ", getItemdesc()=" + getItemdesc() + ", getCreated()="
				+ getCreated() + ", getUpdated()=" + getUpdated() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	  
	
}
