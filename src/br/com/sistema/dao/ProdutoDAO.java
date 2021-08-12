package br.com.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.sistema.modelo.EntradaDeProduto;
import br.com.sistema.modelo.Produto;

public class ProdutoDAO {
	
	public void gravarProduto(EntradaDeProduto entradaDeProduto) throws SQLException {
		Connection connection = null;
		
		try {
			connection = new ConnectionFactory().recuperarConexao();
			connection.setAutoCommit(false);
			boolean adicionaProdutoFuncionou = adicionaProduto(entradaDeProduto.getProduto());
			boolean registrarEntradaFuncionou = registrarEntrada(entradaDeProduto);
			System.out.println("registro "+ registrarEntradaFuncionou +", "+ adicionaProdutoFuncionou);
			System.out.println(entradaDeProduto.getProduto().toString());
			if(adicionaProdutoFuncionou && registrarEntradaFuncionou) {
				connection.commit();
			}
		}catch (SQLException e) {
			System.out.println(e);
		} finally {
			connection.close();
		}
	}
	
	private boolean registrarEntrada(EntradaDeProduto entradaDeProduto) {
		boolean funcionou = false;
		String sql = "INSERT INTO ENTRADA_DE_PRODUTO (DATA, QUANTIDADE, DESCRICAO, ID_PRODUTO) VALUES (CURRENT_TIMESTAMP,?,?,1);";
		try (Connection connection = new ConnectionFactory().recuperarConexao()) {
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setDouble(1, entradaDeProduto.getQuantidade());
				pstm.setString(2, entradaDeProduto.getDescricacao());
				//pstm.setInt(3, entradaDeProduto.getProduto().getId());
				pstm.executeUpdate();
			}
			funcionou = true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return funcionou;
	}
	
	private boolean adicionaProduto(Produto produto) throws SQLException {
		boolean funcionou = false;
		String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO, PRECO, DESATIVADO) VALUES (?,?,?,0);";
		try (Connection connection = new ConnectionFactory().recuperarConexao()) {
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, produto.getNome());
				pstm.setString(2, produto.getDescricao());
				pstm.setBigDecimal(3, produto.getPreco());
				pstm.executeUpdate();
			}
			funcionou = true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return funcionou;
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
