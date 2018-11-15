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
     <!-- 弹出层js -->
  <script type="text/javascript" src="/statics/js/shade.js"></script>
  <script type="text/javascript" src="/statics/js/cart/carts.js"> </script>
  <script type="text/javascript" src="/statics/js/cart/Order.js"> </script>
    
<title>尤洪</title>
</head>
<body>  
<!--Begin Header Begin-->
  <%@include file="public/header.jsp" %>
<!--End Header End--> 


<!--Begin Menu Begin-->

 <div id="searchBar">
<%--  <%@include file="../../common/searcheBar.jsp" %> --%>
  </div>
<%--   <%@include file="../../common/categoryBar1.jsp" %> --%>
<!--End Menu End--> 

  

 <div id="myCart">
    
         <%@include file="BuyCarInfo.jsp" %>
     
     <%--  <%@include file="BuyCarInfo2.jsp" %> --%>
 </div>


    
    <!--Begin Footer Begin -->
  
   <%@include file="public/footer.jsp" %> 

    <!--End Footer End -->    


</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
