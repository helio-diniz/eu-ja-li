package org.coursera.eujali.repository;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.coursera.eujali.model.Usuario;

@Named
@Default
public class UsuariosDAO implements Serializable, Usuarios {

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	public UsuariosDAO() {
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
	public Usuario porId(Long id) {

		return (Usuario) em.createQuery("   select u "+ 
		                                "     from Usuario u " + 
	                                    "left join u.trofeus t " + 
	                                    "left join u.leituras l " + 
				                        " where u.id = :usuarioId")
				.setParameter("usuarioId", id).getSingleResult();

	}

	@Override
	public List<Usuario> todos() {
		return em.createQuery("from Usuario", Usuario.class).getResultList();
	}

	@Override
	public void salvar(Usuario usuario) {
		em.merge(usuario);

	}

	@Override
	public Usuario porCodigo(String codigo) {
		Usuario usuario = null;
		try {
			usuario = this.em.createQuery("from Usuario where lower(codigo) = :codigoUsuario", Usuario.class)
					.setParameter("codigoUsuario", codigo.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {
			// Nenhum usuario encontrado com o código informado
		}
		return usuario;
	}

}
