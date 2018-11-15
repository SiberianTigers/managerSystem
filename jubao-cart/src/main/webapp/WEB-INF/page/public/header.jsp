<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="/statics/css/style.css">
<script type="text/javascript" src="/statics/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="/statics/js/jquery-1.11.1.min_044d0927.js"></script>
<script type="text/javascript"
	src="/statics/js/jquery.bxslider_e88acd1b.js"></script>

  <script type="text/javascript" src="/statics/js/jquery-1.8.2.min.js"></script>

<script type="text/javascript" src="/statics/js/menu.js"></script>

<script type="text/javascript" src="/statics/js/select.js"></script>

<script type="text/javascript" src="/statics/js/lrscroll.js"></script>
<!-- 
<script type="text/javascript" src="/statics/js/iban.js"></script>
<script type="text/javascript" src="/statics/js/fban.js"></script>
<script type="text/javascript" src="/statics/js/f_ban.js"></script>
<script type="text/javascript" src="/statics/js/mban.js"></script>
<script type="text/javascript" src="/statics/js/bban.js"></script>
<script type="text/javascript" src="/statics/js/hban.js"></script>
<script type="text/javascript" src="/statics/js/tban.js"></script>
<script type="text/javascript" src="/statics/js/lrscroll_1.js"></script> -->
<!-- 
 <script type="text/javascript" src="/statics/locajs/jquery-1.6.4.js"></script>
 <script type="text/javascript" src="/statics/locajs/jquery_cookie.js"></script>
  <script type="text/javascript" src="/statics/locajs/header.js"></script> -->

<script type="text/javascript">
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
</script>

<!--Begin Header Begin-->
<div class="soubg">
	<div class="sou">
		<span class="fr"> <c:choose>
				<c:when test="${not empty sessionScope.session_user }">
					<span class="fl">欢迎来到聚宝  <span id="userInfo"> <a
							href='http://localhost:8086/login/user/userManager'>${sessionScope.session_user.userCode}</a>&nbsp;<a
							href='http://localhost:8086/login/userlogin/sessionOut'>退出</a>
					</span> </span>
				</c:when>

				<c:otherwise>
					<span class="fl">欢迎来到聚宝 <span id="userInfo"><a
							href="http://localhost:8086/login/userlogin/tologin">登录</a></span>&nbsp;
						<a href="http://localhost:8086/login/userlogin/toRegister"
						style="color:#ff4e00;">注册</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|
					</span>
				</c:otherwise>
			</c:choose> <span class="ss">
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
		<a href="http://localhost:8083"><img src="/statics/images/jbw.png" /></a>
	</div>
	<div class="search">
		<form action="/search/search_product.html" method="get"
			onsubmit="return checkInfo()" id="searchform">
			<input type="hidden" name="pageIndex" value="1" id="pageIndex" /> <input
				type="text" id="queryString" name="queryString" class="s_ipt"
				value="${queryString }" /> <input type="submit" value="搜   索"
				class="s_btn" />
		</form>
		<span class="fl"><a href="#">咖啡</a><a href="#">iphone 6S</a><a
			href="#">新鲜美食</a><a href="#">蛋糕</a><a href="#">日用品</a><a href="#">连衣裙</a></span>
	</div>
	<div class="i_car">
		<div class="car_t">
			购物车 [ <span>${cart.count }</span> ]
		</div>
		<div class="car_bg">
			<!--Begin 购物车未登录 Begin-->
			<div class="un_login">
				还未登录！<a href="Login.html" style="color:#ff4e00;">马上登录</a> 查看购物车！
			</div>
			<!--End 购物车未登录 End-->
			<!--Begin 购物车已登录 Begin-->
			<ul class="cars">
			   <c:forEach items="${cart.itemList }" var="cart">
			    <li>
				<div class='img'>
				<a href='#'><img src='http://119.29.195.240${cart.item.image}' width='58' height='58' /></a></div>
				<div class='name'>
				<a href='#'>${cart.item.title}</a></div>
				<div class='price'><font color='#ff4e00'>${cart.price}</font> ${cart.num}</div>
			   </li>
			 
			   </c:forEach>
					
			</ul>
			<div class="price_sum">
				共计&nbsp; <font color="#ff4e00">￥</font><span>${cart.price }</span>
			</div>
			<div class="price_a">
				<a href="#">去购物车结算</a>
			</div>
			<!--End 购物车已登录 End-->
		</div>
	</div>
</div>

<!--End Header End-->

