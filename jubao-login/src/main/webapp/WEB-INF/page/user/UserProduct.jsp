<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
 var contextPath=${ctx};
</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if IE 6]>
    <script src="js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->
        
  <!--   <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>    
        -->
	
   	<script type="text/javascript" src="${ctx}/statics/js/manager/option.js"></script>
   	 <script type="text/javascript" src="${ctx}/statics/js/common/shade.js"></script>
   
<title>管理中心</title>
</head>
<body>  
<!--Begin Header Begin-->
 <%@include file="shop_header.jsp" %>
        <!--End 所在收货地区 End-->
        
<!--End Header End--> 
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
	 <!--左侧菜单  big --> 
	 <div class="m_content">
	 
         
             <%@include file="leftMaven.jsp" %>

	
	 <!-- 左侧菜单 end -->
	
	  
	   
	   <!-- 右侧分类管理  big -->
	   
	   
	  		<div class="m_right">
            <p></p>
            <div class="mem_tit">用户订单</div>
             
<%--              
             <%@include file="../../common/proudAdd/UserProductOrder.jsp" %> --%>
         
            </div>	  
	  <!-- 右侧分类管理end  -->	       
    </div>  
</div>
	<!--End 用户中心 End--> 
    <!--Begin Footer Begin -->
     <%@include file="footer.jsp" %>
    <!--End Footer End -->    
</body>
<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
