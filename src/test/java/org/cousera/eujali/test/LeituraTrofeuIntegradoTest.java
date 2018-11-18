package org.cousera.eujali.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.coursera.eujali.model.EstadoTrofeu;
import org.coursera.eujali.model.Livro;
import org.coursera.eujali.model.Trofeu;
import org.coursera.eujali.model.Usuario;
import org.coursera.eujali.repository.LivrosDAO;
import org.coursera.eujali.repository.LeiturasDAO;
import org.coursera.eujali.repository.TrofeusDAO;
import org.coursera.eujali.repository.UsuariosDAO;
import org.coursera.eujali.service.LivroService;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LeituraTrofeuIntegradoTest extends SistemaTest {

	private JdbcDatabaseTester jdt;
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tr;
	private boolean ocorreuExcecao;

	public LeituraTrofeuIntegradoTest() {
		super();
		emf = Persistence.createEntityManagerFactory("EuJaLiPU");
	}

	@Before
	public void inicializaTeste() throws Exception {
		jdt = new JdbcDatabaseTester("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/portal-livros", "root", "");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
		
		em = emf.createEntityManager();
		tr = em.getTransaction();
		tr.begin();
		ocorreuExcecao = false;
		
		usuarios = new UsuariosDAO();
		((UsuariosDAO) usuarios).setEm(em);
		livros = new LivrosDAO();
		((LivrosDAO)livros).setEm(em);
		leituras = new LeiturasDAO();
		((LeiturasDAO) leituras).setEm(em);
		trofeus = new TrofeusDAO();
		((TrofeusDAO) trofeus).setEm(em);
		livroService = new LivroService();
		livroService.setUsuarios(usuarios);
		livroService.setTrofeus(trofeus);
		leitores = new Usuario[QUANTIDADE_LEITORES];
		livrosLidos = new Livro[QUANTIDADE_LIVROS];
	}

	@After
	public void finalizaTeste() throws Exception {
		if (ocorreuExcecao){
			tr.rollback();
		};
		em.close();
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}

	
	@Test
	public void when01LivroLidoInformaticaThenRegistra02PontosJoséSilva() {
		try {
			carregarLeitores(new Long[] { 1L });
			carregarLivros(new Long[] { 1L });
			verificarNaoNulo(new Long[] { 1L }, new Long[] { 1L });
			verificarCarregamento(new Long[] { 1L }, new Long[] { 1L });
			marcarLido(leitores[0]);
			tr.commit();
			carregarLeitores(new Long[] { 1L });
			assertEquals(new Integer(2), leitores[0].getPontos());
			assertEquals(new Integer(0), leitores[0].getQuantidadeTrofeus());
			assertEquals(1, leitores[0].getTrofeus().size());
			Trofeu trofeu1 = (Trofeu) leitores[0].getTrofeus().get(0);
			assertEquals(EstadoTrofeu.CONCORRENDO, trofeu1.getEstado());
			assertEquals(new Integer(1), trofeu1.getQuantidadeLivros());
			ranking = livroService.ranking(QUANTIDADE_LEITORES_RANKING);
			assertNotNull(ranking);
			assertEquals(10, ranking.size());
			verificarRanking(
					new String[] { "José Silva", "Ana Paula Félix", "Carla Ferrari", "Carlos Silva", "Edvaldo Pereira", 
							"Lucas Souza", "Luciana Campos", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles" },
					new int[] { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
		} catch (Exception e) {
			ocorreuExcecao = true;
		}
	}
 
	@Test
	public void when02LivrosLidosInformaticaThenRegistra04PontosJoséSilva() {
		try {
			carregarLeitores(new Long[] { 1L });
			carregarLivros(new Long[] { 1L, 2L });
			verificarNaoNulo(new Long[] { 1L }, new Long[] { 1L, 2L });
			verificarCarregamento(new Long[] { 1L }, new Long[] { 1L, 2L });
			marcarLido(leitores[0]);
			tr.commit();
			carregarLeitores(new Long[] { 1L });
			assertEquals(new Integer(6), leitores[0].getPontos());
			assertEquals(new Integer(0), leitores[0].getQuantidadeTrofeus());
			assertEquals(1, leitores[0].getTrofeus().size());
			Trofeu trofeu1 = (Trofeu) leitores[0].getTrofeus().get(0);
			assertEquals(EstadoTrofeu.CONCORRENDO, trofeu1.getEstado());
			assertEquals(new Integer(2), trofeu1.getQuantidadeLivros());
			ranking = livroService.ranking(QUANTIDADE_LEITORES_RANKING);
			assertNotNull(ranking);
			assertEquals(10, ranking.size());
			verificarRanking(
					new String[] { "José Silva", "Ana Paula Félix", "Carla Ferrari", "Carlos Silva", "Edvaldo Pereira", 
							"Lucas Souza", "Luciana Campos", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles" },
					new int[] { 6, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
		} catch (Exception e) {
			ocorreuExcecao = true;
		}
	}
	
	@Test
	public void when04LivrosLidosInformaticaThenRegistra04PontosJoséSilva() {
		try {
			carregarLeitores(new Long[] { 1L });
			carregarLivros(new Long[] { 1L, 2L, 4L, 5L });
			verificarNaoNulo(new Long[] { 1L }, new Long[] { 1L, 2L, 4L, 5L });
			verificarCarregamento(new Long[] { 1L }, new Long[] { 1L, 2L, 4L, 5L });
			marcarLido(leitores[0]);
			tr.commit();
			carregarLeitores(new Long[] { 1L });
			assertEquals(new Integer(13), leitores[0].getPontos());
			assertEquals(new Integer(0), leitores[0].getQuantidadeTrofeus());
			assertEquals(1, leitores[0].getTrofeus().size());
			Trofeu trofeu1 = (Trofeu) leitores[0].getTrofeus().get(0);
			assertEquals(EstadoTrofeu.CONCORRENDO, trofeu1.getEstado());
			assertEquals(new Integer(4), trofeu1.getQuantidadeLivros());
			ranking = livroService.ranking(QUANTIDADE_LEITORES_RANKING);
			assertNotNull(ranking);
			assertEquals(10, ranking.size());
			verificarRanking(
					new String[] { "José Silva", "Ana Paula Félix", "Carla Ferrari", "Carlos Silva", "Edvaldo Pereira", 
							"Lucas Souza", "Luciana Campos", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles" },
					new int[] { 13, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
		} catch (Exception e) {
			ocorreuExcecao = false;
		}
	}

	@Test
	public void when07LivrosLidosInformaticaThenRegistra22Pontos01TrofeuInformaticaJoséSilva() {
		try {
			carregarLeitores(new Long[] { 1L });
			carregarLivros(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L });
			verificarNaoNulo(new Long[] { 1L }, new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L });
			verificarCarregamento(new Long[] { 1L }, new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L });
			marcarLido(leitores[0]);
			tr.commit();
			carregarLeitores(new Long[] { 1L });
			assertEquals(new Integer(22), leitores[0].getPontos());
			assertEquals(new Integer(1), leitores[0].getQuantidadeTrofeus());
			assertEquals(2, leitores[0].getTrofeus().size());
			Trofeu trofeu1 = (Trofeu) leitores[0].getTrofeus().get(0);
			Trofeu trofeu2 = (Trofeu) leitores[0].getTrofeus().get(1);
			assertEquals(EstadoTrofeu.CONQUISTADO, trofeu1.getEstado());
			assertEquals(EstadoTrofeu.CONCORRENDO, trofeu2.getEstado());
			assertEquals(new Integer(5), trofeu1.getQuantidadeLivros());
			assertEquals(new Integer(2), trofeu2.getQuantidadeLivros());
			ranking = livroService.ranking(QUANTIDADE_LEITORES_RANKING);
			assertNotNull(ranking);
			assertEquals(10, ranking.size());
			verificarRanking(
					new String[] { "José Silva", "Ana Paula Félix", "Carla Ferrari", "Carlos Silva", "Edvaldo Pereira", 
							"Lucas Souza", "Luciana Campos", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles" },
					new int[] {22, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
		} catch (Exception e) {
			ocorreuExcecao = false;
		}
	}

	@Test
	public void when07LivrosLidosInformatica06CulinariaThenRegistra22Pontos01TrofeuInformatica01CulinariaJoséSilva() {
		try {
			carregarLeitores(new Long[] { 1L });
			carregarLivros(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L });
			verificarNaoNulo(new Long[] { 1L }, new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L });
			verificarCarregamento(new Long[] { 1L }, new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L });
			marcarLido(leitores[0]);
			tr.commit();
			carregarLeitores(new Long[] { 1L });
			assertEquals(new Integer(39), leitores[0].getPontos());
			assertEquals(new Integer(2), leitores[0].getQuantidadeTrofeus());
			assertEquals(4, leitores[0].getTrofeus().size());
			Trofeu trofeu1 = (Trofeu) leitores[0].getTrofeus().get(0);
			Trofeu trofeu2 = (Trofeu) leitores[0].getTrofeus().get(1);
			Trofeu trofeu3 = (Trofeu) leitores[0].getTrofeus().get(2);
			Trofeu trofeu4 = (Trofeu) leitores[0].getTrofeus().get(3);
			assertEquals(EstadoTrofeu.CONQUISTADO, trofeu1.getEstado());
			assertEquals(EstadoTrofeu.CONCORRENDO, trofeu2.getEstado());
			assertEquals(EstadoTrofeu.CONQUISTADO, trofeu3.getEstado());
			assertEquals(EstadoTrofeu.CONCORRENDO, trofeu4.getEstado());
			assertEquals(new Integer(5), trofeu1.getQuantidadeLivros());
			assertEquals(new Integer(2), trofeu2.getQuantidadeLivros());
			assertEquals(new Integer(5), trofeu3.getQuantidadeLivros());
			assertEquals(new Integer(1), trofeu4.getQuantidadeLivros());
			ranking = livroService.ranking(QUANTIDADE_LEITORES_RANKING);
			assertNotNull(ranking);
			assertEquals(10, ranking.size());
			verificarRanking(
					new String[] { "José Silva", "Ana Paula Félix", "Carla Ferrari", "Carlos Silva", "Edvaldo Pereira", 
							"Lucas Souza", "Luciana Campos", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles" },
					new int[] { 39, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
		} catch (Exception e) {
			ocorreuExcecao = false;
		}
		
	}

}
