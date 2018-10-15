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
		  <link rel="stylesheet" href="/statics/assets/css/ace-ie.min.css" />
		<![endif]-->
			<script src="/statics/js/jquery-1.9.1.min.js"></script>
			<script src="/statics/assets/js/bootstrap.min.js"></script>
			<script src="/statics/assets/js/typeahead-bs2.min.js"></script>
			<script src="/statics/assets/layer/layer.js" type="text/javascript"></script>
			<script src="/statics/assets/laydate/laydate.js"
				type="text/javascript"></script>
			<title>交易金额</title>
</head>

<body>
	<div class="margin clearfix">
		<div class="detailed_style clearfix">
			<em class="type"></em>
			<div class="shop_logo">
				<img src="/statics/images/detailnoimg.png" />
			</div>
			<ul class="shop_content clearfix">
				<li class="shop_name"><label class="label_name">店铺名称：</label><span
					class="info">${shop.storeName }</span>
					<div class="Authenticate">
						<i class="icon-yxrz"></i><i class="icon-yhk"></i><i
							class="icon-sjrz"></i><i class="icon-grxx"></i>
					</div></li>
				<li><label class="label_name">店铺类型：</label><span class="info">${shop.shopType == 1?"个人店铺":"企业店铺" }</span></li>
				<li><label class="label_name">店铺分类：</label><span class="info">${shop.catename }</span></li>
				<li><label class="label_name">申请时间：</label><span class="info"><fmt:formatDate
							value="${shop.storeCreateTime }" pattern="yyyy-MM-dd HH:ss" /></span></li>
				<li><label class="label_name">状&nbsp;&nbsp;&nbsp;&nbsp;态：</label><span
					class="info">待审核</span></li>
				<li><label class="label_name">申请人姓名：</label><span class="info">${shop.owName }</span></li>
				<li><label class="label_name">移动电话：</label><span class="info">${shop.phone }</span></li>
				<li><label class="label_name">电子邮箱：</label><span class="info">${shop.email }</span></li>
				<li><label class="label_name">固定电话：</label><span class="info"></span></li>
				<li><label class="label_name">身份证号：</label><span class="info">${shop.idCard }</span></li>
			</ul>
		</div>
		<div class="Store_Introduction">
			<div class="title_name">店铺介绍</div>
			<div class="info">${shop.shopDesc }</div>
		</div>
		<div class="Store_Introduction">
			<div class="title_name">其他认证</div>
			<div class="info"></div>
		</div>
		<div class="At_button">
			<button onclick="through_save('this',${shop.id});"
				class="btn btn-primary radius" type="submit">通 过</button>
			<button onclick="cancel_save(${shop.id});" class="btn btn-danger  btn-warning"
				type="button">拒 绝</button>
			<button onclick="return_close();" class="btn btn-default radius"
				type="button">返回上一步</button>
		</div>
	</div>
</body>
</html>
<script>
	//通过
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.iframeAuto(index);

	function through_save(obj, id) {
		layer.confirm('确认要开通该店铺吗？', function(index) {
			$.ajax({
				"type" : "POST", //请求类型
				"url" : "/ShopsAudit/approval",
				"data" : {
					"shopid" : id
				},
				"datatype" : "JSON", //请求返回的数据类型
				"success" : function(result) { //返回请求结果 	
					if (result.status == "200") {
						layer.msg('已开通!', {
							icon : 1,
							time : 1000
						});
						
						location.href = "/ShopsAudit/toShop_audit.html";
						parent.$('#parentIframe').css("display", "none");
						parent.$('.Current_page').css({
							"color" : "#333333"
						});


					} else {
						alert(result.msg);
					}
				},
				error : function() {
					alert("加载出错");
				}
			});
		});


	}

	//返回操作
	function return_close() {
		location.href = "/ShopsAudit/toShop_audit.html";
		parent.$('#parentIframe').css("display", "none");
		parent.$('.Current_page').css({
			"color" : "#333333"
		});

	}
	//拒绝
	function cancel_save(id) {
		var index = layer.open({
			type : 1,
			title : '内容',
			maxmin : true,
			shadeClose : false,
			area : [ '600px', '' ],
			content : ('<div class="shop_reason"><span class="content">请说明拒绝该申请人申请店铺的理由，以便下次在申请时做好准备。</span><textarea name="content"  id="contentInfo" class="form-control" id="form_textarea" placeholder="请填写拒绝理由" onkeyup="checkLength(this);"></textarea><span class="wordage">剩余字数：<span id="sy" style="color:Red;">500</span>字</span></div>'),
			btn : [ '确定', '取消' ],
			yes : function(index, layero) {
				if ($('.form-control').val() == "") {
					layer.alert('回复内容不能为空！', {
						title : '提示框',
						icon : 0,
					})
				} else {
				 var contentInfo=$("#contentInfo").val();  //获取到审批错误信息
              alert(contentInfo);
        //拒绝审核开通店铺
		$.ajax({
				"type" : "POST", //请求类型
				"url" : "/ShopsAudit/Refused",
				"data" : {
					"shopid" : id,
					"contentInfo":contentInfo
				},
				"datatype" : "JSON", //请求返回的数据类型
				"success" : function(result) { //返回请求结果 	
					if (result.status == "200") {                        
					
					layer.msg('提交成功!', {
						icon : 1,
						time : 1000
					});
					
								
					} else {
						alert(result.msg);
					}
				},
				error : function() {
					alert("加载出错");
				}
			});

   				layer.close(index);		
				}
			}
		})

	}
	/*字数限制*/
	function checkLength(which) {
		var maxChars = 500; //
		if (which.value.length > maxChars) {
			layer.open({
				icon : 2,
				title : '提示框',
				content : '您输入的字数超过限制!',
			});
			// 超过限制的字数了就将 文本框中的内容按规定的字数 截取
			which.value = which.value.substring(0, maxChars);
			return false;
		} else {
			var curr = maxChars - which.value.length; //减去当前输入的
			document.getElementById("sy").innerHTML = curr.toString();
			return true;
		}
	}
	;
</script>
