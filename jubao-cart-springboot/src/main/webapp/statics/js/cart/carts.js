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
		url : "/cart/cart/confirmItemOrder",
		type : "get",
		datatype : "html",
		success : function(jsonstr) {
			$("#myCart").empty();
			$("#myCart").html(jsonstr);
		}
	});
}



/*减少数量*/
function subQuantity(obj, entityid, itemType) {
	var quantity = Number(getPCount(jq(obj))) - 1;

	if (quantity == 0) {
		quantity = 1;
	}
	modifyCart(entityid, quantity, jq(obj), itemType);

}

/*添加数量*/
function addQuantity(obj, entityid, stock, itemType) {
	var quantity = Number(getPCount(jq(obj))) + 1;
	if (stock < quantity) {
		showMessage("商品数量不足");
		return;
	}
	modifyCart(entityid, quantity, jq(obj), itemType);

}

//获取表单数量值
function getPCount(obj) {
	return obj.parent().find(".car_ipt").val();
}
;

//修改表单数据            //商品数量    id     需修改数量的对象
function modifyCart(entityid, quantity, obj, itemType) {
	$.ajax({
		url : "/cart/cart/updateCartItem",
		data : {
			"pid" : entityid,
			"num" : quantity,
			"typeItem" : itemType
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 200) {
				obj.parent().find(".car_ipt").val(quantity);
				$("#sumPrice").empty(); //清除总价格
				$("#sumPrice").append("商品总价：<b style='font-size:22px; color:#ff4e00;'>￥" + result.data.price + "</b>")
				sesttlement1();

			} else {
				alert(result.msg);
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

var del = 0;
var itemtype = "";
var obj;
/***
 * 删除前奏
 */
function dele(eid, itemType, delobj) {
	del = eid;
	itemtype = itemType;
	obj = delobj;
	/*alert(obj.parent().parent().parent().text());*/
	ShowDiv('MyDiv', 'fade');

}

//删除商品

function deletex() {
	$.ajax({
		url : "/cart/cart/deleteCartItem",
		data : {
			"pid" : del,
			"typeItem" : itemtype
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 200) {
				obj.parent().parent().remove(); //删除节点
				$("#sumPrice").empty(); //清除总价格
				$("#sumPrice").append("商品总价：<b style='font-size:22px; color:#ff4e00;'>￥" + result.data.price + "</b>");
				CloseDiv('MyDiv', 'fade'); //关闭窗口
			} else {
				showMessage(result.message);
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