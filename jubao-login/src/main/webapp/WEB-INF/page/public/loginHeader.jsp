<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script type="text/javascript">
 var contextPath="${ctx}";
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>My JSP 'Header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link type="text/css" rel="stylesheet" href="${ctx}/statics/css/style.css" />
  </head>
  
  <body>
  
  <!-- 弹出层   bigen -->
  <div id="fade1" class="black_overlay"></div>
    <div id="MyDiv1" class="white_content">             
        <div class="white_d">
            <div class="notice_t">
                <span class="fr" style="margin-top:10px; cursor:pointer;" onclick="CloseDiv_1('MyDiv1','fade1')"><img src="${ctx}/statics/images/close.gif" /></span>
            </div>
            <div class="notice_c">
           		
                <table border="0" align="center" style="margin-top:;" cellspacing="0" cellpadding="0">
                  <tr valign="top">
                    <td width="40"><img src="${ctx}/statics/images/suc.png" /></td>
                    <td>
                    	<span id="showMessage" style="color:#3e3e3e; font-size:18px; font-weight:bold;">操作成功</span><br />
                    	
                    </td>
                  </tr>
                </table>
                    
            </div>
        </div>
    </div>    
    <!-- 弹出层 end -->
    <script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.2.min.js"></script>

  </body>
</html>
