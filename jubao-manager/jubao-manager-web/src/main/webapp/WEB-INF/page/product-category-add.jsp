<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="/statics/assets/css/bootstrap.min.css" rel="stylesheet" />

<link rel="stylesheet" href="/statics/css/style.css" />


<link rel="stylesheet" href="/statics/assets/css/ace.min.css" />

<link rel="stylesheet" href="/statics/assets/css/font-awesome.min.css" />
<link href="/statics/Widget/icheck/icheck.css" rel="stylesheet"
	type="text/css" />



<script src="/statics/js/jquery-1.9.1.min.js"></script>
<script src="/statics/assets/js/bootstrap.min.js"></script>
<title>添加产品分类</title>
</head>
<body>
	<div class="type_style">
		<div class="type_title">产品类型信息</div>
		<div class="type_content">
			<div class="Operate_btn">
				<button type="button" class="btn  btn-warning" data-toggle="modal"
					data-target="#gridSystemModal">
					<i class="icon-edit align-top bigger-125"></i>新增子类型
				</button>
				<!-- 		<button href="javascript:ovid()" class="btn  btn-success">
					<i class="icon-ok align-top bigger-125"></i>禁用该类型
				</button> -->
				<button href="javascript:ovid()" class="btn  btn-danger"
					id="deleteCate">
					<i class="icon-trash   align-top bigger-125"></i>删除该类型
				</button>
			</div>
			<form action="" method="post" class="form form-horizontal"
				id="form-user-add">
				<input id="id" type="hidden" name="id" value="${id }">
				<!-- 隐藏域中的商品分类id -->
				<div class="Operate_cont clearfix">
					<label class="form-label"><span class="c-red">*</span>分类名称：</label>
					<div class="formControls ">
						<input type="text" class="input-text" value="${cateName }"
							placeholder="" id="cateName" name="cateName"> <font></font>
					</div>
				</div>
				<div class="Operate_cont clearfix">
					<label class="form-label"><span class="c-red">*</span>排序：</label>
					<div class="formControls ">
						<input type="text" class="input-text" value="${sort }"
							placeholder="" id="user-name" readonly="readonly"
							name="product-category-name">
					</div>
				</div>
				<div class="Operate_cont clearfix">
					<label class="form-label"><span class="c-red">*</span>是否父类：
					</label>
					<div class="formControls">
						&nbsp; <select name="isParent" id="is_Parent">
							<c:choose>
								<c:when test="${isParent == true}">
									<option value="1" selected="selected">是</option>
									<option value="0">否</option>
								</c:when>
								<c:otherwise>
									<option value="1">是</option>
									<option value="0" selected="selected">否</option>
								</c:otherwise>
							</c:choose>
						</select>
					</div>
				</div>
				<div class="">
					<div class="" style=" text-align:center">
						<input class="btn btn-primary radius" type="submit"
							id="updateCate" value="修改">
					</div>
				</div>
			</form>



			<c:if test="${parentId == 0}">
	     当前LOG:<img alt="log" src="http://${cateImg }">
				<form id="catelogfrom" method="post">
					<input id="id" type="hidden" name="id" value="${id }">
					<div class="Operate_cont clearfix">
						<label class="form-label">上传Log图片：</label>
						<div class="formControls">
							<input type="file" name="catelog"> <br /> <a
								href="javaScript:void(0)" id="cateload"
								class="btn btn-xs btn-success">上传</a>
						</div>
					</div>
				</form>

			</c:if>

			<br /> <br /> <br />


			<!-- 添加商品规格 -->
			<c:if test="${isParent == false}">
				<div class="Operate_btn">
					<label class="form-label">添加商品规格模板：</label>
					<button type="button" class="btn  btn-warning" id="group">
						<i class="glyphicon glyphicon-plus bigger-126"></i>新增分组
					</button>
				</div>
			


			<div class="Operate_cont clearfix">

				<ul id="templateul" style="line-height: 20px">
					<c:if test="${ not empty temp  }">

						<c:forEach items="${temp }" var="k">

							<li class="param">
								<ul>
								 
								 <li>
									<input class="easyui-addParam" style="width: 150px;"
										name="group" value="${k.group }" />
										&nbsp;
										 <a href="javascript:;" title="添加子项"
										class="btn btn-xs btn-success addParam"> <i	class='glyphicon glyphicon-plus'></i>
									</a>
								 </li>

									<c:forEach items="${k.params }" var="v">
									<li><span>|-------</span><input style="width: 150px;"
											value="${v }" class="easyui-delParam" name="param"/>&nbsp;
											<a href="javascript:;" title="删除"
											class="btn btn-xs btn-pink ads_link delParam"> <i
												class='glyphicon glyphicon-minus'></i>
										</a>
									</li>

								</c:forEach>
								</ul>
							</li>
						</c:forEach>
					</c:if>
					
				</ul>
				<!-- 按钮组 -->
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" id="templateulRest">重置</button>
					<button type="button" class="btn btn-primary" id="templateulAdd">提交</button>
				</div>

          </c:if>

			</div>







		</div>
	</div>

	<!-- 模板 -->
	<div class="itemParamAddTemplate" style="display: none;">
		<li class="param">
			<ul>
				<li><input class="easyui-addParam" style="width: 150px;"
					name="group" />&nbsp; <a href="javascript:;" title="添加子项"
					class="btn btn-xs btn-success addParam"> <i
						class='glyphicon glyphicon-plus'></i>
				</a></li>
				<li><span>|-------</span><input style="width: 150px;"
					class="easyui-delParam" name="param" />&nbsp; <a
					href="javascript:;" title="删除"
					class="btn btn-xs btn-pink ads_link delParam"> <i
						class='glyphicon glyphicon-minus'></i>
				</a></li>
			</ul>
		</li>
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
					<h4 class="modal-title" id="gridModalLabel">添加子分类</h4>
				</div>
				<div class="modal-body">

					<form action="" method="post" class="form form-horizontal"
						id="formAdd">
						<input id="parentId" type="hidden" name="parentId" value="${id }">
						<!-- 隐藏域中的商品分类id -->
						<div class="Operate_cont clearfix">
							<label class="form-label"><span class="c-red">*</span>分类名称：</label>
							<div class="formControls ">
								<input type="text" class="input-text" value=""
									placeholder="请输入分类名称" id="name" name="name"> <font></font>
							</div>

						</div>
						<div class="Operate_cont clearfix">
							<label class="form-label"><span class="c-red">*</span>是否父类：
							</label>
							<div class="formControls">
								&nbsp; <select name="isParent" id="isParent">
									<option value="0">否</option>
									<option value="1">是</option>
								</select>
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










	<script type="text/javascript"
		src="/statics/Widget/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript"
		src="/statics/Widget/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="/statics/assets/layer/layer.js"></script>
	<script type="text/javascript" src="/statics/js/H-ui.js"></script>
	<script type="text/javascript" src="/statics/js/H-ui.admin.js"></script>



	<script type="text/javascript">
	
