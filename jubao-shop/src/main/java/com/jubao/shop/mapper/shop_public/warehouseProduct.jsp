<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <div class="mem_t">出售中的宝贝</div> -->
<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">出售中的宝贝</div>

  <!-- Table -->
  <table class="table">
    <tr><td style="width: 70px">商品名</td><td style="width: 50px">图片</td><td style="width: 50px">价格</td><td style="width: 50px">库存</td><td style="width: 50px"> 状态</td><td style="width: 50px">销量</td><td>操作</td></tr>
      <c:forEach items="${productList }" var="product">
        <tr>
           <td style="width: 50px">${product.title }</td>
            <td style="width: 50px"><img src="http://119.29.195.240${product.getImg()[0] }" width="80px" height="30px" ></td>
            <td style="width: 50px">${product.sell_point }</td>
            <td style="width: 50px">${product.price }</td>
            <td style="width: 50px">${product.num }</td>
            <td style="width: 50px">${product.status }</td>
            <td style="width: 50px">${product.haveSales }</td>
            <td style="width: 50px"><a>修改</a>&nbsp;&nbsp;&nbsp;</span><a>删除</a></td>
        </tr>
     </c:forEach>
  </table>
</div>