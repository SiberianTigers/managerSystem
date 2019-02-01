package com.jubao.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * ��Ʒ��
 * 
 * @author 12146
 *
 */
public class Product {

	private Long pid;// id
	private String title;// ��Ʒ����
	private String sell_point;// ����
	private Float price;// ���
	private Integer num;// �������
	private String image = ""; // ��ƷͼƬ
	private Integer cid;// ����id
	private Integer status;// ��Ʒ״̬
	private Date created;// ���ʱ��
	private Date updated;// �޸�ʱ��
	private Long storid;// ��������id
	private Integer haveSales;// ��������
	private Integer numberOfCollections;// ���ղش���
  
	private String desc;// ��Ʒ����

	private String temp_value_add;// ��Ʒ���

	private String cateName;//商品分类名称
	
	
	
	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTemp_value_add() {
		return temp_value_add;
	}

	public void setTemp_value_add(String temp_value_add) {
		this.temp_value_add = temp_value_add;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSell_point() {
		return sell_point;
	}

	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

  

	public Long getStorid() {
		return storid;
	}

	public void setStorid(Long storid) {
		this.storid = storid;
	}

	public Integer getHaveSales() {
		return haveSales;
	}

	public void setHaveSales(Integer haveSales) {
		this.haveSales = haveSales;
	}

	public Integer getNumberOfCollections() {
		return numberOfCollections;
	}

	public void setNumberOfCollections(Integer numberOfCollections) {
		this.numberOfCollections = numberOfCollections;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", title=" + title + ", sell_point=" + sell_point + ", price=" + price + ", num="
				+ num + ", image=" + image + ", cid=" + cid + ", status=" + status + ", created=" + created
				+ ", updated=" + updated + ", storid=" + storid + ", haveSales=" + haveSales + ", numberOfCollections="
				+ numberOfCollections + ", getPid()=" + getPid() + ", getTitle()=" + getTitle() + ", getSell_point()="
				+ getSell_point() + ", getPrice()=" + getPrice() + ", getNum()=" + getNum() + ", getImage()="
				+ getImage() + ", getCid()=" + getCid() + ", getStatus()=" + getStatus() + ", getCreated()="
				+ getCreated() + ", getUpdated()=" + getUpdated() + ", getStorid()=" + getStorid() + ", getHaveSales()="
				+ getHaveSales() + ", getNumberOfCollections()=" + getNumberOfCollections() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
   
	 /***
	  * ����Ʒ��ͼƬ�ֿ�
	  * @return
	  */
	public List<String> getImg() {

		List<String> imgList = new ArrayList<String>();	
		String[] img = this.image.split(",");
		for (int x = 0; x < img.length; x++) {
			System.out.println(img[x]+"-------�и�ͼƬ------");
			imgList.add(img[x]);
		}	
		return imgList;
	}

}
