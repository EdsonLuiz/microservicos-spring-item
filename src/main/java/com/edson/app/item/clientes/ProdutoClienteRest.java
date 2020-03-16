package com.edson.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.edson.app.commons.models.entities.Produto;

@FeignClient(name = "servico-produtos")
public interface ProdutoClienteRest {
	
	@GetMapping("/")
	public List<Produto> findAll();
	
	@GetMapping("/{id}")
	public Produto findById(@PathVariable Long id);
	
	@PostMapping("/")
	public Produto save(@RequestBody Produto produto);
	
	@PutMapping("/{id}")
	public Produto update(@RequestBody Produto produto, @PathVariable Long id);
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id);
}
