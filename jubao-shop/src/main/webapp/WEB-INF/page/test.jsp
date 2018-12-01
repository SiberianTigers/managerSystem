<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<link rel="stylesheet" type="text/css" href="/statics/css/upload/up.css">
<script type="text/javascript" src="/statics/js/jquery-1.12.4.min.js"></script>

</head>






<body>
<!-- 	<form action="/shop/shop/uploadFile" enctype="multipart/form-data"> -->

		<div class="startCss">
			
			<ul id="formimg">	
				
			   <!-- 上传按钮组件 -->
			   <li style="width: 40px;" class="box" >
			   <button style="" type="button" name="上传" id="add"  class="content">上传</button>
			   </li>
			   
			   <!-- 图片选择器 -->
			   <li class="imgContainer">
				   <img onclick="getElementById('file').click()" id="book-pic" />
					 <!-- 选择图片临时存储 --> 
					<input type="file"
					multiple="multiple" id="file" name='file'/>
				</li>
				
			 <!-- imglist 选择的图片 -->
				
			<!-- <li class="imglist">
				 <span class="btn">
				 <img alt="" src="/statics/images/user.jpg">
				 </span>
				 <i class="delImg">×</i>
				</li>
			  -->
			<!--   <li class="updateimglist">
				 <span class="btn">
				 <img alt="" src="/statics/images/user.jpg">
				 </span>
				 <i class="delImg">×</i>
			  </li> -->
				
			</ul>
			
		</div>
		
<!-- 	</form> -->

</body>
<script type="text/javascript" src="/statics/locajs/myUploadFile.js"></script>
<!-- <script type="text/javascript">


	var formData = new FormData(); //存放选择图片之后的集合，这里可以对图片进行删除操作。

	$("#file").on("change", function() {
		var formDatax = new FormData(); //存放当次选择的文件
		var len = this.files.length;//文件选择的个数
		var index = 0;//记录器   ,读取成功回调时记录回调次数，根据回调序号取出存入在formDatax中的图片文件
		for (var x = 0; x < this.files.length; x++) {
			var rd = new FileReader(); //创建文件读取对象	
			var files = this.files[x]; //获取file组件中的文件
			rd.readAsDataURL(files); //文件读取装换为base64类型
			formDatax.append(x, files);//将文件存入到本次选择的dateFrom对象中
			
			rd.onload = function(e) { //由于  FileReader 对象是异步读取文件的  ，当上面循环完毕之后才调用 下面的   onload 函数 
				//加载完毕之后获取结果赋值给img
				//	document.getElementById("book-pic").src = this.result;	
				if (index < len) {
					var d = new Date();//获取当前时间
					var times = d.getTime();//获取毫秒数  ×
					var files=formDatax.get(index);//根据 记录器取出 存放在本次选择的中的 图片
					formData.set(times.toString(),files);//以当前毫秒数为key ,value 为 files 存入到全局fromDate对象中
					var str = "<li class='imglist'><span><img  id='" + times + "' title='" + files.name + "' src='" + this.result + "' onclick='showInfo(this)' /></span><i class='delImg'>×</i></li>";
					$("#formimg").append(str);
					index++;//记录器
				       $(".delImg").unbind("click",del);
				       $(".delImg").bind("click",del);
				}
			}
		
		}
		
	});

    /***
     图片点击事件
    */ 
/* 	function showInfo(obj) {
		var id = $(obj).attr("id");
		var files = formData.get(id);

		alert(id + "==" + files.name);
	} */
	
	
//上传图片	
$("#add").on("click",function(){
                
                  var imgdate = new FormData(); //存放最终上传时的图片集合
 
                $(".imglist img").each(function(i, e) {
                	var ids = $(this).attr("id");
                	var files = formData.get(ids);
                	imgdate.append('files',files);              	  
  /*               	var files = imgdate.getAll('files');        */	
                });
           
				$.ajax({
				   url:"/shop/shop/uploadFile",
				   type:'POST',
				   data: imgdate,
				   dataType: "json",
				   async: false,
				   cache: false,
				   contentType: false,
				   processData: false,
				   success: function (data) {
				    if(data.status == "200"){
				    alert(data.data);
				    }	   
				   },error: function(){
					   alert(data.msg);
				   }
				}); 
				

});	
	
//删除图片事件	
var del= function(){
 var img=$(this).parent().find("img");  //得到图片上的id
 var imgid=$(img).attr("id")
 formData.delete(imgid.toString());
 $(this).parent().remove();
}

	
</script> -->
</html>
