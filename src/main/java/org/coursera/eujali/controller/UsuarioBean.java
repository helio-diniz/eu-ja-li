package org.coursera.eujali.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.coursera.eujali.model.Trofeu;
import org.coursera.eujali.model.Usuario;
import org.coursera.eujali.repository.Usuarios;
import org.coursera.eujali.security.SpringSecurityUtil;
import org.coursera.eujali.security.UsuarioSistema;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Trofeu trofeuSelecionado;
	@Inject
	private Usuarios usuarios;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Trofeu getTrofeuSelecionado() {
		return trofeuSelecionado;
	}

	public void setTrofeuSelecionado(Trofeu trofeuSelecionado) {
		this.trofeuSelecionado = trofeuSelecionado;
	}
	
	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void insicializar() {
		UsuarioSistema usuarioLogado = SpringSecurityUtil.getUsuarioLogado();
		if (usuarioLogado != null) {
			usuario = usuarios.porId(usuarioLogado.getUsuario().getId());
		}
	}

	public void buscarLivrosTrofeu() {
	}

}
