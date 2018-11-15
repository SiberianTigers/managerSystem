<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="css/style.css" />
<!--[if IE 6]>
    <script src="js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->
<title>商品搜索</title>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="public/header.jsp" />
	<!-- end -->
	<div class="i_bg">
		<div class="postion">
			<span class="fl">全部 > 美妆个护 > 香水 > </span> <span class="n_ch">
				<span class="fl">品牌：<font>香奈儿</font></span> <a href="#"><img
					src="/statics/images/s_close.gif" /></a>
			</span>
		</div>
		<!--Begin 筛选条件 Begin-->
		<div class="content mar_10">
			<table border="0" class="choice"
				style="width:100%; font-family:'宋体'; margin:0 auto;" cellspacing="0"
				cellpadding="0">
				<tr valign="top">
					<td width="70">&nbsp; 品牌：</td>
					<td class="td_a"><a href="#" class="now">香奈儿（Chanel）</a><a
						href="#">迪奥（Dior）</a><a href="#">范思哲（VERSACE）</a><a href="#">菲拉格慕（Ferragamo）</a><a
						href="#">兰蔻（LANCOME）</a><a href="#">爱马仕（HERMES）</a><a href="#">卡文克莱（Calvin
							Klein）</a><a href="#">古驰（GUCCI）</a><a href="#">宝格丽（BVLGARI）</a><a
						href="#">阿迪达斯（Adidas）</a><a href="#">卡尔文·克莱恩（CK）</a><a href="#">凌仕（LYNX）</a><a
						href="#">大卫杜夫（Davidoff）</a><a href="#">安娜苏（Anna sui）</a><a
						href="#">阿玛尼（ARMANI）</a><a href="#">娇兰（Guerlain）</a></td>
				</tr>
				<tr valign="top">
					<td>&nbsp; 价格：</td>
				<%-- 	<td class="td_a">
	                <c:forEach items="${SearchResult.priceItem()}" var="price">
	                 <a href="#">${price }</a>
	                </c:forEach> 
					</td> --%>
			        <td class="td_a"><a href="#">0-199</a><a href="#" class="now">200-399</a><a href="#">400-599</a><a href="#">600-899</a><a href="#">900-1299</a><a href="#">1300-1399</a><a href="#">1400以上</a>
				        
				</tr>
				<tr>
					<td>&nbsp; 类型：</td>
					<td class="td_a"><a href="#">女士香水</a><a href="#">男士香水</a><a
						href="#">Q版香水</a><a href="#">组合套装</a><a href="#">香体走珠</a><a
						href="#">其它</a></td>
				</tr>
				<tr>
					<td>&nbsp; 香型：</td>
					<td class="td_a"><a href="#">浓香水</a><a href="#">香精Parfum香水</a><a
						href="#">淡香精EDP淡香水</a><a href="#">香露EDT</a><a href="#">古龙水</a><a
						href="#">其它</a></td>
				</tr>
			</table>
		</div>
		<!--End 筛选条件 End-->

		<div class="content mar_20">
			<!--     <div class="l_list"> -->
			<div class="list_t">
				<span class="fl list_or"> <a href="#" class="now">默认</a> <a
					href="#"> <span class="fl">销量</span> <span class="i_up">销量从低到高显示</span>
						<span class="i_down">销量从高到低显示</span>
				</a> <a href="#"> <span class="fl">价格</span> <span class="i_up">价格从低到高显示</span>
						<span class="i_down">价格从高到低显示</span>
				</a> <a href="#">新品</a>
				</span> <span class="fr">共发现${SearchResult.recordCount }件</span>
			</div>
			<div class="list_c">

				<ul class="cate_list">
					<c:forEach items="${SearchResult.itemList }" var="item">
						<li>
							<div class="img">
								<a href="/product/product_details.html?pid=${item.pid }"><img src="http://119.29.195.240${item.geImgList()[0] }" width="210"
									height="185" /></a>
							</div>
							<div class="price">
								<font>￥<span>${item.price }</span></font> &nbsp;
							</div>
							<div class="name">
								<a href="#">${item.title }</a>
							</div>
							<div class="name">
								<a href="#">${item.storeName }</a>
							</div>
							 
							<!-- <div class="carbg">
								<a href="#" class="j_car">加入购物车</a>
							</div> -->
						</li>
					</c:forEach>
				</ul>

				<div class="pages">
					<c:if test="${SearchResult.curPage>1 }">
						<a href="javaScript:void(0)" class="p_pre"
							onclick="pageToIndex(document.forms[0],${SearchResult.curPage-1})">上一页</a>
					</c:if>

					<c:forEach items="${SearchResult.pageUtil()}" var="page">
						<c:choose>
							<c:when test="${page == SearchResult.curPage }">
								<a href="javaScript:void(0)"
									onclick="pageToIndex(document.forms[0],${page})" class="cur">${page}</a>
							</c:when>
							<c:otherwise>
								<a href="javaScript:void(0)"
									onclick="pageToIndex(document.forms[0],${page})">${page}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<c:if test="${SearchResult.curPage < SearchResult.pageCount}">
						<a href="javaScript:void(0)"
							onclick="pageToIndex(document.forms[0],${SearchResult.curPage+1})"
							class="p_pre">下一页</a>
					</c:if>
				</div>



			</div>
			<!--   </div>
 -->
		</div>

		<!--Begin Footer Begin -->
		<jsp:include page="public/footer.jsp" />
		<!--End Footer End -->
		<script type="text/javascript" src="/statics/locajs/CategoryList.js"></script>
	</div>

</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
