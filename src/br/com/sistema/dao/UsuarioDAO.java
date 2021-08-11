package br.com.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.sistema.modelo.Usuario;

public class UsuarioDAO {

	public boolean usuarioExiste(Usuario usuario) throws SQLException {
		String sql = "SELECT EMAIL, SENHA FROM USUARIO";
		boolean autenticaUsuario = false;
		try (Connection connection = new ConnectionFactory().recuperarConexao()) {
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				try (ResultSet result = pstm.executeQuery()) {
					while (result.next()) {
						boolean emailValido = usuario.getEmail().equals(result.getString(1));
						boolean senhaValida = usuario.getSenha().equals(result.getString(2));
						if(emailValido && senhaValida) {
							autenticaUsuario = true;
						}
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return autenticaUsuario;
	}
}
