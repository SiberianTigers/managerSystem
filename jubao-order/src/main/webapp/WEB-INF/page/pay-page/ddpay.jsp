<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<script type="text/javascript">
 var contextPath="${ctx}"
</script>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Last-Modified" content="201805120000">
    <meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi,initial-scale=1.0"/>
    <script src="/statics/pay-page/ddpay/lib/jquery-3.3.1.min.js"></script>
    <script src="/statics/pay-page/ddpay/lib/jquery.qrcode.js"></script>
    <script src="/statics/pay-page/ddpay/lib/utf.js"></script>
    <link rel="stylesheet" href="/statics/pay-page/ddpay/css/ddpayPC.css?v=201805120000" media="screen and (min-width : 960px)"></link>
    <link rel="stylesheet" href="/statics/pay-page/ddpay/css/ddpayMobile.css?v=201805120000" media="screen and (max-width : 960px)"></link>
    <title>选择支付</title>
</head>
<body class="bodyPay">

<script type="text/javascript">
  var  payTo=1;
  var appId=200;
  var playerId;
 var  goodsId=1;
  var orderId=1;
var  payPrice=200;
 var qrcodeURL;
  

</script>

<div id="contentPay" class="contentPay"></div>
<script src="/statics/pay-page/ddpay.min.js" charset="utf-8"></script>
</body>

 <script type="text/javascript">
    var websocket = null;
     //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/EasyBuyx/websocket");
   }
    else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
     websocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
         setMessageInnerHTML("WebSocket连接成功");
         //window.location.href=${ctx}+"/pre/login.jsp";
        // window.location.href="https://www.baidu.com";
    }
     //接收到消息的回调方法
     websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
        window.location.href="https://www.baidu.com";
     }

    //连接关闭的回调方法
    websocket.onclose = function () {
       setMessageInnerHTML("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
         closeWebSocket();
     }

    //将消息显示在网页上
     function setMessageInnerHTML(innerHTML) {
         document.getElementById('message').innerHTML += innerHTML + '<br/>';
     }
 
    //关闭WebSocket连接
     function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
     }
     
 $("#IDbtnBack").on("click",function(){
  window.location.href="http://localhost:8088/order/order/confirm_order.html";
 }); 
     
     
</script>
</html>