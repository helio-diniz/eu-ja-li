package org.coursera.eujali.repository;


import org.coursera.eujali.model.Estilo;
import org.coursera.eujali.model.Trofeu;
import org.coursera.eujali.model.Usuario;

public interface Trofeus {
	public Trofeu porEstilo(Usuario usuario, Estilo estilo);
	public Trofeu porId(Long id);
	public void salvar(Trofeu trofeu);

}
