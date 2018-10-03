<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!-- <link href="/statics/assets/css/bootstrap.min.css" rel="stylesheet" />
 -->
 
 <link rel="stylesheet" href="/statics/css/style.css" />

<link href="/statics/assets/css/codemirror.css" rel="stylesheet">

 <link rel="stylesheet" href="/statics/assets/css/ace.min.css" />
<link rel="stylesheet"
	href="/statics/Widget/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">

<link rel="stylesheet" href="/statics/assets/css/font-awesome.min.css" />





<script src="/statics/assets/js/jquery.min.js"></script>
<script type="text/javascript">
	window.jQuery || document.write("<script src='/statics/assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<!-- <script src="/statics/assets/js/ace-elements.min.js"></script>
<script src="/statics/assets/js/ace.min.js"></script> -->
<!-- <script src="/statics/assets/js/bootstrap.min.js"></script>
 -->
<!--  <script src="/statics/assets/js/typeahead-bs2.min.js"></script>
 -->
 
 
 <script type="text/javascript"
	src="/statics/Widget/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script src="/statics/js/lrtk.js" type="text/javascript"></script>
<title>分类管理</title>
</head>

<body>
	<div class=" clearfix">
		<div id="category">
			<div id="scrollsidebar" class="left_Treeview">
				<div class="show_btn" id="rightArrow">
					<span></span>
				</div>
				<div class="widget-box side_content">
					<div class="side_title">
						<a title="隐藏" class="close_btn"><span></span></a>
					</div>
					<div class="side_list">
						<div class="widget-header header-color-green2">
							<h4 class="lighter smaller">产品类型列表</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main padding-8">
								<div id="treeDemo" class="ztree">
								   
								  <!-- 此处就是商品分类的树形菜单的值 -->
								   
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--跳转到商品分类添加界面          /itemCategory/product-category-add            -->
			<iframe id="testIframe" Name="testIframe" FRAMEBORDER=0
				SCROLLING=AUTO src="/itemCategory/product-category-add" class="page_right_style">				
		    </iframe>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		$("#category").fix({
			float : 'left',
			//minStatue : true,
			skin : 'green',
			durationTime : false
		});
		
	});
</script>
<script type="text/javascript">
	//初始化宽度、高度  
	$(".widget-box").height($(window).height());
	$(".page_right_style").width($(window).width() - 220);
	//当文档窗口发生改变时 触发  
	$(window).resize(function() {
		$(".widget-box").height($(window).height());
		$(".page_right_style").width($(window).width() - 220);
	})



	var zTreeObj;
	// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	var setting = {
		async : {
			enable : true,
			url : "/itemCategory/loadItemCategory",
			autoParam : [ "id"],
			dataFilter : filter
		},
		callback: {
		onClick: zTreeOnClick
	}	
	};
	 //点击分类显示对应的分类信息
	function zTreeOnClick(event, treeId, treeNode) {
	///itemCategory/toTest
      $("#testIframe").attr("src","/itemCategory/product-category-add?cateName="+treeNode.name+"&&id="+treeNode.id+"&&sort="+treeNode.sort+"&&isParent="+treeNode.isParent);
   //   $("#testIframe").location.href="itemCategory/toTest";
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
	//刷新分类列表方法
 function loadInfo(){
	
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
	
	}
	
</script>