/* 
//页面加载完毕再执行 
$(function(){
  
  alert(${isParent == false});
  //如果是3级分类就去查询模板信息
   if(${isParent == false}){
    
    	$.post("/itemCategory/findTemplate/"+${id},{},function(data){
				if(data.status == "200"){
		
			  for(var x=0;x<data.data.length;x++){
			   var  valueStr="<li class='param'><ul>"; 
			   valueStr+="<li><input class='easyui-addParam' style='width: 150px;' name='group' value='"+data.data[x].group+"' />&nbsp; <a href='javascript:;' title='添加子项' class='btn btn-xs btn-success addParam'> <i class='glyphicon glyphicon-plus'></i></a></li> ";
			    
			     for(var i=0;i<data.data[x].params.length;i++){
			        
			      valueStr+="<li><span>|-------</span><input style='width: 150px;' class='easyui-delParam' name='param' value='"+data.data[x].params[i]+"' />&nbsp; <a href='javascript:;' title='删除' class='btn btn-xs btn-pink ads_link delParam'> <i class='glyphicon glyphicon-minus'></i>
			
			     }
			    valueStr+="</ul>"
			    
			    $("#templateul").append(valueStr);
			    
			     }	  
				  
		    }else{
				alert(data.msg);
		 }
	 });
   }
});
	 */
	
	
	//模板提交
