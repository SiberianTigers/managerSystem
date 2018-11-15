<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="/statics/bootstrap/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/statics/css/style.css">
<script type="text/javascript" src="/statics/js/jquery-1.12.4.min.js"></script>
<link type="text/css" rel="stylesheet"
	href="${ctx}/statics/css/style_add.css" />
<!-- <script type="text/javascript">
	//搜索非空验证
	function checkInfo() {
		var queryString = $("#queryString").val();
		if (queryString == "") {
			$("#queryString").focus();
			$("#queryString").css({
				"background-color" : "#9999FF",
				"opacity" : "0.3"
			});
			return false;
		}
		return true;
	}
</script> -->

<!--Begin Header Begin-->
<div class="soubg">
	<div class="sou">
		<span class="fr"> <span class="fl">欢迎来到聚宝 <span
				id="userInfo"><a
					href="http://localhost:8086/login/user/userManager">${sessionScope.user.userCode }</a></span>&nbsp;&nbsp;|&nbsp;<a href="#">我的收藏</a>&nbsp;|
		</span> <span class="ss">
				<div class="ss_list">
					<a href="#">收藏夹</a>
					<div class="ss_list_bg">
						<div class="s_city_t"></div>
						<div class="ss_list_c">
							<ul>
								<li><a href="#">我的收藏夹</a></li>
								<li><a href="#">我的收藏夹</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="ss_list">
					<a href="#">客户服务</a>
					<div class="ss_list_bg">
						<div class="s_city_t"></div>
						<div class="ss_list_c">
							<ul>
								<li><a href="http://localhost:8085/shop/shop/toShop.html">卖家中心</a></li>
							</ul>

						</div>
					</div>
				</div>
				<div class="ss_list">
					<a href="#">网站导航</a>
					<div class="ss_list_bg">
						<div class="s_city_t"></div>
						<div class="ss_list_c">
							<ul>
								<li><a href="#">网站导航</a></li>
								<li><a href="#">网站导航</a></li>
							</ul>
						</div>
					</div>
				</div>
		</span> <span class="fl">|&nbsp;关注我们：</span> <span class="s_sh"><a
				href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span> <span
			class="fr">|&nbsp;<a href="#">手机版&nbsp;<img
					src="/statics/images/s_tel.png" align="absmiddle" /></a></span>
		</span>
	</div>
</div>

<div class="top">
	<div class="logo">
		<a href="http://localhost:8083"><img src="/statics/images/jbw.png"/></a>
	</div>
	<div class="search" style="float: right;">
	     <table style="width: 800px">
	        <tr ><td>查看购物车</td><td>确认订单</td><td>选择支付</td><td>确认收获</td><td>评价</td></tr>
	       
	       <tr>
	       <td colspan="6"> <div class="progress">
			<div class="progress-bar progress-bar-success" style="width: 30%">
<!-- 			 <div style="width:20px;height:20px; border-radius:50%;border: 2px solid red;margin-left: 10px"></div>
 --><!-- 			 			 <div style="width:20px;height:20px; border-radius:50%;border: 2px solid red;margin-right: -50px;"></div>			 
 -->		   </div>
		</td>
		</tr>
	     </table>

	</div>

</div>

<!--End Header End-->

