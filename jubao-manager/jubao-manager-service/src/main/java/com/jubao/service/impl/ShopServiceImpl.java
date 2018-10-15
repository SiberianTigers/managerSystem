package com.jubao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jubao.mapper.ShopMapper;
import com.jubao.mapper.ShopMessageMapper;
import com.jubao.mapper.ShopRefusedMapper;
import com.jubao.pojo.Shop;
import com.jubao.pojo.ShopCateExtension;
import com.jubao.pojo.ShopMessage;
import com.jubao.pojo.ShopRefused;
import com.jubao.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopMapper shopMapper;

	@Autowired
	private ShopRefusedMapper ShopRefusedMapper;

	@Autowired
	private ShopMessageMapper ShopMessageMapper;

	@Override
	public List<ShopCateExtension> getShopCate() {
		// TODO Auto-generated method stub
		return shopMapper.getShopCate();
	}

	@Override
	public List<Shop> selectShop(Shop shop) {
		// TODO Auto-generated method stub
		return shopMapper.selectShop(shop);
	}

	@Override
	public int auditShop() {
		// TODO Auto-generated method stub
		return shopMapper.auditShop();
	}

	@Override
	public boolean addapprovalShop(int id, int storeStatus) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			if (shopMapper.approvalShop(id, storeStatus) > 0) {
				ShopMessage shopMessage = new ShopMessage();
				shopMessage.setManagerid(1);
				shopMessage.setPrivateInfo("恭喜你成功申请开通店铺,请自觉遵守本平台相关营业规则,祝愿您生意兴隆，财源滚滚来。");
				shopMessage.setSendDate(new Date());
				shopMessage.setShopid(id);
				shopMessage.setStatus(1);
				shopMessage.setTopic("店铺申请成功通知!");
				ShopMessageMapper.addShopMessage(shopMessage);
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return flag;

	}

	@Override
	public boolean addRefusedShop(int id, int storeStatus, ShopRefused shopRefused) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			if (shopMapper.approvalShop(id, storeStatus) > 0) {
				ShopRefusedMapper.addShopRefused(shopRefused);
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

}
