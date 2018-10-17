package com.jubao.mapper;

import org.apache.ibatis.annotations.Param;

import com.jubao.pojo.Manager;

public interface loginMapper {

	//管理员登录
	Manager managerLogin(@Param("managerName")String Name);
}
