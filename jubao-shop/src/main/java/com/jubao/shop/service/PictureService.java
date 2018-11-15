package com.jubao.shop.service;

import java.util.Date;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {

	/**
	 * ͼƬ�ϴ�������
	 * @param multipartFile
	 * @return
	 */
	public Map<String,String> PictureLoad(MultipartFile multipartFile);
	
	
	/***
	 * �������浽��ʱ��
	 * @param num
	 * @return
	 */
	public Date getEndDateTime(int num,long time);
}
