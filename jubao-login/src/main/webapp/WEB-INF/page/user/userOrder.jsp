<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   <!--Begin 第一步：查看购物车 Begin -->
    <div class="content mar_20">
    	<table border="1" class="car_tab" style="width:1200px; margin-bottom:50px;" cellspacing="0" cellpadding="0">
          <tr>
            <td class="car_th">商品名称</td>
            <td class="car_th" >属性</td>
            <td class="car_th">购买数量</td>
            <td class="car_th" >单价</td>
            <td class="car_th" >购买店铺</td>
          </tr>
      <c:forEach items="${orderList}" var="order">  
         <tr><td colspan="5"> <fmt:formatDate value="${order.createtime }" pattern="yyyy-MM-dd"/> &nbsp;&nbsp;&nbsp; ${order.payment }</td></tr><!-- 店铺名 -->
         <c:forEach items="${order.orderDetailList }" var="orderDetail">
          <tr>
            <td>
            	<div class="c_s_img"><img src="http://119.29.195.240${orderDetail.picpath }" width="73" height="73" /></div>
                ${orderDetail.title }
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
