<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />    
<title>用户中心</title>
</head>
<body>  
<!-- header  头部 -->
<jsp:include page="shop_header.jsp"/>

<!--header -->


<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
	<div class="m_content">
	
	  <!-- 左侧菜单 -->
   	  <jsp:include page="leftMaven.jsp" />
   	
   	    <!-- 右侧内容域  start -->  
		<div class="m_right">
		
		<!-- 店铺基本信息 -->
        	<div class="m_des">
            	<table border="0" style="width:870px; line-height:22px;" cellspacing="0" cellpadding="0">
                  <tr valign="top">
                    <td width="115"><img src="/statics/images/user.jpg" width="90" height="90" /></td>
                    <td>
                    	<div class="m_user">用户名:${user.nickName }</div>
                        <div class="m_notice">
                        	用户中心公告！
                        </div>
                        <div class="m_notice">
                        	   订单提醒：<font style="font-family:'宋体';">待发货(<span>0</span>) &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 待收货(<span>1</span>)</font>
                        </div>
                    </td>
                    
                  </tr>
                </table>	
            </div>
            
          <!-- 使用ajax加载页面进来 -->
          <div id="view">
            
            
          </div>
            <!-- end  -->
           
        </div>
         <!-- 右侧内容域  end -->    
         
         
         
    </div> 
	<!--End 用户中心 End--> 
    <!--Begin Footer Begin -->
		<jsp:include page="footer.jsp" />
    <!--End Footer End -->    
    
	<script type="text/javascript" src="/statics/locajs/shop.js">	

	</script>
	
</div>


</body>

</html>
