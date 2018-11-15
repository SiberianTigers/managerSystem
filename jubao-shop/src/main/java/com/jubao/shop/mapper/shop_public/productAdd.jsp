<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="/statics/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript"
	src="/statics/zTree/js/jquery.ztree.all-3.5.min.js"></script>

<div class="mem_t">发布宝贝</div>
<form class="form-horizontal" id="proudctform"
	enctype="multipart/form-data">


	<div class="form-group">
		<label for="inputEmail3" class="col-sm-2 control-label">商品类别</label>
		<div class="col-sm-3">
			<input type="button" value="选择类目" type="button"
				class="btn  btn-warning" data-toggle="modal" id="selectCate" />
			<!-- <input type="button" value="选择类目" type="button"
				class="btn  btn-warning" data-toggle="modal"
				data-target="#gridSystemModal" /> -->
			<input type="hidden" id="Cateid" name="cid"/> &nbsp;<span></span>
		</div>
	</div>
	<div class="form-group">
		<label for="inputEmail3" class="col-sm-2 control-label">商品标题</label>
		<div class="col-sm-3">
			<input type="text" class="form-control" name="title" id="title">
		</div>
	</div>

	<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">商品卖点</label>
		<div class="col-sm-3">
			<input type="text" class="form-control" name="sell_point"
				id="sell_point">
		</div>
	</div>

	<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">商品价格</label>
		<div class="col-sm-3">
			<input type="number" class="form-control" name="price" id="price"
				onkeyup="value=value.replace(/[^1234567890-]+/g,'')">
		</div>
	</div>
	<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">库存数量</label>
		<div class="col-sm-3">
			<input type="number" class="form-control" name="num" id="num"
				onkeyup="value=value.replace(/[^1234567890-]+/g,'')">
		</div>
	</div>
    <div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">商品状态</label>
		<div class="col-sm-3">
		  <select class="form-control" name="status">
		     <option value="1">上新</option>
		     <option value="2">存放仓库</option>
		  </select>
		</div>
	</div>
	<!-- 图片上传插件 -->
	<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">图片上传</label>

		<div class="col-sm-10">
		<!-- 	<ul class="upload-ul clearfix">
				<li class="upload-pick">
					<div class="webuploader-container clearfix" id="goodsUpload"></div>
				</li>
			</ul> -->
		<ul class="upload-ul clearfix">	
		<li class="upload-pick">
<!--         <input type="file" name="file" multiple="multiple"/><br> -->
         <input type="file" name="file" multiple="multiple"/><br>
        </li>
		</ul>
		</div>

	</div>


	<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">商品规格</label>
		<input type="hidden" name="temp_value_add" id="temp_value_add" />
		<!-- 存放商品规格项的 -->
		<div class="col-sm-3" id="template">
			<!--  <ul style="text-align: center;">template
		   	<li><p style="text-align: center;background-color:orange;" name="group">模板</p></li>
		     <li><p name="params" > 颜色  <input type="text" name="temp_value" /></p></li>
		     <li><p name="params"> 颜色  <input type="text" name="temp_value"/></p></li>
		     <li><p name="params"> 颜色  <input type="text" name="temp_value"/></p></li>
	  </ul>   -->


		</div>
	</div>



	<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">商品描述</label>
		<div class="col-sm-9">
			<input type="hidden" name="desc" id="desc">
			<!-- 存放text域中的文本 -->
			<textarea style="width:100%;height:300px;visibility: hidden;"
				id="v_content" name="v_content"> 
xx 
			</textarea>
		</div>
	</div>


	<div class="form-group">
		<div class="col-sm-11" style="text-align: right;">
			<button type="button" class="btn  btn-warning" id="productAdd">
				<i class="icon-edit align-top bigger-125"></i>确认提交
			</button>
		</div>
	</div>
</form>



<!-- Modal -->
<div id="gridSystemModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="gridModalLabel" style="display: none;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="gridModalLabel">选择商品类目</h4>
			</div>
			<div class="modal-body" style="height:300px; overflow:scroll;">




				<div id="treeDemo" class="ztree">

					<!-- 此处就是商品分类的树形菜单的值 -->
				</div>




			</div>
			<!-- 按钮组 -->
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<!-- 				<button type="button" class="btn btn-primary" id="addCate">添加</button> -->
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<!-- Modal   end-->



<script type="text/javascript"
	src="/statics/js/uploadfile/webuploader.min.js" />
<script type="text/javascript" src="/statics/js/uploadfile/diyUpload.js" />
<script>
/* 
	$(function() {
	
	
	
		//上传图片
		var $tgaUpload = $('#goodsUpload').diyUpload({
			url : "http://localhost:8085/shop/shop/uploadFile",
			uploadSuccess : function(data) {
			  $.ajax({
                async : false,
                url : "http://localhost:8085/shop/shop/uploadFile",
                data : {
                    prodId : prodId,
                    imgUrl : data.message,
                    type : 1,
                    sort : 1
                },
                dataType : "JSON",
                type : "POST",
                success : function(result) {
                    if (result > 0) {
                        getPic(1);
                    }
                }
            });
			},
			error : function(err) {
				alert();
			},
			buttonText : '',
			accept : {
				title : "Images",
				extensions : 'gif,jpg,jpeg,bmp,png'
			},
			thumb : {
				width : 120,
				height : 90,
				quality : 100,
				allowMagnify : true,
				crop : true,
				type : "image/jpeg"
			}
		});


	}); */
