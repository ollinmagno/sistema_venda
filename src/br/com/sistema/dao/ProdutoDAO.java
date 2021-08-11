package br.com.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistema.modelo.Produto;

public class ProdutoDAO {
	

	public void adicionaProduto(Produto produto) throws SQLException {
		String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO, PRECO, DESATIVADO) VALUES (?,?,?,0);";
		try (Connection connection = new ConnectionFactory().recuperarConexao()) {
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, produto.getNome());
				pstm.setString(2, produto.getDescricao());
				pstm.setBigDecimal(3, produto.getPreco());
				pstm.execute();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public List<Produto> listaProdutos() throws SQLException{
		List<Produto> listaProdutos = new ArrayList<Produto>();
		
		String sql = "SELECT ID, NOME, DESCRICAO, PRECO, DESATIVADO FROM PRODUTO;";
		try(Connection connection = new ConnectionFactory().recuperarConexao()){
			try(PreparedStatement pstm = connection.prepareStatement(sql)){
				try(ResultSet resultset = pstm.executeQuery()){
					while(resultset.next()) {
						Produto produto = new Produto();
						produto.setId(resultset.getInt(1));
						produto.setNome(resultset.getString(2));
						produto.setDescricao(resultset.getString(3));
						produto.setPreco(resultset.getBigDecimal(4));
						produto.setDesativado(resultset.getBoolean(5));
						listaProdutos.add(produto);
					}
				}
			}
		}
		return listaProdutos;
	}
	
	public List<Produto> listaProdutosDesativadosDoSistema() throws SQLException {
		List<Produto> listaProdutosDesativados = new ArrayList<Produto>();
		
		String sql = "SELECT ID, NOME, DESCRICAO, PRECO, DESATIVADO FROM PRODUTO WHERE DESATIVADO = 1;";
		try(Connection connection = new ConnectionFactory().recuperarConexao()){
			try(PreparedStatement pstm = connection.prepareStatement(sql)){
				try(ResultSet resultset = pstm.executeQuery()){
					while(resultset.next()) {
						Produto produto = new Produto();
						produto.setId(resultset.getInt(1));
						produto.setNome(resultset.getString(2));
						produto.setDescricao(resultset.getString(3));
						produto.setPreco(resultset.getBigDecimal(4));
						produto.setDesativado(resultset.getBoolean(5));
						listaProdutosDesativados.add(produto);
					}
				}
			}
		}
		return listaProdutosDesativados;
	}
}
