package com.jubao.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryImmediateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.common.utis.JubaoResult;
import com.common.utis.ZTree;
import com.jubao.pojo.ItemCategory;
import com.jubao.service.ItemCategoryService;
import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping(value = "/itemCategory")
public class ItemCategoryController {

	@Resource
	private ItemCategoryService itemCategoryService;

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
			@RequestParam(value = "id", required = false) Integer id, // 商品分类id
			@RequestParam(value = "sort", required = false) Integer sort, // 商品分类排序
			@RequestParam(value = "isParent", required = false) boolean isParent, Model model)
			throws UnsupportedEncodingException {
		String filePath = null;
		if (cateName != null) {
			// get提交方式乱码处理提交
			filePath = new String(cateName.getBytes("ISO-8859-1"), "utf-8");
		}
		System.out.println("=======================" + filePath + "=======" + id + "=======" + isParent);

		model.addAttribute("cateName", filePath);
		model.addAttribute("id", id);
		model.addAttribute("sort", sort);
		model.addAttribute("isParent", isParent);
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
			return JubaoResult.ok();
		}
		return JubaoResult.build(400, "删除失败");

	}

	/***
	 * 修改商品分类
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/updateCate", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult updateCate(@RequestParam(value = "cateName") String name,@RequestParam(value="id")Integer id,@RequestParam(value="isParent")Integer isParent) {

		System.out.println(id+"==修改商品分类===" + name);

		ItemCategory cate = new ItemCategory();
		cate.setId(id);//分类id
		cate.setName(name);//分类名称
		cate.setIsParent(isParent);   //是否为父类
		cate.setUpdated(new Date());
		
		if (itemCategoryService.updateCate(cate) > 0) {
			return JubaoResult.ok();
		}

		return JubaoResult.build(400, "删除失败");

	}
}
