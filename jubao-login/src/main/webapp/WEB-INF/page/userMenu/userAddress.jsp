<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="address">
	<div class="a_close"></div>
	<table border="0" class="add_t" align="center"
		style="width:98%; margin:10px auto;" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="2" style="font-size:14px; color:#ff4e00;">已添加的地址</td>
		</tr>
		<tr style="font-weight: bold;">
			<td>收货人</td>
			<td>所在地区</td>
			<td>详细地址</td>
			<td>邮编</td>
			<td>电话</td>
			<td>操作</td>
			<td></td>
		</tr>
		<c:forEach items="${userAddressList }" var="userAdds">
			<tr style="font-size: 13px" class="defaultAdds">
				<td>${userAdds.remark }</td>
				<td>${userAdds.sfName }- ${userAdds.sqName } -
					${userAdds.qxName }</td>
				<td>${userAdds.addressInfo }</td>
				<td>${userAdds.zipCode }</td>
				<td>${userAdds.phone }</td>
				<td><a href="javascript:void(0)"
					onclick="deleteUseraddress('${userAdds.id}')">删除</a></td>
				<c:choose>
					<c:when test="${userAdds.isdefault==1 }">
						<td><span style="background-color: orange;padding: 2px;">默认地址</span></td>
					</c:when>
					<c:otherwise>
						<td style="display: bolk;"><a
							href="javaScript:void(0)" onclick="setDefault(${userAdds.id})"><span
								style="color:#ff4e00;">设为默认</span></a></td>
					</c:otherwise>

				</c:choose>

			</tr>

		</c:forEach>

	</table>

</div>


