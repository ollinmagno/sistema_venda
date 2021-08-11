package br.com.sistema.modelo;

import java.time.LocalDateTime;

public class SaidaDeProduto {
	private Integer id;
	private LocalDateTime data = LocalDateTime.now();
	private Venda venda;
	
	public SaidaDeProduto() {
		
	}

	public SaidaDeProduto(LocalDateTime data, Venda venda) {
		this.data = data;
		this.venda = venda;
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

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
}
