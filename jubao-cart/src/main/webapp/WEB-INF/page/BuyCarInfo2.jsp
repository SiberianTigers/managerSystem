<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="i_bg">  
    <div class="content mar_20">
    	<img src="${ctx}/statics/images/img2.jpg" />        
    </div>
    
        
            <div class="two_t">
            	选择收货地址
            </div>
            <ul class="pay" id="address_check">
        <c:forEach items="${requestScope.address }" var="ress">
             <c:choose>           
                <c:when test="${ress.isdefault==1 }">
                        <li  class="checked" onclick="addressAddClass();"><input type="hidden" value="${ress.id }"/> <span>${ress.address }<b>&nbsp;(${ress.remark }&nbsp;收)</b></span> <p>${ress.addressInfo }</p><div class="ch_img"></div></li>   
                </c:when>
                
                <c:otherwise>
                        <li  onclick="addressAddClass();"><input type="hidden" value="${ress.id }"/><span>${ress.address }<b>&nbsp;(${ress.remark }&nbsp;收)</b></span> <p>${ress.addressInfo }</p> <div class="ch_img"></div></li>       
                </c:otherwise>
                </c:choose>        
          </c:forEach>
            </ul>
    
    
    
    
    <!--Begin 第二步：确认订单信息 Begin -->
    <div class="content mar_20">
    	<div class="two_bg">
        	<div class="two_t">
            	<span class="fr"><a href="#">修改</a></span>确认订单信息
            </div>
            <table border="0" class="car_tab" style="width:1110px;" cellspacing="0" cellpadding="0">
              <tr>
                <td class="car_th" width="550">商品名称</td>
                <td class="car_th" width="140">属性</td>
                <td class="car_th" width="150">购买数量</td>
                <td class="car_th" width="130">单价</td>
                  <td class="car_th" width="130">小计</td>
              </tr>
              
              <c:forEach items="${sessionScope.cart.items }" var="cart">  
          <tr>
            <td>
            	<div class="c_s_img"><img src="${ctx}/files/${cart.product.fileName}" width="73" height="73" /></div>
                ${cart.product.name }
            </td>
            <td align="center">${cart.product.type1}<br/>${cart.product.type2}</td>
            <td align="center">${cart.quantity }</td>
            <td align="center" >${cart.product.price }</td>
            <td align="center" style="color:#ff4e00;">${cart.cost }</td>
          </tr>
        </c:forEach>  
            </table>
           
       
            
          <hr/>
       
   
   
   
            <table border="0" style="width:900px; margin-top:20px;" cellspacing="0" cellpadding="0">
              <tr>
                <td align="right">
                	该订单完成后，您将获得 <font color="#ff4e00">${sessionScope.cart.price }</font> 积分 ，以及价值 <font color="#ff4e00">￥0.00</font> 的红包 <br />
                    商品总价: <font color="#ff4e00">￥${sessionScope.cart.price }</font>  + 配送费用: <font color="#ff4e00">￥15.00</font>
                </td>
              </tr>
              <tr height="70">
                <td align="right">
                	<b style="font-size:14px;">应付款金额：<span style="font-size:22px; color:#ff4e00;">￥${sessionScope.cart.price+15 }</span></b>
                </td>
              </tr>
              <tr height="70">
<%--                 <td align="right"><a href="${ctx}/pay-page/ddpay.jsp?goodsPrice=100&&goodsName=好东西&&appId=XCcgNxBCcrC90F2FF78C3CDE87A0E383&&goodsId=1&&wx=1&&alipay=1&&succHide=1&&playerId=1"><img src="${ctx}/statics/images/btn_sure.gif" /></a></td>
${ctx}/pay-page/ddpay.jsp
 --%>           
               <td align="right"><a href="javascript:void(0)" onclick="create_order()"><img src="${ctx}/statics/images/btn_sure.gif" /></a></td>    
       </tr>
            </table>

            
        	
        </div>
    </div>
	<!--End 第二步：确认订单信息 End-->
    
    
    <!--Begin Footer Begin -->
  
  
    <!--End Footer End -->    
</div>
