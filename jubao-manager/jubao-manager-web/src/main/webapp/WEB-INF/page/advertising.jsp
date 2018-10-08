<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
<script type="text/javascript"
	src="/statics/Widget/swfupload/swfupload.js"></script>
<script type="text/javascript"
	src="/statics/Widget/swfupload/swfupload.queue.js"></script>
<script type="text/javascript"
	src="/statics/Widget/swfupload/swfupload.speed.js"></script>
<script type="text/javascript"
	src="/statics/Widget/swfupload/handlers.js"></script>
<title>广告管理</title>
</head>

<body>
	<div class=" clearfix" id="advertising">
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
						<h4 class="lighter smaller">广告图分类</h4>
					</div>
					<div class="widget-body">
						<ul class="b_P_Sort_list">
							<li><i class="orange  fa fa-user-secret"></i><a
								href="/Advertising/advertising" class="cate" categoryId="0">全部(${cateSum })</a></li>
							<c:forEach items="${adverCateList }" var="cateList">
								<li><i class="fa fa-image pink "></i> <a
									href="/Advertising/advertising?cateid=${cateList.categoryId }"
									class="cate" categoryId="${cateList.categoryId }">${cateList.categoryName }(${cateList.effectiveNumber }/
										${cateList.advertisinNumber })</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="Ads_list">
			<div class="border clearfix">
				<span class="l_f"> <a href="javascript:ovid()" id="ads_add"
					class="btn btn-warning"><i class="fa fa-plus"></i> 添加广告</a> <a
					href="javascript:ovid()" class="btn btn-danger"><i
						class="fa fa-trash"></i> 批量删除</a>
				</span> <span class="r_f">共：<b>${cateSum }</b>条广告
				</span>
			</div>
			<div class="Ads_lists">
				<table class="table table-striped table-bordered table-hover"
					id="sample-table">
					<thead>
						<tr>
							<th width="25"><label><input type="checkbox"
									class="ace"><span class="lbl"></span></label></th>
							<th width="80">ID</th>
							<th>发布者</th>
							<th width="100">分类</th>
							<th width="220px">图片</th>
							<th width="250px">链接地址</th>
							<th width="150px">生效时间</th>
							<th width="180px">过期时间</th>
							<th width="70px">状态</th>
							<th width="250px">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${advertisingList }" var="advertising">
							<tr>
								<td><label><input type="checkbox" class="ace"><span
										class="lbl"></span></label></td>
								<td>${advertising.advertisinId }</td>
								<td>${advertising.advertisinUserType == 1?"管理员":"卖家" }</td>
								<td>${advertising.categoryName }</td>
								<td><span class="ad_img"><img
										src="${locahast }${advertising.advertisinUrl }" width="100%"
										height="100%" /></span></td>
								<td><a href="#" target="_blank"><a
										href="http://${advertising.advertisinToUrl  }">${advertising.advertisinToUrl }</a></a></td>
								<td><fmt:formatDate
										value="${advertising.advertisinStartTime }"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><fmt:formatDate value="${advertising.advertisinStart }"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="td-status"><c:choose>
										<c:when test="${advertising.advertisinStatus == 1 }">
											<span class="label label-success radius">显示</span>
										</c:when>
										<c:otherwise>
											<span class="label label-error radius">隐藏</span>
										</c:otherwise>
									</c:choose></td>
								<td class="td-manage"><c:choose>
										<c:when test="${advertising.advertisinStatus == 1}">
											<a onClick="member_stop(this,${advertising.advertisinId})"
												href="javascript:;" title="停用"
												class="btn btn-xs btn-success"> <i
												class="fa fa-check  bigger-120"></i>
											</a>
										</c:when>
										<c:otherwise>
											<a onClick="member_start(this,${advertising.advertisinId})"
												href="javascript:;" title="显示" class="btn btn-xs"> <i
												class='fa fa-close bigger-120'></i>
											</a>
										</c:otherwise>
									</c:choose> <a title="编辑"
									onclick="member_edit('编辑','member-add.html','4','','510')"
									href="javascript:;" class="btn btn-xs btn-info"><i
										class="fa fa-edit bigger-120"></i></a> <a title="删除"
									href="javascript:;" onclick="member_del(this,'1')"
									class="btn btn-xs btn-warning"><i
										class="fa fa-trash  bigger-120"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<!-- 分页 big -->
				<!--分页-->
				<nav aria-label="...">
				<ul class="pagination">
					<c:if test="${pageInfo.pageNum>1}">
						<li><a
							href="/Advertising/advertising?cateid=${cateid }&&pageIndex=${pageIndex-1}"
							aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					</c:if>
					<c:forEach items="${pageInfo.navigatepageNums}" var="page">

						<c:choose>
							<c:when test="${pageInfo.pageNum == page}">
								<li class="active"><a
									href="/Advertising/advertising?cateid=${cateid }&&pageIndex=${page}">${page}<span
										class="sr-only">(current)</span></a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="/Advertising/advertising?cateid=${cateid }&&pageIndex=${page}">${page}<span
										class="sr-only">(current)</span></a></li>
							</c:otherwise>
						</c:choose>

					</c:forEach>

					<c:if test="${ pageInfo.pageNum < pageInfo.pages}">
						<li><a
							href="/Advertising/advertising?cateid=${cateid }&&pageIndex=${pageInfo.pageNum +1}"
							aria-label="Previous"><span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>

				</ul>
				</nav>


				<!-- end -->

			</div>
		</div>
	</div>
	<!--添加广告样式-->
	<div id="add_ads_style" style="display:none">
		<div class="add_adverts">
			<form action="" id="addfrom">
				<ul>
					<li><label class="label_name">所属分类</label> <span
						class="cont_style"> <select class="form-control"
							id="categoryId" name="categoryId">

						</select></span></li>
					<li><label class="label_name">投放时长</label><span
						class="cont_style"><input name="advertisinTime" type="text"
							id="form-field-1" placeholder="0" class="col-xs-10 col-sm-5"
							style="width:50px"
							onkeyup="value=value.replace(/[^1234567890-]+/g,'')"></span></li>
					<li><label class="label_name">投放时段</label><span
						class="cont_style"><input name="advertisinStartTime"
							type="datetime-local" id="startDateTime" placeholder="0"
							class="col-xs-10 col-sm-5" style="width:200px"></span></li>
					<li><label class="label_name">链接地址</label><span
						class="cont_style"><input name="advertisinToUrl"
							type="text" id="form-field-1" placeholder="地址"
							class="col-xs-10 col-sm-5" style="width:450px"></span></li>
					<li><label class="label_name">状&nbsp;&nbsp;态：</label> <span
						class="cont_style"> &nbsp;&nbsp;<label><input
								name="advertisinStatus" type="radio" checked="checked"
								class="ace" value="1"><span class="lbl">显示</span></label>&nbsp;&nbsp;&nbsp;
							<label><input name="advertisinStatus" type="radio"
								class="ace" value="2"><span class="lbl">隐藏</span></label></span>
						<div class="prompt r_f"></div></li>
					<li><label class="label_name">图片</label><span
						class="cont_style">
							<div class="demo">
								<div class="logobox">
									<div class="resizebox">
										<img src="/statics/images/image.png" width="100px" alt=""
											height="100px" id="preview" />
									</div>
								</div>
								<div class="logoupload">
									<div class="btnbox">
										<input type="file" name="imgInfo" id="imgInfo" />
									</div>
									<div style="clear:both;height:0;overflow:hidden;"></div>
									<div class="progress-box" style="display:none;">
										<div class="progress-num">
											上传进度：<b>0%</b>
										</div>
										<div class="progress-bar">
											<div style="width:0%;" class="bar-line"></div>
										</div>
									</div>
									<div class="prompt">
										<p>图片大小小于5MB,支持.jpg;.gif;.png;.jpeg格式的图片</p>
									</div>
								</div>
							</div>


					</span></li>


				</ul>
			</form>
		</div>
	</div>
