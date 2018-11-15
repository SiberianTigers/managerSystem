<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <div class="mem_t">出售中的宝贝</div> -->
<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">出售中的宝贝</div>

  <!-- Table -->
  <table class="table">
    <tr><td>商品名</td><td>图片</td><td>价格</td><td>库存</td><td>状态</td><td>销量</td><td>操作</td></tr>
      <c:forEach items="${productList }" var="product">
        <tr>
           <td >${product.title }</td>
            <td><img src="http://119.29.195.240${product.getImg()[0] }" width="80px" height="30px" ></td>
            <td>${product.sell_point }</td>
            <td>${product.price }</td>
            <td>${product.num }</td>
            <td>${product.status }</td>
            <td>${product.haveSales }</td>
            <td><a href="javaScript:void(0)" style="color: blue;" class="xiajia" pid="${product.pid }" >下架</a></td>
        </tr>
     </c:forEach>
  </table>
</div>