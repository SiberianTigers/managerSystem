<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="/statics/css/upload/up.css">

<script type="text/javascript" src="/statics/js/jquery-1.12.4.min.js"></script>

</head>






<body>

	<img onclick="getElementById('file').click()" style="cursor:pointer;"
		title="点击添加图片" alt="点击添加图片" height="80px" width="100px"
		src="/statics/images/jia_b.gif">

	<input type="file" multiple="multiple" id="file" name='file'
		style="height:0;width:0;z-index: -1; position: absolute;left: 10px;top: 5px;" />

	<form action="/shop/shop/uploadFile" enctype="multipart/form-data">

		<div class="startCss">
			<ul id="formimg" class="imgContainer">
				<li><img onclick="getElementById('file').click()" /> <input
					type="file" multiple="multiple" id="file" name='file'
					onchange="show(this)" /></li>
				<li><img onclick="getElementById('file').click()" /> <input
					type="file" multiple="multiple" id="file" name='file'
					onchange="show(this)" /></li>

				<li><img onclick="getElementById('file').click()" /> <input
					type="file" multiple="multiple" id="file" name='file'
					onchange="show(this)" /></li>

			</ul>
		</div>


		<!--  <input type="submit" name="上传"> -->
	</form>

</body>
<script type="text/javascript">

	$(".imgContainer [name=file]").on("change", function() {

		alert("--");
		var rd = new FileReader(); //创建文件读取对象
		var files = this.files[0]; //获取file组件中的文件
		rd.readAsDataURL(files); //文件读取装换为base64类型
		rd.onloadend = function(e) {
			//加载完毕之后获取结果赋值给img
			//   document.getElementById("book-pic").src = this.result;           
			$(this).attr("src", this.result);
			var str = "<li><img onclick='getElementById('file').click()' title='点击添加图片' alt='点击添加图片'/><input type='file' multiple='multiple' id='file' name='file' onchange='show(this)'/></li>";
			$("#formimg").append(str);
		}

	});
</script>
</html>
