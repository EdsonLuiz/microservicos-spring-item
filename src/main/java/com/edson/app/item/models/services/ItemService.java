package com.edson.app.item.models.services;

import java.util.List;

import com.edson.app.item.models.Item;
import com.edson.app.item.models.Produto;

public interface ItemService {

	public List<Item> findAll();
	public Item findById(Long id, Integer quantidade);
	
	public Produto save(Produto produto);
	public Produto update(Produto produto, Long id);
	public void delete(Long id);
}
