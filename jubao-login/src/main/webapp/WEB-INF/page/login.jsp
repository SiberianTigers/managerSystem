<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.12.4.min.js"></script>
<link type="text/css" rel="stylesheet"
	href="${ctx}/statics/css/style.css" />
<title>用户登录</title>
</head>
<body>
	<!--Begin Login Begin-->
	<div class="log_bg">
		<div class="top">
			<div class="logo">
				<a href="http://localhost:8083"><img
					src="${ctx}/statics/images/logs.png" /></a>
			</div>
		</div>
		<div class="login" style="background-image:url('https://img.alicdn.com/tfs/TB1K0.ImwHqK1RjSZJnXXbNLpXa-1190-600.jpg');">
			<div class="login-form">
			<p style="color:red;" id="info">&nbsp;<p/>
			      <!-- 回调的url -->
			      
				 <input type="hidden"  value="${callBackUrl }" id="callBackUrl"/>
				<h2>
					密码登录<a href="${ctx}/login/userlogin/toRegister" style="color:#ff4e00;">账号注册</a>
				</h2>
				
				<form action="" method="post" id="loginForm">
					<div class="item">
						<label class="nick left"></label> <input type="text" name="userCodeOrPhone"  value="${userCode }"
							id="username" placeholder="用户名/已验证手机" />
					</div>
					<div class="item">
						<label class="psw left"></label> <input type="password" name="password" value="${password }"
							id="password" />
					</div>
					<div class="item2">
						<input type="checkbox" name="auto" id="auto" ${auto }/> <label for="auto">记住密码</label>
						<a href="">忘记密码?</a>
					</div>
					<a href="javascript:void(0)" class="login-btn" onclick="login()">登
						录</a>
				</form>
			</div>
		</div>
		
	<!--Begin Footer Begin-->
	<footer class="footer">
       <jsp:include page="public/footerx.jsp" />
    </footer>
	<!--End Footer End -->
	</div>
<script type="text/javascript" src="${ctx}/statics/js/login/login.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/shade.js"></script>
</body>
</html>