$("#templateulAdd").on("click",function(){
   
   var arr = [];
  

   //遍历  name=group的表单   
   $("#templateul [name=group]").each(function(i,f){ 
       
       var g=[];//值
       var key= this.value;//分组
        //跟据当前这个  name=grpop的对象去查找当前对象所在的ul中  name=param的表单对象
       var p = $(this).parentsUntil("ul").parent().find("[name=param]");
        //获取到 对象后遍历每个name=param的对象 
        p.each(function(a, b) {         
        if(this.value.trim() == ""){
          alert("请填写完整");
         return false;
        }
         g.push(this.value);//取出它们的值。存入 g[] 数组
        
        });
        
        
       if(key.trim()!= "" && g.length>0){//判断 分组， 和值是不是为空
         arr.push({ //不是空则存入 数组
          "group":key,
          "params":g
         });
       }else{
        return false;
       }   
   });
      
      
      //添加操作
      	var url = "/itemCategory/addTemplate/"+${id};
			$.post(url,{"paramData":JSON.stringify(arr)},function(data){
				if(data.status == "200"){
				  alert("添加成功");	
				}else{
				alert(data.msg);
				}
			});
			
			
});
 
	
	
	
	
//模板重置
$("#templateulRest").on("click",function(){

  $("#templateul").empty();//清空ur中的内容

});





//添加分组	
/* $("#group").on("click",function(){ 

  var temple = $(".itemParamAddTemplate li").eq(0).clone();  //克隆一个<li>  <ul><li></li></ul></li>   
			  
			  $("#templateul").append(temple);  //将li添加到  id #templateul 的ul中 
			  
			  temple.find(".addParam").click(function(){//给 添加分组的li中的 <a>标签 添加   点击   添加子分类 事件
			  
				  var li = $(".itemParamAddTemplate li").eq(2).clone();//克隆到
				
				  li.find(".delParam").click(function(){
						$(this).parent().remove();
				  });
				  li.appendTo($(this).parentsUntil("ul").parent());
			  });
			  
			  temple.find(".delParam").click(function(){
				  	$(this).parent().remove();
			  });

});
 */

$("#groupProject").on("click",function(){ 
alert("----");

  $("#templateform").html("<input  type='text' name='axc'/>");
});
		
	
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-user-add").Validform({
		tiptype:2,
		callback:function(form){
			form[0].submit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
});

//图片选择
/* $("#catelog").on("change",function(){
    
     var info=$(this).val();
     
     alert(info);
     
     $.ajax({
			"type" : "get", //请求类型
			"url" : "/itemCategory/addcate",
			"data" : $("#formAdd").serialize(),
			"datatype" : "JSON", //请求返回的数据类型
			"success" : function(result) { //返回请求结果 	
			   if(result.status == "200"){
			     alert("添加成功");    
			   //  parent.location.reload();//刷新页面			     
			      $('#gridSystemModal').modal('hide');
			      window.parent.loadInfo();//只刷新父页面
			   }else{
			    alert("添加失败");
			   }			
			},
			error : function() {
				alert("加载出错");
			}
		});
     
     
     
     
     
   
}); */


//上传图片

$("#cateload").on("click",function(){

  				 var formData = new FormData(document.getElementById("catelogfrom"));//表单id
				$.ajax({
				   url:"/itemCategory/uploadCatelog",
				   type:'POST',
				   data: formData,
				   dataType: "json",
				   async: false,
				   cache: false,
				   contentType: false,
				   processData: false,
				   success: function (data) {
				   if(data.status =="200"){
				     alert("上传成功!");
				   }else{
				   alert(data.msg);
				   }
				   },error:function(){
				     alert("上传出错");
				   }
				   
				   });
});


    //添加分组	
