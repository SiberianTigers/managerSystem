/**
 * 
 * 异步加载页面
 * 
 */
/*$(".left_menu").on("click", function() {
	var url = $(this).attr("url");
	$.ajax({
		url : url,

		type : "get",

		dataType : "html",

		success : function(data) {
			$("#view").empty();
			$("#view").append(data);
			$(this).text();
		},
		error : function() {
			alert("加载页面失败");
		}
	});
});
*/
//选择菜单
function select_menu(obj) {
	var url = $(obj).attr("url");
	$.ajax({
		url : url,
		type : "get",

		dataType : "html",

		success : function(data) {
			$("#view").empty();
			$("#view").append(data);
			$(".left_menu").children().css("color", "black");
			$(obj).children().css("color", "red");
		},
		error : function() {
			alert("加载页面失败");
		}
	});

}

/***
 * 查询订单数量
 */
function loadOrderStatusNumber() {
	var shopid = $("#shopid").val();
	$.ajax({
		url : "/shop/shop/findOrderNumber",
		type : "POST",
		data : {
			"shopid" : shopid
		},
		datatype : "JSON",
		success : function(data) {
			var str = "订单提醒：<font style='font-family:'宋体';'><a href='javaScript:void(0)'   class='left_menu' url='/shop/shop/findOrderByShop?status=0&&sid=" + shopid + "' onclick='select_menu(this)' ><span>待付款(" + data.dfk + ")</span></a>&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;<a href='javaScript:void(0)'   class='left_menu' url='/shop/shop/findOrderByShop?status=1&&sid=" + shopid + "' onclick='select_menu(this)' ><span>待发货(" + data.yfk + ")</span></a></font>";
			$("#OrderInfo").empty();
			$("#OrderInfo").append(str);
		},
		error : function() {}
	});


}
//延迟加载
$(function() {
	setTimeout('loadOrderStatusNumber()', 3000);
});




