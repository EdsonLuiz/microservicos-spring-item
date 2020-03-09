package com.edson.app.item.models.services;

import java.util.List;

import com.edson.app.item.models.Item;

public interface ItemService {

	public List<Item> findAll();
	public Item findById(Long id, Integer quantidade);
}