$("#group").on("click",function(){ 

  var temple = $(".itemParamAddTemplate li").eq(0).clone();  //克隆一个<li>  <ul><li></li></ul></li>   
			  
			  $("#templateul").append(temple);  //将li添加到  id #templateul 的ul中 
			  
			  temple.find(".addParam").on("click",function(){//给 添加分组的li中的 <a>标签 添加   点击   添加子分类 事件
			  
				  var li = $(".itemParamAddTemplate li").eq(2).clone();//克隆到
				
				  li.find(".delParam").click(function(){
						$(this).parent().remove();
				  });
				  li.appendTo($(this).parentsUntil("ul").parent());
			  });
			  
			  temple.find(".delParam").click(function(){
				  	$(this).parent().remove();
			  });

});








<!-- 添加-->
$(".btn-warning").on("click",function(){
 <!-- 表单重置-->
$("#formAdd")[0].reset();
 
});

/**
 验证非空

*/
function checkAddNull(){
 $("#name").next().css({'color':'black'});
    $("#name").next().text("");
 var name =$("#name").val();
 if(name == "" ||name == null){
   $("#name").next().css({'color':'red'});
   $("#name").next().text("请输入分类名称");
   return false;
 }
 return true;
}



//点击提交进行添加操作
$("#addCate").on("click",function(){


 if(!checkAddNull()){
    return;
 }

/*
添加操作
*/
$.ajax({
			"type" : "get", //请求类型
			"url" : "/itemCategory/addcate",
			"data" : $("#formAdd").serialize(),
			"datatype" : "JSON", //请求返回的数据类型
			"success" : function(result) { //返回请求结果 	
			   if(result.status == "200"){
			     alert("添加成功");    
			   //  parent.location.reload();//刷新页面			     
			      $('#gridSystemModal').modal('hide');
			      window.parent.loadInfo();//只刷新父页面
			   }else{
			    alert("添加失败");
			   }			
			},
			error : function() {
				alert("加载出错");
			}
		});


});

//删除
$("#deleteCate").on("click",function(){
    
    if(!window.confirm("确定要删除该类别及其子类别吗？")){
         return;
    }
    
    $.ajax({
			"type" : "get", //请求类型
			"url" : "/itemCategory/deleteCate/"+${id}+"/"+${isParent},
			"datatype" : "JSON", //请求返回的数据类型
			"success" : function(result) { //返回请求结果 	
			   if(result.status == "200"){
			     alert("刪除成功");    
			  //   parent.location.reload();//刷新页面
			      window.parent.loadInfo();//只刷新父页面
			   }else{
			    alert("刪除失败");
			   }			
			},
			error : function() {
				alert("刪除出错");
			}
		});
    
    
});

//修改商品分类信息
$("#updateCate").on("click",function(){  
  var cateName=$("#cateName").val();
  var id=$("#id").val();
  var is_Parent=$("#is_Parent").val();
  var flag=${isParent};
  
  
  if(cateName == ""){
   $("#cateName").next().css({'color':'red'});
   $("#cateName").next().text("分类名称不能为空");
   return;
  }
  if(id == 0){
    alert("顶级分类不能修改!");
   return;
  }
  if(flag){//提醒分类是否修改 分类级别
     if(is_Parent == 0){     
       if(!confirm("该分类当前为父分类，如果将其修改为子分类将会导致其下的子类不可用，是否继续？")){
           return;
       }
     }
  }
  
 
 $.ajax({
			"type" : "get", //请求类型
			"url" : "/itemCategory/updateCate",
			"data":$("#form-user-add").serialize(),
			"datatype" : "JSON", //请求返回的数据类型
			"success" : function(result) { //返回请求结果 	
			   if(result.status == "200"){
			     alert("修改成功");    
			  //parent.location.reload();//刷新  本页面和父 页面
			   window.parent.loadInfo();//只刷新父页面
			   }else{
			    alert("修改失败");
			   }			
			},
			error : function() {
				alert("修改出错");
			}
		});

});


</script>


<script type="text/javascript">
 
  $(document).ready(function(){
  
  
    

   
  
  
  });

</script>


</body>
</html>