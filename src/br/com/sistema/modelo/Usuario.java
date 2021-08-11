package br.com.sistema.modelo;

public class Usuario {
	
	private int id;
	private String email;
	private String nome;
	private String senha;
	private String cpf;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String email, String senha) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
