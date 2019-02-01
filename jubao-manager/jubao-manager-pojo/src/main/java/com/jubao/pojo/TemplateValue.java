package com.jubao.pojo;

import java.util.Date;
import java.util.List;

import com.common.utis.GuiGE;

/***
 * 商品模板值
 * @author 12146
 *
 */
public class TemplateValue {

	  private Long id;
	  private Long pid;
	  private String paramData;
	  private Date createTime;
	  private Date updateTime;

	  private List<GuiGE> ggList;//存放规格
	  
	  private List<GuiGE> productParam;//产品参数
	  
	  
	  
	  
	  
	public List<GuiGE> getProductParam() {
		return productParam;
	}
	public void setProductParam(List<GuiGE> productParam) {
		this.productParam = productParam;
	}
	public List<GuiGE> getGgList() {
		return ggList;
	}
	public void setGgList(List<GuiGE> ggList) {
		this.ggList = ggList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getParamData() {
		return paramData;
	}
	public void setParamData(String paramData) {
		this.paramData = paramData;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "TemplateValue [id=" + id + ", pid=" + pid + ", paramData=" + paramData + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

}
