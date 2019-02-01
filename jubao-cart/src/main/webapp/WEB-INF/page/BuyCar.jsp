<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>我的购物车</title>
</head>
<body>  
<!--Begin Header Begin-->
  <%@include file="public/header.jsp" %>
<!--End Header End--> 


<!--Begin Menu Begin-->

 <div id="searchBar">

  </div>

 <div id="myCart">
        
         <%@include file="BuyCarInfo.jsp" %>
 
 </div>


    
    <!--Begin Footer Begin -->
  
   <%@include file="public/footer.jsp" %> 

    <!--End Footer End -->    

   <!-- 弹出层js -->
  <script type="text/javascript" src="/statics/js/shade.js"></script>
  <script type="text/javascript" src="/statics/js/cart/carts.js"> </script>
  <script type="text/javascript" src="/statics/js/cart/Order.js"> </script>
</body>

</html>
