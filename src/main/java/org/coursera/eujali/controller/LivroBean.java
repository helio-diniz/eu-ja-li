package org.coursera.eujali.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.coursera.eujali.model.Livro;
import org.coursera.eujali.model.Usuario;
import org.coursera.eujali.security.SpringSecurityUtil;
import org.coursera.eujali.security.UsuarioSistema;
import org.coursera.eujali.service.LivroService;
import org.coursera.eujali.util.jsf.FacesUtil;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Livro livro;
	@Inject
	private LivroService livroService;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public String getImagemCapa() {
		return (livro != null) ? livro.getImagemCapa() : "Bistro.jpg";
	}
	
	@PostConstruct
	public void inicializar(){
		UsuarioSistema usuarioLogado = SpringSecurityUtil.getUsuarioLogado();
		if (usuarioLogado != null){
			usuario = usuarioLogado.getUsuario();
		}
	}

	public void marcarLido() {
		livroService.marcarLido(usuario, livro);
		FacesUtil.addInfoMessage("Leitura de livro marcada pelo usuario com sucesso!");
	}

}
