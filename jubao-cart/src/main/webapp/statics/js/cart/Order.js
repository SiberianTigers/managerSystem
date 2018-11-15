
function create_order(){//创建订单数据
   
	var address_check=$(".checked>input").val();//获取选择的地址id
	
	//alert(address_check);
	if(address_check ==""){		
		alert("请选中收货地址");
		return;
	}
	
	$.ajax({
		
		url:contextPath+"/OrderServlet",
		data:{
		"action":"create_Order",
         "address_check":address_check
		},
		type:"POST",	
		success: function(result){		 
		//	alert(result);

		  var info = eval("(" + result + ")");
			
		  window.location.href=contextPath+"/pay-page/ddpay.jsp?playerId="+info.playerId+"&&appId="+info.appId+"&&goodsPrice="+info.goodsPrice+"&&wx=1&&alipay=1&&succHide=1&&goodsName="+info.goodsName+"&&goodsId=1";
			
		}	
	});
	
	
	
}

/**
 * 显示订单片段
 */
function selectOrder(orderType){

	$.ajax({
		
		url:contextPath+"/OrderServlet",
		data:{
		"action":"to_Order_view",
         "orderType":orderType
		},
		type:"POST",
		dataType:"HTML",
		success: function(result){		 
        $("#info").empty();
        $(result).appendTo("#info");
		}	
	});
	
}

//付款
function fukuang(playerId,goodsName){
	
	alert(playerId+"==========="+goodsName);
	
	  window.location.href=contextPath+"/pay-page/ddpay.jsp?playerId="+playerId+"&&appId=XCcgNxBCcrC90F2FF78C3CDE87A0E383&goodsPrice=100&&wx=1&&alipay=1&&succHide=1&&goodsName="+goodsName+"&&goodsId=1";

	
}

//删除
function deleteOrder(orderid,isdefault){
	
	if(orderid ==""){
		
		return;
	}	
$.ajax({		
		url:contextPath+"/OrderServlet",
		data:{
		"action":"deleteOrder",
         "orderid":orderid
		},
		type:"POST",
		dataType:"JSON",
		success: function(result){		 
			 if(result.status==1){    		
		
				 window.location.href=contextPath+"/OrderServlet?action=UserOrderShow&orderType="+isdefault+""
	    	 }else{
	    		 alert(result.message);
	    	 }
		}	
	});
	
}


