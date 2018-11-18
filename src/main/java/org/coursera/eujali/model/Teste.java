package org.coursera.eujali.model;

//import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.coursera.eujali.repository.LeiturasDAO;
import org.coursera.eujali.repository.LivrosDAO;
import org.coursera.eujali.repository.TrofeusDAO;
import org.coursera.eujali.repository.UsuariosDAO;
import org.coursera.eujali.service.LivroService;



public class Teste {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EuJaLiPU");
		EntityManager em =emf.createEntityManager();
		
		EntityTransaction trx = em.getTransaction();
		
		UsuariosDAO usuarioDAO = new UsuariosDAO();
		usuarioDAO.setEm(em);
		LivrosDAO livroDAO = new LivrosDAO();
		livroDAO.setEm(em);
		LeiturasDAO leituraDAO = new LeiturasDAO();
		leituraDAO.setEm(em);
		TrofeusDAO trofeusDAO = new TrofeusDAO();
		trofeusDAO.setEm(em);
		
		trx.begin();
		Usuario usuario = usuarioDAO.porId(1L);
		Livro livro = livroDAO.porId(1L);
		LivroService livroService = new LivroService();
		livroService.setTrofeus(trofeusDAO);
//		livroService.setLeituras(leituraDAO);
		livroService.setUsuarios(usuarioDAO);
		
		System.out.println("Trofeus do Usuario: "+ usuario.getTrofeus().size());
		System.out.println("Leituras do Usuario: "+ usuario.getLeituras().size());
		
		livroService.marcarLido(usuario, livro);

	
		trx.commit();
		/*
		
		//--------------------------------------------
		EntityTransaction trx = em.getTransaction();
		UsuariosDAO usuarioDAO = new UsuariosDAO();
		usuarioDAO.setEm(em);
		LivrosDAO livroDAO = new LivrosDAO();
		livroDAO.setEm(em);
		trx.begin();
			Usuario usuario = usuarioDAO.porId(1L);
			Livro livro = livroDAO.porId(1L);
			Leitura leitura = new Leitura();
			leitura.setUsuario(usuario);
			leitura.setLivro(livro);
			leitura.setPontos(2);
			usuario.getLeituras().add(leitura);
			em.merge(leitura);
			em.merge(usuario);
	
		trx.commit();
		//--------------------------------------------
		
		
		List<Leitura> leituras = em.createQuery("from Leitura", Leitura.class).getResultList();
		for(Leitura leitura: leituras){
			System.out.println(leitura.getPontos());
		}
		
		Usuario usuario = null;
		usuario =  (Usuario)(em.createQuery("select u from Usuario u " 
				               + "LEFT JOIN u.trofeus t "
				               + "LEFT JOIN u.leituras l "	
		                       + "where u.id = :usuarioId "
		           )
				   .setParameter("usuarioId", new Long(1L))
				   .getSingleResult());
			
		System.out.println("Trofeu: " + usuario.getNome() + " - Quantidade Trofeus: " + usuario.getTrofeus().size() + " Pontos: " + usuario.getLeituras().get(0).getPontos());

		

		Trofeu trofeu = null;
		trofeu = em.createQuery("from Trofeu " +
		          "where usuario.id = :usuarioId "
	               +"and estilo = :estilo "
	               +"and estado = :estado"
		           , Trofeu.class)
				   .setParameter("usuarioId", new Long(1L))
	 			   .setParameter("estilo", Estilo.INFORMATICA)
				   .setParameter("estado", EstadoTrofeu.CONQUISTADO)
				   .getSingleResult();
		if (trofeu !=null) {
			System.out.println("Trofeu: " + trofeu.getId() + " - " + trofeu.getEstilo() );
		}	
		

		List<Livro> livros = em.createQuery("from Livro", Livro.class).getResultList();
		for(Livro livro: livros){
			System.out.println(livro.getTitulo()+ " - ");
			for(Autor autor: livro.getAutores()){
				System.out.print(autor.getNome() + " ");
			}
			System.out.println("");
			System.out.println("--------------------------------------------------------------------------------------------");
		}
		
		System.out.println("--------------------------------------------------------------------------------------------");
		List<Usuario> usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();
		for(Usuario usuario: usuarios){
			System.out.println("Código: " + usuario.getCodigo() + " Nome: "+ usuario.getNome());
		}

		
		System.out.println("--------------------------------------------------------------------------------------------");
		List<PontuacaoLeitura> pontuacao = em.createQuery("from PontuacaoLeitura", PontuacaoLeitura.class).getResultList();
		for(PontuacaoLeitura ponto: pontuacao){
			System.out.println("Livro: " + ponto.getLivro().getTitulo() + " Usuario: "+ ponto.getUsuario().getNome());
		}
		*/		
		em.close();
	}

}
