package org.coursera.eujali.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.coursera.eujali.model.Grupo;
import org.coursera.eujali.model.Usuario;
import org.coursera.eujali.repository.Usuarios;
import org.coursera.eujali.repository.UsuariosDAO;
import org.coursera.eujali.util.cdi.CDIServiceLocator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String codigo) throws UsernameNotFoundException {
		Usuarios usuarios = CDIServiceLocator.getBean(UsuariosDAO.class);
		Usuario usuario = usuarios.porCodigo(codigo);
		UsuarioSistema user = null;
		if (usuario != null){
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> autorizacoes = new ArrayList<>();
		for (Grupo grupo: usuario.getGrupos()){
			autorizacoes.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}
		return autorizacoes;
	}

}
