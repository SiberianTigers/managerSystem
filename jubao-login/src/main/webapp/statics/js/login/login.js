/**
 * Created by bdqn on 2016/5/3.
 */
//登录的方法
/*function login(){
    var loginName=$("#loginName").val();
    var password=$("#password").val();
    $.ajax({
        url:contextPath+"/Login",
        method:"post",
        data:{loginName:loginName,password:password,action:"login"},
        success:function(jsonStr){
            var result=eval("("+jsonStr+")");
            if(result.status==1){
                window.location.href=contextPath+"/Home?action=index";
            }else{
                showMessage(result.message)
            }
        }
    })
}*/
function checkNull() {
	$("#info").text('');
	var username = $("#username").val();
	var password = $("#password").val();
	if (username == "") {
		return false;
	} else if (password == "") {

		return false;
	}
	return true;
}


//登陸請求
function login() {
	
	if (!checkNull()) {
		return;
	}
 
	var username = $("#username").val();
	var password = $("#password").val();
	var auto=$("#auto").prop('checked');
	
	$.ajax({
		url : "/login/userlogin/checkUserInfo",
		type : "post",
		datatype:"json",
		data : {
			"userCodeOrPhone" : username,
			"password" : password,
			"auto":auto
		},
		success : function(result) {
			if (result.status == "200") {
				var callBackUrl = $("#callBackUrl").val();
				if (callBackUrl == "" || callBackUrl == null) {
					window.location.href = "http://localhost:8083" //跳转到首页
				} else {
					window.location.href =callBackUrl; //跳转到回调的地方
				}
			} else if (result.status == "400") {
				$("#info").text(result.msg);
			}
		}
	});


}