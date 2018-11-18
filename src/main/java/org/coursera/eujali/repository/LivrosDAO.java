package org.coursera.eujali.repository;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.coursera.eujali.filter.LivroFilter;
import org.coursera.eujali.model.Livro;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

@Named
@Default
public class LivrosDAO implements Serializable, Livros {

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	public LivrosDAO() {
		super();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Livro porId(Long id) {
		return this.em.find(Livro.class, id);
	}

	@Override
	public List<Livro> todos() {
		// TODO Auto-generated method stub
		return this.em.createQuery("from Livro", Livro.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Livro> filtrados(LivroFilter livroFilter) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Livro.class);

		if (StringUtils.isNotBlank(livroFilter.getTitulo())) {
			criteria.add(Restrictions.ilike("titulo", livroFilter.getTitulo(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(livroFilter.getEditora())) {
			criteria.add(Restrictions.ilike("editora", livroFilter.getEditora(), MatchMode.ANYWHERE));
		}
		if (livroFilter.getEstilos() != null && livroFilter.getEstilos().length >0){
			criteria.add(Restrictions.in("estilo", livroFilter.getEstilos()));
		}
		
		if (livroFilter.getAnoPublicacao() > 0) {
			criteria.add(Restrictions.eq("anoPublicacao", livroFilter.getAnoPublicacao()));
		}

		return criteria.addOrder(Order.asc("id")).list();
	}

	@Override
	public void salvar(Livro livro) {
		em.merge(livro);

	}

}
