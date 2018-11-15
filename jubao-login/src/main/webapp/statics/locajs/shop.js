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
function select_menu(obj){	
	var url =$(obj).attr("url");
	$.ajax({
		url : url,
		type : "get",

		dataType : "html",

		success : function(data) {
			$("#view").empty();
			$("#view").append(data);
			$(".left_menu").children().css("color","black");
			$(obj).children().css("color","red");
		},
		error : function() {
			alert("加载页面失败");
		}
	});
	
}



