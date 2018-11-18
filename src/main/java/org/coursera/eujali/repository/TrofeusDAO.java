package org.coursera.eujali.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.coursera.eujali.model.EstadoTrofeu;
import org.coursera.eujali.model.Estilo;
import org.coursera.eujali.model.Trofeu;
import org.coursera.eujali.model.Usuario;

public class TrofeusDAO implements Serializable, Trofeus {

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	public TrofeusDAO() {
		super();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Trofeu porEstilo(Usuario usuario, Estilo estilo) {
		// TODO Auto-generated method stub
		Trofeu trofeu;
		try {
			trofeu = em.createQuery("from Trofeu " +
								          "where usuario.id = :usuarioId "
							           +"and estilo = :estilo "
							           +"and estado = :estado"
								           , Trofeu.class)
					.setParameter("usuarioId", usuario.getId())
					.setParameter("estilo", estilo)
					.setParameter("estado", EstadoTrofeu.CONCORRENDO)
					.getSingleResult();
		} catch (Exception e) {
			trofeu = null;
		}		
		return trofeu;
	}

	@Override
	public void salvar(Trofeu trofeu) {
		// Apenas para manter compatibilidade; Troféu será salvo por Usuario
	}

	@Override
	public Trofeu porId(Long id) {
		return (Trofeu) em.createQuery("     from Trofeu t " +
		                               "left join t.livros l"+
		                               "    where t.id = :trofeuId")
				.setParameter("trofeuId", id)
				.getSingleResult();
	}

}
