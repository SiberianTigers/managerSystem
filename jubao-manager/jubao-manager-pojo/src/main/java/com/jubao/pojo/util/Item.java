package com.jubao.pojo.util;

import java.util.ArrayList;
import java.util.List;

/***
 * 查询服务使用的 实体类
 * 
 * @author 12146
 *
 */
public class Item {

	private String pid; // 商品id
	private Long id; // 临时用
	private String title; // 商品标头
	private String sellPoint;// 买点
	private double price;// 商品价格
	private String image;// 商品图片
	private String categoryName;// 分类名称
	private String item_des;// 商品描述
	private Long haveSales; // 销售数量
	private String storeName;// 店铺名称
	private Long sid;// 店铺id
	private long numberOfCollections;// 商品收藏次数

	private String itemType;// 商品规格

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	private Integer num;// 商品数量

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getHaveSales() {
		return haveSales;
	}

	public void setHaveSales(Long haveSales) {
		this.haveSales = haveSales;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public double getPrice() {
		return price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getNumberOfCollections() {
		return numberOfCollections;
	}

	public void setNumberOfCollections(long numberOfCollections) {
		this.numberOfCollections = numberOfCollections;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getItem_des() {
		return item_des;
	}

	public void setItem_des(String item_des) {
		this.item_des = item_des;
	}

	/***
	 * 
	 * 获取多张图片
	 * 
	 * @return
	 */
	public List<String> geImgList() {

		if (this.image == null || this.image.equals("")) {
			return new ArrayList<String>();
		}

		List img = new ArrayList<String>();
		try {
			String[] spitImg = this.image.split(",");

			for (int x = 0; x < spitImg.length; x++) {
				if (spitImg[x].indexOf("_60x60q90.jpg") != -1) {
					String imgs = spitImg[x].replace("_60x60q90.jpg", "_430x430q90.jpg");
					img.add(imgs);
				} else {
					img.add(spitImg[x]);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return img;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj instanceof Item)) {
			return false;
		}
		Item additem = (Item) obj;// 将传递进来的对象转为item商品对象
                   System.out.println("比较是否相等");   
		System.out.println(((additem.getId().intValue() == this.getId().intValue())
				&& (additem.getItemType().equals(this.getItemType()))) == true ? true : false);

		return ((additem.getId().intValue() == this.getId().intValue())
				&& (additem.getItemType().equals(this.getItemType()))) == true ? true : false;
	}

}
