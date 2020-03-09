package com.edson.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.edson.app.item.models.Produto;

@FeignClient(name = "servico-produtos", url = "localhost:8001")
public interface ProdutoClienteRest {
	
	@GetMapping("/produtos")
	public List<Produto> findAll();
	
	@GetMapping("/produtos/{id}")
	public Produto findById(@PathVariable Long id);
	
}
