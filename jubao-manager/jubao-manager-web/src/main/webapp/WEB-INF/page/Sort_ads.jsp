<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport"
			content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link href="/statics/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="/statics/css/style.css" />
		<link href="/statics/assets/css/codemirror.css" rel="stylesheet">
			<link rel="stylesheet" href="/statics/assets/css/ace.min.css" />
			<link rel="stylesheet" href="/statics/font/css/font-awesome.min.css" />
			<!--[if lte IE 8]>
		  <link rel="stylesheet" href="/statics/assets/css/ace-ie.min.css" />
		<![endif]-->
			<script src="/statics/js/jquery-1.9.1.min.js"></script>
			<script src="/statics/assets/js/typeahead-bs2.min.js"></script>
			<script src="/statics/js/lrtk.js" type="text/javascript"></script>
			<script src="/statics/assets/js/jquery.dataTables.min.js"></script>
			<script src="/statics/assets/js/jquery.dataTables.bootstrap.js"></script>
			<script src="/statics/assets/layer/layer.js" type="text/javascript"></script>
			<title>分类管理</title>
</head>

<body>
	<div class="page-content clearfix">
		<div class="sort_style">
			<div class="border clearfix">
				<span class="l_f"> <a href="javascript:ovid()" id="sort_add"
					class="btn btn-warning"><i class="fa fa-plus"></i> 添加分类</a> <!--         <a href="javascript:ovid()" class="btn btn-danger"><i class="fa fa-trash"></i> 批量删除</a>
 -->
				</span> <span class="r_f">共：<b>${pageInfo.size }</b>类
				</span>
			</div>
			<div class="sort_list">
				<table class="table table-striped table-bordered table-hover"
					id="sample-table">
					<thead>
						<tr>
							<th width="25px"><label><input type="checkbox"
									class="ace"><span class="lbl"></span></label></th>
							<th width="50px">ID</th>
							<th width="100px">分类名称</th>
							<th width="50px">数量</th>
							<th width="250px">描述</th>
							<th width="100px">图片尺寸</th>
							<th width="80px">计费价格/小时</th>
							<th width="100px">加入时间</th>
							<th width="70px">状态</th>
							<th width="250px">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageInfo.list }" var="cate">
							<tr>
								<td><label><input type="checkbox" class="ace"><span
											class="lbl"></span></label></td>
								<td>${cate.categoryId }</td>
								<td>${cate.categoryName }</td>
								<td>${cate.advertisinNumber }</td>
								<td>${cate.categoryDsc }</td>
								<td>${cate.advertisinWidet}*${cate.advertisinHight }</td>
								<td>${cate.categoryPirce }</td>
								<td><fmt:formatDate value="${cate.advertisinCreateTime }"
										pattern="yyyy-MM-dd" /></td>
								<td class="td-status"><c:choose>
										<c:when test="${cate.categoryStatus == 1 }">
											<span class="label label-success radius">显示</span>
										</c:when>
										<c:otherwise>
											<span class="label label-error radius">隐藏</span>
										</c:otherwise>
									</c:choose></td>


								<td class="td-manage">
									<!-- "btn btn-xs btn-success" --> <%-- <a onClick="member_stop(this,${cate.categoryId})" href="javascript:;"   title=${cate.categoryStatus == 1?"停用":"启用"} class="btn btn-xs btn-success"}>
						   ${cate.categoryStatus == 1?"<i class='fa fa-check  bigger-120'></i>":"<i class='fa fa-close bigger-120'></i>"}
						    </a> --%> <c:choose>
										<c:when test="${cate.categoryStatus == 1 }">
											<a onClick="member_stop(this,${cate.categoryId})"
												href="javascript:;" title="停用"
												class="btn btn-xs btn-success"> <i
												class='fa fa-check  bigger-120'></i>
											</a>
										</c:when>
										<c:otherwise>
											<a onClick="member_start(this,${cate.categoryId})"
												href="javascript:;" title="启用" class="btn btn-xs"}> <i
												class='fa fa-close bigger-120'></i>
											</a>
										</c:otherwise>
									</c:choose> <a title="编辑" href="javascript:;" class="btn btn-xs btn-info"
									onclick='member_edit(${cate.categoryId},"${cate.categoryName }",${cate.advertisinNumber },${cate.categoryPirce },${cate.advertisinWidet },${cate.advertisinHight },${cate.categoryStatus},"${cate.categoryDsc}")'>
										<i class="fa fa-edit bigger-120"></i>
								</a> <a title="删除" href="javascript:;"
									onclick="member_del(this,${cate.categoryId})" class="btn btn-xs btn-warning"><i
										class="fa fa-trash  bigger-120"></i></a> <a
									href="javascript:ovid()" name="Ads_list.html"
									class="btn btn-xs btn-pink ads_link"
									onclick="AdlistOrders('561');" title="幻灯片广告列表"><i
										class="fa  fa-bars  bigger-120"></i></a>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!--添加分类-->
	<div class="sort_style_add margin" id="sort_style_add"
		style="display:none">
		<div class="">
			<form action="" method="post" id="categoryAdd">
				<ul>
					<input type="hidden" name="categoryId" id="categoryId" />
					<li><label class="label_name">分类名称</label>
						<div class="col-sm-9">
							<input name="categoryName" type="text" id="categoryName"
								Info="分类名称" placeholder="" class="col-xs-10 col-sm-5">
						</div></li>
					<li><label class="label_name">存储广告数</label>
						<div class="col-sm-9">
							<input name="advertisinNumber" type="text" id="advertisinNumber"
								Info="存储广告数" placeholder=""
								onkeyup="value=value.replace(/[^1234567890-]+/g,'')"
								class="col-xs-10 col-sm-5">
						</div></li>
					<li><label class="label_name">广告价格</label>
						<div class="col-sm-9">
							<input name="categoryPirce" type="text" id="categoryPirce"
								Info="广告价格" placeholder=""
								onkeyup="value=value.replace(/[^1234567890-]+/g,'')"
								class="col-xs-10 col-sm-5">
						</div></li>
					<li><label class="label_name">图片尺寸</label>
						<div class="col-sm-9">
							<span class="cont_style"> <input name="advertisinWidet"
								type="text" id="advertisinWidet" Info="图片宽度" placeholder="0"
								class="col-xs-10 col-sm-5"
								onkeyup="value=value.replace(/[^1234567890-]+/g,'')"
								style="width:80px"><span class="l_f"
									style="margin-left:10px;">x</span><input name="advertisinHight"
									type="text" id="advertisinHight" Info="图片高度" placeholder="0"
									class="col-xs-10 col-sm-5"
									onkeyup="value=value.replace(/[^1234567890-]+/g,'')"
									style="width:80px"></span>
						</div></li>
					<li><label class="label_name">分类状态</label> <span
						class="add_content"> &nbsp;&nbsp; <label> <input
								name="categoryStatus" value="1" id="categoryStatus1"
								type="radio" checked="checked" class="ace"><span
									class="lbl">显示</span></label>&nbsp;&nbsp;&nbsp; <label><input
								name="categoryStatus" id="categoryStatus2" type="radio"
								value="2" class="ace"> <span class="lbl">隐藏</span></label>
					</span></li>
					<li><label class="label_name">分类说明</label>
						<div class="col-sm-9">
							<input type="hidden" id="categoryDsc" name="categoryDsc" />
							<!-- 由于 "data" :$("#categoryAdd").serialize(),  提交表单时不能序列化textarea 域的值，通过js取得 textarea域中的值，赋值给隐藏文本  -->
							<textarea name="分类说明" class="form-control" id="categoryDscInfo"
								name="categoryDscInfo" placeholder="广告位描述"
								onkeyup="checkLength(this);"></textarea>
							<span class="wordage">剩余字数：<span id="sy"
								style="color:Red;">200</span>字
							</span>
						</div></li>
				</ul>
				<input type="reset" style="display:none;" />
			</form>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$('#sort_add').on('click', function() {
		$("input[type=reset]").trigger("click"); //清空表单
		layer.open({
			type : 1,
			title : '添加分类',
			maxmin : true,
			shadeClose : false, //点击遮罩关闭层
			area : [ '750px', '' ],
			content : $('#sort_style_add'),
			btn : [ '提交', '取消' ],
			yes : function(index, layero) {
				var num = 0;
				var str = "";
				$(".sort_style_add input[type$='text']").each(function(n) {
					if ($(this).val() == "") {

						layer.alert(str += "" + $(this).attr("Info") + "不能为空！\r\n", {
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

					$("#categoryDsc").val($("#categoryDscInfo").val()); //将 textarea域中的值赋值给隐藏表单  
					//添加操作
					$.ajax({
						"type" : "POST", //请求类型
						"url" : "/AdvertisingCateGory/addAdvertisingCate",
						"data" : $("#categoryAdd").serialize(),
						"datatype" : "JSON", //请求返回的数据类型
						"success" : function(result) { //返回请求结果 	

							if (result.status == "200") {
								window.location.href = "/AdvertisingCateGory/Sort_ads.html";
							} else { //添加失败
								layer.alert('添加失败！', {
									title : '提示框',
									icon : 1,
								});

							}

						},
						error : function() {
							alert("加载出错咯");
						}
					});
					layer.close(index);
				}
			}
		});
	});

	<!-- 编辑广告位分类 -->

	function member_edit(id, name, num, pirce, w, h, status, des) {
		$("#categoryId").val(id);
		$("#categoryName").val(name);
		$("#advertisinNumber").val(num);
		$("#categoryPirce").val(pirce);
		$("#advertisinWidet").val(w);
		$("#advertisinHight").val(h);
		if (status == 1) {
			$("#categoryStatus1").prop("checked", true);
			$("#categoryStatus2").prop("checked", false);
		} else {
			$("#categoryStatus2").prop("checked", true);
			$("#categoryStatus1").prop("checked", false);
		}
		$("#categoryDscInfo").val(des);


		layer.open({
			type : 1,
			title : '编辑分类',
			maxmin : true,
			shadeClose : false, //点击遮罩关闭层
			area : [ '750px', '' ],
			content : $('#sort_style_add'),
			btn : [ '提交', '取消' ],
			yes : function(index, layero) {
				var num = 0;
				var str = "";
				$(".sort_style_add input[type$='text']").each(function(n) {
					if ($(this).val() == "") {

						layer.alert(str += "" + $(this).attr("Info") + "不能为空！\r\n", {
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

					$("#categoryDsc").val($("#categoryDscInfo").val()); //将 textarea域中的值赋值给隐藏表单  
					//添加操作
					$.ajax({
						"type" : "POST", //请求类型
						"url" : "/AdvertisingCateGory/updateAdvertisingCate",
						"data" : $("#categoryAdd").serialize(),
						"datatype" : "JSON", //请求返回的数据类型
						"success" : function(result) { //返回请求结果 	

							if (result.status == "200") {
								layer.alert('修改成功！', {
									title : '提示框',
									icon : 1,
								});
								window.location.href = "/AdvertisingCateGory/Sort_ads.html";
							} else { //添加失败
								layer.alert('添加失败！', {
									title : '提示框',
									icon : 1,
								});

							}

						},
						error : function() {
							alert("加载出错咯");
						}
					});
					layer.close(index);
				}
			}
		});

	}
	;









	<!--  end -->
	function checkLength(which) {
		var maxChars = 200; //
		if (which.value.length > maxChars) {
			layer.open({
				icon : 2,
				title : '提示框',
				content : '您出入的字数超多限制!',
			});
			// 超过限制的字数了就将 文本框中的内容按规定的字数 截取
			which.value = which.value.substring(0, maxChars);
			return false;
		} else {
			var curr = maxChars - which.value.length; //250 减去 当前输入的
			document.getElementById("sy").innerHTML = curr.toString();
			return true;
		}
	}
	;
	/*广告图片-停用*/
	function member_stop(obj, id) {
		layer.confirm('确认要关闭吗？', {
			icon : 0,
		},
			function(index) {

				$.ajax({
					"type" : "POST", //请求类型
					"url" : "/AdvertisingCateGory/updateCateStatus",
					"data" : {
						"id" : id,
						"status" : "2"
					},
					"datatype" : "JSON", //请求返回的数据类型
					"success" : function(result) { //返回请求结果 	
						if (result.status == "200") {
							$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,id)" href="javascript:;" title="显示"><i class="fa fa-close bigger-120"></i></a>');
							$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">隐藏</span>');
							$(obj).remove();
						} else {
							alert("修改失败");
						}
					}
				}),
				layer.msg('关闭!', {
					icon : 5,
					time : 1000
				});
			});


	}
	/*广告图片-启用*/
	function member_start(obj, id) {
		layer.confirm('确认要显示吗？', {
			icon : 0,
		}, function(index) {
			$.ajax({
				"type" : "POST", //请求类型
				"url" : "/AdvertisingCateGory/updateCateStatus",
				"data" : {
					"id" : id
				},
				"datatype" : "JSON", //请求返回的数据类型
				"success" : function(result) { //返回请求结果 	
					if (result.status == "200") {
						$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="member_stop(this,id)" href="javascript:;" title="关闭"><i class="fa fa-check  bigger-120"></i></a>');
						$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">显示</span>');
						$(obj).remove();
					} else {
						alert("修改失败");
					}
				}
			}),
			layer.msg('显示!', {
				icon : 6,
				time : 1000
			});
		});
	}
	/*广告图片-删除*/
	function member_del(obj, id) {
		layer.confirm('确认要删除吗？', {
			icon : 0,
		}, function(index) {
			$.ajax({
				"type" : "POST", //请求类型
				"url" : "/AdvertisingCateGory/deleteCate",
				"data" : {
					"id" : id
				},
				"datatype" : "JSON", //请求返回的数据类型
				"success" : function(result) { //返回请求结果 	
					if (result.status == "200") {

						$(obj).parents("tr").remove();

					} else {
						alert("修改失败");
					}
				}
			}),

			layer.msg('已删除!', {
				icon : 1,
				time : 1000
			});
		});
	}
	//面包屑返回值
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.iframeAuto(index);
	$('.Order_form ,.ads_link').on('click', function() {
		var cname = $(this).attr("title");
		var cnames = parent.$('.Current_page').html();
		var herf = parent.$("#iframe").attr("src");
		parent.$('#parentIframe span').html(cname);
		parent.$('#parentIframe').css("display", "inline-block");
		parent.$('.Current_page').attr("name", herf).css({
			"color" : "#4c8fbd",
			"cursor" : "pointer"
		});
		//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+">" + cnames + "</a>");
		parent.layer.close(index);

	});
	function AdlistOrders(id) {
		window.location.href = "Ads_list.html?=" + id;
	}
	;
</script>
<script type="text/javascript">
	jQuery(function($) {
		var oTable1 = $('#sample-table').dataTable({
			"aaSorting" : [ [ 1, "desc" ] ], //默认第几个排序
			"bStateSave" : true, //状态保存
			 "searching":false,
			 "paging": false,//开启表格分页
			"aoColumnDefs" : [
				//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
				{
					"orderable" : false,
					"aTargets" : [ 0, 2, 4, 6, 7, ]
				} // 制定列不参与排序

			]
		});
		$('table th input:checkbox').on('click', function() {
			var that = this;
			$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function() {
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});

		});
		$('[data-rel="tooltip"]').tooltip({
			placement : tooltip_placement
		});
		function tooltip_placement(context, source) {
			var $source = $(source);
			var $parent = $source.closest('table')
			var off1 = $parent.offset();
			var w1 = $parent.width();

			var off2 = $source.offset();
			var w2 = $source.width();

			if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
			return 'left';
		}
	})
</script>