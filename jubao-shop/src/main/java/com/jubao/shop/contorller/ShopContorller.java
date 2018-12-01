package com.jubao.shop.contorller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.common.pojo.ItemCatResult;
import com.common.utis.HttpClientUtil;
import com.common.utis.IDUtils;
import com.common.utis.JubaoResult;
import com.common.utis.TtemplateUtil;
import com.jubao.pojo.Product;
import com.jubao.pojo.ProductTemplate;
import com.jubao.pojo.Product_desc;
import com.jubao.pojo.Shop;
import com.jubao.pojo.TemplateValue;
import com.jubao.pojo.User;
import com.jubao.shop.pojo.vo.OrderCustom;
import com.jubao.shop.pojo.vo.OrderVo;
import com.jubao.shop.pojo.vo.ProductCustom;
import com.jubao.shop.service.PictureService;
import com.jubao.shop.service.ProductCateService;
import com.jubao.shop.service.ProudctService;
import com.jubao.shop.service.ShopService;
import com.jubao.shop.service.TemplateModelService;
import com.jubao.shop.service.UserService;

/***
 * 店铺
 * 
 * @author 12146
 *
 */
@Controller
@RequestMapping(value = "/shop")
public class ShopContorller {

	private Logger log = Logger.getLogger(ShopContorller.class);

	@Autowired
	private ProductCateService CategoriesService;
	@Autowired
	private TemplateModelService TemplateModelService;
	@Autowired
	private PictureService PictureService;
	@Autowired
	private ProudctService proudctService;
	private String SEARCH_LOCALHOST = "http://localhost:8084/search/";
	@Autowired
	private UserService userService;
	@Autowired
	private ShopService shopService;

