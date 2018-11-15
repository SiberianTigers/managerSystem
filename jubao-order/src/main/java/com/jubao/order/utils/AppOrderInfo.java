package com.jubao.order.utils;
/**
 * 订单信息
 * @author dell
 *
 */
public class AppOrderInfo {

   private String  payTo; //"AliPay"支付到支付宝、default支付到微信
   private String   appId="XCcgNxBCcrC90F2FF78C3CDE87A0E383";// 开通后获得的appId
   private String   playerId;// 支付玩家唯一标识
   private String   goodsId;//  添加商品的商品Id
   private String   goodsName="";//自定义商品名称
   private double   goodsPrice;// 支付金额(分)）
   private String   qrcodeURL;// 支付收款码URL
   private String   wx="1";
   private String   alipay="1";
   private String   succHide="1";
   private String orderId;
   private String sign;
   
   
   
   
public String getSign() {
	return sign;
}
public void setSign(String sign) {
	this.sign = sign;
}
public String getOrderId() {
	return orderId;
}
public void setOrderId(String orderId) {
	this.orderId = orderId;
}
public String getPayTo() {
	return payTo;
}
public void setPayTo(String payTo) {
	this.payTo = payTo;
}
public String getAppId() {
	return appId;
}
public void setAppId(String appId) {
	this.appId = appId;
}
public String getPlayerId() {
	return playerId;
}
public void setPlayerId(String playerId) {
	this.playerId = playerId;
}
public String getGoodsId() {
	return goodsId;
}
public void setGoodsId(String goodsId) {
	this.goodsId = goodsId;
}
public String getGoodsName() {
	return goodsName;
}
public void setGoodsName(String goodsName) {
	this.goodsName = goodsName;
}


public double getGoodsPrice() {
	return goodsPrice;
}
public void setGoodsPrice(double goodsPrice) {
	this.goodsPrice = goodsPrice;
}
public String getQrcodeURL() {
	return qrcodeURL;
}
public void setQrcodeURL(String qrcodeURL) {
	this.qrcodeURL = qrcodeURL;
}
public String getWx() {
	return wx;
}
public void setWx(String wx) {
	this.wx = wx;
}
public String getAlipay() {
	return alipay;
}
public void setAlipay(String alipay) {
	this.alipay = alipay;
}
public String getSuccHide() {
	return succHide;
}
public void setSuccHide(String succHide) {
	this.succHide = succHide;
}
	
   
}
