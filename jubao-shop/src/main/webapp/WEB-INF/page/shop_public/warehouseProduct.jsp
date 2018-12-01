<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <div class="mem_t">出售中的宝贝</div> -->
<div class="panel panel-default">
	<!-- Default panel contents -->
	<div class="panel-heading">仓库中的宝贝</div>
	<!-- Table -->
	<table class="table" border="1" style="width: 98%;">
		<tr style="text-align: center;">
			<td>图片</td>
			<td>商品名</td>
			<td>价格</td>
			<td>库存</td>
			<td>状态</td>
			<td>销量</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${productList }" var="product">
			<tr style="text-align: center;">
				<td><img src="http://119.29.195.240${product.getImg()[0] }"
					width="40px" height="40px"></td>
				<td style="width: 12%">${product.title }</td>
				<%--  <td >${product.sell_point }</td> --%>
				<td>${product.price }</td>
				<td>${product.num }</td>
				<td>${product.status }</td>
				<td>${product.haveSales }</td>
				<td style="width: 16%"><a href="javaScript:void(0)"
					class="shangjia btn btn-success btn-xs" pid="${product.pid }">上架</a>&nbsp;&nbsp;&nbsp;<a class="btn btn-info btn-xs" pid="${product.pid }"  onclick="updateItem(this)" >修改</a>&nbsp;&nbsp;&nbsp;</span><a
					href="javascript:void(0)" class="deleteProduct btn btn-danger btn-xs" pid=${product.pid }>
						删除</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
	<script type="text/javascript" src="/statics/locajs/ProductList.js"></script>