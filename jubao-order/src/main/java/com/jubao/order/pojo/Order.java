package com.jubao.order.pojo;

import java.util.Date;
import java.util.List;



public class Order {
	private Long id;//主键
	private String orderCode;// 订单编码
	private double payment;// 金额
	private Integer paymenttype;// 订单类型
	private double post_fee;// 邮费
	private Integer status;// 订单状态
	private Date createtime;// 订单创建事件
	private Date paymenttime;// 订单付款时间
	private Date consigntime;// 发货时间
	private Date endtime;// 交易完成时间
	private String shippingname;// 物流名称
	private Long shippingcode;// 物流号
	private Integer userid;// 用户id
	private Integer buyerrate;// 是否已评论
	private Integer addressid;// 收获地址id
	private Long shopid;//店铺id
	private List<OrderDetail> orderDetailList=null;
	private Long wid;//合单id
	private String userCode;//用户昵称
	
	
   
	

	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Long getWid() {
		return wid;
	}
	public void setWid(Long wid) {
		this.wid = wid;
	}
	public Long getShopid() {
		return shopid;
	}
	public void setShopid(Long shopid) {
		this.shopid = shopid;
	}
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
   
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public Integer getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(Integer paymenttype) {
		this.paymenttype = paymenttype;
	}
	public double getPost_fee() {
		return post_fee;
	}
	public void setPost_fee(double post_fee) {
		this.post_fee = post_fee;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getPaymenttime() {
		return paymenttime;
	}
	public void setPaymenttime(Date paymenttime) {
		this.paymenttime = paymenttime;
	}
	public Date getConsigntime() {
		return consigntime;
	}
	public void setConsigntime(Date consigntime) {
		this.consigntime = consigntime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getShippingname() {
		return shippingname;
	}
	public void setShippingname(String shippingname) {
		this.shippingname = shippingname;
	}
	public Long getShippingcode() {
		return shippingcode;
	}
	public void setShippingcode(Long shippingcode) {
		this.shippingcode = shippingcode;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getBuyerrate() {
		return buyerrate;
	}
	public void setBuyerrate(Integer buyerrate) {
		this.buyerrate = buyerrate;
	}
	public Integer getAddressid() {
		return addressid;
	}
	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}
	
	
	
}
