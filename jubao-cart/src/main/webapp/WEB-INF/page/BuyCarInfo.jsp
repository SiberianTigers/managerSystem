<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />




<title>我的购物车</title>
</head>
<body>
	<div class="i_bg">

		<!--Begin 第一步：查看购物车 Begin -->
		<div class="content mar_20">
			<table border="0" class="car_tab"
				style="width:1200px; margin-bottom:50px;" cellspacing="0"
				cellpadding="0">
				<tr>
					<td class="car_th" width="490">商品名称</td>
					<td class="car_th" width="140">属性</td>
					<td class="car_th" width="150">购买数量</td>
					<td class="car_th" width="130">单价</td>
					<td class="car_th" width="140">店铺小计</td>
					<td class="car_th" width="150">操作</td>
				</tr>
				<c:forEach items="${cart.cartMap }" var="store">
					<tr>
						<td colspan="6">店铺：${store.value[0].item.storeName }</td>
					</tr>
					<c:forEach items="${store.value }" var="cart">
						<tr>
							<td>

								<div class="c_s_img">
									<img src="${cart.item.image }" width="73" height="73" />
								</div> ${cart.item.title }
							</td>
							<td align="center">${cart.item.itemType}<%-- <br/>${cart.product.type2} --%></td>
							<td align="center">

								<div class="c_num">
									<input type="button" value="" onclick="subQuantity(this)"
										class="car_btn_1" /> <input type="text" value="${cart.num }"
										id="number" pid="${cart.item.id}"
										itemType="${cart.item.itemType}" sid="${cart.item.sid }"
										num="${cart.item.num }" class="car_ipt" /> <input
										type="button" value="" onclick="addQuantity(this)"
										class="car_btn_2" />
								</div>

							</td>
							<td align="center" style="color:#ff4e00;font-size: 14px;">${cart.item.price }</td>
							<td align="center" style="font-size: 14px;">${cart.price}</td>
							<td align="center"><a onclick="dele(this)">删除</a>&nbsp;
								&nbsp;<a href="#">收藏</a></td>
						</tr>
					</c:forEach>
				</c:forEach>
				
				<!-- 如果用户未登录 -->
				<c:if test="${empty sessionScope.session_user}">
					<tr>
						<td style="text-align: center;" colspan="6">您登录后将显示以往购物车中保存的商品信息</td>
					</tr>
				</c:if>
				
				<tr height="70">
					<td colspan="6"
						style="font-family:'Microsoft YaHei'; border-bottom:0;"><label
						class="r_rad"><input type="checkbox" name="clear"
							checked="checked" /></label><label class="r_txt">清空购物车</label> <span
						class="fr" id="sumPrice">商品总价：<b
							style="font-size:22px; color:#ff4e00;">￥${cart.price }</b></span></td>
				</tr>
				<tr valign="top" height="150">
					<td colspan="6" align="right"><a href="http://www.pytiger.cn"><img
							src="${ctx}/statics/images/buy1.gif" /></a>&nbsp; &nbsp; <a
						href="http://localhost:8088/order/order/confirm_order.html"><img
							src="${ctx}/statics/images/buy2.gif" /></a></td>
				</tr>
			</table>

		</div>
		<!--End 第一步：查看购物车 End-->


		<!--Begin 弹出层-删除商品 Begin-->
		<div id="fade" class="black_overlay"></div>
		<div id="MyDiv" class="white_content">
			<div class="white_d">
				<div class="notice_t">
					<span class="fr" style="margin-top:10px; cursor:pointer;"
						onclick="CloseDiv('MyDiv','fade')"><img
						src="${ctx}/statics/images/close.gif" /></span>
				</div>
				<div class="notice_c">

					<table border="0" align="center" style="font-size:16px;"
						cellspacing="0" cellpadding="0">
						<tr valign="top">
							<td>您确定要把该商品移除购物车吗？</td>
						</tr>
						<tr height="50" valign="bottom">
							<td><a onclick="deletex();" class="b_sure">确定</a><a href="#"
								class="b_buy" onclick="CloseDiv('MyDiv','fade')">取消</a></td>
						</tr>
					</table>

				</div>
			</div>
		</div>
		<!--End 弹出层-删除商品 End-->

	</div>

</body>

</html>
