package com.common.utis;

import java.util.List;

public class ZtreeUtis {

	private Integer id; // 机构：机构id（orgId）、设备终端：设备id（deviceId）
	private Integer pid;// 机构：机构pid（superId）、设备终端：机构id（orgId）
	private String name;// 机构：机构name（orgName）、设备终端：设备No（deviceNo）
	private boolean open=false;// 机构：机构树节点打开open|关闭false，设备默认为false
	private boolean isParent;// 是否为父节点，设备默认为false
	private Integer sort; // 排序

	private List<?> children; // 装子节点

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(int isParent) {
		if(isParent == 0){
			this.isParent = true;		
		}else{
			this.isParent = false;
		}
	
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<?> getChildren() {
		return children;
	}

	public void setChildren(List<?> children) {
		this.children = children;
	}
	 
}
