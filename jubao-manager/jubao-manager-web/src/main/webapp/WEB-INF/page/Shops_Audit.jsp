<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />
 <link href="/statics/assets/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="/statics/css/style.css"/>       
        <link href="/statics/assets/css/codemirror.css" rel="stylesheet">
        <link rel="stylesheet" href="/statics/assets/css/ace.min.css" />
        <link rel="stylesheet" href="/statics/font/css/font-awesome.min.css" />
        <!--[if lte IE 8]>
		  <link rel="stylesheet" href="/statics/assets/css/ace-ie.min.css" />
		<![endif]-->
		<script src="/statics/js/jquery-1.9.1.min.js"></script>
        <script src="/statics/assets/js/bootstrap.min.js"></script>
		<script src="/statics/assets/js/typeahead-bs2.min.js"></script>           	
		<script src="/statics/assets/js/jquery.dataTables.min.js"></script>
		<script src="/statics/assets/js/jquery.dataTables.bootstrap.js"></script>
        <script src="/statics/assets/layer/layer.js" type="text/javascript" ></script>          
        <script src="/statics/assets/laydate/laydate.js" type="text/javascript"></script>
        <script src="/statics/js/displayPart.js" type="text/javascript"></script>
<title>交易金额</title>
</head>

<body>
<div class="margin clearfix">
 <div class="Shops_Audit">
   <div class="prompt">当前共有<b>${shopList.size() }</b>家店铺申请入住</div>
   <!--申请列表-->
   <div class="audit_list">
     <table class="table table-striped table-bordered table-hover" id="sample-table">
       <thead>
		 <tr>
            <th width="180px">店铺名称</th>
            <th width="120px">所属分类</th>
            <th width="120px">店铺类型</th>
            <th width="">简介</th>
            <th width="150px">添加时间</th>
            <th width="100px">审核状态</th>                
            <th width="250px">操作</th>
			</tr>
		</thead>
        <tbody>
        <c:forEach items="${shopList }" var="shop">
        <tr>
         <td>${shop.storeName }</td>
         <td>${shop.catename }</td>
         <td>
         <c:choose>
        <c:when test="${shop.shopType == 1 }">
         	个人店铺
        </c:when>     
         <c:otherwise>
         企业店铺
         </c:otherwise>
         
         </c:choose>
    
         
         </td>
         <td class="displayPart" displayLength="80">${shop.shopDesc }</td>
         <td>
           <fmt:formatDate value="${shop.storeCreateTime }"  pattern="yyyy-MM-dd HH:ss"/> 
         </td>
         <td>待审核</td>
          
          <td class="td-manage">
          
            <form  action="/ShopsAudit/shopping_detailed.html" id="${shop.id }"  method="post">
             <input  type="hidden" name="id" value="${shop.id }"/>
            <input  type="hidden" name="storeOw"  value="${shop.storeOw }"/>
            <input  type="hidden" name="shopType"  value="${shop.shopType }"/>
            <input  type="hidden" name="storeName"  value="${shop.storeName }"/>
            <input  type="hidden" name="catename"  value="${shop.catename }"/>
            <input  type="hidden" name="storeStatus"  value="${shop.storeStatus }"/>
            <input  type="hidden" name="storeCreateTime"  value="${shop.storeCreateTime }"/>
             <input  type="hidden" name="storeAddress"  value="${shop.storeAddress }"/>
             <input  type="hidden" name="phone"  value="${shop.phone }"/>
             <input  type="hidden" name="idCard"  value="${shop.idCard }"/>
              <input  type="hidden" name="shopDesc"  value="${shop.shopDesc }"/>
              <input  type="hidden" name="email"  value="${shop.email }"/>
              <input  type="hidden" name="owName"  value="${shop.owName }"/>
              <input type="submit" value="详细" class="btn btn-xs btn-info Refund_detailed"/>        
              <a title="删除" href="javascript:;"  onclick="member_del(this,'1')" class="btn btn-xs btn-danger" >删除</a>
            </form>        



<%--            <a title="店铺详细" href="javaScript:void(0)" onclick="showInfo(${shop.id})"  class="btn btn-xs btn-info Refund_detailed">详细</a>        
 --%>          
           
          </td>
          
        </tr>
        </c:forEach>
        </tbody>
        </table>
     	<!--分页-->
				<nav aria-label="...">
				<ul class="pagination">
					<c:if test="${pageInfo.pageNum>1}">
						<li><a
							href="/ShopsAudit/toShop_audit.html?pageIndex=${pageIndex-1}"
							aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					</c:if>
					<c:forEach items="${pageInfo.navigatepageNums}" var="page">

						<c:choose>
							<c:when test="${pageInfo.pageNum == page}">
								<li class="active"><a
									href="/ShopsAudit/toShop_audit.html?pageIndex=${page}">${page}<span
										class="sr-only">(current)</span></a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="/ShopsAudit/toShop_audit.html?pageIndex=${page}">${page}<span
										class="sr-only">(current)</span></a></li>
							</c:otherwise>
						</c:choose>

					</c:forEach>

					<c:if test="${ pageInfo.pageNum < pageInfo.pages}">
						<li><a
							href="/ShopsAudit/toShop_audit.html?pageIndex=${pageInfo.pageNum +1}"
							aria-label="Previous"><span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:if>

				</ul>
				</nav>


				<!-- end -->
   
   </div>
 </div>
</div>
</body>
</html>
<script>

$(function () {  
        $(".displayPart").displayPart();  
   });
$(function() {
		var oTable1 = $('#sample-table').dataTable( {
		//"aaSorting": [],//默认第几个排序
		"bStateSave": true,//状态保存
		//"dom": 't',
		"bFilter":false,
		"paging" : false, //开启表格分页
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,1,2,3,4,5,6]}// 制定列不参与排序
		] } );
				$('table th input:checkbox').on('click' , function(){
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox')
					.each(function(){
						this.checked = that.checked;
						$(this).closest('tr').toggleClass('selected');
					});
						
				});
})
/*店铺-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',{icon:0,},function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
//面包屑返回值
var index = parent.layer.getFrameIndex(window.name);
parent.layer.iframeAuto(index);
$('.Refund_detailed').on('click', function(){
	var cname = $(this).attr("title");
	var chref = $(this).attr("href");
	var cnames = parent.$('.Current_page').html();
	var herf = parent.$("#iframe").attr("src");
    parent.$('#parentIframe').html(cname);
    parent.$('#iframe').attr("src",chref).ready();;
	parent.$('#parentIframe').css("display","inline-block");
	parent.$('.Current_page').attr({"name":herf,"href":"javascript:void(0)"}).css({"color":"#4c8fbd","cursor":"pointer"});
	//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+" class='iframeurl'>" + cnames + "</a>");
    parent.layer.close(index);
	
});
</script>
