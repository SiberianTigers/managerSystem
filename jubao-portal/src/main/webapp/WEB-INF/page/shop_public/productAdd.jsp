<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="/statics/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript"
	src="/statics/zTree/js/jquery.ztree.all-3.5.min.js"></script>

<div class="mem_t">发布宝贝</div>
<form class="form-horizontal" id="proudctform">


	<div class="form-group">
		<label for="inputEmail3" class="col-sm-2 control-label">商品类别</label>
		<div class="col-sm-3">
			<input type="button" value="选择类目" type="button"
				class="btn  btn-warning" data-toggle="modal" id="selectCate" />
			<!-- <input type="button" value="选择类目" type="button"
				class="btn  btn-warning" data-toggle="modal"
				data-target="#gridSystemModal" /> -->
			<input type="hidden" id="Cateid" name="cid"> &nbsp;<span></span>
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
			<input type="text" class="form-control" name="sell_point" id="sell_point">
		</div>
	</div>

	<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">商品价格</label>
		<div class="col-sm-3">
			<input type="number" class="form-control" name="price" id="price" onkeyup="value=value.replace(/[^1234567890-]+/g,'')">
		</div>
	</div>
	<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">库存数量</label>
		<div class="col-sm-3">
			<input type="number" class="form-control" name="num" id="num" onkeyup="value=value.replace(/[^1234567890-]+/g,'')">
		</div>
	</div>

	<!-- 图片上传插件 -->
	<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">图片上传</label>

		<div class="col-sm-10">
			<ul class="upload-ul clearfix">
				<li class="upload-pick">
					<div class="webuploader-container clearfix" id="goodsUpload">
						<div class="webuploader-pick"></div>
						<div id="rt_rt_1cqppgmtrh231s611ie9g4gc271"
							style="position: absolute; top: 0px; left: 0px; width: 120px; height: 90px; overflow: hidden; bottom: auto; right: auto;">
							<input type="file" name="file"
								class="webuploader-element-invisible" multiple="multiple"
								accept=""> <label
								style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
						</div>
					</div>
				</li>
			</ul>
		</div>

	</div>


  	<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">商品规格</label>
		<div class="col-sm-3">
			<input type="number" class="form-control" name="num" id="num" onkeyup="value=value.replace(/[^1234567890-]+/g,'')">
		</div>
	</div>



	<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">商品描述</label>
		<div class="col-sm-9">
			<textarea style="width:100%;height:300px;visibility: hidden;"
				id="v_content" name="v_content"> 
			</textarea>
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
	$(function() {
		//上传图片
		var $tgaUpload = $('#goodsUpload').diyUpload({
			url : '/uploadFilePath',
			success : function(data) {},
			error : function(err) {},
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


	});
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
		callback : {//点击时间
			onDblClick : zTreeOnClick
		}
	};


	//双击分类显示对应的分类信息
	function zTreeOnClick(event, treeId, treeNode) {
		if (treeNode.parent == true ) {//判断是否是父类
				$("#Cateid").val(treeNode.id); //设置分类id
		$("#Cateid").next().text(treeNode.name);
		$("#gridSystemModal").modal('hide');
		}
	
	};

//============================点击选择类目弹出模态框================================================================


	//点击弹出选择商品类目框
	$("#selectCate").on("click", function() {

		$("#gridSystemModal").modal('show');
		
		/* 
		
				//初始化加载分类菜单	
				$.ajax({
					url : "http://localhost:8081/rest/Categories/loadCateType",
					type : "GET",
					dataType : "jsonp", //指定服务器返回的数据类型
					async : false,
					jsonpCallback :"showData", //指定回调函数名称
					success : function(data) {
						//alert("---------"+data);
					}
				});
		 */
		 
		 
		//初始化加载分类菜单	
		$.ajax({
			url : "http://localhost:8083/shop/loadCate.json",
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


</script>
