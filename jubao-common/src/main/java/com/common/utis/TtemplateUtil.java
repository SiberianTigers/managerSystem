package com.common.utis;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/***
 * 模板工具类
 * 
 * @author 12146
 *
 */
public class TtemplateUtil {

	private String group;// 分组

	private String params;// 分组中的项

	@JSONField(serialize=false)  
	private List<GuiGE> keyValue;// 键值对

	
	public List<GuiGE> getKeyValue() {
		if (params != null) {
			try {
				keyValue = JSON.parseArray(params, GuiGE.class);
				return keyValue;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return new ArrayList<GuiGE>();
	}

	
	public void setKeyValue(List<GuiGE> keyValue) {
		this.keyValue = keyValue;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
