package br.com.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.sistema.modelo.Produto;

public class ProdutoDAO {
	

	public void adicionaProduto(Produto produto) throws SQLException {
		String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO, PRECO) VALUES (?,?,?);";
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
}
