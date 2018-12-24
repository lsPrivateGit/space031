package com.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.mapper.ItemsMapper;
import com.springmvc.po.Items;
import com.springmvc.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemsMapper itemsMapper;
	
	@Override
	public List<Items> getItemLists() {
		
		return itemsMapper.getItemLists();
	}

	@Override
	public Items findItemsById(Integer id) {
		
		return itemsMapper.findItemsById(id);
	}

	@Override
	public void updateitem(Items items) {
		itemsMapper.updateitem(items);
	}

}
