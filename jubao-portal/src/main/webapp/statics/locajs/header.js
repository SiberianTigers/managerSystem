
function showUser(){
}



$(function() {
	
	//jsop 请求用户登陆信息
	var cookies = $.cookie("JB_TOKEN");
	
	alert(cookies);
	$.ajax({
	    type: "get",
	     url : "http://127.0.0.1:8086/login/userlogin/getUser/" + cookies,
	    dataType: "jsonp",
	    jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
	    jsonpCallback:"showUser",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据
	    success: function(json){
	    	if(json.status == 200){
	    		$("#userInfo").empty();
	    		$("#userInfo").append("<a href='http://localhost:8086/login/user/userManager'>"+json.data.userCode+"</a>&nbsp;<a href='http://localhost:8086/login/userlogin/sessionOut'>退出</a>");
	    	}
	    	
	    },
	    error: function(){
	    }
	});

	

});

