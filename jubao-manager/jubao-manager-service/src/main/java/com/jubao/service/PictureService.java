package com.jubao.service;

import java.util.Date;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {

	/**
	 * 图片上传服务类
	 * @param multipartFile
	 * @return
	 */
	public Map<String,String> PictureLoad(MultipartFile multipartFile);
	
	
	/***
	 * 计算出广告到期时间
	 * @param num
	 * @return
	 */
	public Date getEndDateTime(int num,long time);
}
