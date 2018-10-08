package com.jubao.service.impl;

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
import com.jubao.service.PictureService;

@Service
public class PictureServiceImpl2 implements PictureService {

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
		
		if (!fileInfo.isEmpty()) {// �ж��Ƿ�Ϊ��
			// ��Ϊ��

			if (fileInfo.getSize() < 5242880) {

				try {
					List<String> gifList = Arrays.asList("jpg", "gif", "png", "jpeg");

					// �ļ��ϸ�
					String fineName = fileInfo.getOriginalFilename();// ��ȡ�ļ�����

					if (gifList.contains(org.springframework.util.StringUtils.getFilenameExtension(fineName))) {
						// �ļ���ʽ�ϸ�
						String newFileName = IDUtils.genImageName(); // ��ȡ �µ��ļ���
						newFileName = newFileName + "." + StringUtils.getFilenameExtension(fineName); // ���ļ����Ӿ��ļ����ĺ�׺��
						String imgPath = new DateTime().toString("yyyy/MM/dd");// ����ļ����ļ�������

						boolean flag = FtpUtil.uploadFile(IMGFILE_LOCAHAST, PORT, USER, PASSWORD, BASEPATH, imgPath,
								newFileName, fileInfo.getInputStream());
						if (!flag) {
							resultMap.put("Info","0");
							resultMap.put("message","�ϴ��ļ�ʧ��");
						 return resultMap;
						}
						resultMap.put("Info","1");
						resultMap.put("url",IMGFILE_LOCAHAST+File.separator+imgPath+File.separator+newFileName);
						System.out.println(IMGFILE_LOCAHAST+File.separator+imgPath+File.separator+newFileName);
						return resultMap;			
					} else {
						// �ļ���ʽ��ok
						resultMap.put("Info","0");
						resultMap.put("message","�ļ���ʽ���ϸ�");
						return resultMap;
					}
				} catch (Exception e) {
					// TODO: handle exception
					resultMap.put("Info","0");
					resultMap.put("message","�ϴ��ļ���������");
					return resultMap;
				}

			} else {
				// �ļ�����
				resultMap.put("Info","0");
				resultMap.put("message","�ļ�����");
				return resultMap;
			}

		} else {
			// ͼƬΪ��
			resultMap.put("Info","0");
			resultMap.put("message","�ļ�Ϊ��");
			return resultMap;
		}

	}

	@Override
	public Date getEndDateTime(int num,long time) {
		// TODO Auto-generated method stub
		//�õ�����ʱ��
		long startTime=num*3600000;  
	
		 Date date=new Date(startTime+time);
		 
		return date;
	}

}
