
function showData(result) {
	var data = JSON.stringify(result);
	var strdate = "";
	$("#cateItem").empty();
	for (var x = 0; x < result.data.length; x++) {
		if (x >= 10) {
			continue;
		}
		strdate += "<li><div class='fj'>";
		strdate += "<span class='n_img'><span></span><img src='http://" + result.data[x].cateImg + "' /></span>"
		strdate += "<span class='fl'>" + result.data[x].name + "</span></div> ";

		strdate += "<div class='zj' style='top:-" + (x * 40) + "px;'><div class='zj_l'>";
		//二级分类
		for (var i = 0; i < result.data[x].item.length; i++) {
			strdate += "<div class='zj_l_c'>";
			strdate += "<h2>" + result.data[x].item[i].name + "</h2>";
			//三级分类
			for (var z = 0; z < result.data[x].item[i].item.length; z++) {
				var array = result.data[x].item[i].item[z].split('|');
				strdate += "<a href='/search/search_product.html?queryString=" + array[1] + "'>" + array[1] + "</a>|";
			}
			strdate += "</div>";
		}

		strdate += "</div>" +
			"</div>" +
			"</li>"
	}
	$("#cateItem").append(strdate);
}


$(function() {

	
	setTimeout('loadCate()', 2000); //延迟1秒

});



function loadCate(){
	
	
	//初始化加载分类菜单	
	$.ajax({
		url : "http://localhost:8081/rest/Categories/getCategories",
		type : "GET",
		dataType : "jsonp", //指定服务器返回的数据类型
		jsonpCallback : "showData", //指定回调函数名称
		success : function(data) {
			jq(".leftNav ul li").hover(
				function() {
					jq(this).find(".fj").addClass("nuw");
					jq(this).find(".zj").show();
				}
				,
				function() {
					jq(this).find(".fj").removeClass("nuw");
					jq(this).find(".zj").hide();
				}
			)
		}
	});
	
}

