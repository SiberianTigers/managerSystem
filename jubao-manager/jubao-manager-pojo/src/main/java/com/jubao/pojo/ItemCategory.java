package com.jubao.pojo;

import java.util.Date;

/***
 * 商品分类
 * @author 12146
 *
 */
public class ItemCategory {

  private   Integer   id;
    private  Integer parentId=0;
    private String  name;
    private Integer status;
    private Integer sortOrder;
    private Integer isParent;
    private Date created;
    private Date updated;
    private String cateImg;//1级分类log
    
    
    
	public String getCateImg() {
		return cateImg;
	}
	public void setCateImg(String cateImg) {
		this.cateImg = cateImg;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Integer getIsParent() {
		return isParent;
	}
	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
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
		return "ItemCategory [id=" + id + ", parentId=" + parentId + ", name=" + name + ", status=" + status
				+ ", sortOrder=" + sortOrder + ", isParent=" + isParent + ", created=" + created + ", updated="
				+ updated + ", getId()=" + getId() + ", getParentId()=" + getParentId() + ", getName()=" + getName()
				+ ", getStatus()=" + getStatus() + ", getSortOrder()=" + getSortOrder() + ", getIsParent()="
				+ getIsParent() + ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	} 

	  
    
}
