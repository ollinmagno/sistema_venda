package br.com.sistema.modelo;

import java.time.LocalDateTime;

public class EntradaDeProduto {
	private Integer	id;
	private LocalDateTime data = LocalDateTime.now();
	private double quantidade;
	private String descricacao;
	private Produto produto;
	
	public EntradaDeProduto() {
		
	}

	public EntradaDeProduto(LocalDateTime data, double quantidade, String descricacao, Produto produto) {
		this.data = data;
		this.quantidade = quantidade;
		this.descricacao = descricacao;
		this.produto = produto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricacao() {
		return descricacao;
	}

	public void setDescricacao(String descricacao) {
		this.descricacao = descricacao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
