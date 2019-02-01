package com.common.pojo;

import java.util.List;

/***
 * 商品分类工具类
 * @author 12146
 *
 */
public class ItemCate {

//转换为json数据时使用 的别名做key

 private Integer id;

 private String url;
 

 private String name;
 
 private String cateImg;

 private List<?> item;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public List<?> getItem() {
	return item;
}

public void setItem(List<?> item) {
	this.item = item;
}

public String getCateImg() {
	return cateImg;
}

public void setCateImg(String cateImg) {
	this.cateImg = cateImg;
}
	
 
 
}
