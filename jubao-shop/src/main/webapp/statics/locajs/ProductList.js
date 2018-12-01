//正上出售中的商品

/****
 * 
 * 
 * 商品状态，1-正常，2-下架，3-删除，4移入仓库
 */


//点击商品下架
$(".xiajia").on("click", function() {
	var pid = $(this).attr("pid");
	var obj=$(this).parent().parent();
	if (!confirm("该商品正在销售中,确定要将该商品下架吗？")) {
		return;
	}	
	$.ajax({
		url : "/shop/shop/updateStatus",
		type : "get",
		data : {
			"pid" : pid,
			"status":"2"
		},
		datatype : "json",
		success : function(data) {
			if (data.status == "200") {
				alert("操作成功");
				 obj.remove();
				  }
		},
		error : function() {
			alert("操作出错");
		}
	});

});


/****
 * 
 * 删除商品
 */
$(".deleteProduct").on("click", function() {
	 var pid=$(this).attr("pid");
		var obj=$(this).parent().parent();
	$.ajax({
		url : "/shop/shop/deleteProduct",
		type : "get",
		data : {
			"pid" : pid,
		},
		datatype : "json",
		success : function(data) {
			if (data.status == "200") {
				alert("操作成功");
				 obj.remove();//清除当前行
			}
		},
		error : function() {
			alert("加载页面失败");
		}
	});
});

/****
 * 商品上架功能
 */
$(".shangjia").on("click",function(){
	var pid=$(this).attr("pid");		
	var obj=$(this).parent().parent();
	$.ajax({
		url : "/shop/shop/updateStatus",
		type : "get",
		data : {
			"pid" : pid,
			"status":"1"
		},
		datatype : "json",
		success : function(data) {
			if (data.status == "200") {
				alert("操作成功");
				obj.remove();
			}
		},
		error : function() {
			alert("加载页面失败");
		}
	});
	
});


/**
 * 修改商品
 */
function updateItem(obj) { 
	var pid = $(obj).attr("pid");
	$.ajax({
		url : "/shop/shop/updateItem",
		type : "get",
		data:{
			"pid":pid
		},
		dataType : "html",
		success : function(data) {
			$("#view").empty();
			$("#view").append(data);
		},
		error : function() {
			alert("加载页面失败");
		}
	});

}

