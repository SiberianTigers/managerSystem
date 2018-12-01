<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="/statics/bootstrap/bootstrap.min.css">
  </head>
  
  <body>
     
     
     <div style="margin: 0 auto; width:80%;margin-top:10%;text-align: center; ">
       
       <a class="btn btn-success" href="">我要开店</a>&nbsp;&nbsp;&nbsp;
       <a class="btn btn-info" href="http://localhost:8083">返回购物</a>
     
     
     </div>
     
     
    
  </body>
  
  <script type="text/javascript" src="/statics/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/statics/bootstrap/bootstrap.min.js"></script>
</html>
