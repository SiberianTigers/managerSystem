

//发送验证码
function checksendMessage(){
	
	var phone=$("#userPhone").val();
	var info1=$("#info1");
	var getMessage=$("#getMessage");//获取按钮对象
	if(phone ==""){		
		info1.html("请输入手机号码");
		return false;
	}else if(phone.length!=11){
		
		info1.html("格式不正确,输入的手机号有误");
		
	   return false;
	}else{
		return true;
	}
}





//获取验证码-------------------yes
var MessageClick=function() {

	if(!checksendMessage()){
		return;
	}
	var phone=$("#userPhone").val();
	var info1=$("#info1");
	var getMessage=$("#getMessage");//获取按钮对象
	$.ajax({
		"url":"/login/userlogin/isPhone",
	   "method":"post",
		 data:{
			"phone":phone
			 
		 },
	  dataType:"JSON",
	  success: function(result){
		  if(result.status== 200){
			  info1.html(result.data);
			  getMessage.unbind("click");
			  var count=60;
		  var setInt=setInterval(function() {			 
				 count--;
				  getMessage.html(count+"秒后重新获取");
				 
				    if(count==0){
				    	getMessage.bind('click',MessageClick);
				    	getMessage.html("点击获取验证码");
				    	clearInterval(setInt);
				    } 
			 },1000);
			 
		  }else if(result.status== 400){			  
			  info1.html(result.msg);
		  }
	  }
	});
}


//  验证码框  失去焦点事件
$(document).ready(function(){
	
	$("#phoneInfo").blur(function(){
		
		checkMessage();
					
	});
	
	$("#addButton").bind('click', function() {

		  if(checkMessage()==false){
			  return;
			}

			var userPhone=$("#userPhone").val();
			var password=$("#password").val();
			var password2=$("#password2").val();
			var info3=$("#info3");
			var info4=$("#info4");
			info3.html("");
			info4.html("");
			
			if(password ==""){
				info3.html("请设置登录密码");
				return;
			}
			if(password2 ==""){
		        info4.html("请输入确认密码");
			  return;
			 }
			if(password !=password2){
				info4.html("输入的确认密码不一致");
				  return;
			}
			
			$.ajax({
				"url":"/login/userlogin/addNewUser",
				 "Type":"POST",
				 data:{
				 "userPhone":userPhone,
				 "password":password
				 }
				 ,
			  dataType:"JSON",
			  success: function(result){
				  if(result.status== 200){
			         
					  alert(result.data+"3秒后自动跳转到登录页面");
			         
			         setInterval(function() { 
			         	window.location.href="/login/userlogin/tologin";
			         },3000)
				  }else{			  
					  alert(result.msg);
				  }
			  }
			});
				
	});
	
	

	
	//获取验证码
	$("#getMessage").bind('click',MessageClick);
	
});

/**
 * 验证接受到的短信是否正确
 */
function checkMessage(){
	var phone=$("#userPhone").val();
	var info1=$("#info1");
	var message=$("#phoneInfo").val();
	var info2=$("#info2");
	info1.html("");
	info2.html("");
	if(phone ==""){	
		info1.html("请输入手机号码");
		return false;
	}else if(phone.length!=11){	
		info1.html("格式不正确,输入的手机号有误");	
	   return false;
	}else if(message ==""){
    	return false;
    }else if(message.length!=4){
		return false;
	}
	 
	
	$.ajax({
		"url":"/login/userlogin/checkPhoneMessageInfo",
		 "Type":"POST",
		 data:{
			"phone":phone,
			 "phoneMessage":message
		 },
	  dataType:"JSON",
	  success: function(result){
		  if(result.status== 200){
			  info2.html(result.data);
		     $("#password").attr("readOnly",false);
			 $("#password2").attr("readOnly",false);
			 return true;
		  }else if(result.status== 400){			  
			  info2.html(result.msg);
			  return false;
		  }
	  }
	});
	
}



//完成注册功能
function saveUser(){
  	
  if(checkMessage()==false){
	  return false;
	}

	var userPhone=$("#userPhone").val();
	var password=$("#password").val();
	var password2=$("#password2").val();
	var info3=$("#info3");
	var info4=$("#info4");
	info3.html("");
	info4.html("");
	
	if(password ==""){
		info3.html("请设置登录密码");
		return false;
	}
	if(password2 ==""){
        info4.html("请输入确认密码");
	  return false;
	 }
	if(password !=password2){
		info4.html("输入的确认密码不一致");
		  return false;
	}
	
	$.ajax({
		"url":"/login/userlogin/addNewUser",
		 "Type":"POST",
		 data:{
		 "userPhone":userPhone,
		 "password":password
		 }
		 ,
	  dataType:"JSON",
	  success: function(result){
		  alert(result.status);
		  if(result.status == 200){			  
			  alert(result.data+"3秒后自动跳转到登录页面");
	         
	         setInterval(function() { 
	         	window.location.href="/login/userlogin/tologin";
	         },3000)
		  }else{			  
			  alert(result.msg);
		  }
	  }
	});
	
}