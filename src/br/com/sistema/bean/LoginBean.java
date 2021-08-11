package br.com.sistema.bean;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.sistema.dao.UsuarioDAO;
import br.com.sistema.modelo.Usuario;
import br.com.sistema.util.RedirectView;

@ManagedBean
@ViewScoped
public class LoginBean {
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public RedirectView efetueLogin() throws SQLException {
		System.out.println("fazendo login do usuário: "+ this.usuario.getEmail());
		FacesContext context = FacesContext.getCurrentInstance();
		boolean existe = new UsuarioDAO().usuarioExiste(this.usuario);
		if(existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return new RedirectView("sistema");
		}
		context.getExternalContext().getFlash().setKeepMessages(true);
		error();
		return new RedirectView("login");
	}
	private void error() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Usuário ou senha incorreto. Confira-a."));
	}
	public RedirectView deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return new RedirectView("login");
	}
}
