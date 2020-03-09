package com.edson.app.item.models;

public class Item {

	private Produto produto;
	private Integer quantidade;

	public Item() {
	}

	public Item(Produto produto, Integer quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Double getTotal() {
		return produto.getPreco() * quantidade;
	}
}
