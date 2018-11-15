<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
    <script type="text/javascript">
       var contpath="${ctx}";
     </script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   
    <link rel="stylesheet" href="${ctx }/statics/css/regis/base.css"/>
    <link rel="stylesheet" href="${ctx }/statics/css/regis/register.css"/>
   
     <title>注册</title>
</head>

<body>
  
<header id="headNav">
    <div class="innerNav clear">
        <a class="fl" href="http://localhost:8083/"><img src="${ctx}/statics/images/logs.png" alt="聚宝网"/></a>

        <div class="headFont fr">
            <span>您好，欢迎光临 "聚宝网"<a href="${ctx}/pre/login2.jsp">请登录</a></span>
            <a class="helpLink" href="#"><i class="runbun"></i>帮助中心<i class="turnb"></i></a>
        </div>
    </div>
</header>
<!-- <section id="secTab"> -->
    <div class="innerTab">
        <h2>用户注册</h2>
        <form>
            <div class="tableItem">
                <span class="userPhone">手机号</span>
                <input type="text"  id="userPhone" name="userPhone"   placeholder="手机号"/>
            </div>
            <p id="info1" style="color: red;"></p>
            <div class="clear">
                <div class="tableItem onlyItem fl">
                    <span class="userPhone">验证码</span>
                    <input class="" type="text" id="phoneInfo" name="phoneInfo"  placeholder="验证码"/>
                </div>            
                <a  href="javascript:void(0)" class="tableText fr" id="getMessage" >获取验证码</a>
  
               </div>
            <p id="info2" style="color: red;"></p>
            <div class="tableItem">
                <span class="setPass">设置密码</span>
                <input type="password"  id="password"  placeholder="密码" name="password" readonly="readonly"/>
            </div>
            <p id="info3" style="color: red;"></p>
            <div class="tableItem">
                <span class="turePass">确认密码</span>
                <input type="password" id="password2"  placeholder="确认密码"   name="password2" readonly="readonly"/>
            </div>
            <p id="info4" style="color: red;"></p>
            <p class="clickRegist">点击注册，表示您同意 "聚宝网" <a href="#">《服务协议》</a></p>
            <p  class="tableBtn" id="addButton"style="text-align: center;">同意协议并注册</p>
        </form>
    </div>
<!-- </section> -->
<footer id="footNav">
     <script type="text/javascript" src="/statics/js/common/jquery-1.8.2.min.js"></script>
    
   <script type="text/javascript" src="/statics/js/register/registerpage.js"></script>
</footer>


</body>
</html>