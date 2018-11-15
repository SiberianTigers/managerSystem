<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测试获取图片颜色</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/statics/css/style.css">
	<link rel="stylesheet" href="/statics/lbtjs/styles.css" />
	  <script type="text/javascript" src="/statics/js/jquery-1.11.1.min_044d0927.js"></script>
	<script type="text/javascript" src="/statics/js/jquery.bxslider_e88acd1b.js"></script>
      <script type="text/javascript" src="/statics/js/jquery-1.8.2.min.js"></script>
	<!--   <script type="text/javascript" src='/statics/lbtjs/jquery.js'></script> -->
	   <script type="text/javascript" src='/statics/lbtjs/jquery.adaptive-backgrounds.js'></script>
  <script type="text/javascript" src='/statics/lbtjs/waypoints.min.js'></script>
   <script type="text/javascript" src='/statics/lbtjs/main.js'></script>
  
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
     
     
  <body>
  
   <div class='image-wrapper slow'>

  <div class='inner'>

    <span class='color'>

      <span class='swatch'></span>

    </span>

    <img src="/statics/lbtjs/images/1.jpg" data-adaptive-background='1' data-description='grandpa'>

  </div>

</div>



<div class='image-wrapper'>

  <div class='inner'>

    <span class='color'>

      <span class='swatch'></span>

    </span>

    <img src="/statics/lbtjs/images/2.jpg" data-adaptive-background='1'>

  </div>

</div>



<div class='image-wrapper'>

  <div class='inner'>

    <span class='color'>

      <span class='swatch'></span>

    </span>

    <img src="/statics/lbtjs/images/3.jpg" data-adaptive-background='1' data-description='boy'>

  </div>

</div>






<div class='image-wrapper'>

  <div class='inner'>

    <span class='color'>

      <span class='swatch'></span>

    </span>

    <img src="/statics/lbtjs/images/4.jpg" data-adaptive-background='1'>

  </div>

</div>





 
  
  
  <div class="banner">    	
                <ul class="slide_box bxslider">
                   <li><img src="https://aecpm.alicdn.com/simba/img/TB1W4nPJFXXXXbSXpXXSutbFXXX.jpg" width="800" height="401"/></li>
                    <li><img src="https://aecpm.alicdn.com/simba/img/TB1_JXrLVXXXXbZXVXXSutbFXXX.jpg" width="800" height="401"/></li> 
                    <li><img src="https://img.alicdn.com/tps/i4/TB1HjvoihnaK1RjSZFBSuwW7VXa.jpg_q60.jpg" width="800" height="401"/></li>  
                </ul>	       
  </div>
  
        <script type="text/javascript">
        //var jq = jQuery.noConflict();
        (function(){
            $(".bxslider").bxSlider({
                auto:true,
                mode:'fade',
                pager:true
            });
            
           $.adaptiveBackground.run();
            
        })();
        </script>
    <div>
      <img alt="取色" id="color" src="https://aecpm.alicdn.com/simba/img/TB1W4nPJFXXXXbSXpXXSutbFXXX.jpg">
    </div> 
<!--   https://img.alicdn.com/tps/i4/TB1HjvoihnaK1RjSZFBSuwW7VXa.jpg_q60.jpg
 --> 
  

 
  </body>
</html>
