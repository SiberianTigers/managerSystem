/***
 * 商品詳請頁面展示   延遲加載出店鋪的基本信息，和随机推荐的6个商品
 */
function A() {
	var sid = $("#sid").val(); //獲取店鋪id
	if (sid == "" || sid == 0) {
		return;
	}

	$("#shopInfo").empty(); //清空節點
	var str = "";
	$.ajax({
		url : "http://localhost:8083/shop/loadShop.json?",
		type : "POST",
		data : {
			"sid" : sid
		},
		datatype : "JSON",
		success : function(data) {
			str += "<div class='fav_t'><a href='#'>" + data.data.storeName + "</a><br/>" + data.data.storeCreateTime + "</div><ul>";
			str += "<li><div class='name'>店铺创建于  : " + data.data.storeCreateTime + "</div><div class='name'>用户评分:" + data.data.storeUserNumber + "</div></li>";

			for (var x = 0; x < data.data.itemList.length; x++) {
				str += "<li><div class='img'>";
				var imginfo = data.data.itemList[x].image.split(',');
				str += "<a href='#'><img src='http://119.29.195.240" + imginfo[0] + "' width='185' height='162' /></a></div>";
				str += "<div class='name'>";
				str += "<a href='#'>" + data.data.itemList[x].title + "</a></div>";
				str += "<div class='price'>";
				str += "<font>￥<span>" + data.data.itemList[x].price + "</span></font></div>	</li>";
			}
			str += "</ul>";
			$("#shopInfo").append(str); //加入到节点中

		}
	});



}


//页面加载完毕执行，延迟3秒执行 加载出店铺信息
$(document).ready(function() {
	setTimeout('A()', 3000); //延迟3秒
});

//点击选择商品规格
function selectInfo(obj) {
	$(obj).siblings().removeClass("checked");


	$(obj).toggleClass("checked");

}
;




//添加商品到购物车
function addItemCart(pid) {
	var flag=true;
	//========================获取商品规格===============================	
	var arr = "";

	$(".des_choice [class=fl]").each(function(i, f) {

		var typeName = $(this).html();

		var type = $(this).next().find("[class=checked]");
		var typeItem = "";
		$(type).each(function(v, s) {

			typeItem = $(this).attr("typeItem");

		});
		if(typeItem == "" || typeItem == null){
			flag = false;
		}	
			arr += typeName + typeItem + "|"
	});
	
	if(!flag){
		alert("请选择商品规格");
		return;
	}
	
	if (arr.length < 2) {
		alert("请选择商品规格");
		return;
	}


	//========================获取商品规格   end===============================		

	//获取商品数量
	var num = $("#num").val();

	$.ajax({
		type : "get",
		url : "http://localhost:8087/cart/cart/addItem/",
		dataType : "jsonp",
		data : {
			"pid" : pid,
			"typeItem" : arr,
			"num" : num
		},
		jsonp : "callback", //传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
		jsonpCallback : "showUser", //自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据
		success : function(result) {
			
			if (result.status == 200) {
				ShowDiv_1('MyDiv1', 'fade1');
		     $(".cars").empty();//清空div
			 var str="";
			 for(var x=0;x<result.data.itemList.length;x++){
				str+="<li>";
				str+="<div class='img'>";
				str+="<a href='#'><img src='http://119.29.195.240"+result.data.itemList[x].item.image+"' width='58' height='58' /></a></div>";
				str+="<div class='name'>";
				str+="<a href='#'>"+result.data.itemList[x].item.title+"</a></div>";
				str+="<div class='price'><font color='#ff4e00'>"+result.data.itemList[x].item.price+"</font> "+result.data.itemList[x].num+"</div></li>"
			 }			
			$(".cars").append(str);//加入div中
			$(".car_t").empty();//请购物车数量
			$(".car_t").append("购物车 [ <span>"+result.data.count+"</span> ]");
			$(".price_sum").empty();//清除价格
			$(".price_sum").append("共计&nbsp; <font color='#ff4e00'>￥</font><span>"+result.data.price+"</span>");
			}

		},
		error : function() {}
	});

}