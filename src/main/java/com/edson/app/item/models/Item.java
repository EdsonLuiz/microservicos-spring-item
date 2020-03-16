package com.edson.app.item.models;

import com.edson.app.commons.models.entities.Produto;

public class Item {

	private Produto produto;
	private Integer quantidade;

	public Item() {
	}

	public Item(Produto produto, Integer quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getTotal() {
		return produto.getPreco() * quantidade;
	}
}
