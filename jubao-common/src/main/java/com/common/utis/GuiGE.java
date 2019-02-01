package com.common.utis;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
/***
 * 商品规格类
 * @author 12146
 *
 */
public class GuiGE {

	private String k;

	private String v;

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	/***
	 * 将规格值切割分开
	 * @return
	 */
	@JSONField(serialize=false)
	public List<String> getGuige() {

		List ggList = new ArrayList<String>();
		if (v != null && !"".equals(v)) {
                
			System.out.println("---将规格值切割分开-------");
			String[] gg = v.split(",");
			for (int x = 0; x < gg.length; x++) {
				ggList.add(gg[x]);
			}
		}
		return ggList;
	}

}
