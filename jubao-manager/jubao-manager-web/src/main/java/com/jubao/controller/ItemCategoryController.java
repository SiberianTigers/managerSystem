package com.jubao.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utis.HttpClientUtil;
import com.common.utis.JubaoResult;
import com.common.utis.TtemplateUtil;
import com.common.utis.ZTree;
import com.jubao.pojo.ItemCategory;
import com.jubao.pojo.ProductTemplate;
import com.jubao.service.ItemCategoryService;
import com.jubao.service.PictureService;
import com.jubao.service.ProudctTemplateService;

/***
 * 商品分类
 * 
 * @author 12146
 *
 */
@Controller
@RequestMapping(value = "/itemCategory")
public class ItemCategoryController {

	private Logger log = Logger.getLogger(ItemCategoryController.class);

	@Resource
	private ItemCategoryService itemCategoryService;

	@Resource
	private PictureService pictureService;

	@Resource
	private ProudctTemplateService ProudctTemplateService;

	// rest服务层主机ip
	private String REST_LOCALHAST = "http://localhost:8081/rest/";

	/***
	 * 跳转到商品分类管理界面
	 * 
	 * @return
	 */
	@RequestMapping("/Category_Manage")
	public String toCategoryType() {

		return "Category_Manage";
	}

	/***
	 * 跳转到商品分类添加
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "product-category-add", produces = "text/html;charset=utf-8")
	public String toCategoryTypeAdd(@RequestParam(value = "cateName", required = false) String cateName, // 商品分类名称
			@RequestParam(value = "id", required = false,defaultValue="-1") Integer id, // 商品分类idparentId
			@RequestParam(value = "sort", required = false) Integer sort, // 商品分类排序
			@RequestParam(value = "isParent", required = false) boolean isParent,
			@RequestParam(value = "parentId", required = false, defaultValue = "0") Integer parentId,
			@RequestParam(value = "cateImg", required = false, defaultValue = "") String cateImg, Model model) // 商品分类id
			throws UnsupportedEncodingException {
		String filePath = null;
		if (cateName != null) {
			// get提交方式乱码处理提交
			filePath = new String(cateName.getBytes("ISO-8859-1"), "utf-8");
		}
		System.out.println(
				"=======================" + filePath + "=======" + id + "=======" + isParent + "-------" + parentId);

		if(isParent == false && id!=-1){
				
			ProductTemplate ProudctTemplate=ProudctTemplateService.findTemplate(id);
			 if(ProudctTemplate!=null){
							 
	 	 List<TtemplateUtil> temp= JSONArray.parseArray(ProudctTemplate.getParamData(),TtemplateUtil.class);
				 
				 for(TtemplateUtil str:temp){
					 
					 log.info("---product-category-add-----------"+str.getGroup());
				 }			 
					model.addAttribute("temp", temp);
				 
			 }
			
			
		}
		
		
	

		model.addAttribute("cateName", filePath);
		model.addAttribute("id", id);
		model.addAttribute("sort", sort);
		model.addAttribute("isParent", isParent);
		model.addAttribute("parentId", parentId);
		model.addAttribute("cateImg", cateImg);

		return "product-category-add";
	}

	/***
	 * 加载商品分类列表 produces=MediaType.APPLICATION_JSON_VALUE +";charset=utf-8"
	 */
	@RequestMapping(value = "/loadItemCategory", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public Object loadItemCategory(@RequestParam(value = "id", defaultValue = "-1") Integer id) {

		System.out.println("=============================");

		List<ZTree> CateInfoList = new ArrayList<ZTree>();

		if (id == -1) {
			ZTree cateInfo = new ZTree();
			cateInfo.setId(0);
			cateInfo.setName("聚宝网商品分类");
			cateInfo.setIsParent(1);
			cateInfo.setSort(1);
			cateInfo.setOpen(true);
			cateInfo.setPid(-1);
			CateInfoList.add(cateInfo);
		}
		if (id != -1) {
			// 查询所有商品分类
			List<ItemCategory> categoryList = itemCategoryService.getAllIntegerCategory(id);

			for (ItemCategory cate : categoryList) {
				ZTree cateInfo = new ZTree();
				cateInfo.setId(cate.getId());
				cateInfo.setPid(cate.getParentId());
				cateInfo.setName(cate.getName());
				cateInfo.setIsParent(cate.getIsParent());
				cateInfo.setSort(cate.getSortOrder());
				cateInfo.setCateLog(cate.getCateImg());
				CateInfoList.add(cateInfo);
			}
		}
		return JSONArray.toJSON(CateInfoList);
	}

	/***
	 * 跳转到商品分类添加
	 */
	@RequestMapping("/toTest")
	public String Testtree() {

		return "Testtree";
	}

	/***
	 * 添加商品分类
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/addcate", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult addcate(ItemCategory category) {

		if (category == null) {
			return JubaoResult.build(400, "表单为空");
		}

		System.out.println(category + "======");

		category.setStatus(1);
		category.setCreated(new Date());
		Integer sortindex = itemCategoryService.selectSortIndex(category.getParentId()) == null ? 1
				: itemCategoryService.selectSortIndex(category.getParentId()) + 1;
		category.setSortOrder(sortindex);

		// 添加分类
		if (itemCategoryService.addCate(category) > 0) {

			// 调用redis服务层清空缓存中的商品分类
			HttpClientUtil.doGet(REST_LOCALHAST + "Categories/delRedisItemCate");
			return JubaoResult.ok();
		}
		return JubaoResult.build(400, "添加失败");
	}

	/***
	 * 刪除商品分类
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/deleteCate/{id}/{isParent}", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public JubaoResult deleteCate(@PathVariable Integer id, @PathVariable boolean isParent) {

		System.out.println(id + "===" + isParent);

		if (itemCategoryService.deleteCate(id) > 0) {
			if (isParent) {// 如果是父节点就进去删除子节点
				itemCategoryService.deleteCateChile(id);
			}

			// 调用redis服务层清空缓存中的商品分类
			HttpClientUtil.doGet(REST_LOCALHAST + "Categories/delRedisItemCate");
			return JubaoResult.ok();
		}
		return JubaoResult.build(400, "删除失败");

	}

	/***
	 * 修改商品分类
	 * 
	 * @param category
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/updateCate", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult updateCate(@RequestParam(value = "cateName") String name, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "isParent") Integer isParent) throws UnsupportedEncodingException {

		System.out.println(id + "==修改商品分类===" + name);
		if (name != null) {
			// get提交方式乱码处理提交
			name = new String(name.getBytes("ISO-8859-1"), "utf-8");
		}
		ItemCategory cate = new ItemCategory();
		cate.setId(id);// 分类id
		cate.setName(name);// 分类名称
		cate.setIsParent(isParent); // 是否为父类
		cate.setUpdated(new Date());

		if (itemCategoryService.updateCate(cate) > 0) {
			// 调用redis服务层清空缓存中的商品分类
			HttpClientUtil.doGet(REST_LOCALHAST + "Categories/delRedisItemCate");
			return JubaoResult.ok();
		}

		return JubaoResult.build(400, "删除失败");

	}

	/***
	 * 上传分类父级log
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "uploadCatelog", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult uploadCatelog(@RequestParam(value = "catelog") MultipartFile catelog,
			@RequestParam(value = "id", required = false) Integer id) {
		System.out.println("-------------" + id + "============" + catelog.getOriginalFilename());
		// 判断图片是否为空
		if (!catelog.isEmpty()) {

			// 判断图片大小
			if (catelog.getSize() <= 10000) {

				String name = catelog.getOriginalFilename();

				name = "/statics/images/" + name;
				System.out.println("-------------" + name);
				Map<String, String> mapresult = pictureService.PictureLoad(catelog);
				if (mapresult.get("Info").equals("1")) {
					name = "119.29.195.240\\" + mapresult.get("url");
					if (itemCategoryService.updateCatelog(name, id) > 0) {
						// 调用redis服务层清空缓存中的商品分类
						HttpClientUtil.doGet(REST_LOCALHAST + "Categories/delRedisItemCate");
						return JubaoResult.ok();
					}
				} else {
					return JubaoResult.build(400, mapresult.get("message"));
				}

			} else {
				// 图片不合格
				return JubaoResult.build(400, "图片大小不合格");
			}

		} else {
			// 图片为空
		}

		return JubaoResult.build(400, "图片选择失败");
	}

	/***
	 * 添加商品规格模板
	 * 
	 * @return
	 */
	@RequestMapping(value = "addTemplate/{cid}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public JubaoResult addTemplate(@RequestParam("paramData") String paramData, @PathVariable("cid") Integer cid) {

		if (paramData == null || cid == null) {
			return JubaoResult.build(500, "数据错误");
		}

		ProductTemplate pt = new ProductTemplate();
		pt.setCategoryid(cid);
		pt.setParamData(paramData);
		pt.setCreateTime(new Date());

		log.info("--addTemplate Info-----" + paramData);

		if (ProudctTemplateService.addTemplate(pt) > 0) {
			return JubaoResult.ok();
		}
		return JubaoResult.build(500, "添加失败");
	}

	
	/***
	 * 查询出分类下的模板
	 */
	@RequestMapping(value="findTemplate/${cid}")
	@ResponseBody
	public JubaoResult  findTemplate(@PathVariable("cid")Integer cid,Model model){
		//判断是不是3级分类，是就去查询分类下的模板
			ProductTemplate ProudctTemplate = ProudctTemplateService.findTemplate(cid);
			if (ProudctTemplate != null) {
				model.addAttribute("ProductTemplate", ProudctTemplate);		
				JubaoResult.ok(ProudctTemplate);
			 }
		return JubaoResult.build(500,"该分类没有添加模板信息");
	}
	
}
