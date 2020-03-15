package com.edson.app.item.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.edson.app.item.models.Item;
import com.edson.app.item.models.Produto;
import com.edson.app.item.models.services.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class ItemController {
	
	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;
	
	@Autowired
	private Environment env;

	@Value("${configuracao.texto}")
	private String texto;
	
	@GetMapping("/")
	public List<Item> index() {
		return itemService.findAll();
	}
	
	@PostMapping("/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Produto store(@RequestBody Produto produto) {
		return itemService.save(produto);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Produto update(@RequestBody Produto produto, @PathVariable Long id) {
		return itemService.update(produto, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void destroy(@PathVariable Long id) {
		itemService.delete(id);
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
	
	@GetMapping("/obter-config")
	public ResponseEntity<?> obterConfiguracao(@Value("${server.port}") String port) {
		Map<String, String> json = new HashMap<String, String>();
		json.put("texto", texto);
		json.put("port", port);
		
		if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nome", env.getProperty("configuracao.autor.nome"));
			json.put("autor.email", env.getProperty("configuracao.autor.email"));
		}
		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
}