	@RequestMapping(value = "/toShop.html")
	public String toShop(HttpServletRequest request, Model model) {
		try {
			User user = userService.checkLogin(request);
			if (user != null) {
				System.out.println("---------用戶類型-------" + user.getUserType());

				if (user.getUserType() == 2) {
					System.out.println("---------用戶類型-------" + user.getUserType());
					model.addAttribute("user", user);
					Shop shop = shopService.findShopByUserId(user.getUserid());
					request.getSession().setAttribute("shop", shop);

					return "shop";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "index";
	}

	/***
	 * 加载需要的页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ajaxView.html", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	public String ajaxView() {

		return "shop_public/productAdd";
	}

	/***
	 * 加载需要出售中的宝贝
	 * 
	 * @return
	 */
	@RequestMapping(value = "saleProduct.html", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	public String saleProduct(Model model, HttpServletRequest request) {

		Shop shop = (Shop) request.getSession().getAttribute("shop");

		Product p = new Product();
		p.setStorid(shop.getId());// 待更改 店铺 id
		p.setStatus(1);

		List<ProductCustom> productList = proudctService.getByStatusProduct(p);

		model.addAttribute("productList", productList);

		return "shop_public/saleProudct";
	}

	/***
	 * 仓库中中的宝贝
	 * 
	 * @return
	 */
	@RequestMapping(value = "warehouseProduct.html", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	public String warehouseProduct(Model model, HttpServletRequest request) {
		Shop shop = (Shop) request.getSession().getAttribute("shop");

		Product p = new Product();
		p.setStorid(shop.getId());// 待更改 店铺id
		p.setStatus(2);

		List<ProductCustom> productList = proudctService.getByStatusProduct(p);

		model.addAttribute("productList", productList);

		return "shop_public/warehouseProduct";
	}

	/***
	 * 获取商品类别
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadCate.json", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String loadCate() {

		ItemCatResult item = CategoriesService.getTreeCate();

		return JSON.toJSONString(item.getData());
	}

	/***
	 * 根据商品类别获取到商品规格模板
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "getTemplateModel/{cid}", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public ProductTemplate TemplateModel(@PathVariable Integer cid) {
		ProductTemplate item = TemplateModelService.findByIdProductTemplate(cid);

		/*
		 * if(item!=null){
		 * 
		 * List<TtemplateUtil> temp=
		 * JSONArray.parseArray(item.getParamData(),TtemplateUtil.class); }
		 */
		return item;
	}

	/***
	 * 添加商品
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "addPrduct", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public JubaoResult addPrduct(ProductCustom product, HttpServletRequest request) {
		log.info("--------------" + product);

		if (product == null) {
			return JubaoResult.build(500, "信息不完整");
		}
		try {
			Shop shop = (Shop) request.getSession().getAttribute("shop");
			// 商品
			product.setCreated(new Date());
			product.setStorid(shop.getId());
			product.setPid(IDUtils.genItemId());// 设置商品id
			product.setUpdated(new Date());
			// 商品规格
			TemplateValue temp = new TemplateValue();
			temp.setParamData(product.getTemp_value_add());
			temp.setCreateTime(new Date());
			temp.setPid(product.getPid());

			// 商品描述
			Product_desc desc = new Product_desc();
			desc.setCreated(new Date());
			desc.setItemdesc(product.getDesc());
			desc.setItemid(product.getPid());

			// 执行添加商品操作
			if (proudctService.addProduct(product, temp, desc)) {
				if (product.getStatus() == 1) {
					Map<String, String> m = new HashMap<String, String>();
					m.put("pid", product.getPid().toString());
					String str = HttpClientUtil.doGet(SEARCH_LOCALHOST + "item/delItemToSolrById", m);
				}
				return JubaoResult.ok();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JubaoResult.build(500, e.getMessage());
		}
		return JubaoResult.build(500, "添加失败");
	}

	/***
	 * 上传文件
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "uploadFile", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public JubaoResult uploadFile(@RequestParam("files") MultipartFile[] upladFile, HttpServletRequest request) {

		Shop shop = (Shop) request.getSession().getAttribute("shop");
		if (shop == null) {
			return JubaoResult.build(505, "无店铺信息,请登录");
		}
		log.info("---------------uploadFile-----------" + upladFile.length);
		int success = 0;
		int fall = 0;
		List<String> infoList = new ArrayList<String>();
		Map<String, String> resultMap = null;
		StringBuilder imgurl = new StringBuilder();
		try {
			for (int x = 0; x < upladFile.length; x++) {
				log.info("---------------uploadFile-----------" + upladFile[x].getOriginalFilename());
				resultMap = PictureService.PictureLoad(upladFile[x]);
				if (resultMap.get("Info").equals("1")) {
					log.info("---------------uploadFile-----------" + resultMap.get("url"));
					imgurl.append(resultMap.get("url") + ","); // 将图片url添加字符中
					success++;
				} else {
					fall++;
					infoList.add(upladFile[x].getOriginalFilename() + ":" + resultMap.get("message"));
				}
			}
			if (success == upladFile.length) {
				imgurl.deleteCharAt(imgurl.length() - 1);
				return JubaoResult.ok(imgurl.toString());
			} else {
				return JubaoResult.build(500, "成功：" + success + ",失败：" + fall, infoList.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JubaoResult.build(500, e.getMessage());
		}
	}

	@RequestMapping(value = "/totest.html")
	public String totest() {

		return "test";
	}

	/****
	 * 修改正在出售中商品狀態信息
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "updateStatus", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult updateStatus(@RequestParam("pid") Long pid, @RequestParam("status") Integer status) {
		if (proudctService.updateStatus(pid, status)) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("pid", pid.toString());
			if (status == 2) {
				String str = HttpClientUtil.doGet(SEARCH_LOCALHOST + "item/delItemToSolrById", m);
			} else if (status == 1) {
				String str = HttpClientUtil.doGet(SEARCH_LOCALHOST + "item/addItemToSolrById", m);
			}
			return JubaoResult.ok();
		}
		return JubaoResult.build(500, "修改状态失败");
	}

	/**
	 * * deleteDroduct 刪除商品
	 */

	@RequestMapping(value = "deleteProduct", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult deleteProduct(@RequestParam("pid") Long pid) {

		if (proudctService.deleteProduct(pid) > 0) {
			return JubaoResult.ok();
		}
		return JubaoResult.build(500, "修改状态失败");
	}

	/****
	 * 跳转到修改商品 显示修改参数
	 */
	@RequestMapping(value = "updateItem", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	public String updateItem(@RequestParam("pid") Long pid, Model model) {
		try {
			ProductCustom product = proudctService.findProductByPid(pid);
			List<TtemplateUtil> keyValue = proudctService.findTemplateValue(pid);
			Product_desc desc = proudctService.findItemByIdDesc(pid);
			model.addAttribute("product", product);
			model.addAttribute("keyValues", keyValue);
			model.addAttribute("desc", desc);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "shop_public/productUpdate";
	}

	/***
	 * 
	 * 执行修改操作
	 * 
	 */

	@RequestMapping(value = "updateItemInfo", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public JubaoResult updateItemInfo(ProductCustom product) {
		log.info("--------------" + product);

		if (product == null) {
			return JubaoResult.build(500, "信息不完整");

		}
		try {
			Product dataBaseproduct = proudctService.findProductByPid(product.getPid());
			if (dataBaseproduct == null) {
				return JubaoResult.build(500, "商品信息不存在");
			}

			// 商品标题
			if (dataBaseproduct.getTitle().equals(product.getTitle())) {
				product.setTitle(null);
			}
			// 卖点
			if (dataBaseproduct.getSell_point() != null) {
				if (dataBaseproduct.getSell_point().equals(product.getSell_point())) {
					product.setSell_point(null);
				}
			}
			// 价格
			if (dataBaseproduct.getPrice() != null) {
				if (dataBaseproduct.getPrice() == product.getPrice()) {
					product.setPrice(null);
				}
			}
			if (dataBaseproduct.getNum() != null) {
				// 数量
				if (dataBaseproduct.getNum() == product.getNum()) {
					product.setNum(null);
				}
			}
			// 商品
			product.setUpdated(new Date());
			// 商品规格
			TemplateValue temp = new TemplateValue();
			temp.setParamData(product.getTemp_value_add());
			temp.setUpdateTime(new Date());
			temp.setPid(product.getPid());
			Product_desc desc = null;
			if (!org.apache.commons.lang3.StringUtils.isBlank(product.getDesc())) {
				// 商品描述
				desc = new Product_desc();
				desc.setUpdated(new Date());
				desc.setItemdesc(product.getDesc());
				desc.setItemid(product.getPid());
			}

			// 执行添加商品操作
			if (proudctService.updateProduct(product, temp, desc)) {

				if (product.getStatus() == 1) {
					Map<String, String> m = new HashMap<String, String>();
					m.put("pid", product.getPid().toString());
					String str1 = HttpClientUtil.doGet(SEARCH_LOCALHOST + "item/addItemToSolrById", m);
				} else if (product.getStatus() == 2) {
					Map<String, String> m = new HashMap<String, String>();
					m.put("pid", product.getPid().toString());
					String str = HttpClientUtil.doGet(SEARCH_LOCALHOST + "item/delItemToSolrById", m);
				}
				return JubaoResult.ok();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JubaoResult.build(500, e.getMessage());
		}
		return JubaoResult.build(500, "修改失败");
	}

	/**
	 * * deleteDroduct 刪除商品
	 */

	@RequestMapping(value = "shopMyOrder", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public String shopMyOrder(int sid, int status, Model model) {

		List<OrderCustom> orderList = shopService.findOrderByShop(sid, status);

		model.addAttribute("orderList", orderList);

		return "shop_public/userOrder";
	}

	/**
	 * * deleteDroduct 获取订单数量
	 */

	@RequestMapping(value = "findOrderNumber", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public Map findOrderNumber(int shopid) {

		Map<String, String> statusresult = shopService.findOrderStatusNumber(shopid);

		return statusresult;
	}

	/**
	 * * deleteDroduct 获取订单数量
	 */

	@RequestMapping(value = "findOrderByShop", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public String findOrderByShop(int sid, int status, Model model) {

		List<OrderCustom> orderList = shopService.findOrderByShop(sid, status);

		model.addAttribute("orderList", orderList);
		if (status == 1) {
			model.addAttribute("type", 1);
		}
		return "shop_public/userOrder";
	}

	/***
	 * 查询订单送货地址
	 */

	@RequestMapping(value = "findUserGetAddress", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public OrderCustom findOrderByShop(int address) {

		OrderCustom getAddress = shopService.findUserGetAddress(address);

		return getAddress;
	}

	/***
	 * 查询订单送货地址
	 */

	@RequestMapping(value = "confirm", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult confirm(OrderVo ordervo) {

		System.out.println("---------" + ordervo);
		if (ordervo == null) {
			return JubaoResult.build(400, "提交失败,请重试");
		}
		JubaoResult jubaoResult = shopService.updateOrderStatus(ordervo);

		return jubaoResult;
	}
}
