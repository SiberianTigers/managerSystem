<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link href="/statics/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="/statics/css/style.css" />
		<link href="/statics/assets/css/codemirror.css" rel="stylesheet">
			<link rel="stylesheet" href="/statics/assets/css/ace.min.css" />
			<link rel="stylesheet" href="/statics/font/css/font-awesome.min.css" />
			<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->
			<script src="/statics/js/jquery-1.9.1.min.js"></script>
			<script src="/statics/assets/js/bootstrap.min.js"></script>
			<script src="/statics/assets/js/typeahead-bs2.min.js"></script>
			<script src="/statics/js/lrtk.js" type="text/javascript"></script>
			<script src="/statics/assets/js/jquery.dataTables.min.js"></script>
			<script src="/statics/assets/js/jquery.dataTables.bootstrap.js"></script>
			<script src="/statics/assets/layer/layer.js" type="text/javascript"></script>
			<script src="/statics/assets/laydate/laydate.js"
				type="text/javascript"></script>
			<script src="/statics/js/H-ui.js" type="text/javascript"></script>
			<script src="/statics/js/displayPart.js" type="text/javascript"></script>
			<title>文章管理</title>
</head>

<body>
	<div class="clearfix">
		<div class="article_style" id="article_style">
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
							<h4 class="lighter smaller">店铺分类</h4>
						</div>
						<div class="widget-body">
							<ul class="b_P_Sort_list">
								<li><i class="orange  fa fa-list "></i><a
									href="/Shop/toShop_list.html">全部(${cateSum })</a></li>
								<c:forEach items="${shopCateList }" var="shopCate">
									<li><i class="fa fa-shopping-bag pink "></i> <a
										href="/Shop/toShop_list.html?cateid=${shopCate.id }">${shopCate.name }(${shopCate.shopcount })</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!--文章列表-->
			<div class="Ads_list">
				<div class="search_style">
					<form action="/Shop/toShop_list.html" method="post" id="shopFrom">
						<ul class="search_content clearfix">

							<li><label class="l_f">经营类型</label> <select
								name="storeCategory" style=" width:150px">
									<option value="">--全部--</option>
									<c:forEach items="${shopCateList }" var="shopCate">>
						 	   <c:choose>
											<c:when test="${shop.storeCategory == shopCate.id}">
												<option value="${shopCate.id }" selected="selected">${shopCate.name }</option>
											</c:when>
											<c:otherwise>
												<option value="${shopCate.id }">${shopCate.name }</option>
											</c:otherwise>
										</c:choose>

									</c:forEach>
							</select></li>
							<li><label class="l_f">店铺名称</label><input name="storeName"
								type="text" value="${shop.storeName }" class="text_add"
								placeholder="请输入要查找的店铺名称" style=" width:150px"></li>
							<li style="width:90px;">
								<button type="submit" class="btn_search">
									<i class="fa fa-search"></i>查询
								</button>
							</li>

						</ul>
						<input type="hidden"  name="pageSize" id="psize"/>
					</form>
				</div>
				<div class="border clearfix">
					<span class="l_f">
					<button
					   id="addprivate"	class="btn btn-danger"><i class="fa fa-trash"></i> 批量私信
					</button>
					</span> 
					<span class="l_f">
					 每页显示:<select name="pageSize" id="pageSize">
					<c:choose>
					    <c:when test="${pageSize ==2 }">
					       	 <option value="2" selected="selected">2</option>
					  		 <option value="5">5</option>
					   		 <option value="10">10</option>
					    	<option value="20">20</option>
					    </c:when>
					      <c:when test="${pageSize ==5 }">
					       	 <option value="2">2</option>
					  		 <option value="5" selected="selected">5</option>
					   		 <option value="10">10</option>
					    	<option value="20">20</option>
					    </c:when>
					      <c:when test="${pageSize ==10 }">
					       	 <option value="2" >2</option>
					  		 <option value="5">5</option>
					   		 <option value="10" selected="selected">10</option>
					    	<option value="20">20</option>
					    </c:when>
					      <c:when test="${pageSize ==20 }">
					       	 <option value="2" >2</option>
					  		 <option value="5" >5</option>
					   		 <option value="10">10</option>
					    	<option value="20" selected="selected">20</option>
					    </c:when>
				
					 
					
					</c:choose>					
				
					 </select>
					</span>
						<span
						class="r_f">共：<b>${cateSum }</b>家
					</span>
				</div>
				<div class="article_list">
					<table class="table table-striped table-bordered table-hover"
						id="sample-table">
						<thead>
							<tr>
								<th width="25"><label><input type="checkbox"
										class="ace"><span class="lbl"></span></label></th>
								<th width="180">店铺名称</th>
								<th width="120px">所属分类</th>
								<th width="120px">店铺类型</th>
								<th width="80px">所在地</th>
								<th width="80px">简介</th>
								<th width="50px">经营状态</th>
								<th width="50px">违规记分</th>
								<th width="150px">添加时间</th>
								<th width="50px">审核状态</th>
								<th width="150px">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageInfo.list }" var="shop">
								<tr>
									<td><label><input type="checkbox"
											class="ace checkboxs" shopid="${shop.id }"><span
												class="lbl"></span></label></td>
									<td>${shop.storeName }</td>
									<td>${shop.catename }</td>
									<td><c:choose>
											<c:when test="${shop.shopType == 1 }">
												个人店铺
											</c:when>
											<c:otherwise>
											企业店铺
											</c:otherwise>
										</c:choose></td>
									<td>${shop.storeAddress }</td>

									<td class="displayPart" displayLength="60">
										${shop.shopDesc }</td>

									<td>${shop.vname }</td>
									<td>${shop.points }</td>
									<td><fmt:formatDate value="${shop.storeCreateTime }"
											pattern="yyyy-MM-dd" /></td>
									<td><c:choose>
											<c:when test="${shop.storeStatus == 1 }">
									    通过    
									  </c:when>
											<c:otherwise>
									  待审核
									  </c:otherwise>

										</c:choose></td>

									<td class="td-manage"><a title="私信" href="javascript:;"
										onclick="modalsetDate(${shop.id},'${shop.storeName }')"
										class="btn btn-xs  btn-warning" data-toggle="modal"
										data-target="#gridSystemModal"> <i
											class="glyphicon glyphicon-envelope"></i>
									</a></td>

								</tr>


							</c:forEach>

						</tbody>
					</table>

					<!--分页-->
					<nav aria-label="...">
					<ul class="pagination">
						<c:if test="${pageInfo.pageNum>1}">
							<li><a
								href="/Shop/toShop_list.html?cateid=${shop.storeCategory }&&shopName=${shop.storeName }&&pageIndex=${pageIndex-1}"
								aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
						</c:if>
						<c:forEach items="${pageInfo.navigatepageNums}" var="page">

							<c:choose>
								<c:when test="${pageInfo.pageNum == page}">
									<li class="active"><a
										href="/Shop/toShop_list.html?cateid=${shop.storeCategory }&&shopName=${shop.storeName }&&pageIndex=${page}">${page}<span
											class="sr-only">(current)</span></a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="/Shop/toShop_list.html?cateid=${shop.storeCategory }&&shopName=${shop.storeName }&&pageIndex=${page}">${page}<span
											class="sr-only">(current)</span></a></li>
								</c:otherwise>
							</c:choose>

						</c:forEach>

						<c:if test="${ pageInfo.pageNum < pageInfo.pages}">
							<li><a
								href="/Shop/toShop_list.html?cateid=${shop.storeCategory }&&shopName=${shop.storeName }&&pageIndex=${pageInfo.pageNum +1}"
								aria-label="Previous"><span aria-hidden="true">&raquo;</span></a>
							</li>
						</c:if>

					</ul>
					</nav>


				</div>
			</div>
		</div>
	</div>


	<!-- Modal -->
	<div id="gridSystemModal" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="gridModalLabel" style="display: none;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="gridModalLabel"></h4>
				</div>
				<div class="modal-body">

					<form action="" method="post" class="form form-horizontal"
						id="formAdd">
						<input id="parentId" type="hidden" name="shopid" />
						<!-- 店铺id -->

						<div class="Operate_cont clearfix">
							<label class="form-label"><span class="c-red">*</span>标题：</label>
							<div class="formControls ">
								<input type="text" class="input-text" value=""
									placeholder="请输入标题名称" id="topic" name="topic" /> <font></font>
							</div>

						</div>
						<div class="Operate_cont clearfix">
							<label class="form-label">内容：</label>
							<div class="formControls">
								<input type="hidden" name="privateInfo" id="privateInfo" />
									<input type="hidden" name="array" id="array" />
								<!-- 留言内容 -->
								<textarea id="textareaInfo" name="textareaInfo" rows=""
									class="textarea" placeholder="給店家说点什么吧...最少输入6个字符"
									datatype="*10-300" onKeyUp="textarealength(this,300)">
						</textarea>
								<p class="textarea-numberbar">
									<em class="textarea-length">0</em>/300
								</p>
							</div>
						</div>

					</form>


				</div>
				<!-- 按钮组 -->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="addCate">添加</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>



