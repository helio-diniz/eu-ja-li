package org.coursera.eujali.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;

	public EntityManagerProducer() {
		factory = Persistence.createEntityManagerFactory("EuJaLiPU");
	}

	// @Inject injeta um manager criado por este produtor
	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		return factory.createEntityManager();
	}

	// Quando fecha requisição, este método é chamando para fechar o manager
	// @Dispose funciona como um gatilho para perceber que a requisção foi fechada e antes de eliminar seu objeto EntityManager
	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}
}
