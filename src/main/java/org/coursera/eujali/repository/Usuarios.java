package org.coursera.eujali.repository;

import java.util.List;

import org.coursera.eujali.model.Usuario;

public interface Usuarios {

	public Usuario porId(Long id);
	public List<Usuario> todos();
	public void salvar(Usuario usuario);
	public Usuario porCodigo(String codigo);
	
}
