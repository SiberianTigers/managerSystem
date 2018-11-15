package com.jubao.shop.service.impl;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.common.utis.FtpUtil;
import com.common.utis.IDUtils;
import com.jubao.shop.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {

	@Value("${IMGFILE_LOCAHAST}")
	private String IMGFILE_LOCAHAST;
	@Value("${USER}")
	private String USER;
	@Value("${PASSWORD}")
	private String PASSWORD;
	@Value("${BASEPATH}")
	private String BASEPATH;
	@Value("${PORT}")
	private Integer PORT;

	@Override
	public Map<String,String> PictureLoad(MultipartFile fileInfo) {
		// TODO Auto-generated method stub
 
		 Map<String,String>resultMap=new HashMap<String, String>();
		
		if (!fileInfo.isEmpty()) {// 判断是否为空
			// 不为空

			if (fileInfo.getSize() < 5242880) {

				try {
					List<String> gifList = Arrays.asList("jpg", "gif", "png", "jpeg","icon");

					// 文件合格
					String fineName = fileInfo.getOriginalFilename();// 获取文件名称

					if (gifList.contains(org.springframework.util.StringUtils.getFilenameExtension(fineName))) {
						// 文件格式合格
						String newFileName = IDUtils.genImageName(); // 获取 新的文件名
						newFileName = newFileName + "." + StringUtils.getFilenameExtension(fineName); // 新文件名加旧文件名的后缀名
						String imgPath = new DateTime().toString("yyyy/MM/dd");// 存放文件的文件夹名称

						boolean flag = FtpUtil.uploadFile(IMGFILE_LOCAHAST, PORT, USER, PASSWORD, BASEPATH, imgPath,
								newFileName, fileInfo.getInputStream());
						if (!flag) {
							resultMap.put("Info","0");
							resultMap.put("message","上传文件失败");
						 return resultMap;
						}
						resultMap.put("Info","1");
						resultMap.put("url",File.separator+imgPath+File.separator+newFileName);
						System.out.println(IMGFILE_LOCAHAST+File.separator+imgPath+File.separator+newFileName);
						return resultMap;			
					} else {
						// 文件格式不ok
						resultMap.put("Info","0");
						resultMap.put("message","文件格式不合格");
						return resultMap;
					}
				} catch (Exception e) {
					// TODO: handle exception
					resultMap.put("Info","0");
					resultMap.put("message","上传文件发生错误");
					return resultMap;
				}

			} else {
				// 文件过大
				resultMap.put("Info","0");
				resultMap.put("message","文件过大");
				return resultMap;
			}

		} else {
			// 图片为空
			resultMap.put("Info","0");
			resultMap.put("message","文件为空");
			return resultMap;
		}

	}

	@Override
	public Date getEndDateTime(int num,long time) {
		// TODO Auto-generated method stub
		//得到到期时间
		long startTime=num*3600000;  
	
		 Date date=new Date(startTime+time);
		 
		return date;
	}

}
