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
        
  <!--   <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>    
        
	<script type="text/javascript" src="js/select.js"></script> -->
        
<%--      <link rel="stylesheet" type="text/css" href="${ctx}/statics/css/userShoucuang.css"/>
 --%>
 <title>管理中心</title>
</head>
<body>  
<!--Begin Header Begin-->
        <!--End 所在收货地区 End-->
     <%--  <%@include file="../../common/searcheBar.jsp" %> --%>
<!--End Header End--> 
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
	<div class="m_content">
   		
   		<!-- 判断用户类型 ：普通用户和管理员用户显示的菜单不一样 -->
    	

       <div class="m_right">
            <p></p>
            <div class="mem_tit">我的收藏</div>   
            
            <div class="mem_tit" style="font-size: 12px">
            	<p>全部宝贝</p>
            </div> 
            
  
    <div class="list_c">
            	
                <ul class="cate_list">
                <c:forEach items="${requestScope.pList }" var="plist">
                	<li class="showView">
                    	<div class="img"><span class="types" style="display: none;"><input  type="image" src="${ctx }/statics/images/close.gif"/></span><a href="Product?action=ProducInfo&Producid=${plist.id }"><img src="${ctx}/files/${plist.fileName}" width="180" height="180"/></a></div>
                        <div class="price">
                            <font>￥<span>${plist.price }</span></font> &nbsp; 26R
                        </div>
                        <div class="name"><a href="#">${plist.name }</a></div>
                      <%--   <div class="carbg">
                            <a href="javascript:void(0)" class="j_car" onclick="addCarByParam('${plist.id}',1)">加入购物车</a>
                        </div> --%>
                    </li>
                 </c:forEach>
                </ul>
                
                
           <!-- 分页部分  big-->  
            <%--     	  <%@include file="../../common/pages.jsp" %> --%>
                 <!-- 分页部分  eng--> 
                
                
                
            </div>
        </div>
            
            
            
            
            
            
            
            
            
            
            
            
            
            <!-- 用户地址信息 -->
          <div id="userAddressInfo">
<%--                  <%@include file="userMenu/userAddress.jsp" %>
 --%>          </div>
			
        </div>
        
        
        
        
        
    </div>
	<!--End 用户中心 End--> 
    <!--Begin Footer Begin -->
 <%@include file="footer.jsp" %>
    <!--End Footer End -->    
</div>
     <script src="${ctx}/statics/js/cart/address.js" type="text/javascript"></script>
</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
