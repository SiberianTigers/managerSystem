
var formData = new FormData(); //存放选择图片之后的集合，这里可以对图片进行删除操作。

$("#file").on("change", function() {
	var formDatax = new FormData(); //存放当次选择的文件
	var len = this.files.length; //文件选择的个数
	var index = 0; //记录器   ,读取成功回调时记录回调次数，根据回调序号取出存入在formDatax中的图片文件
	for (var x = 0; x < this.files.length; x++) {
		var rd = new FileReader(); //创建文件读取对象	
		var files = this.files[x]; //获取file组件中的文件
	//===========验证==================================	
		if((files.size/1024)>1024){
			alert("图片不能大于1M");
			return;
		}
		if(!verificationPicFile(files.name)){
			return;
		}
	//===========验证结束=======================================	
		rd.readAsDataURL(files); //文件读取装换为base64类型
		formDatax.append(x, files); //将文件存入到本次选择的dateFrom对象中		
		rd.onload = function(e) { //由于  FileReader 对象是异步读取文件的  ，当上面循环完毕之后才调用 下面的   onload 函数 
			//加载完毕之后获取结果赋值给img
			//	document.getElementById("book-pic").src = this.result;	
			if (index < len) {
				var d = new Date(); //获取当前时间
				var times = d.getTime(); //获取毫秒数
				var files = formDatax.get(index); //根据 记录器取出 存放在本次选择的中的 图片
				formData.set(times.toString(), files); //以当前毫秒数为key ,value 为 files 存入到全局fromDate对象中
				var str = "<li class='imglist'><span><img  id='" + times + "' title='" + files.name + "' src='" + this.result + "' onclick='showInfo(this)' /></span><i class='delImg'>×</i></li>";
				$("#formimg").append(str);
				index++; //记录器
				$(".delImg").unbind("click", del);
				$(".delImg").bind("click", del);
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
/*$("#add").on("click", function() {*/

function fileUpload(){
 	var imgdate = new FormData(); //存放最终上传时的图片集合

	$(".imglist img").each(function(i, e) {
		var ids = $(this).attr("id");
		var files = formData.get(ids);
		imgdate.append('files', files);
	/*  var files = imgdate.getAll('files'); */
	});	
    if(imgdate.getAll('files') == "" || imgdate.getAll('files') == null){
    	return true;
    }
	$.ajax({
		url : "/shop/shop/uploadFile",
		type : 'POST',
		data : imgdate,
		dataType : "json",
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		success : function(data) {
			if (data.status == "200") {
				$("#image").val(data.data);
				return true;
			} else if(data.status == "505"){
				alert(data.msg);
				window.location.href="http://localhost:8086/login/userlogin/tologin";
			}else{	
				alert(data.msg + data.data);
			    return false;
			}
		},
		error : function() {
			alert(data.msg);
		  return false;
		}
	});

}
/*});*/

//删除图片事件	
var del = function() {
	var img = $(this).parent().find("img"); //得到图片上的id
	var imgid = $(img).attr("id")
	formData.delete(imgid.toString());
	$(this).parent().remove();
}



//图片大小验证
function verificationPicFile(file) {
	var fileSize = 0;
	var fileMaxSize = 1024; //1M
	fileSize = file.size;
	var size = fileSize / 1024;
	if (size > fileMaxSize) {
		alert("文件大小不能大于1M！");
		return false;
	} else {
		return true;
	}

}


//图片类型验证
function verificationPicFile(file) {
	var fileTypes = [ ".jpg", ".png", "jpeg" ];
	var filePath = file;
	//当括号里面的值为0、空字符、false 、null 、undefined的时候就相当于false
		var isNext = false;
		var fileEnd = filePath.substring(filePath.indexOf("."));
		for (var i = 0; i < fileTypes.length; i++) {
			if (fileTypes[i] == fileEnd) {
				isNext = true;
				return true;
			}
		}
		if (!isNext) {
			alert('不接受此文件类型');
			return false;
		}
}