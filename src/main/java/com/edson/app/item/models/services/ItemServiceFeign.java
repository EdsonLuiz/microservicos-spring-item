package com.edson.app.item.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.edson.app.item.clientes.ProdutoClienteRest;
import com.edson.app.item.models.Item;
import com.edson.app.item.models.Produto;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ProdutoClienteRest produtoClienteRestFeign;
	
	@Override
	public List<Item> findAll() {
		return produtoClienteRestFeign
				.findAll()
				.stream()
				.map(p -> new Item(p, 1))
				.collect(Collectors.toList())
				;
	}

	@Override
	public Item findById(Long id, Integer quantidade) {
		Produto produto = produtoClienteRestFeign.findById(id);
		return new Item(produto, quantidade);
	}

	@Override
	public Produto save(Produto produto) {
		return produtoClienteRestFeign.save(produto);
	}

	@Override
	public Produto update(Produto produto, Long id) {
		return produtoClienteRestFeign.update(produto, id);
	}

	@Override
	public void delete(Long id) {
		produtoClienteRestFeign.delete(id);
	}

}