</body>
</html>
<script>


 $("#pageSize").on("change",function(){
    var pageSize=$("#pageSize").val();//获取下拉框选择的值
     $("#psize").val(pageSize);//将每页显示几条的数据赋值到表单字段 id为psize。
     $("#shopFrom").submit();//提交表单
 });


	//批量发送私信
	$("#addprivate").on("click", function() {

		var checkboxs = $(".checkboxs:checked");

		if (checkboxs.length == 0) {
			alert("请选择数据!");
		} else {
			if (confirm("你确定要批量私信吗？")) {
		    $("#addCate").attr("type","2");
		    $("#gridModalLabel").text("批量给店铺私信");
	        $('#gridSystemModal').modal('show');
			}
		}
	}); 
	
	/* $("#addprivate").on("click",function(){
	
	alert("==");
	
	}); */


	//给模态框传递值
	function modalsetDate(id, shopname) {
		$("#parentId").val(id);
		$("#gridModalLabel").text("给店铺:" + shopname + " 私信");
		$("#addCate").attr("type","1");
	}
	//验证表单信息
	function checkFrom() {
		var topic = $("#topic").val(); //标题
		var textareaInfo = $("#textareaInfo");
		if (topic == "") {
			$("#topic").attr("placeholder", "标题不能为空");
			$("#topic").focus();
			return false;
		} else if (textareaInfo.val().length <= 6) {
			$(".textarea").focus();
			$(".textarea").attr("placeholder", "私信内容不能小于6个字符");
			return false;
		} else {
			$("#privateInfo").val(textareaInfo.val()); //给隐藏表单赋值
			return true;
		}

	}

	//添加操作
	$("#addCate").on("click", function() {
		alert("---------------" + checkFrom());
		if (checkFrom()) {	
		    var type=$("#addCate").attr("type");
           if(type == 1){		
			//單獨添加
			$.ajax({
				"type" : "POST", //请求类型
				"url" : "/Shop/shopMessage",
				"data" : $("#formAdd").serialize(),
				"datatype" : "JSON", //请求返回的数据类型
				"success" : function(result) { //返回请求结果 	
					if (result.status == "200") {
						alert("添加成功");
						//  parent.location.reload();//刷新页面			     
						$('#gridSystemModal').modal('hide');
						window.parent.loadInfo(); //只刷新父页面
					} else {
						alert("添加失败");
					}
				},
				error : function() {
					alert("加载出错");
				}
			});
		}else{		
			  //批量发送私信  
		  	var checkboxs = $(".checkboxs:checked");
				var array = new Array();
				$.each(checkboxs, function() {
					array.push($(this).attr("shopid"));
				}); 		
			 $("#array").val(JSON.stringify(array)); 
				
			$.ajax({
					"type" : "POST", //请求类型
					"url" : "/Shop/BatchAdd",
					"data" : $("#formAdd").serialize(),
					"datatype" : "JSON", //请求返回的数据类型
					"success" : function(result) { //返回请求结果 	
						if (result.status == "200") {
							alert("操作成功");
						} else {
							alert(result.msg);
						}
					},
					error : function() {
						alert("加载出错");
					}
				}); 
		
		}
		
        }

	});



	$(function() {
		$(".displayPart").displayPart();
	});
	laydate({
		elem : '#start',
		event : 'focus'
	});
	//面包屑返回值
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.iframeAuto(index);
	$('#add_page').on('click', function() {
		var cname = $(this).attr("title");
		var chref = $(this).attr("href");
		var cnames = parent.$('.Current_page').html();
		var herf = parent.$("#iframe").attr("src");
		parent.$('#parentIframe').html(cname);
		parent.$('#iframe').attr("src", chref).ready();
		;
		parent.$('#parentIframe').css("display", "inline-block");
		parent.$('.Current_page').attr({
			"name" : herf,
			"href" : "javascript:void(0)"
		}).css({
			"color" : "#4c8fbd",
			"cursor" : "pointer"
		});
		//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+" class='iframeurl'>" + cnames + "</a>");
		parent.layer.close(index);

	});
	$(function() {
		var oTable1 = $('#sample-table').dataTable({
			"aaSorting" : [ [ 1, "desc" ] ], //默认第几个排序
			"bStateSave" : true, //状态保存
			"aoColumnDefs" : [
				//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
				{
					"orderable" : false,
					"aTargets" : [ 0, 2, 3, 4, 5, 7, 8 ]
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
	})

	$(function() {
		$("#article_style").fix({
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
	//初始化宽度、高度  
	$(".widget-box").height($(window).height());
	$(".Ads_list").width($(window).width() - 220);
	//当文档窗口发生改变时 触发  
	$(window).resize(function() {
		$(".widget-box").height($(window).height());
		$(".Ads_list").width($(window).width() - 220);
	});

	/*文章-删除*/
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
</script>
