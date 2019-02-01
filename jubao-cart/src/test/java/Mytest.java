import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jubao.cart.utils.Cart;
import com.jubao.cart.utils.CartItem;
import com.jubao.pojo.util.Item;

public class Mytest {

	public static void main(String[] args) {

		Cart c = new Cart();

		List<CartItem> listCartItem = new ArrayList<CartItem>();
		Item item = new Item();
		item.setId(2131321313L);
		item.setItemType("abab");

		CartItem cart = new CartItem(item, 2);

		listCartItem.add(cart);

		c.getCartMap().put(132131212L, listCartItem);

		Object o = JSONObject.toJSON(c);
		
		System.out.println(o.toString());
		
	
	}

}
