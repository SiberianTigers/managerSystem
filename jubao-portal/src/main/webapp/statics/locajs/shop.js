/**
 * 
 * 异步加载页面
 * 
 */
$(".left_menu").on("click", function() {
	var url = $(this).attr("url");
	$.ajax({
		url : url,

		type : "get",

		dataType : "html",

		success : function(data) {
			$("#view").empty();
			$("#view").append(data);
		},
		error : function() {
			alert("加载页面失败");
		}
	});
});






