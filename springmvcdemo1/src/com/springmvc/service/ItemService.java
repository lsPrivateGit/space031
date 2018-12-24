package com.springmvc.service;

import java.util.List;

import com.springmvc.po.Items;

public interface ItemService {
	
	/**
	 * 查找所有商品列表信息
	 * @return List<Items> 
	 */
	public List<Items> getItemLists();
	
	/*
	 * 根据id查找单个商品信息
	 * @return Items 
	 */
	public Items findItemsById(Integer id);

	/**
	 * 更新商品信息
	 * @param items
	 */
	public void updateitem(Items items); 
}
