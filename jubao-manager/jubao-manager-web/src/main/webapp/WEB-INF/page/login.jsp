<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/statics/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/statics/assets/css/font-awesome.min.css" />
<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
<link rel="stylesheet" href="/statics/assets/css/ace.min.css" />
<link rel="stylesheet" href="/statics/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="/statics/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="/statics/css/style.css" />
<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->
<script src="/statics/assets/js/ace-extra.min.js"></script>
<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
<script src="/statics/js/jquery-1.9.1.min.js"></script>
<script src="/statics/assets/layer/layer.js" type="text/javascript"></script>
<title>登录</title>
</head>

<body class="login-layout Reg_log_style">
	<div class="logintop">
		<span>欢迎后台管理界面平台</span>
		<ul>
			<li><a href="#">返回首页</a></li>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>
	<div class="loginbody">
		<div class="login-container">
			<div class="center">
				<img src="/statics/images/logo1.png" />
			</div>

			<div class="space-6"></div>

			<div class="position-relative">
				<div id="login-box" class="login-box widget-box no-border visible">
					<div class="widget-body">
						<div class="widget-main">
							<h4 class="header blue lighter bigger">
								<i class="icon-coffee green"></i> 管理员登录
							</h4>

							<div class="login_icon">
								<img src="/statics/images/login.png" />
							</div>

							<form id="loginfroms" method="post" action="/loginvalidate">
								<fieldset>
									<ul>
									<input type="hidden" name="name" class="name"/>
									<input type="hidden" name="pwd" class="pwd"/>
										<li class="frame_style form_error"><label
											class="user_icon"></label><input name="用户名" type="text"
											id="name" /><i>用户名</i></li>
										<li class="frame_style form_error"><label
											class="password_icon"></label><input name="密码"
											type="password" id="pwd" /><i>密码</i></li>
										<li class="frame_style form_error"><label
											class="Codes_icon"></label><input name="验证码" type="text"
											id="Codes_text" /><i>验证码</i>
											<div class="Codes_region"></div></li>

									</ul>
									<div class="space"></div>

									<div class="clearfix">
										<label class="inline"> <input type="checkbox"
											class="ace"> <span class="lbl">保存密码</span>
										</label> <input type="submit"
											class="width-35 pull-right btn btn-sm btn-primary"
											id="login_btn"> <i class="icon-key"></i> 登录 </input>
									</div>

									<div class="space-4"></div>
								</fieldset>
							</form>

							<div class="social-or-login center">
								<span class="bigger-110">通知</span>
							</div>

							<div class="social-login center">本网站系统不再对IE8以下浏览器支持，请见谅。</div>
						</div>
						<!-- /widget-main -->

						<div class="toolbar clearfix"></div>
					</div>
					<!-- /widget-body -->
				</div>
				<!-- /login-box -->
			</div>
			<!-- /position-relative -->
		</div>
	</div>
	<div class="loginbm">
		版权所有 2016 <a href="">南京思美软件系统有限公司</a>
	</div>
	<strong></strong>
</body>
</html>
<script>
	$('#login_btn').on('click', function() {
		$(".name").val($("#name").val());
		$(".pwd").val($("#pwd").val());
		var num = 0;
		var str = "";
		$("input[type$='text'],input[type$='password']").each(function(n) {
			if ($(this).val() == "") {

				layer.alert(str += "" + $(this).attr("name") + "不能为空！\r\n", {
					title : '提示框',
					icon : 0,
				});
				num++;
				return false;
			}
		});

		if (num > 0) {
			return false;
		} else {
// 			alert("=====");
			// 	  layer.alert('登录成功！',{
			// 	            title: '提示框',				
			// 	   icon:1,		
			// 	  });
			// 	        location.href="index.html";
			// 	   layer.close(index);	 
			// 	   alert("进入方法");
			         $("#loginfroms").submit();
		}

	});

	$(document).ready(function() {
		$("input[type='text'],input[type='password']").blur(function() {
			var $el = $(this);
			var $parent = $el.parent();
			$parent.attr('class', 'frame_style').removeClass(' form_error');
			if ($el.val() == '') {
				$parent.attr('class', 'frame_style').addClass(' form_error');
			}
		});
		$("input[type='text'],input[type='password']").focus(function() {
			var $el = $(this);
			var $parent = $el.parent();
			$parent.attr('class', 'frame_style').removeClass(' form_errors');
			if ($el.val() == '') {
				$parent.attr('class', 'frame_style').addClass(' form_errors');
			} else {
				$parent.attr('class', 'frame_style').removeClass(' form_errors');
			}
		});
	})
</script>

</html>
