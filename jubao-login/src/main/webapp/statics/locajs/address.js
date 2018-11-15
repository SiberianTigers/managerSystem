//頁面加載完畢 ，加载地址
$(function() {

	$.ajax({
		url : "/login/address/getAddress",
		Type : "POST",
		data : {
			"parentId" : -1
		},
		dataType : "JSON",
		success : function(result) {
			if (result.status == 200) {
				$("#coutry1").empty();
				$("<option value='0' selected='selected'>请选择...</option>").appendTo("#coutry1");
				for (var x = 0; x < result.data.length; x++) {
					var city = "<option value=" + result.data[x].areaId + ">" + result.data[x].areaName + "</option>";
					$(city).appendTo("#coutry1");
				}
			} else {
				alert(result.msg);
			}
		}
	});



});


//页面加载完毕执行，延迟3秒执行 加载出店铺信息
$(document).ready(function() {
	setTimeout('loadUserAddress()', 3000); //延迟3秒
});

//异步加载出用户收获地址
function  loadUserAddress(){	
	$.ajax({
		url : "/login/user/ajaxUserAddress",
		type : "get",
		dataType : "html",
		success : function(data) {
			$("#userAddressInfo").empty();
			$("#userAddressInfo").append(data);
		},
		error : function() {
			alert("加载页面失败");
		}
	});
	
}




//省级联动市级
function Selectcheck() {
	var addres = $("#coutry1").val();
	$("#sf").val(addres); //设置选中的值
	$.ajax({
		url : "/login/address/getAddress",
		Type : "POST",
		dataType : "JSON",
		data : {
			"action" : "getCtiy",
			"parentId" : addres
		},
		success : function(result) {
			if (result.status == 200) {
				$("#city").empty();
				$("<option value='0' selected='selected'>请选择...</option>").appendTo("#city");
				for (var x = 0; x < result.data.length; x++) {
					var city = "<option value=" + result.data[x].areaId + ">" + result.data[x].areaName + "</option>";
					$(city).appendTo("#city");
				}
			} else {
				alert(result.msg);
			}
		}
	});


}
//市级联动区  县级
function Selectcheck1() {
	var addres = $("#city").val();
	$("#sq").val(addres); //设置选中的值
	$.ajax({
		url : "/login/address/getAddress",
		Type : "POST",
		dataType : "JSON",
		data : {
			"action" : "getCtiy",
			"parentId" : addres
		},
		success : function(result) {
			if (result.status == 200) {
				$("#area").empty();
				$("<option value='0' selected='selected'>请选择...</option>").appendTo("#area");
				for (var x = 0; x < result.data.length; x++) {
					var city = "<option value=" + result.data[x].areaId + ">" + result.data[x].areaName + "</option>";
					$(city).appendTo("#area");
				}
			} else {
				alert(result.msg);
			}
		}
	});
}

//三级分类选择事件
$("#area").on("change", function() {
	var addres = $(this).val();
	$("#qx").val(addres); //设置选中的值
});


//添加用户收货地址
function addUserAddress() {
	$("#Info").html("");
	$("#Info1").html("");
	$("#Info2").html("");
	$("#Info3").html("");
	$("#Info4").html("");

	var coutry = $("#coutry1 option:selected");
	var city = $("#city option:selected");
	var area = $("#area option:selected");
	var addressInfo = $("#addressInfo").val(); //获取详细地址
	var youbian = $("#youbian").val();
	var getName = $("#getName").val();
	var phone = $("#iphone").val();





	//alert(youbian+getName+phone+defaults);
	if (coutry.text() == "请选择..." || city.text() == "请选择..." || area.text() == "请选择...") {
		$("#Info").html("*请填写完整地区");
		return;
	} else if (addressInfo == "") {
		$("#Info1").html("*请填写详细收货地址");
		return;
	} else if (youbian == "") {
		$("#Info2").html("*请填写邮编");
		return;
	} else if (getName == "") {
		$("#Info3").html("*请填写收货人");
		return;
	} else if (phone == "") {
		$("#Info4").html("*请填写手机号码");
		return;
	}
	var defaults = 0;
	//判断是否选中默认地址
	/*		$("#defaultAddress").click(function(){
				if($(this).prop("checked")){
					defaults=1;
				}else{
					defaults=0;
				}		
			});*/

	if ($("#defaultAddress").prop("checked")) {
		defaults = 1;
	}
	//解决中文乱码问题
   /* var params= jQuery('#from1').serialize();
	params= decodeURIComponent(params,true);
    params= encodeURI(encodeURI(params));*/
	$.ajax({
		url : "/login/user/addUserGetAddress",
		type : "POST",
		datatype : "JSON",
		data :$('#from1').serialize(),
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(result) {
			if (result.status == 200) {				
				alert("新增成功");
				loadUserAddress();//加载刷新页面
			} else {
				alert(result.msg)
			}

		}
	});

}
/*
$(document).ready(function() {

	$(".defaultAdds").hover(function() {
		$(".tdAddress").css('display', 'block');
	}, function() {
		$(".tdAddress").css('display', 'none');
	})

});*/

//删除用户地址

function deleteUseraddress(addressid) {
	var flag = window.confirm("确定要删除该地址吗?")

	if (!flag) {
		return;
	}
	$.ajax({
		url : contextPath + "/Address",
		Type : "POST",
		dataType : "JSON",
		data : {
			"action" : "deleteuserAddressInfo",
			"addressid" : addressid
		},
		success : function(result) {
			if (result.status == 1) {
				showUserAddressView();
			} else {
				alert(result.message);
			}
		}
	});






}
//显示用户地址
function showUserAddressView() {
	$.ajax({
		url : contextPath + "/Address",
		data : {
			"action" : "userAddressInfo",
		},
		Type : "POST",
		dataType : "HTML",

		success : function(result) {
			$("#userAddressInfo").empty();
			$(result).appendTo("#userAddressInfo");

		}
	});

}






//設置默認地址
function setDefault(id){
	$.ajax({
		url : "/login/user/setDefault",
		type : "POST",
		datatype : "JSON",
		data :{
			"id":id
		},
	    success : function(result) {
			if (result.status == 200) {				
				loadUserAddress();//加载刷新页面
			} else {
				alert(result.msg)
			}

		}
	});
 
}

