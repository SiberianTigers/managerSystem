<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<link type="text/css" rel="stylesheet"
	href="${ctx}/statics/css/address.css" />
<script type="text/javascript" src="/statics/js/cart/Order.js"> </script>


<title>确认订单信息</title>
</head>
<body>
	<!--Begin Header Begin-->
	<jsp:include page="public/header.jsp" />
	<!--End Header End-->
	<div class="content mar_20" style="width: 800px;">
		<%-- <img src="${ctx}/statics/images/img2.jpg" /> --%>

	</div>


	<div class="two_t">选择收货地址</div>
	<ul class="pay" id="address_check">
		<c:forEach items="${address }" var="ress">
			<c:choose>
				<c:when test="${ress.isdefault==1 }">
					<li class="checked" onclick="addressAddClass();"><input
						type="hidden" value="${ress.id }" /> <span>${ress.sfName }-${ress.sqName }-${ress.qxName }<b>&nbsp;(${ress.remark }&nbsp;收)</b></span>
						<p>${ress.addressInfo }</p>
						<div class="ch_img"></div></li>
				</c:when>

				<c:otherwise>
					<li onclick="addressAddClass();"><input type="hidden"
						value="${ress.id }" /><span>${ress.sfName }-${ress.sqName }-${ress.qxName }<b>&nbsp;(${ress.remark }&nbsp;收)</b></span>
						<p>${ress.addressInfo }</p>
						<div class="ch_img"></div></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>




	<!--Begin 第二步：确认订单信息 Begin -->
	<!-- 		<div class="content mar_20"> -->
	<!-- <div class="two_bg"> -->
	<div class="two_t">
		<span class="fr"><a href="#">修改</a></span>确认订单信息
	</div>
	<table border="0" class="car_tab" style="width:1200px;" cellspacing="0"
		cellpadding="0">
		<tr>
			<td class="car_th" width="300">商品名称</td>
			<td class="car_th" width="140">属性</td>
			<td class="car_th" width="150">购买数量</td>
			<td class="car_th" width="130">单价</td>
			<td class="car_th" width="130">小计</td>
		</tr>
		<c:forEach items="${cart.storeList }" var="store">
			<tr>
				<td colspan="6">店铺：${store.name}</td>
			</tr>
			<c:forEach items="${store.itemList }" var="cart">
				<tr>
					<td>
						<div class="c_s_img">
							<img src="http://119.29.195.240${cart.item.image }" width="73"
								height="73" />
						</div> 店铺:${cart.item.storeName }<br /> ${cart.item.title } <br />
					</td>
					<td align="center">${cart.itemType}</td>
					<td align="center">${cart.num }</td>
					<td align="center">${cart.item.price }</td>
					<td align="center" style="color:#ff4e00;">${cart.price }</td>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>



	<hr />




	<table border="0" style="width:1200px; margin-top:20px;"
		cellspacing="0" cellpadding="0">
		<tr>
			<td align="right">该订单完成后，您将获得 <font color="#ff4e00">${cart.price }</font>
				积分 ，以及价值 <font color="#ff4e00">￥0.00</font> 的红包 <br /> 商品总价: <font
				color="#ff4e00">￥${cart.price }</font> + 配送费用: <font color="#ff4e00">￥15.00</font>
			</td>
		</tr>
		<tr height="70">
			<td align="right"><b style="font-size:14px;">应付款金额：<span
					style="font-size:22px; color:#ff4e00;">￥${cart.price+15 }</span></b></td>
		</tr>
		<tr height="70">
			<%--                 <td align="right"><a href="${ctx}/pay-page/ddpay.jsp?goodsPrice=100&&goodsName=好东西&&appId=XCcgNxBCcrC90F2FF78C3CDE87A0E383&&goodsId=1&&wx=1&&alipay=1&&succHide=1&&playerId=1"><img src="${ctx}/statics/images/btn_sure.gif" /></a></td>
${ctx}/pay-page/ddpay.jsp
 --%>
			<form action="/order/order/create_Order" method="post"
				id="createOrderForm">
				<input type="hidden" name="address" id="address" />
			</form>

			<td align="right"><a href="javascript:void(0)"
				onclick="createOrder()"> <img
					src="${ctx}/statics/images/btn_sure.gif" /></a></td>
		</tr>
	</table>

	<!--End 第二步：确认订单信息 End-->


	<!--Begin Footer Begin -->
	<jsp:include page="public/footer.jsp" />
	<!--End Footer End -->

</body>
<script type="text/javascript" src="/statics/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/shade.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/cart/carts.js"> </script>
</html>
