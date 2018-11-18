package org.coursera.eujali.security;

import javax.faces.context.FacesContext;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class SpringSecurityUtil {
	
	public static UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario =  null;
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken)
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null){
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		return usuario;
	}
}
