<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if IE 6]>
    <script src="js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->
        
  <!--   <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>    
        
	<script type="text/javascript" src="js/select.js"></script> -->
        
     
<title>管理中心</title>
</head>
<body>  
<!--Begin Header Begin-->
 <%@include file="shop_header.jsp" %>
        <!--End 所在收货地区 End-->
     <%--  <%@include file="../../common/searcheBar.jsp" %> --%>
<!--End Header End--> 
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
	<div class="m_content">
   		
   		<!-- 判断用户类型 ：普通用户和管理员用户显示的菜单不一样 -->
            
      <%--        <%@include file="../userMenu/leftMenu.jsp" %> --%>



        		<div class="m_right">
            <p></p>
            <div class="mem_tit">个人资料</div>   
            
            <div class="mem_tit" style="font-size: 12px">
            	<p>个人基本资料&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${requestScope.UpdateInfo } </span></p>
            </div> 
            <form action="${ctx }/login?action=UpdateUserInfo" method="post" id="from1" enctype="multipart/form-data">
            <table border="0" class="add_tab" style="width:930px;"  cellspacing="0" cellpadding="0">
                 <tr>
                <td align="right">当前头像:</td>
                <td style="font-family:'宋体';"><img src="${ctx}/files/${sessionScope.loginUser.imginfo}" alt="" /><input type="file"  class="add_ipt" name="imagex"/>仅支持、PNG、GIF、JEPN </td>            
              </tr>
                <tr>
                <td align="right">昵称:</td>
                <td style="font-family:'宋体';"><input type="text"  class="add_ipt" name="userName" id="userName" value="${sessionScope.loginUser.userName }"/><span id="Info4" style="color: red;"></span></td>            
              </tr>
              <tr>
               <td align="right">真实姓名:</td>
                <td style="font-family:'宋体';"><input type="text"  class="add_ipt" name="names" id="names" value="${sessionScope.loginUser.names }" /><span id="Info2" style="color: red;"></span></td>
              </tr>
              <tr>
                <td align="right">性别:</td>
                <c:if test="${sessionScope.loginUser.sex==0 }">
                
                                <td style="font-family:'宋体';">男<input type="radio" checked="checked"  name="sex1" value="0"/>女<input type="radio"  name="sex1" value="1"/><span id="Info3" style="color: red;"></span></td>
                
                </c:if>
                  <c:if test="${sessionScope.loginUser.sex==1 }">
                
                                <td style="font-family:'宋体';">男<input type="radio"   name="sex1" value="0"/>女<input type="radio"  name="sex1" checked="checked" value="1"/><span id="Info3" style="color: red;"></span></td>
                
                </c:if>
              </tr>
           
             <tr>
                <td width="135" align="right">居住地</td>
                <td colspan="3" style="font-family:'宋体';">
                	<select class="jj" name="country" style="background-color:#f6f6f6;">
                      <option value="1" selected="selected">中国</option>
                    </select>
                      <!--省级 -->
                	<select class="jj" name="province" id="coutry1" onchange="Selectcheck()">
                      <option value="0">请选择...</option>                      
                      <c:forEach items="${requestScope.Address }" var="address1">
                          
                           <c:choose>
                               <c:when test="${requestScope.userinfoars.shenfen==address1.areaId }">
                                   <option value="${address1.areaId }" selected="selected">${address1.areaName }</option>
                               </c:when>
                               
                             <c:otherwise>
                                  <option value="${address1.areaId }">${address1.areaName }</option>
                             </c:otherwise>
                             
                           </c:choose>
                           
                      </c:forEach>
                      
                    </select>
                    
                    <!-- 城市 -->
                    <select class="jj" name="city" id="city" onchange="Selectcheck1()">
                             
                               <c:forEach items="${requestScope.city }" var="address1">
                          
                           <c:choose>
                               <c:when test="${requestScope.userinfoars.city==address1.areaId }">
                                   <option value="${address1.areaId }" selected="selected">${address1.areaName }</option>
                               </c:when>
                               
                             <c:otherwise>
                                  <option value="${address1.areaId }">${address1.areaName }</option>
                             </c:otherwise>
                             
                           </c:choose>
                           
                      </c:forEach>
                            
  
                    </select>
                    <!--区 县级 -->
                    <select class="jj" name="area" id="area">
 
                        <c:forEach items="${requestScope.quyu }" var="address1">
                          
                           <c:choose>
                               <c:when test="${requestScope.userinfoars.quyu==address1.areaId }">
                                   <option value="${address1.areaId }" selected="selected">${address1.areaName }</option>
                               </c:when>
                               
                             <c:otherwise>
                                  <option value="${address1.areaId }">${address1.areaName }</option>
                             </c:otherwise>
                             
                           </c:choose>
                           
                      </c:forEach>
                  
                    </select>
                </td>
                </tr>
                    
            </table>
            <div style="float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="add_b" value="保存"/></div>
            </form>
            
            
            
            
            
            
            
            
            
            
            
            
            
            <!-- 用户地址信息 -->
          <div id="userAddressInfo">
<%--                  <%@include file="userMenu/userAddress.jsp" %>
 --%>          </div>
			
        </div>
        
        
        
        
        
    </div>
	<!--End 用户中心 End--> 
    <!--Begin Footer Begin -->
 <%@include file="../public/footerx.jsp" %>
    <!--End Footer End -->    
</div>
     <script src="${ctx}/statics/js/cart/address.js" type="text/javascript"></script>
</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
