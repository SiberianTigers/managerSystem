<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<!-- <link type="text/css" rel="stylesheet" href="/statics/css/style.css" />      -->
<!--    <script type="text/javascript" src="/statics/js/jquery-1.8.2.min.js"></script> -->
<!--     <script type="text/javascript" src="/statics/js/menu.js"></script>    
 -->
<!-- 	<script type="text/javascript" src="/statics/js/lrscroll_1.js"></script>   
 -->

<script type="text/javascript" src="/statics/js/n_nav.js"></script>

<link rel="stylesheet" type="text/css" href="/statics/css/ShopShow.css" />
<link rel="stylesheet" type="text/css" href="/statics/css/MagicZoom.css" />
<script type="text/javascript" src="/statics/js/MagicZoom.js"></script>

<script type="text/javascript" src="/statics/js/num.js">
	var jq = jQuery.noConflict();
</script>


<title>商品详情</title>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="public/header.jsp" />
	<!-- end -->
      
	<div class="i_bg">
		<div class="postion">
<!-- 			<span class="fl">全部 > 美妆个护 > 香水 > 迪奥 > 迪奥真我香水</span>
 -->		</div>
		<div class="content">

			<div id="tsShopContainer">
				<div id="tsImgS">
					<a href="/statics/images/p_big.jpg" title="Images"
						class="MagicZoom" id="MagicZoom"><img
						src="http://119.29.195.240${Item.geImgList()[0] }" width="390" height="390" /></a>
				</div>
				
				
				<!-- 放大镜 -->
				<div id="tsPicContainer">
					<div id="tsImgSArrL" onclick="tsScrollArrLeft()"></div>
					<div id="tsImgSCon">
						<ul>
						  <input id="sid" value="${Item.sid }" type="hidden"/> <!-- 店鋪id -->
						 
						 <c:forEach items="${Item.geImgList() }" var="item">
						 
						 	<li onclick="showPic(0)" rel="MagicZoom" class="tsSelectImg"><img
								src="http://119.29.195.240${item }" tsImgS="http://119.29.195.240${item }"
								width="79" height="79" /></li>
						 
						 </c:forEach>
						
					
						</ul>
					</div>
					<div id="tsImgSArrR" onclick="tsScrollArrRight()"></div>
				</div>
				
						<!-- 放大镜  end-->
				
				
				
				<img class="MagicZoomLoading" width="16" height="16"
					src="/statics/images/loading.gif" alt="Loading..." />
			</div>

			<div class="pro_des">
				<div class="des_name">
					<p>${Item.title }</p>
					${Item.sellPoint }
				</div>
				<div class="des_price">
					价格：<b>￥${Item.price }</b><br />
				</div>


				<!-- 商品規格 -->
				<c:forEach items="${temp.ggList }" var="gg">
					<div class="des_choice">
						<span class="fl">${gg.k }</span>
						<ul>
						<!-- class="checked"  -->
							<c:forEach items="${gg.getGuige() }" var="g">
								<li  typeItem="${g }" onclick="selectInfo(this)">${g }<div class="ch_img"></div></li>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
				<!-- 商品規格  end-->


				<div class="des_share">
					<div class="d_sh">
						分享
						<div class="d_sh_bg">
							<a href="#"><img src="/statics/images/sh_1.gif" /></a> <a
								href="#"><img src="/statics/images/sh_2.gif" /></a> <a href="#"><img
								src="/statics/images/sh_3.gif" /></a> <a href="#"><img
								src="/statics/images/sh_4.gif" /></a> <a href="#"><img
								src="/statics/images/sh_5.gif" /></a>
						</div>
					</div>
					<div class="d_care">
						<a onclick="ShowDiv('MyDiv','fade')">收藏商品</a>
					</div>
				</div>
				<div class="des_join">
					<div class="j_nums">
						<input type="text" value="1" name="" id="num" class="n_ipt" onkeyup="value=value.replace(/[^1234567890-]+/g,'')"  /> <input
							type="button" value="" onclick="addUpdate(jq(this));"
							class="n_btn_1" /> <input type="button" value=""
							onclick="jianUpdate(jq(this));" class="n_btn_2" />
					</div>
					<span class="fl">
					        <a onclick="addItemCart(${Item.pid})">
					        <img src="/statics/images/j_car.png" />
					        </a>
				    </span>
							&nbsp;
						 <button   type="button" class="btn btn-success">
						   立即购买
						 </button>
				</div>
			</div>

	<!-- 待开发   广告位		<div class="s_brand">
				<div class="s_brand_img">
					<img src="/statics/images/sbrand.jpg" width="188" height="132" />
				</div>
				<div class="s_brand_c">
					<a href="#">进入品牌专区</a>
				</div>
			</div> -->


		</div>
		<div class="content mar_20">
		<!-- 店铺信息展示 -->
			<div class="l_history" id="shopInfo">
			
			
			</div>
		<!-- end -->	
			
			<div class="l_list">
		
				<div class="des_border">
					<div class="des_tit">
						<ul>
							<li class="current"><a href="#p_attribute">商品属性</a></li>
							<li><a href="#p_details">商品详情</a></li>
							<li><a href="#p_comment">商品评论</a></li>
						</ul>
					</div>
					<div class="des_con" id="p_attribute">

                         
                         <!-- 产品参数   -->
 
						<table border="1" align="center"
							style="width:100%; font-family:'宋体'; margin:10px auto;"
							cellspacing="0" cellpadding="0">

							<tr>
								<td colspan="2" style="text-align: center;">产品参数</td>
							</tr>
							<c:forEach items="${temp.productParam }" var="temps">
								<tr>
									<td width="50%">${temps.k }</td>
									<td width="50%" style="text-align: center;">${temps.v }</td>
								</tr>
							</c:forEach>


						</table>

  					 <!-- 产品参数   -->
					</div>
				</div>

            <!-- 商品描述   -->
				<div class="des_border" id="p_details">
					<div class="des_t">商品详情</div>
					<div class="des_con">
				  ${desc.itemdesc }
					</div>
				</div>
            <!-- end  -->
            
            
				<div class="des_border" id="p_comment">
					<div class="des_t">商品评论</div>

					<table border="0" class="jud_tab" cellspacing="0" cellpadding="0">
						<tr>
							<td width="175" class="jud_per">
								<p>80.0%</p>好评度
							</td>
							<td width="300">
								<table border="0" style="width:100%;" cellspacing="0"
									cellpadding="0">
									<tr>
										<td width="90">好评<font color="#999999">（80%）</font></td>
										<td><img src="/statics/images/pl.gif" align="absmiddle" /></td>
									</tr>
									<tr>
										<td>中评<font color="#999999">（20%）</font></td>
										<td><img src="/statics/images/pl.gif" align="absmiddle" /></td>
									</tr>
									<tr>
										<td>差评<font color="#999999">（0%）</font></td>
										<td><img src="/statics/images/pl.gif" align="absmiddle" /></td>
									</tr>
								</table>
							</td>
							<td width="185" class="jud_bg">
								购买过雅诗兰黛第六代特润精华露50ml的顾客，在收到商品才可以对该商品发表评论</td>
							<td class="jud_bg">您可对已购买商品进行评价<br />
							<a href="#"><img src="/statics/images/btn_jud.gif" /></a></td>
						</tr>
					</table>



					<table border="0" class="jud_list"
						style="width:100%; margin-top:30px;" cellspacing="0"
						cellpadding="0">
						<tr valign="top">
							<td width="160"><img src="/statics/images/peo1.jpg"
								width="20" height="20" align="absmiddle" />&nbsp;向死而生</td>
							<td width="180">颜色分类：<font color="#999999">粉色</font> <br />
								型号：<font color="#999999">50ml</font>
							</td>
							<td>产品很好，香味很喜欢，必须给赞。 <br /> <font color="#999999">2015-09-24</font>
							</td>
						</tr>
						<tr valign="top">
							<td width="160"><img src="/statics/images/peo2.jpg"
								width="20" height="20" align="absmiddle" />&nbsp;就是这么想的</td>
							<td width="180">颜色分类：<font color="#999999">粉色</font> <br />
								型号：<font color="#999999">50ml</font>
							</td>
							<td>送朋友，她很喜欢，大爱。 <br /> <font color="#999999">2015-09-24</font>
							</td>
						</tr>
						<tr valign="top">
							<td width="160"><img src="/statics/images/peo3.jpg"
								width="20" height="20" align="absmiddle" />&nbsp;墨镜墨镜</td>
							<td width="180">颜色分类：<font color="#999999">粉色</font> <br />
								型号：<font color="#999999">50ml</font>
							</td>
							<td>大家都说不错<br /> <font color="#999999">2015-09-24</font>
							</td>
						</tr>
						<tr valign="top">
							<td width="160"><img src="/statics/images/peo4.jpg"
								width="20" height="20" align="absmiddle" />&nbsp;那*****洋 <br />
							<font color="#999999">（匿名用户）</font></td>
							<td width="180">颜色分类：<font color="#999999">粉色</font> <br />
								型号：<font color="#999999">50ml</font>
							</td>
							<td>下次还会来买，推荐。<br /> <font color="#999999">2015-09-24</font>
							</td>
						</tr>
					</table>



					<div class="pages">
						<a href="#" class="p_pre">上一页</a><a href="#" class="cur">1</a><a
							href="#">2</a><a href="#">3</a>...<a href="#">20</a><a href="#"
							class="p_pre">下一页</a>
					</div>

				</div>


			</div>
		</div>


		<!--Begin 弹出层-收藏成功 Begin-->
		<div id="fade" class="black_overlay"></div>
		<div id="MyDiv" class="white_content">
			<div class="white_d">
				<div class="notice_t">
					<span class="fr" style="margin-top:10px; cursor:pointer;"
						onclick="CloseDiv('MyDiv','fade')"><img
						src="/statics/images/close.gif" /></span>
				</div>
				<div class="notice_c">

					<table border="0" align="center" style="margin-top:;"
						cellspacing="0" cellpadding="0">
						<tr valign="top">
							<td width="40"><img src="/statics/images/suc.png" /></td>
							<td><span
								style="color:#3e3e3e; font-size:18px; font-weight:bold;">您已成功收藏该商品</span><br />
								<a href="#">查看我的关注 >></a></td>
						</tr>
						<tr height="50" valign="bottom">
							<td>&nbsp;</td>
							<td><a href="#" class="b_sure">确定</a></td>
						</tr>
					</table>

				</div>
			</div>
		</div>
		<!--End 弹出层-收藏成功 End-->


		<!--Begin 弹出层-加入购物车 Begin-->
		<div id="fade1" class="black_overlay"></div>
		<div id="MyDiv1" class="white_content">
			<div class="white_d">
				<div class="notice_t">
					<span class="fr" style="margin-top:10px; cursor:pointer;"
						onclick="CloseDiv_1('MyDiv1','fade1')"><img
						src="/statics/images/close.gif" /></span>
				</div>
				<div class="notice_c">

					<table border="0" align="center" style="margin-top:;"
						cellspacing="0" cellpadding="0">
						<tr valign="top">
							<td width="40"><img src="/statics/images/suc.png" /></td>
							<td><span
								style="color:#3e3e3e; font-size:18px; font-weight:bold;">宝贝已成功添加到购物车</span><br />
								购物车共有1种宝贝（3件） &nbsp; &nbsp; 合计：1120元</td>
						</tr>
						<tr height="50" valign="bottom">
							<td>&nbsp;</td>
							<td><a href="#" class="b_sure">去购物车结算</a><a href="#"
								class="b_buy">继续购物</a></td>
						</tr>
					</table>

				</div>
			</div>
		</div>
		<!--End 弹出层-加入购物车 End-->



		<!--Begin Footer Begin -->
		<jsp:include page="public/footer.jsp" />
		<!--End Footer End -->
	</div>

</body>
<script type="text/javascript" src="/statics//statics/js/lrscroll_1.js"></script>
<script src="/statics/js/ShopShow.js"></script>
<script src="/statics/locajs/product.js"></script>

<script type="text/javascript" src="/statics/js/p_tab.js"></script>

<script type="text/javascript" src="/statics/js/shade.js"></script>

</html>
