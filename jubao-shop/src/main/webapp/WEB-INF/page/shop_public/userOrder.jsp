<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   <!--Begin 第一步：查看购物车 Begin -->
   <c:choose>
      
      <c:when test="${type == 1 }">
       <div class="mem_tit">待发货</div>
      </c:when>
      
     <c:otherwise>
      <div class="mem_tit">未付款的订单</div>
     </c:otherwise>
  
   </c:choose>
   
    <div>
    	<table border="1" style="width:98%" id="tab">
          <tr>
            <td class="car_th">商品名称</td>
            <td class="car_th" >属性</td>
            <td class="car_th">购买数量</td>
            <td class="car_th" >单价</td>
            <td class="car_th" >购买店铺</td>
          </tr>
      <c:forEach items="${orderList}" var="order">  
         <tr class="${order.id }"><td colspan="5">订单号:${order.id }&nbsp;&nbsp;&nbsp;下单时间:<%--  <fmt:formatDate value= --%>${order.createtime }<%--  pattern="yyyy-MM-dd HH:mm:ss"/>  --%>&nbsp;&nbsp;&nbsp;订单总金额: ${order.payment }&nbsp;&nbsp;&nbsp;
          下单用户：${order.userCode }
            <c:if test="${type == 1 }">
              &nbsp;&nbsp;&nbsp;<a  href="javaScript:void(0)" class="btn btn-primary btn-xs" data-toggle="modal" onclick="toShouHuo('${order.addressid}','${order.id}')">去发货</a>
          </c:if>          
          </td>
        </tr><!-- 店铺名 -->

         <c:forEach items="${order.orderDetailList }" var="orderDetail">
       
          <tr class="${order.id }">
            <td>
            	<div class="c_s_img"> <img src="http://119.29.195.240${orderDetail.picpath }" width="73" height="73" /></div>
            	 <!--  链接到商品详情页面 -->
                <a href="http://localhost:8083/product/product_details.html?pid=${orderDetail.itemid }">${orderDetail.title }</a>
            </td>
            <td align="center">${orderDetail.itemType}<%-- <br/>${cart.product.type2} --%></td>
            <td align="center">
                	<input type="text" value="${orderDetail.num }" id="number" class="car_ipt" />                    
            </td>
            <td align="center" style="color:#ff4e00;">${orderDetail.price }</td>
            <td align="center">${orderDetail.storeName }</td>
          </tr>
       
           </c:forEach>
          
       </c:forEach>    
       
        </table>
        
    </div>
	<!--End 第一步：查看购物车 End--> 
	
	<div class="modal fade in" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="display:none;  padding-right: 16px;">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
            <h4 class="modal-title" id="exampleModalLabel">发货</h4>
          </div>
          <div class="modal-body">
         <form id="orderfrom">
          <table border="1" style="width: 100%"> 
          <tr><td style="text-align: center;">订单号:</td><td style="width: 70%"><input name="orderCustom.id" type="text" id="orderid" readonly="readonly" style="width: 100%"/></td></tr>
          <tr><td style="text-align: center;"> 收货地址:</td><td style="width: 70%"><input name="addressInfo" type="text" id="address" readonly="readonly" style="width: 100%"/></td></tr>
           <tr><td style="text-align: center;">收货人:</td><td style="width: 70%"><input name="remark" type="text" id="userName" readonly="readonly" style="width: 100%"/></td></tr>
           <tr><td style="text-align: center;">运送快递:</td><td style="width: 70%"><select name="orderCustom.shippingname"><option value="1" >顺丰</option><option value="2" >圆通</option><option value="3" >中通</option></select></td></tr>
           <input id="rest" name="res" type="reset" style="display:none;" /> 
         </table>
        </form> 
          </div>
          <div class="modal-footer">
            <button type="button" onclick="fh()"  class="btn btn-primary">确认发货</button>
          </div>
        </div>
      </div>
    </div>
	
	<script type="text/javascript">
//收货
function toShouHuo(addressid,orderid){
	$.ajax({
		url : "/shop/shop/findUserGetAddress",
		type : "POST",
		data : {
			"address" : addressid
		},
		datatype : "JSON",
		success : function(data) {
			$("input[name='res']").click(); 
			$("#orderid").val(orderid);
			$("#address").val(data.sf +"-"+data.sq +"-"+data.qy+"-"+data.addressInfo);
			$("#userName").val(data.remark);
			$("#exampleModal").modal('show');
		},
		error : function() {}
	});
}

//确认发货
function fh(){
	$.ajax({
		url : "/shop/shop/confirm",
		type : "POST",
		data : $("#orderfrom").serialize(),
		datatype : "JSON",
		success : function(data) {
		  if(data.status == 200){
		      var orderid=$("#orderid").val();
		      $("."+orderid).remove();
			  alert("发货成功,请将宝贝打包等待快递公司上门取件!");
			  $("#exampleModal").modal('hide');
		  }
		},
		error : function() {
			alert("修改出錯");
		}
	});
	
}

</script>
	
	
	
	
