package com.jubao.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utis.GuiGE;
import com.common.utis.HttpClientUtil;
import com.common.utis.JubaoResult;
import com.common.utis.TtemplateUtil;
import com.jubao.pojo.Product_desc;
import com.jubao.pojo.TemplateValue;
import com.jubao.portal.service.ItemService;

/***
 * 商品类
 * 
 * @author 12146
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Value("${CONN_URL}")
	private String CONN_URL;

	/***
	 * 获取商品的规格
	 */
	@Override
	public TemplateValue findByIdTemplateValue(Long pid) {
		// TODO Auto-generated method stub

		Map param = new HashMap<String, String>();
		param.put("pid", pid + "");
		try {
			String result = HttpClientUtil.doPost(CONN_URL + "item/getTemplateValue", param);

			JubaoResult jubaoResult = JSON.parseObject(result, JubaoResult.class);
			if (jubaoResult.getStatus() == 200) {
				TemplateValue temp = JSONObject.parseObject(jubaoResult.getData().toString(), TemplateValue.class);

				List<TtemplateUtil> tempUtit = JSONArray.parseArray(temp.getParamData(), TtemplateUtil.class);
				List<GuiGE> ggList = null;//产品规格
				List<GuiGE> productParam = null;//产品参数
				
				
				for (TtemplateUtil t : tempUtit) {
					//获取产品参数
					productParam= JSONArray.parseArray(t.getParams().toString(), GuiGE.class);
							temp.setProductParam(productParam);
							
					if (t.getGroup().equals("规格")) {//获取产品规格
						System.out.println(t.getParams());
						ggList = JSONArray.parseArray(t.getParams().toString(), GuiGE.class);
						temp.setGgList(ggList);
					}
				}
				for(GuiGE  g:productParam){
					
					System.out.println(g.getV()+"====================产品参数");
				}
             
				
				
				return temp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Product_desc findByIdProduct_desc(Long pid) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Map param = new HashMap<String, String>();
		param.put("pid", pid + "");
		try {
			String result = HttpClientUtil.doPost(CONN_URL + "item/getProduct_desc", param);
			JubaoResult jubaoResult = JSON.parseObject(result, JubaoResult.class);
			if (jubaoResult.getStatus() == 200) {
				Product_desc desc = JSONObject.parseObject(jubaoResult.getData().toString(), Product_desc.class);
				return desc;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
