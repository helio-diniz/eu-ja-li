package org.coursera.eujali.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Seguranca {
	
	public String getNomeUsuario(){
		String nome = null;
		UsuarioSistema usuarioLogado = SpringSecurityUtil.getUsuarioLogado();
		if (usuarioLogado != null){
			nome = usuarioLogado.getUsuario().getNome();
		}	
		return nome;
	}


}
