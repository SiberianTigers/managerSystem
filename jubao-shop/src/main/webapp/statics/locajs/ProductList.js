//正上出售中的商品

//點擊修改商品下架
$(".xiajia").on("click", function() {
	var pid = $(this).attr("pid");

	$.ajax({
		url : "/shop/shop/updateStatus",
		type : "get",
		data : {
			"pid" : pid,
			"status" : 2
		},
		dataType : "JSON",
		success : function(data) {
			if (data.status == "200") {
				$(".table").remove($(this).parent().parent()); //刪除
			}
		},
		error : function() {
			alert("加载页面失败");
		}
	});
});


/***
 * 上架
 */

$(".shangjia").on("click",function(){
	
	var pid = $(this).attr("pid");

	$.ajax({
		url : "/shop/shop/updateStatus",
		type : "get",
		data : {
			"pid" : pid,
			"status" : 1
		},
		dataType : "JSON",
		success : function(data) {
			if (data.status == "200") {
				$(".table").remove($(this).parent().parent()); //刪除
			}
		},
		error : function() {
			alert("加载页面失败");
		}
	});
	
});

/****
 * 刪除
 */

$("#deleteProduct").on("click",function(){
	$.ajax({
		url : "/shop/shop/deleteProduct",
		type : "get",
		data : {
			"pid" : pid,
			"status" : 1
		},
		dataType : "JSON",
		success : function(data) {
			if (data.status == "200") {
				$(".table").remove($(this).parent().parent()); //刪除
			}
		},
		error : function() {
			alert("加载页面失败");
		}
	});
	
	
});





