package com.edson.app.item.models.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.edson.app.item.models.Item;
import com.edson.app.item.models.Produto;

@Service
public class ItemServiceImpl implements ItemService {

	private static final String HTTP_LOCALHOST_8001_PRODUTOS = "http://localhost:8001/produtos";
	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public List<Item> findAll() {
		List<Produto> produtos = Arrays.asList(clienteRest
				.getForObject(HTTP_LOCALHOST_8001_PRODUTOS, Produto[].class));
		
		return produtos
				.stream()
				.map(p -> new Item(p, 1))
				.collect(Collectors.toList())
				;
	}

	@Override
	public Item findById(Long id, Integer quantidade) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		Produto produto = clienteRest
				.getForObject(HTTP_LOCALHOST_8001_PRODUTOS+"/{id}", Produto.class, pathVariables);
		return new Item(produto, quantidade);
	}

}
