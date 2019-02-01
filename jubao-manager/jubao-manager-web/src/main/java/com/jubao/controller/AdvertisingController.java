package com.jubao.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.common.utis.HttpClientUtil;
import com.common.utis.JubaoResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jubao.pojo.AdvercateExtension;
import com.jubao.pojo.Advertising;
import com.jubao.pojo.AdvertisingCategory;
import com.jubao.service.AdvertisingCategoryService;
import com.jubao.service.AdvertisingService;
import com.jubao.service.PictureService;

@Controller
@RequestMapping(value = "/Advertising")
public class AdvertisingController extends BaseController {

	@Autowired
	private AdvertisingCategoryService advertisingCategoryService;

	@Autowired
	private AdvertisingService advertisingService;
	@Autowired
	private PictureService pictureService;

	// rest服务层主机ip
	private String REST_LOCALHAST = "http://localhost:8081/rest/";

	private static final String locahast = "http://119.29.195.240";

	/***
	 * 跳转到添加图片管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/advertising")
	public String toImgManager(Model model, @RequestParam(value = "cateid", defaultValue = "0") Integer cateid,
			@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex) {
		// 查询出广告分类信息 以及广告数量
		List<AdvercateExtension> adverCateList = advertisingService.getCateGroupCount();
		int count = 0;
		for (AdvercateExtension cate : adverCateList) {// 统计有效总数
			count += cate.getEffectiveNumber();
		}
		PageHelper.startPage(pageIndex, 5);// startIndex 从第几条数据取值
		// pageSize :每页显示几条数据

		Advertising adver = new Advertising();
		if (cateid != 0) {
			adver.setCategoryId(cateid);
		}

		// 查询出广告
		List<Advertising> advertisingList = advertisingService.getCateidAdvertising(adver);

		PageInfo<Advertising> pageInfo = new PageInfo<Advertising>(advertisingList);

		System.out.println(locahast + "总分页数==size" + pageInfo.getSize() + "==pageSize" + pageInfo.getPageSize());

		model.addAttribute("adverCateList", adverCateList);
		model.addAttribute("cateSum", count);
		model.addAttribute("advertisingList", advertisingList);
		model.addAttribute("locahast", locahast);// 图片存放主机ip
		model.addAttribute("cateid", cateid);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageInfo", pageInfo);

		return "advertising";
	}

	/***
	 * 加载广告分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadCate", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult loadCate() {
		List<AdvertisingCategory> cateList = advertisingCategoryService
				.getAllAdvertisingCategory(new AdvertisingCategory(null, 1));
		if (cateList != null) {
			return JubaoResult.ok(cateList);
		} else {
			return JubaoResult.build(400, "加载分类失败");
		}
	}

	/***
	 * 添加广告
	 * 
	 * @return
	 */
	@RequestMapping(value = "addAdvertising", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public JubaoResult addAdvertising(Advertising advertising,
			@RequestParam(value = "imgInfo", required = false) MultipartFile fileInfo, HttpServletRequest request) {

		if (advertising == null) {
			return JubaoResult.build(400, "表单数据为空");
		}

		System.out.println("添加广告==============" + advertising);
		// 上傳圖片
		Map<String, String> resultMap = null;
		try {
			resultMap = pictureService.PictureLoad(fileInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (resultMap.get("Info") == "1") {
			advertising.setAdvertisinUrl(resultMap.get("url"));// 图片存放路径
			System.out.println("----------成功----"+resultMap.get("url"));
		} else {
			return JubaoResult.build(400, resultMap.get("message"));
		}

		// 获取到广告过期时间
		advertising.setAdvertisinStart(pictureService.getEndDateTime(advertising.getAdvertisinTime(),
				advertising.getAdvertisinStartTime().getTime()));
		advertising.setAdvertisinCreate(new Date());// 创建时间
		advertising.setAdvertisinUserType(1);// 设置用户类型
		advertising.setAdvertisinUserId(1);

		try {
			if (advertisingService.addAdvertising(advertising) > 0) {
				// 调用redis服务层清空缓存中的广告
				HttpClientUtil.doGet(REST_LOCALHAST + "advertising/delRedisAdvertising");
				return JubaoResult.ok();

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JubaoResult.build(400, "添加失敗");
	}

	/***
	 * 修改广告状态
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public JubaoResult updateStatus(@RequestParam(value = "vid") Integer vid,
			@RequestParam(value = "status", defaultValue = "1") Integer status) {

		try {
			if (advertisingService.updateAdvertising(new Advertising(vid, status)) > 0) {
				// 调用redis服务层清空缓存中的广告
				HttpClientUtil.doGet(REST_LOCALHAST + "advertising/delRedisAdvertising");
				return JubaoResult.ok();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JubaoResult.build(400, "修改失败");
	}

	/***
	 * 删除图片分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteAdvertising", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public JubaoResult deleteCate(@RequestParam(value = "id", required = false) Integer id) {

		System.out.println(id + "=============");
		// 添加操作
		if (advertisingService.deleteAdvertising(id) > 0) {
			// 调用redis服务层清空缓存中的广告
			HttpClientUtil.doGet(REST_LOCALHAST + "advertising/delRedisAdvertising");
			return JubaoResult.ok();
		}
		return JubaoResult.build(500, "操作错误");
	}

	/***
	 * 批量删除图片分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/Sumdelete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public JubaoResult Sumdelete(@RequestParam(value = "array", required = false) String array) {

		JSONArray json = JSONArray.parseArray(array); // 将字符串转为JSON数组类型

		int[] arr = JSON.toJavaObject(json, int[].class); // 将json字符串数组转换为 int数组

		for (int x = 0; x < arr.length; x++) {

			System.out.println("===批量删除广告id为：=====" + arr[x]);
		}

		if (advertisingService.deleteCount(arr) == arr.length) {
			// 调用redis服务层清空缓存中的广告
			HttpClientUtil.doGet(REST_LOCALHAST + "advertising/delRedisAdvertising");
			return JubaoResult.ok();
		}
		// 添加操作
		return JubaoResult.build(500, "操作失败");
	}

	/***
	 * 修改赋值
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateSetInfo", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult updateSetInfo(@RequestParam(value = "vid") Integer vid) {

		List<Advertising> AdvertisingList = advertisingService.getCateidAdvertising(new Advertising(vid, null));
		if (AdvertisingList != null && AdvertisingList.size() > 0) {

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

			AdvertisingList.get(0).setDateInfo(sf.format(AdvertisingList.get(0).getAdvertisinStartTime()));

			System.out.println("=====日期消息" + AdvertisingList.get(0).getDateInfo());
			return JubaoResult.ok(AdvertisingList.get(0));

		} else {
			return JubaoResult.build(400, "加载分类失败");
		}
	}

	/***
	 * 修改保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateSave", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public JubaoResult updateSave(Advertising advertising,
			@RequestParam(value = "imgInfo", required = false) MultipartFile fileInfo, HttpServletRequest request) {

		System.out.println("修改广告==============" + advertising);
		// 上傳圖片
		Map<String, String> resultMap = pictureService.PictureLoad(fileInfo);

		if (resultMap.get("Info") == "1") {
			advertising.setAdvertisinUrl(resultMap.get("url"));// 图片存放路径
		} else if (resultMap.get("message").equals("文件为空")) {
			advertising.setAdvertisinUrl(null);
		} else {
			return JubaoResult.build(400, resultMap.get("message"));
		}
		if (advertising == null) {
			return JubaoResult.build(400, "表单数据为空");
		}
		// 获取到广告过期时间
		advertising.setAdvertisinStart(pictureService.getEndDateTime(advertising.getAdvertisinTime(),
				advertising.getAdvertisinStartTime().getTime()));

		if (advertisingService.updateAdvertising(advertising) > 0) {
			// 调用redis服务层清空缓存中的广告
			HttpClientUtil.doGet(REST_LOCALHAST + "advertising/delRedisAdvertising");
			return JubaoResult.ok();
		}
		return JubaoResult.build(400, "修改失敗");
	}
}
