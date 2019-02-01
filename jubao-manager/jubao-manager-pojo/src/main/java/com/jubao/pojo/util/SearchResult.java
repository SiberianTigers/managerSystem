package com.jubao.pojo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResult {
	// 商品列表
	private List<Item> itemList;
	// 总记录数
	private long recordCount;
	// 总页数
	private long pageCount;
	// 当前页
	private long curPage;

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getCurPage() {
		return curPage;
	}

	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}

	@Override
	public String toString() {
		return "SearchResult [itemList=" + itemList + ", recordCount=" + recordCount + ", pageCount=" + pageCount
				+ ", curPage=" + curPage + "]";
	}

	/***
	 * 设置分页的页码： 显示 当前页的前 3 页 和后3页
	 * 
	 * @return
	 */

	public List<Integer> pageUtil() {

		System.out.println("-----------pageUtil-----------");

		List<Integer> pageList = new ArrayList<Integer>();

		int p = (int) this.curPage - 2;
		int s = (int) this.curPage + 3;

		if (p <= 0) {
			p = 1;
		}

		if (s >= this.pageCount) {
			s = (int) this.pageCount;
		}

		for (int x = p; x <= s; x++) {
			pageList.add(x);
		}
		return pageList;
	}

	/***
	 * 获取当前查询中的 最高价格和最低价格
	 */
/*	public List<String> priceItem() {
		List<String> dataPrice = new ArrayList<String>();
		if (itemList != null && itemList.size() > 0) {

			long[] max = { itemList.get(0).getPrice() };
			long[] min = { itemList.get(0).getPrice() };

			for (Item item : itemList) {
				if (item.getPrice() > max[0]) {
					max[0] = item.getPrice();
				}
				if (item.getPrice() < max[0]) {
					min[0] = item.getPrice();
				}
			}

			*//**
			 * 运算出 价格区间
			 *//*
			for (long x = min[0]; x < max[0]; x += min[0]) {
				dataPrice.add(x + "-" + (x + min[0]));
			}

		}
             return dataPrice;
	}*/

}
