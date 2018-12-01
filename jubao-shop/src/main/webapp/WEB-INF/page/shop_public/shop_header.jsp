<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--  <link  rel="stylesheet" type="text/css"   href="/statics/css/upload/globle.css" />  -->
<link rel="stylesheet" type="text/css"
	href="/statics/bootstrap/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/statics/css/style.css">
<script type="text/javascript" src="/statics/js/jquery.min.js"></script>
<script type="text/javascript" src="/statics/bootstrap/bootstrap.min.js"></script>

<link href="/statics/kindeditor-4.1.10/themes/default/default.css" rel="stylesheet">			

<script type="text/javascript" src="/statics/js/common.js"></script>

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
		<span class="fr"> <span class="fl">你好 ,<a
				href="http://localhost:8086/login/user/userManager">${user.userCode }</a>&nbsp;&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|
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
								<li><a href="/shop/toShop.html">卖家中心</a></li>
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
		<a href="http://localhost:8083"><img
			src="/statics/images/shop_log.png" /></a>
	</div>
	
	<!-- 搜索框 -->
	<div class="search">

		<form action="/search/search_product.html" method="get"
			onsubmit="return checkInfo()" id="searchform">
			<div class="col-lg-10">
				<div class="input-group">
					<input type="hidden" name="pageIndex" value="1" id="pageIndex" /> <input
						type="text" class="form-control" id="queryString"
						name="queryString" value="${queryString }" placeholder="">
					<span class="input-group-btn"><input
						class="btn  btn-danger" type="submit" value="搜  索">
					</span>
				</div>
				<!-- /input-group -->
			</div>
			<!-- /.col-lg-6 -->
		</form>
		<span class="fl"> <a href="#">iphone 6S</a><a href="#">新鲜美食</a><a
			href="#">蛋糕</a><a href="#">日用品</a><a href="#">连衣裙</a></span>
	</div>
	<!-- 搜索框  end  -->

</div>

<!--End Header End-->

