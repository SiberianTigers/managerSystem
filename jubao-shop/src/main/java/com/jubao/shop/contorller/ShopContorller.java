package com.jubao.shop.contorller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.jubao.pojo.Product;
import com.jubao.pojo.ProductTemplate;
import com.jubao.pojo.Product_desc;
import com.jubao.pojo.TemplateValue;
import com.jubao.shop.service.PictureService;
import com.jubao.shop.service.ProductCateService;
import com.jubao.shop.service.ProudctService;
import com.jubao.shop.service.TemplateModelService;

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
	
	@Value("${SEARCH_LOCALHOST}")
	private String SEARCH_LOCALHOST;

	@RequestMapping(value = "/toShop.html")
	public String toShop() {

		return "shop";
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
	public String saleProduct(Model model) {
		
		 Product p=new Product();
		  p.setStorid(1);//待更改   店铺 id
		  p.setStatus(1);
		
		 List<Product>productList= proudctService.getByStatusProduct(p);
		
		  model.addAttribute("productList",productList);
		 
		 
		return "shop_public/saleProudct";
	}
	

	/***
	 * 仓库中中的宝贝
	 * 
	 * @return
	 */
	@RequestMapping(value = "warehouseProduct.html", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	public String warehouseProduct(Model model) {
		
		 Product p=new Product();
		  p.setStorid(1);//待更改   店铺id
		  p.setStatus(2);
		
		 List<Product>productList= proudctService.getByStatusProduct(p);
		
		  model.addAttribute("productList",productList);
		 
		 
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
	public JubaoResult addPrduct(Product product, @RequestParam(value = "file") MultipartFile[] file) {
		log.info("--------------"+product);
		
		if (product == null) {
			return JubaoResult.build(500, "信息不完整");

		}
		try {
			Map<String, String> resultMap = null;
			for (int x = 0; x < file.length; x++) {
				resultMap = PictureService.PictureLoad(file[x]);
				if(resultMap.get("Info").equals("1")){
					product.setImage(product.getImage()+resultMap.get("url")+",");
				}
			}

			// 商品
			product.setCreated(new Date());
			product.setStatus(1);
			product.setStorid(1);
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
				
				if(product.getStatus()!=2){
				   HttpClientUtil.doPost(SEARCH_LOCALHOST+"item/uploadItemThisSolr");
				}
				return JubaoResult.ok();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JubaoResult.build(500, e.getMessage());
		}
		return JubaoResult.build(500,"添加失败");
	}

	/***
	 * 上传文件
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "uploadFile", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public JubaoResult uploadFile(@RequestParam("file") MultipartFile upladFile) {
		log.info("---------------uploadFile-----------" + upladFile.getName());
		try {
			Map<String, String> resultMap = PictureService.PictureLoad(upladFile);

			if (resultMap.get("Info").equals("1")) {
				return JubaoResult.ok();
			} else {
				return JubaoResult.build(500, resultMap.get("message"));
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
	 * 修改正在出售中商品信息
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "updateStatus",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public JubaoResult updateStatus(@RequestParam("pid")Integer pid,@RequestParam("status")Integer status) {	    
		  if(proudctService.updateStatus(pid,status)){			  
				return JubaoResult.ok();		  
		  }
		 return JubaoResult.build(500,"修改状态失败");	
	}
	
	
	/** *  deleteDroduct
  	 * 刪除商品
	 */
	
	@RequestMapping(value = "deleteProduct",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public JubaoResult deleteProduct(@RequestParam("pid")Integer pid) {	    
		 
		if(proudctService.deleteProduct(pid)>0){			  
				return JubaoResult.ok();		  
		  }
		 return JubaoResult.build(500,"修改状态失败");	
	}
}
