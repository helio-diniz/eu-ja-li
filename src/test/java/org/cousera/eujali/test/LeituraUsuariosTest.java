package org.cousera.eujali.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.coursera.eujali.controller.UsuarioBean;
import org.coursera.eujali.model.EstadoTrofeu;
import org.coursera.eujali.model.Estilo;
import org.coursera.eujali.repository.Usuarios;
import org.coursera.eujali.repository.UsuariosDAO;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class LeituraUsuariosTest {
	private EntityManagerFactory emf;
	private EntityManager em;
	private static JdbcDatabaseTester jdt;
	private UsuarioBean usuarioBean;
	private Usuarios usuarios;

	public LeituraUsuariosTest() {
		super();
		emf = Persistence.createEntityManagerFactory("EuJaLiPU");
	}

	@Before
	public void inicializarTeste() throws SQLException, Exception {
		em = emf.createEntityManager();
		jdt = new JdbcDatabaseTester("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/portal-livros", "root", "");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicioLeiturasUsuario.xml"));
		jdt.onSetup();
		usuarioBean = new UsuarioBean();
		usuarios = new UsuariosDAO();
		((UsuariosDAO) usuarios).setEm(em);
	}
	
	@After
	public void finalizarTeste() {
		em.close();
	}
	
	@AfterClass
	public static void finaliza() throws Exception {
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/inicio.xml"));
		jdt.onSetup();
	}	
	
	
	@Test
	public void WhenJoseLeCincoLivrosInformaticaThenConquistou16Pontos01Trofeu(){
		usuarioBean.setUsuario(usuarios.porId(1L));
		assertEquals(new Integer(16), usuarioBean.getUsuario().getPontos());
		
		assertEquals(new Long(1), usuarioBean.getUsuario().getLeituras().get(0).getLivro().getId());
		assertEquals(new Integer(2), usuarioBean.getUsuario().getLeituras().get(0).getPontos());
		assertEquals(new Long(2), usuarioBean.getUsuario().getLeituras().get(1).getLivro().getId());
		assertEquals(new Integer(4), usuarioBean.getUsuario().getLeituras().get(1).getPontos());
		assertEquals(new Long(3), usuarioBean.getUsuario().getLeituras().get(2).getLivro().getId());
		assertEquals(new Integer(3), usuarioBean.getUsuario().getLeituras().get(2).getPontos());
		assertEquals(new Long(4), usuarioBean.getUsuario().getLeituras().get(3).getLivro().getId());
		assertEquals(new Integer(3), usuarioBean.getUsuario().getLeituras().get(3).getPontos());
		assertEquals(new Long(5), usuarioBean.getUsuario().getLeituras().get(4).getLivro().getId());
		assertEquals(new Integer(4), usuarioBean.getUsuario().getLeituras().get(4).getPontos());
		
		assertEquals(new Integer(1), usuarioBean.getUsuario().getQuantidadeTrofeus());
		assertEquals(1, usuarioBean.getUsuario().getTrofeus().size());
		usuarioBean.setTrofeuSelecionado(usuarioBean.getUsuario().getTrofeus().get(0));
		assertEquals(Estilo.INFORMATICA, usuarioBean.getTrofeuSelecionado().getEstilo());
		assertEquals(EstadoTrofeu.CONQUISTADO, usuarioBean.getTrofeuSelecionado().getEstado());
		assertEquals(new Integer(5), usuarioBean.getTrofeuSelecionado().getQuantidadeLivros());
		assertEquals(new Long(1), usuarioBean.getTrofeuSelecionado().getLivros().get(0).getId());
		assertEquals(new Long(2), usuarioBean.getTrofeuSelecionado().getLivros().get(1).getId());
		assertEquals(new Long(3), usuarioBean.getTrofeuSelecionado().getLivros().get(2).getId());
		assertEquals(new Long(4), usuarioBean.getTrofeuSelecionado().getLivros().get(3).getId());
		assertEquals(new Long(5), usuarioBean.getTrofeuSelecionado().getLivros().get(4).getId());
	}
	
	@Test
	public void WhenMariaNaoLeNenhumLivroThenConquistou00Pontos00Trofeu(){
		usuarioBean.setUsuario(usuarios.porId(3L));
		assertEquals(new Integer(0), usuarioBean.getUsuario().getPontos());
		assertEquals(new Integer(0), usuarioBean.getUsuario().getQuantidadeTrofeus());
		assertEquals(0, usuarioBean.getUsuario().getLeituras().size());
		assertEquals(0, usuarioBean.getUsuario().getTrofeus().size());
	}
	
	@Test
	public void WhenLuciaLeDoisLivrosInformaticaThenConquistou08Pontos00Trofeu(){
		usuarioBean.setUsuario(usuarios.porId(4L));
		assertEquals(new Integer(8), usuarioBean.getUsuario().getPontos());
		
		assertEquals(2, usuarioBean.getUsuario().getLeituras().size());
		assertEquals(new Long(2), usuarioBean.getUsuario().getLeituras().get(0).getLivro().getId());
		assertEquals(new Integer(4), usuarioBean.getUsuario().getLeituras().get(0).getPontos());
		assertEquals(new Long(5), usuarioBean.getUsuario().getLeituras().get(1).getLivro().getId());
		assertEquals(new Integer(4), usuarioBean.getUsuario().getLeituras().get(1).getPontos());
		
		assertEquals(new Integer(0), usuarioBean.getUsuario().getQuantidadeTrofeus());
		assertEquals(1, usuarioBean.getUsuario().getTrofeus().size());
		usuarioBean.setTrofeuSelecionado(usuarioBean.getUsuario().getTrofeus().get(0));
		assertEquals(Estilo.INFORMATICA, usuarioBean.getTrofeuSelecionado().getEstilo());
		assertEquals(EstadoTrofeu.CONCORRENDO, usuarioBean.getTrofeuSelecionado().getEstado());
		assertEquals(new Integer(2), usuarioBean.getTrofeuSelecionado().getQuantidadeLivros());
		assertEquals(new Long(2), usuarioBean.getTrofeuSelecionado().getLivros().get(0).getId());
		assertEquals(new Long(5), usuarioBean.getTrofeuSelecionado().getLivros().get(1).getId());
	}
	
	@Test
	public void WhenNiloLe01LivroInformatica01Culinaria01LiteraturaThenConquistou12Pontos00Trofeu(){
		usuarioBean.setUsuario(usuarios.porId(7L));
		assertEquals(new Integer(12), usuarioBean.getUsuario().getPontos());
		
		assertEquals(3, usuarioBean.getUsuario().getLeituras().size());
		assertEquals(new Long(6), usuarioBean.getUsuario().getLeituras().get(0).getLivro().getId());
		assertEquals(new Integer(3), usuarioBean.getUsuario().getLeituras().get(0).getPontos());
		assertEquals(new Long(12), usuarioBean.getUsuario().getLeituras().get(1).getLivro().getId());
		assertEquals(new Integer(2), usuarioBean.getUsuario().getLeituras().get(1).getPontos());
		assertEquals(new Long(20), usuarioBean.getUsuario().getLeituras().get(2).getLivro().getId());
		assertEquals(new Integer(7), usuarioBean.getUsuario().getLeituras().get(2).getPontos());
		
		assertEquals(new Integer(0), usuarioBean.getUsuario().getQuantidadeTrofeus());
		assertEquals(3, usuarioBean.getUsuario().getTrofeus().size());
		usuarioBean.setTrofeuSelecionado(usuarioBean.getUsuario().getTrofeus().get(0));
		assertEquals(Estilo.INFORMATICA, usuarioBean.getTrofeuSelecionado().getEstilo());
		assertEquals(EstadoTrofeu.CONCORRENDO, usuarioBean.getTrofeuSelecionado().getEstado());
		assertEquals(new Integer(1), usuarioBean.getTrofeuSelecionado().getQuantidadeLivros());
		assertEquals(new Long(6), usuarioBean.getTrofeuSelecionado().getLivros().get(0).getId());
		usuarioBean.setTrofeuSelecionado(usuarioBean.getUsuario().getTrofeus().get(1));
		assertEquals(Estilo.CULINARIA, usuarioBean.getTrofeuSelecionado().getEstilo());
		assertEquals(EstadoTrofeu.CONCORRENDO, usuarioBean.getTrofeuSelecionado().getEstado());
		assertEquals(new Integer(1), usuarioBean.getTrofeuSelecionado().getQuantidadeLivros());
		assertEquals(new Long(12), usuarioBean.getTrofeuSelecionado().getLivros().get(0).getId());
		usuarioBean.setTrofeuSelecionado(usuarioBean.getUsuario().getTrofeus().get(2));
		assertEquals(Estilo.LITERATURA_CLASSICA, usuarioBean.getTrofeuSelecionado().getEstilo());
		assertEquals(EstadoTrofeu.CONCORRENDO, usuarioBean.getTrofeuSelecionado().getEstado());
		assertEquals(new Integer(1), usuarioBean.getTrofeuSelecionado().getQuantidadeLivros());
		assertEquals(new Long(20), usuarioBean.getTrofeuSelecionado().getLivros().get(0).getId());
	}
	
	@Test
	public void WhenCarlaLe01LivroCulinaria01LiteraturaThenConquistou07Pontos00Trofeu(){
		usuarioBean.setUsuario(usuarios.porId(11L));
		assertEquals(new Integer(7), usuarioBean.getUsuario().getPontos());
		
		assertEquals(2, usuarioBean.getUsuario().getLeituras().size());
		assertEquals(new Long(8), usuarioBean.getUsuario().getLeituras().get(0).getLivro().getId());
		assertEquals(new Integer(3), usuarioBean.getUsuario().getLeituras().get(0).getPontos());
		assertEquals(new Long(18), usuarioBean.getUsuario().getLeituras().get(1).getLivro().getId());
		assertEquals(new Integer(4), usuarioBean.getUsuario().getLeituras().get(1).getPontos());
		
		assertEquals(new Integer(0), usuarioBean.getUsuario().getQuantidadeTrofeus());
		assertEquals(2, usuarioBean.getUsuario().getTrofeus().size());
		usuarioBean.setTrofeuSelecionado(usuarioBean.getUsuario().getTrofeus().get(0));
		assertEquals(Estilo.CULINARIA, usuarioBean.getTrofeuSelecionado().getEstilo());
		assertEquals(EstadoTrofeu.CONCORRENDO, usuarioBean.getTrofeuSelecionado().getEstado());
		assertEquals(new Integer(1), usuarioBean.getTrofeuSelecionado().getQuantidadeLivros());
		assertEquals(new Long(8), usuarioBean.getTrofeuSelecionado().getLivros().get(0).getId());
		usuarioBean.setTrofeuSelecionado(usuarioBean.getUsuario().getTrofeus().get(1));
		assertEquals(Estilo.LITERATURA_CLASSICA, usuarioBean.getTrofeuSelecionado().getEstilo());
		assertEquals(EstadoTrofeu.CONCORRENDO, usuarioBean.getTrofeuSelecionado().getEstado());
		assertEquals(new Integer(1), usuarioBean.getTrofeuSelecionado().getQuantidadeLivros());
		assertEquals(new Long(18), usuarioBean.getTrofeuSelecionado().getLivros().get(0).getId());
	}
	
	@Test
	public void WhenMarianaLeSeteLivrosInformaticaThenConquistou22Pontos01Trofeu(){
		usuarioBean.setUsuario(usuarios.porId(12L));
		assertEquals(new Integer(22), usuarioBean.getUsuario().getPontos());
		assertEquals(7, usuarioBean.getUsuario().getLeituras().size());
		
		assertEquals(new Long(1), usuarioBean.getUsuario().getLeituras().get(0).getLivro().getId());
		assertEquals(new Integer(2), usuarioBean.getUsuario().getLeituras().get(0).getPontos());
		assertEquals(new Long(2), usuarioBean.getUsuario().getLeituras().get(1).getLivro().getId());
		assertEquals(new Integer(4), usuarioBean.getUsuario().getLeituras().get(1).getPontos());
		assertEquals(new Long(3), usuarioBean.getUsuario().getLeituras().get(2).getLivro().getId());
		assertEquals(new Integer(3), usuarioBean.getUsuario().getLeituras().get(2).getPontos());
		assertEquals(new Long(4), usuarioBean.getUsuario().getLeituras().get(3).getLivro().getId());
		assertEquals(new Integer(3), usuarioBean.getUsuario().getLeituras().get(3).getPontos());
		assertEquals(new Long(5), usuarioBean.getUsuario().getLeituras().get(4).getLivro().getId());
		assertEquals(new Integer(4), usuarioBean.getUsuario().getLeituras().get(4).getPontos());
		assertEquals(new Long(6), usuarioBean.getUsuario().getLeituras().get(5).getLivro().getId());
		assertEquals(new Integer(3), usuarioBean.getUsuario().getLeituras().get(5).getPontos());		
		assertEquals(new Long(7), usuarioBean.getUsuario().getLeituras().get(6).getLivro().getId());
		assertEquals(new Integer(3), usuarioBean.getUsuario().getLeituras().get(6).getPontos());		
		
		assertEquals(new Integer(1), usuarioBean.getUsuario().getQuantidadeTrofeus());
		assertEquals(2, usuarioBean.getUsuario().getTrofeus().size());
		usuarioBean.setTrofeuSelecionado(usuarioBean.getUsuario().getTrofeus().get(0));
		assertEquals(Estilo.INFORMATICA, usuarioBean.getTrofeuSelecionado().getEstilo());
		assertEquals(EstadoTrofeu.CONQUISTADO, usuarioBean.getTrofeuSelecionado().getEstado());
		assertEquals(new Integer(5), usuarioBean.getTrofeuSelecionado().getQuantidadeLivros());
		assertEquals(new Long(1), usuarioBean.getTrofeuSelecionado().getLivros().get(0).getId());
		assertEquals(new Long(2), usuarioBean.getTrofeuSelecionado().getLivros().get(1).getId());
		assertEquals(new Long(3), usuarioBean.getTrofeuSelecionado().getLivros().get(2).getId());
		assertEquals(new Long(4), usuarioBean.getTrofeuSelecionado().getLivros().get(3).getId());
		assertEquals(new Long(5), usuarioBean.getTrofeuSelecionado().getLivros().get(4).getId());
		
		usuarioBean.setTrofeuSelecionado(usuarioBean.getUsuario().getTrofeus().get(1));
		assertEquals(Estilo.INFORMATICA, usuarioBean.getTrofeuSelecionado().getEstilo());
		assertEquals(EstadoTrofeu.CONCORRENDO, usuarioBean.getTrofeuSelecionado().getEstado());
		assertEquals(new Long(6), usuarioBean.getTrofeuSelecionado().getLivros().get(0).getId());
		assertEquals(new Long(7), usuarioBean.getTrofeuSelecionado().getLivros().get(1).getId());
	}

	@Test
	public void WhenlUCASLeSeteLivrosInformatica02Culinaria06LiteraturaThenConquistou47Pontos01Trofeu(){
		usuarioBean.setUsuario(usuarios.porId(13L));
		assertEquals(new Integer(47), usuarioBean.getUsuario().getPontos());
		assertEquals(14, usuarioBean.getUsuario().getLeituras().size());
		
		assertEquals(new Long(1), usuarioBean.getUsuario().getLeituras().get(0).getLivro().getId());
		assertEquals(new Integer(2), usuarioBean.getUsuario().getLeituras().get(0).getPontos());
		assertEquals(new Long(2), usuarioBean.getUsuario().getLeituras().get(1).getLivro().getId());
		assertEquals(new Integer(4), usuarioBean.getUsuario().getLeituras().get(1).getPontos());
		assertEquals(new Long(3), usuarioBean.getUsuario().getLeituras().get(2).getLivro().getId());
		assertEquals(new Integer(3), usuarioBean.getUsuario().getLeituras().get(2).getPontos());
		assertEquals(new Long(4), usuarioBean.getUsuario().getLeituras().get(3).getLivro().getId());
		assertEquals(new Integer(3), usuarioBean.getUsuario().getLeituras().get(3).getPontos());
		assertEquals(new Long(5), usuarioBean.getUsuario().getLeituras().get(4).getLivro().getId());
		assertEquals(new Integer(4), usuarioBean.getUsuario().getLeituras().get(4).getPontos());
		assertEquals(new Long(6), usuarioBean.getUsuario().getLeituras().get(5).getLivro().getId());
		assertEquals(new Integer(3), usuarioBean.getUsuario().getLeituras().get(5).getPontos());		
		assertEquals(new Long(7), usuarioBean.getUsuario().getLeituras().get(6).getLivro().getId());
		assertEquals(new Integer(3), usuarioBean.getUsuario().getLeituras().get(6).getPontos());		
		assertEquals(new Long(10), usuarioBean.getUsuario().getLeituras().get(7).getLivro().getId());
		assertEquals(new Integer(3), usuarioBean.getUsuario().getLeituras().get(7).getPontos());		
		assertEquals(new Long(12), usuarioBean.getUsuario().getLeituras().get(8).getLivro().getId());
		assertEquals(new Integer(2), usuarioBean.getUsuario().getLeituras().get(8).getPontos());
		assertEquals(new Long(14), usuarioBean.getUsuario().getLeituras().get(9).getLivro().getId());
		assertEquals(new Integer(4), usuarioBean.getUsuario().getLeituras().get(9).getPontos());
		assertEquals(new Long(15), usuarioBean.getUsuario().getLeituras().get(10).getLivro().getId());
		assertEquals(new Integer(3), usuarioBean.getUsuario().getLeituras().get(10).getPontos());
		assertEquals(new Long(16), usuarioBean.getUsuario().getLeituras().get(11).getLivro().getId());
		assertEquals(new Integer(4), usuarioBean.getUsuario().getLeituras().get(11).getPontos());
		assertEquals(new Long(17), usuarioBean.getUsuario().getLeituras().get(12).getLivro().getId());
		assertEquals(new Integer(5), usuarioBean.getUsuario().getLeituras().get(12).getPontos());
		assertEquals(new Long(18), usuarioBean.getUsuario().getLeituras().get(13).getLivro().getId());
		assertEquals(new Integer(4), usuarioBean.getUsuario().getLeituras().get(13).getPontos());
		
		assertEquals(new Integer(2), usuarioBean.getUsuario().getQuantidadeTrofeus());
		assertEquals(4, usuarioBean.getUsuario().getTrofeus().size());
		usuarioBean.setTrofeuSelecionado(usuarioBean.getUsuario().getTrofeus().get(0));
		assertEquals(Estilo.INFORMATICA, usuarioBean.getTrofeuSelecionado().getEstilo());
		assertEquals(EstadoTrofeu.CONQUISTADO, usuarioBean.getTrofeuSelecionado().getEstado());
		assertEquals(new Integer(5), usuarioBean.getTrofeuSelecionado().getQuantidadeLivros());
		assertEquals(new Long(1), usuarioBean.getTrofeuSelecionado().getLivros().get(0).getId());
		assertEquals(new Long(2), usuarioBean.getTrofeuSelecionado().getLivros().get(1).getId());
		assertEquals(new Long(3), usuarioBean.getTrofeuSelecionado().getLivros().get(2).getId());
		assertEquals(new Long(4), usuarioBean.getTrofeuSelecionado().getLivros().get(3).getId());
		assertEquals(new Long(5), usuarioBean.getTrofeuSelecionado().getLivros().get(4).getId());
		
		usuarioBean.setTrofeuSelecionado(usuarioBean.getUsuario().getTrofeus().get(1));
		assertEquals(Estilo.INFORMATICA, usuarioBean.getTrofeuSelecionado().getEstilo());
		assertEquals(EstadoTrofeu.CONCORRENDO, usuarioBean.getTrofeuSelecionado().getEstado());
		assertEquals(new Long(6), usuarioBean.getTrofeuSelecionado().getLivros().get(0).getId());
		assertEquals(new Long(7), usuarioBean.getTrofeuSelecionado().getLivros().get(1).getId());
		
		usuarioBean.setTrofeuSelecionado(usuarioBean.getUsuario().getTrofeus().get(2));
		assertEquals(Estilo.CULINARIA, usuarioBean.getTrofeuSelecionado().getEstilo());
		assertEquals(EstadoTrofeu.CONCORRENDO, usuarioBean.getTrofeuSelecionado().getEstado());
		assertEquals(new Long(10), usuarioBean.getTrofeuSelecionado().getLivros().get(0).getId());
		assertEquals(new Long(12), usuarioBean.getTrofeuSelecionado().getLivros().get(1).getId());
		
		usuarioBean.setTrofeuSelecionado(usuarioBean.getUsuario().getTrofeus().get(3));
		assertEquals(Estilo.LITERATURA_CLASSICA, usuarioBean.getTrofeuSelecionado().getEstilo());
		assertEquals(EstadoTrofeu.CONQUISTADO, usuarioBean.getTrofeuSelecionado().getEstado());
		assertEquals(new Integer(5), usuarioBean.getTrofeuSelecionado().getQuantidadeLivros());
		assertEquals(new Long(14), usuarioBean.getTrofeuSelecionado().getLivros().get(0).getId());
		assertEquals(new Long(15), usuarioBean.getTrofeuSelecionado().getLivros().get(1).getId());
		assertEquals(new Long(16), usuarioBean.getTrofeuSelecionado().getLivros().get(2).getId());
		assertEquals(new Long(17), usuarioBean.getTrofeuSelecionado().getLivros().get(3).getId());
		assertEquals(new Long(18), usuarioBean.getTrofeuSelecionado().getLivros().get(4).getId());
	}
}