</body>
</html>
<script>

	/* $("#imgInfo").on("change",function(){
	  var imgdata=$(this).val();
	  $(".resizebox").empty();//清空
	  if(imgdata == ""){
	    $(".resizebox").append("<img src='/statics/images/image.png' width='100px' alt='' height='100px'/>");
	  }else{
	  $(".resizebox").append("<img src='"+imgdata+"' width='100px' alt='' height='100px'/>");
	  }
	}); */

	//点击广告分类显示出对应分类的广告
/* $(".cate").on("click",function(){
       var cateid=$(this).attr("categoryId");//获取到分类id
    
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
    
}); */


	//获取文件上传表单中选中的图片 。并且显示出来
	$("#imgInfo").change(function() {
		var $file = $(this);
		var fileObj = $file[0];
		var windowURL = window.URL || window.webkitURL;
		var dataURL;
		var $img = $("#preview");

		if (fileObj && fileObj.files && fileObj.files[0]) {
			dataURL = windowURL.createObjectURL(fileObj.files[0]);
			$img.attr('src', dataURL); //选中时的图片
		} else {
			//未选择文件时显示默认的图片
			$img.attr('src', "/statics/images/image.png");
			dataURL = $file.val();
			var imgObj = document.getElementById("preview");
			// 两个坑:
			// 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
			// 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
			imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

		}
	});





	//初始化宽度、高度  
	$(".widget-box").height($(window).height());
	$(".Ads_list").width($(window).width() - 220);
	//当文档窗口发生改变时 触发  
	$(window).resize(function() {
		$(".widget-box").height($(window).height());
		$(".Ads_list").width($(window).width() - 220);
	});
	$(function() {
		$("#advertising").fix({
			float : 'left',
			//minStatue : true,
			skin : 'green',
			durationTime : false,
			stylewidth : '220',
			spacingw : 30, //设置隐藏时的距离
			spacingh : 250, //设置显示时间距
			set_scrollsidebar : '.Ads_style',
			table_menu : '.Ads_list'
		});
	});
	/*广告图片-停用*/
	function member_stop(obj, id) {
		layer.confirm('确认要关闭吗？', {
			icon : 0,
		}, function(index) {

			$.ajax({
				"type" : "POST", //请求类型
				"url" : "/Advertising/updateStatus",
				"data" : {
					"id" : id,
					"status" : "2"
				},
				"datatype" : "JSON", //请求返回的数据类型
				"success" : function(result) { //返回请求结果 	
					if (result.status == "200") {
						$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,id)" href="javascript:;" title="显示"><i class="fa fa-close bigger-120"></i></a>');
						$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已关闭</span>');
						$(obj).remove();
					} else {
						alert("修改失败");
					}
				}
			})
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
				"url" : "/Advertising/updateStatus",
				"data" : {
					"id" : id
				},
				"datatype" : "JSON", //请求返回的数据类型
				"success" : function(result) { //返回请求结果 	
					if (result.status == "200") {s
			$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="member_stop(this,id)" href="javascript:;" title="关闭"><i class="fa fa-check  bigger-120"></i></a>');
			$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">显示</span>');
			$(obj).remove();
			} else {
						alert("修改失败");
					}
				}
			})
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
			$(obj).parents("tr").remove();
			layer.msg('已删除!', {
				icon : 1,
				time : 1000
			});
		});
	}
	/*******添加广告*********/
	$('#ads_add').on('click', function() {


		/*
添加操作
*/
		$.ajax({
			"type" : "get", //请求类型
			"url" : "/Advertising/loadCate",
			"data" : {},
			"datatype" : "JSON", //请求返回的数据类型
			"success" : function(result) { //返回请求结果 	
				if (result.status == "200") {
					$("#categoryId").empty(); //清空
					var stroption = "<option value=''>选择分类</option>";
					for (var x = 0; x < result.data.length; x++) {
						stroption += "<option value='" + result.data[x].categoryId + "'>" + result.data[x].categoryName + "</option>";
					}
					$("#categoryId").append(stroption);
				} else {
					alert(result.msg);
				}
			},
			error : function() {
				alert("加载出错");
			}
		});

		layer.open({
			type : 1,
			title : '添加广告',
			maxmin : true,
			shadeClose : false, //点击遮罩关闭层
			area : [ '800px', '' ],
			content : $('#add_ads_style'),
			btn : [ '提交', '取消' ],
			yes : function(index, layero) {
				var num = 0;
				var str = "";
				$(".add_adverts input[type$='text']").each(function(n) {
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
					alert("===");
					var formData = new FormData(document.getElementById("addfrom")); //表单id
					$.ajax({
						url : "/Advertising/addAdvertising",
						type : 'POST',
						data : formData,
						dataType : "json",
						async : false,
						cache : false,
						contentType : false,
						processData : false,
						success : function(data) {
							if (data.status == "200") {
								//添加成功
								alert("添加成功");
								layer.close(index);
							} else if (data.status == "400") {
								//图片过大
								alert(data.msg);
							}
						},
						error : function() {
							alert("添加出错");
						}
					});
				}
			}
		});
	})
</script>
<script type="text/javascript">



	laydate.render({
		elem : '#startDateTime'
	});


	function updateProgress(file) {
		$('.progress-box .progress-bar > div').css('width', parseInt(file.percentUploaded) + '%');
		$('.progress-box .progress-num > b').html(SWFUpload.speed.formatPercent(file.percentUploaded));
		if (parseInt(file.percentUploaded) == 100) {
			// 如果上传完成了
			$('.progress-box').hide();
		}
	}

	function initProgress() {
		$('.progress-box').show();
		$('.progress-box .progress-bar > div').css('width', '0%');
		$('.progress-box .progress-num > b').html('0%');
	}

	function successAction(fileInfo) {
		var up_path = fileInfo.path;
		var up_width = fileInfo.width;
		var up_height = fileInfo.height;
		var _up_width,
			_up_height;
		if (up_width > 120) {
			_up_width = 120;
			_up_height = _up_width * up_height / up_width;
		}
		$(".logobox .resizebox").css({
			width : _up_width,
			height : _up_height
		});
		$(".logobox .resizebox > img").attr('src', up_path);
		$(".logobox .resizebox > img").attr('width', _up_width);
		$(".logobox .resizebox > img").attr('height', _up_height);
	}

	var swfImageUpload;
	$(document).ready(function() {
		var settings = {
			flash_url : "/statics/Widget/swfupload/swfupload.swf",
			flash9_url : "/statics/Widget/swfupload/swfupload_fp9.swf",
			upload_url : "upload.php", // 接受上传的地址
			file_size_limit : "5MB", // 文件大小限制
			file_types : "*.jpg;*.gif;*.png;*.jpeg;", // 限制文件类型
			file_types_description : "图片", // 说明，自己定义
			file_upload_limit : 100,
			file_queue_limit : 0,
			custom_settings : {},
			debug : false,
			// Button settings
			button_image_url : "/statics/Widget/swfupload/upload-btn.png",
			button_width : "95",
			button_height : "30 ",
			button_placeholder_id : 'uploadBtnHolder',
			button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
			button_cursor : SWFUpload.CURSOR.HAND,
			button_action : SWFUpload.BUTTON_ACTION.SELECT_FILE,

			moving_average_history_size : 40,

			// The event handler functions are defined in handlers.js
			swfupload_preload_handler : preLoad,
			swfupload_load_failed_handler : loadFailed,
			file_queued_handler : fileQueued,
			file_dialog_complete_handler : fileDialogComplete,
			upload_start_handler : function(file) {
				initProgress();
				updateProgress(file);
			},
			upload_progress_handler : function(file, bytesComplete, bytesTotal) {
				updateProgress(file);
			},
			upload_success_handler : function(file, data, response) {
				// 上传成功后处理函数
				var fileInfo = eval("(" + data + ")");
				successAction(fileInfo);
			},
			upload_error_handler : function(file, errorCode, message) {
				alert('上传发生了错误！');
			},
			file_queue_error_handler : function(file, errorCode, message) {
				if (errorCode == -110) {
					alert('您选择的文件太大了。');
				}
			}
		};
		swfImageUpload = new SWFUpload(settings);
	});
</script>
<script>
	jQuery(function($) {
		var oTable1 = $('#sample-table').dataTable({
			"aaSorting" : [ [ 1, "desc" ] ], //默认第几个排序
			"bStateSave" : true, //状态保存
			"searching" : false,
			"paging" : false, //开启表格分页
			"aoColumnDefs" : [
				//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
				{
					"orderable" : false,
					"aTargets" : [ 0, 2, 3, 4, 5, 7, 8, ]
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