</script>

<script>


	//=====================多文本编辑器=======================================================================


	var editor;
	$(function() {
		editor = TAOTAO.createEditor("#proudctform [name=v_content]");
		TAOTAO.init({
			fun : function(node) {

				TAOTAO.changeItemParam(node, "proudctform");

			}
		});
	});


	//===========================树形菜单=================================================================


	var zTreeObj;
	// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	var setting = {
		/*  check: {
		     enable: true,
		     chkboxType: {"Y":"s", "N":"ps"}
		 },
		 data: {
		     simpleData: {
		         enable: true
		     }
		 } */
		callback : { //点击时间
			onDblClick : zTreeOnClick
		}
	};

	//双击分类显示对应的分类信息
	function zTreeOnClick(event, treeId, treeNode) {
		if (treeNode.parent == true) { //判断是否是父类
			$("#Cateid").val(treeNode.id); //设置分类id
			$("#Cateid").next().text(treeNode.name);
			$("#gridSystemModal").modal('hide');
			//初始化加载分类菜单	 
			$.ajax({
				url : "http://localhost:8085/shop/shop/getTemplateModel/" + treeNode.id,
				type : "GET",
				dataType : "json", //指定服务器返回的数据类型
				success : function(data) { //加载出模板
					$("#template").empty();
					var paramData = eval('(' + data.paramData + ')');
					var tempStr = "";

					for (var x = 0; x < paramData.length; x++) {
						tempStr += "<ul style='text-align: center;'><p style='text-align: center;background-color:orange;' name='group' >" + paramData[x].group + "</p>";
						//参数项目
						for (var s = 0; s < paramData[x].params.length; s++) {
							tempStr += "<li><p name='params'>" + paramData[x].params[s] + "： <input type='text' name='temp_value'/></p></li>";
						}
						tempStr += "</ul>";
					}
					//加入到节点中
					$("#template").append(tempStr);


				}
			});  
			

		}

	};

	//============================点击选择类目弹出模态框================================================================


	//点击弹出选择商品类目框
	$("#selectCate").on("click", function() {

		$("#gridSystemModal").modal('show');


		//初始化加载分类菜单	
		$.ajax({
			url : "http://localhost:8085/shop/shop/loadCate.json",
			type : "GET",
			dataType : "json", //指定服务器返回的数据类型
			//async : false,
			//jsonpCallback :"showData", //指定回调函数名称
			success : function(data) {
				//alert("---------"+data);
				zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, data);
			}
		});
	});

	//验证非空 
	function checkFrom() {
		var cid = $("#Cateid").val();
		var title = $("#title").val();
		var sell_point = $("#sell_point").val();
		var price = $("#price").val();
		var num = $("#num").val();

  















		if (cid == "") {
			alert("请选择商品类别");
			return false;
		} else if (title == "") {
			$("#title").focus();
			return false;
		} else if (sell_point == "") {
			$("#sell_point").focus();
			return false;
		} else if (price == "") {
			$("#price").focus();
			return false;
		} else if (num == "") {
			$("#price").focus();
			return false;
		}
//===================商品规格======================================================		
         var temp_value=[];
        $("#template [name=group]").each(function(i, e) {
           var  g=[];
           var gname=$(this).text();//分组名称 
          
          	var p=$(this).parent().find("[name=params]");//查找父级中name=params的节点
          	 p.each(function(i, e) { 	 
          	 var  v=$(this).children();
           
           if(v.val().trim()!= ""){	  
          	  g.push({
          	  "k":$(this).text(),
          	  "v":v.val()
          	  });
          	 }else{
          	 alert("规格参数不能为空");
          	  return false;
          	 } 
          	 });
           //将数据加入到数组
           temp_value.push({
            "group":gname,
            "params":g       
           });	 
          });	
      
          var tempValue=JSON.stringify(temp_value);
          $("#temp_value_add").val(tempValue);//将数据存入表单中
         
 //=====================================================================================         
  /*        for(var x=0;x<temp_value.length;x++){
           alert(temp_value[x].group+"-----"+temp_value[x].params.toString());
         } 	 
          	  */    
       editor.sync();//将富文本编辑器中的内容设置到text域中
        var text_value=$("#v_content").val();
         
        if(text_value == ""){
        alert("请添加商品描述");
         return false;
        }else{
         $("#desc").val(text_value);
        }
          	
      return true;
   
	}



	//添加商品
	$("#productAdd").on("click", function() {
	
	   if(checkFrom()){
           
           		var formData = new FormData(document.getElementById("proudctform")); //表单id
			    		
					$.ajax({
						url : "/shop/shop/addPrduct",
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
	 
	});
	
</script>
