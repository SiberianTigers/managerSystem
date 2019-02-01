package com.common.utis;

import java.util.List;

/**
 * zTree树 id(节点id),pid（父节点id）,name（节点名称）必须要有， 其他属性可以根据具体需求自行添加
 * 
 * @author LiPing
 * @create 2018-06-08-14:24
 */
public class ZTree {

	private Integer id; // 机构：机构id（orgId）、设备终端：设备id（deviceId）
	private Integer pid;// 机构：机构pid（superId）、设备终端：机构id（orgId）
	private String name;// 机构：机构name（orgName）、设备终端：设备No（deviceNo）
	private boolean open;// 机构：机构树节点打开open|关闭false，设备默认为false
	private Integer isParent;// 是否为父节点，设备默认为false
	private Integer sort; // 排序

	private List<?> children; // 装子节点的容器
	
	public List<?> getChildren() {
		return children;
	}

	public void setChildren(List<?> children) {
		this.children = children;
	}

	private String font;// 标识禁用分类

	private String cateLog = "";
	
	
	public String getCateLog() {
		return cateLog;
	}

	public void setCateLog(String cateLog) {
		this.cateLog = cateLog;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public ZTree() {
		super();
	}

	public ZTree(Integer id, Integer pid, String name, boolean open, Integer isParent) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.open = open;
		this.isParent = isParent;
	}

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

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

}
