package com.jubao.order.pojo.vo;

import com.jubao.order.pojo.Order;

public class OrderCustom extends Order {
	
	private String sf;// 省份
	private String sq;//市区
	private String qy;//区域
	private String addressInfo;//详细地址
	private String remark;//收获人
	public String getSf() {
		return sf;
	}
	public void setSf(String sf) {
		this.sf = sf;
	}
	public String getSq() {
		return sq;
	}
	public void setSq(String sq) {
		this.sq = sq;
	}
	public String getQy() {
		return qy;
	}
	public void setQy(String qy) {
		this.qy = qy;
	}
	public String getAddressInfo() {
		return addressInfo;
	}
	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
