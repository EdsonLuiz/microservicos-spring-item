package com.edson.app.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.edson.app.item.models.Item;
import com.edson.app.item.models.Produto;
import com.edson.app.item.models.services.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {
	
	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;
	
	@GetMapping("/items")
	public List<Item> index() {
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/items/{id}/quantidade/{quantidade}")
	public Item show(@PathVariable Long id, @PathVariable Integer quantidade) {
		return itemService.findById(id, quantidade);
	}
	
	public Item metodoAlternativo(@PathVariable Long id, @PathVariable Integer quantidade) {
		Item item = new Item();
		Produto produto = new Produto();
		item.setQuantidade(quantidade);
		produto.setId(id);
		produto.setNome("Notebook");
		produto.setPreco(3_000.00);
		item.setProduto(produto);
		return item;
	}
}
