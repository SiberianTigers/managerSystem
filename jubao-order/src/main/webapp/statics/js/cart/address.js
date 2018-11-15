//省级联动市级
function Selectcheck(){
	
  var addres=$("#coutry1").val();
	
  $.ajax({
	  url:contextPath+"/Address",
	  Type:"POST",
	  dataType:"JSON",
	  data:{
		  "action":"getCtiy",
		  "parentId":addres
	  },
	  success: function(result){
		  $("#city").empty();
		  $("<option value='0' selected='selected'>请选择...</option>").appendTo("#city");
		  for(var x=0;x<result.length;x++){
           var city="<option value="+result[x].areaId+">"+result[x].areaName+"</option>";
		   $(city).appendTo("#city");
		  }
	  }
  });
  
	
}
//市级联动区  县级
function Selectcheck1(){
	
	  var addres=$("#city").val();
		
	  $.ajax({
		  url:contextPath+"/Address",
		  Type:"POST",
		  dataType:"JSON",
		  data:{
			  "action":"getCtiy",
			  "parentId":addres
		  },
		  success: function(result){
			  $("#area").empty();
			  $("<option value='0' selected='selected'>请选择...</option>").appendTo("#area");
			  for(var x=0;x<result.length;x++){
	           var city="<option value="+result[x].areaId+">"+result[x].areaName+"</option>";
			   $(city).appendTo("#area");
			  }
		  }
	  });	  		
	}




//添加用户收货地址
function addUserAddress(){
	 $("#Info").html("");
	 $("#Info1").html("");
	 $("#Info2").html("");
	 $("#Info3").html("");
	 $("#Info4").html("");
	 
	var coutry=$("#coutry1 option:selected");
	var city=$("#city option:selected");
	var area=$("#area option:selected");
	var addressInfo=$("#addressInfo").val();//获取详细地址
	var youbian=$("#youbian").val();
	var getName=$("#getName").val();
	var phone=$("#iphone").val();
	
	
	
	
	
	//alert(youbian+getName+phone+defaults);
	if(coutry.text()=="请选择..."||city.text()=="请选择..."||area.text()=="请选择..."){
	    $("#Info").html("*请填写完整地区");
		return;
	}else if(addressInfo ==""){
		 $("#Info1").html("*请填写详细收货地址");
		 return;
	}else if(youbian ==""){
		$("#Info2").html("*请填写邮编");
		 return;
	}else if(getName ==""){
		$("#Info3").html("*请填写收货人");
		 return;
	}else if(phone ==""){
		$("#Info4").html("*请填写手机号码");
		 return;
	}
	var defaults=0;
	//判断是否选中默认地址
/*		$("#defaultAddress").click(function(){
			if($(this).prop("checked")){
				defaults=1;
			}else{
				defaults=0;
			}		
		});*/
		
	if($("#defaultAddress").prop("checked")){
		defaults=1;
	}
		  $.ajax({
			  url:contextPath+"/Address",
			  Type:"POST",
			  dataType:"JSON",
			  data:{
				  "action":"addUserGetAddress",
				  "address":coutry.text()+city.text()+area.text(),
				  "addressInfo":addressInfo,
				  "youbian":youbian,
				  "getName":getName,
				  "phone":phone,
				  "defaults":defaults				  
			  },
			  success: function(result){
				  if(result.status==1){
		             window.location.href=contextPath+"/Address?action=getAddress";
				  }else{
					  alert(result.message)
				  }
				  
			  }
		  });	 		
		 		
}

$(document).ready(function(){
	
	$(".defaultAdds").hover(function() {
		$(".tdAddress").css('display','block');
	}, function() {
		$(".tdAddress").css('display','none');
	})
	
});		  

//删除用户地址

function deleteUseraddress(addressid){

 var flag=window.confirm("确定要删除该地址吗?")
 
 if(!flag){
	  return; 
  }
  $.ajax({
	  url:contextPath+"/Address",
	  Type:"POST",
	  dataType:"JSON",
	  data:{
		  "action":"deleteuserAddressInfo",
		  "addressid":addressid
	  },   
      success: function(result){ 	  
    	 if(result.status==1){    		
    		 showUserAddressView();
    	 }else{
    		 alert(result.message);
    	 }
      }
  });	  		
  
  
  
  
  
 
}
//显示用户地址
function showUserAddressView(){
	
	 $.ajax({
		  url:contextPath+"/Address",
		  data:{
			  "action":"userAddressInfo",
		  },
	      Type:"POST",
	      dataType:"HTML",
	    
	      success: function(result){ 	  
	    	 $("#userAddressInfo").empty();
	    	 $(result).appendTo("#userAddressInfo");

	      }
		  
	  });
	
}



