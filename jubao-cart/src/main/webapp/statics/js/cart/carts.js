/**
 * 
 * 调用添加商品到购物车
 * @param entityId
 * @param quantity
 */
function addCarByParam(entityId, quantity) {
	$.ajaxSetup({
		cache : false
	}); //禁止缓存机制
	$.ajax({
		url : contextPath + "/Cart",
		data : {
			action : "add",
			"entityid" : entityId,
			"quantity" : quantity
		},
		success : function(jsonstr) {

			var result = eval("(" + jsonstr + ")");
			if (result.status == 1) {
				showMessage("操作成功");
				refreshCart();
			} else {
				showMessage(result.message);
			}
		}
	});

}

var nums = 0;
/**
 * 商品详情页面添加商品
 */
function addCarByParamx(entityId) {
	$.ajaxSetup({
		cache : false
	}); //禁止缓存机制

	if (type1 == null || type2 == null) {
		showMessage("请选择商品属性");
		return;
	}
	var quantity = getPCountx();

	$.ajax({
		url : contextPath + "/Cart",
		data : {
			action : "add",
			"entityid" : entityId,
			"quantity" : quantity,
			"type1" : type1,
			"type2" : type2
		},
		success : function(jsonstr) {

			var result = eval("(" + jsonstr + ")");
			if (result.status == 1) {
				showMessage("操作成功");
				refreshCart();
			} else {
				showMessage(result.message);
			}
		}
	});

}


/*减少数量*/
function subQuantityx() {
	var quantity = Number(getPCountx()) - 1;
	if (quantity == 0) {
		quantity = 1;
	}
	$(".n_ipt").val(quantity);
}

/*添加数量*/
function addQuantityx(stock) {
	var quantity = Number(getPCountx()) + 1;
	if (stock < quantity) {
		showMessage("商品数量不足");
		return;
	}
	$(".n_ipt").val(quantity);
}

//获取表单数量值
function getPCountx() {
	return $(".n_ipt").val();
}
;

/**
 * 调用返回新的购物车局部刷新
 */
function refreshCart() {
	$.ajax({
		url : contextPath + "/Cart",
		data : {
			action : "refreshCart"
		},
		success : function(jsonstr) {

			$("#searchBar").html(jsonstr);
		}
	});
}






//切换查看购物车页面
function sesttlement1() {
	$.ajax({
		url : contextPath + "/Cart",
		data : {
			action : "toSesttlement1"
		},
		success : function(jsonstr) {

			$("#myCart").html(jsonstr);
		}
	});
}


//切换查看购物车页面
function sesttlement2() {
	$.ajax({
		url : "/cart/cart/cartItemfush",
		type : "get",
		datatype : "html",
		success : function(jsonstr) {
			$("#myCart").empty();
			$("#myCart").html(jsonstr);
		}
	});
}



/*减少数量*/
function subQuantity(obj) {
	var quantity = Number(getPCount($(obj))) - 1; //取得所购商品数量
	if (quantity <= 0) { //数量为0时  , 改为1
		quantity = 1;
	}
	var objx = $(obj).parent().find(".car_ipt"); //取到所购商品
	var pid = $(objx).attr("pid"); //取到商品id
	var itemType = $(objx).attr("itemType"); //取到商品类别
	var sid = $(objx).attr("sid"); //取到商品的店铺id

	modifyCart(pid, quantity, itemType, sid);

}


/*添加数量*/
function addQuantity(obj) {
	var objx = $(obj).parent().find(".car_ipt"); //根据当前对象到父节点去查询 指定类的节点 得到该对象
	var quantity = Number(getPCount($(obj))) + 1; //获取当前商品数量
	var stock = $(objx).attr("num"); //商品总数量   //根据得到的对象去取节点上的属性值  名称为num的 该属性为商品的库存总数量

	if (stock < quantity) { //商品库存总数比较  当前所购的商品数量  如果总数小于所购数则提示   
		alert("商品数量不足");
		return;
	}

	var pid = $(objx).attr("pid"); //取得该节点的 商品id属性
	var itemType = $(objx).attr("itemType"); //取得该节点上的商品规格
	var sid = $(objx).attr("sid"); //取得商品的店铺id

	//调用修改方法修改实时修改所购商品数量
	modifyCart(pid, quantity, itemType, sid);

}

//获取表单数量值
function getPCount(obj) { //点击增加或减少，根据点击的按钮对象去父级节点查询.car_ipt的存放 所购商品数量  返回商品所购量
	return obj.parent().find(".car_ipt").val();
}

//修改表单数据            pid       //商品数量    商品的规格   店铺id
function modifyCart(entityid, quantity, itemType, sid) {
	$.ajax({
		url : "/cart/cart/updateCartItem",
		type : "post",
		data : {
			"pid" : entityid,
			"num" : quantity,
			"typeItem" : itemType,
			"sid" : sid
		},
		datatype : "json",
		success : function(jsonstr) {
			if (jsonstr.status == 200) {
				sesttlement2(); //调用刷新购物车,实时 更新修改状态。
			} else {
				alert(jsonstr.msg);
			}
		}
	});



}

//确认订单信息页面- --------选择收货地址进    地址选项切换
function addressAddClass() {
	$(".pay li").click(function() {
		$(".pay li").removeClass("checked");
		$(this).toggleClass("checked");

	});



}

var itemtype = "";
var pid;
var sid;
/***
 * 删除前奏
 */
function dele(obj) {
	var objx = $(obj).parent().parent().find(".car_ipt");

	pid = $(objx).attr("pid"); //取得该节点的 商品id属性
	itemtype = $(objx).attr("itemType"); //取得该节点上的商品规格
	sid = $(objx).attr("sid"); //取得商品的店铺id
	ShowDiv('MyDiv', 'fade');

}

//删除商品

function deletex() {
	$.ajax({
		url : "/cart/cart/deleteCartItem",
		type:"post",
		data : {
			"pid" : pid,
			"typeItem" : itemtype,
			"sid" : sid
		},
		datatype : "json",
		success : function(result) {
			if (result.status == 200) {
				CloseDiv('MyDiv', 'fade'); //关闭窗口
				sesttlement2(); //调用刷新购物车,实时 更新修改状态。
			} else {
				showMessage(result.msg);
			}

		}
	});

}

var type1 = null;

var type2 = null;


function type1s(obj, type) {
	type1 = type;
	$(".t1").removeClass("checked");
	obj.toggleClass("checked");

}



function type3s(obj, type) {
	type2 = type;
	$(".t3").removeClass("checked");
	obj.toggleClass("checked");

}

//收藏商品
function addfavorite(productid) {
	if (productid == "") {
		return;
	}

	$.ajax({
		url : contextPath + "/ProductFavorite",
		data : {
			action : "productFavorite",
			"Producid" : productid,
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 1) {
				showMessage('商品收藏成功');
			}
		}
	});

}