package org.coursera.eujali.repository;

import java.io.Serializable;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.coursera.eujali.model.Leitura;

@Named
@Default
public class LeiturasDAO implements Serializable, Leituras {

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	public LeiturasDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void salvar(Leitura pontuacao) {
		em.merge(pontuacao);
	}

}
