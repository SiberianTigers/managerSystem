<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Testtree.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet"
	href="/statics/Widget/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">

<script src="/statics/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="/statics/Widget/zTree/js/jquery.ztree.all-3.5.min.js"></script>

</head>
<script type="text/javascript">

	var zTreeObj;
	// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	var setting = {
		async : {
			enable : true,
			url : "/itemCategory/loadItemCategory",
			autoParam : [ "id"],
			dataFilter : filter
		}
	};
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	$(document).ready(function() {
		$.ajax({
			"type" : "POST", //请求类型
			"url" : "/itemCategory/loadItemCategory",
			"data" : {},
			"datatype" : "JSON", //请求返回的数据类型
			"success" : function(result) { //返回请求结果 	
				zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, result);
			},
			error : function() {
				alert("加载失败咯");
			}
		});
	});
</script>
</head>

<BODY>

	<div>
		<ul id="treeDemo" class="ztree">

		</ul>
	</div>


</body>
</html>
