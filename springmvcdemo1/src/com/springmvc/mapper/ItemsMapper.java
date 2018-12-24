package com.springmvc.mapper;

import java.util.List;

import com.springmvc.po.Items;

public interface ItemsMapper {
	
	public List<Items> getItemLists();

	public Items findItemsById(Integer id);

	public void updateitem(Items items); 
	
}
