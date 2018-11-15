<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if IE 6]>
    <script src="js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->
    
    
 
<title>尤洪</title>
</head>
<body>  
<!--Begin Header Begin-->
<%--   <%@include file="/pre/loginHeader/Header.jsp" %> --%>
<!--End Header End--> 


<!--Begin Menu Begin-->
<%--  <%@include file="../../common/searcheBar.jsp" %>
  <%@include file="../../common/categoryBar1.jsp" %> --%>
<!--End Menu End--> 


<div class="i_bg">  
<%--     <div class="content mar_20">
    	<img src="${ctx}/statics/images/img1.jpg" />        
    </div> --%>
    
    <!--Begin 第一步：查看购物车 Begin -->
    <div class="content mar_20">
    	<table border="0" class="car_tab" style="width:1200px; margin-bottom:50px;" cellspacing="0" cellpadding="0">
          <tr>
            <td class="car_th" width="490">商品名称</td>
            <td class="car_th" width="140">属性</td>
            <td class="car_th" width="150">购买数量</td>
            <td class="car_th" width="130">单价</td>
            <td class="car_th" width="140">返还积分</td>
            <td class="car_th" width="150">操作</td>
          </tr>
      <c:forEach items="${cart.itemList }" var="cart">  
          <tr>
            <td>
            	<div class="c_s_img"><img src="http://119.29.195.240${cart.item.image }" width="73" height="73" /></div>
                ${cart.item.title }
            </td>
            <td align="center">${cart.itemType}<%-- <br/>${cart.product.type2} --%></td>
            <td align="center">
            	<div class="c_num">
                    <input type="button" value="" onclick="subQuantity(jq(this),'${cart.item.id}','${cart.itemType}');" class="car_btn_1" />
                	<input type="text" value="${cart.num }" id="number" class="car_ipt" />  
                    <input type="button" value=""  onclick="addQuantity(jq(this),'${cart.item.id}','${cart.item.num}','${cart.itemType}');"  class="car_btn_2"/>
                </div>
            </td>
            <td align="center" style="color:#ff4e00;">${cart.item.price }</td>
            <td align="center">26R</td>
            <td align="center"><a onclick="dele(${cart.item.id},'${cart.itemType }',jq(this))" >删除</a>&nbsp; &nbsp;<a href="#">收藏</a></td>
          </tr>
        </c:forEach>  
        
          <tr height="70">
          	<td colspan="6" style="font-family:'Microsoft YaHei'; border-bottom:0;">
            	<label class="r_rad"><input type="checkbox" name="clear" checked="checked" /></label><label class="r_txt">清空购物车</label>
                <span class="fr" id="sumPrice">商品总价：<b style="font-size:22px; color:#ff4e00;">￥${cart.price }</b></span>
            </td>
          </tr>
          <tr valign="top" height="150">
          	<td colspan="6" align="right">
            	<a href="${ctx}/pre/Index.jsp"><img src="${ctx}/statics/images/buy1.gif" /></a>&nbsp; &nbsp; <a  href="http://localhost:8088/order/order/confirm_order.html"><img src="${ctx}/statics/images/buy2.gif" /></a>
            </td>
          </tr>
        </table>
        
    </div>
	<!--End 第一步：查看购物车 End--> 
    
    
    <!--Begin 弹出层-删除商品 Begin-->
    <div id="fade" class="black_overlay"></div>
    <div id="MyDiv" class="white_content">             
        <div class="white_d">
            <div class="notice_t">
                <span class="fr" style="margin-top:10px; cursor:pointer;" onclick="CloseDiv('MyDiv','fade')"><img src="${ctx}/statics/images/close.gif" /></span>
            </div>
            <div class="notice_c">
           		
                <table border="0" align="center" style="font-size:16px;" cellspacing="0" cellpadding="0">
                  <tr valign="top">
                    <td>您确定要把该商品移除购物车吗？</td>
                  </tr>
                  <tr height="50" valign="bottom">
                    <td><a  onclick="deletex();" class="b_sure">确定</a><a href="#" class="b_buy" onclick="CloseDiv('MyDiv','fade')">取消</a></td>
                  </tr>
                </table>
                    
            </div>
        </div>
    </div>    
    <!--End 弹出层-删除商品 End-->
    
    
    
    <!--Begin Footer Begin -->
  
 <%--   <%@include file="/pre/loginHeader/footerx.jsp" %>  --%>
    <!--End Footer End -->    
</div>

</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
