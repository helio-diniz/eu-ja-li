package org.cousera.eujali.test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.coursera.eujali.controller.LivrosBean;
import org.coursera.eujali.model.Estilo;
import org.coursera.eujali.model.Livro;
import org.coursera.eujali.repository.Livros;
import org.coursera.eujali.repository.LivrosDAO;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LivrosTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private LivrosBean livrosBean;
	private List<Livro> livrosDisponiveis;
	private JdbcDatabaseTester jdt;
	private IDataSet dados;
	private ITable tabelaLivros;

	public LivrosTest() {
		super();
		emf = Persistence.createEntityManagerFactory("EuJaLiPU");
	}

	@Before
	public void inicializarTeste() throws SQLException, Exception {
		em = emf.createEntityManager();
		jdt = new JdbcDatabaseTester("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/portal-livros", "root", "");
		livrosBean = new LivrosBean();
		Livros livros = new LivrosDAO();
		((LivrosDAO) livros).setEm(em);
		livrosBean.setLivros(livros);
		dados = jdt.getConnection().createDataSet();

	}

	@After
	public void finalizarTeste() {
		em.close();
	}

	private void verificarLivros(int quantidadeRegistroEsperado) throws DataSetException {
		assertEquals(tabelaLivros.getRowCount(), quantidadeRegistroEsperado);
		assertEquals(tabelaLivros.getRowCount(), livrosDisponiveis.size());
		int linha = 0;
		for (Livro livro : livrosDisponiveis) {
			assertEquals(livro.getId(), new Long(((BigInteger) tabelaLivros.getValue(linha, "id")).longValue()));
			assertEquals(livro.getTitulo(), String.valueOf(tabelaLivros.getValue(linha, "titulo")));
			assertEquals(livro.getEditora(), String.valueOf(tabelaLivros.getValue(linha, "editora")));
			assertEquals(livro.getEstilo(), Estilo.valueOf((String.valueOf(tabelaLivros.getValue(linha, "estilo")))));
			assertEquals(livro.getImagemCapa(), String.valueOf(tabelaLivros.getValue(linha, "imagem_capa")));
			assertEquals(livro.getPaginas(), (Integer) tabelaLivros.getValue(linha, "paginas"));
			assertEquals(livro.getAnoPublicacao(), (Integer) tabelaLivros.getValue(linha, "ano_Publicacao"));
			linha++;
		}
	}

	@Test
	public void WhenPesquisaTodosLivrosThenObtem20Registros() throws DataSetException {
		livrosBean.pesquisar();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		assertNotNull(livrosDisponiveis);
		tabelaLivros = dados.getTable("LIVRO");
		verificarLivros(20);
	}

	@Test
	public void WhenPesquisaPorTituloDevOpsNaPraticaEntregaDeSoftwareConfiavelEAutomatizadaThenObtem01Livro()
			throws Exception {
		livrosBean.getLivroFilter().setTitulo("DevOps na prática: entrega de software confiável e automatizada");
		livrosBean.pesquisar();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		assertNotNull(livrosDisponiveis);
		QueryDataSet consulta = new QueryDataSet(jdt.getConnection());
		consulta.addTable("livroPorTitulo",
				"select * from livro where titulo = '" + livrosBean.getLivroFilter().getTitulo() + "'");
		tabelaLivros = consulta.getTable("livroPorTitulo");
		verificarLivros(1);
	}

	@Test
	public void WhenPesquisaPorTituloParcialDevOpsThenObtem02Livros() throws Exception {
		livrosBean.getLivroFilter().setTitulo("DevOps");
		livrosBean.pesquisar();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		assertNotNull(livrosDisponiveis);
		QueryDataSet consulta = new QueryDataSet(jdt.getConnection());
		consulta.addTable("livroPorTitulo",
				"select * from livro where titulo like '%" + livrosBean.getLivroFilter().getTitulo() + "%'");
		tabelaLivros = consulta.getTable("livroPorTitulo");
		verificarLivros(2);
	}

	@Test
	public void WhenPesquisaPorTituloInvalidoThenObtemNenhumLivro() throws Exception {
		livrosBean.getLivroFilter().setTitulo("TITULO_INVALIDO");
		livrosBean.pesquisar();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		assertNotNull(livrosDisponiveis);
		QueryDataSet consulta = new QueryDataSet(jdt.getConnection());
		consulta.addTable("livroPorTitulo",
				"select * from livro where titulo = '" + livrosBean.getLivroFilter().getTitulo() + "'");
		tabelaLivros = consulta.getTable("livroPorTitulo");
		verificarLivros(0);
	}

	@Test
	public void WhenPesquisaPorEditoraMelhoramentosThenObtem02Livros() throws Exception {
		livrosBean.getLivroFilter().setEditora("Melhoramentos");
		livrosBean.pesquisar();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		assertNotNull(livrosDisponiveis);
		QueryDataSet consulta = new QueryDataSet(jdt.getConnection());
		consulta.addTable("livroPorEditora",
				"select * from livro where editora like " + "'" + livrosBean.getLivroFilter().getEditora() + "'");
		tabelaLivros = consulta.getTable("livroPorEditora");
		verificarLivros(3);
	}

	@Test
	public void WhenPesquisaPorEditoraParcialMelhoramentosThenObtem02Livros() throws Exception {
		livrosBean.getLivroFilter().setEditora("Melhor");
		livrosBean.pesquisar();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		assertNotNull(livrosDisponiveis);
		QueryDataSet consulta = new QueryDataSet(jdt.getConnection());
		consulta.addTable("livroPorEditora",
				"select * from livro where editora like " + "'%" + livrosBean.getLivroFilter().getEditora() + "%'");
		tabelaLivros = consulta.getTable("livroPorEditora");
		verificarLivros(3);
	}

	@Test
	public void WhenPesquisaPorEditoraInvalidaThenObtemNenhumLivro() throws Exception {
		livrosBean.getLivroFilter().setEditora("EDITORA_INVALIDA");
		livrosBean.pesquisar();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		assertNotNull(livrosDisponiveis);
		QueryDataSet consulta = new QueryDataSet(jdt.getConnection());
		consulta.addTable("livroPorEditora",
				"select * from livro where editora like " + "'%" + livrosBean.getLivroFilter().getEditora() + "%'");
		tabelaLivros = consulta.getTable("livroPorEditora");
		verificarLivros(0);
	}

	@Test
	public void WhenPesquisaPorEstiloInformaticaThenObtem07Livros() throws Exception {
		Estilo[] estilos = {Estilo.INFORMATICA};
		livrosBean.getLivroFilter().setEstilos(estilos);
		livrosBean.pesquisar();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		assertNotNull(livrosDisponiveis);
		QueryDataSet consulta = new QueryDataSet(jdt.getConnection());
		String sql = "select * from livro where estilo in ('" + livrosBean.getLivroFilter().getEstilos()[0] + "')";
		consulta.addTable("livroPorEditora", sql);
		tabelaLivros = consulta.getTable("livroPorEditora");
		verificarLivros(7);
	}
	
	@Test
	public void WhenPesquisaPorEstiloInformaticaCulinariaThenObtem13Livros() throws Exception {
		Estilo[] estilos = {Estilo.INFORMATICA, Estilo.CULINARIA};
		livrosBean.getLivroFilter().setEstilos(estilos);
		livrosBean.pesquisar();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		assertNotNull(livrosDisponiveis);
		QueryDataSet consulta = new QueryDataSet(jdt.getConnection());
		String sql = "select * from livro where estilo in ('" + livrosBean.getLivroFilter().getEstilos()[0] + "', '"+ 
				livrosBean.getLivroFilter().getEstilos()[1] + "')";
		consulta.addTable("livroPorEditora", sql);
		tabelaLivros = consulta.getTable("livroPorEditora");
		verificarLivros(13);
	}

	public void WhenPesquisaPorEstiloNuloThenObtem07Livros() throws Exception {
		livrosBean.pesquisar();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		assertNotNull(livrosDisponiveis);
		QueryDataSet consulta = new QueryDataSet(jdt.getConnection());
		String sql = "select * from livro";
		consulta.addTable("livroPorEditora", sql);
		tabelaLivros = consulta.getTable("livroPorEditora");
		verificarLivros(20);
	}

	@Test
	public void WhenPesquisaPorAnoDePublicacao2014MelhoramentosThenObtem05Livros() throws Exception {
		livrosBean.getLivroFilter().setAnoPublicacao(2014);
		livrosBean.pesquisar();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		assertNotNull(livrosDisponiveis);
		QueryDataSet consulta = new QueryDataSet(jdt.getConnection());
		consulta.addTable("livroPorAnoPublicacao", "select * from livro where ano_publicacao = '"
				+ livrosBean.getLivroFilter().getAnoPublicacao().toString() + "'");
		tabelaLivros = consulta.getTable("livroPorAnoPublicacao");
		verificarLivros(5);
	}

	@Test
	public void WhenPesquisaPorAnoDePublicacao14MelhoramentosThenObtem05Livros() throws Exception {
		livrosBean.getLivroFilter().setAnoPublicacao(14);
		assertEquals(2014, (int) livrosBean.getLivroFilter().getAnoPublicacao());
		livrosBean.pesquisar();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		assertNotNull(livrosDisponiveis);
		QueryDataSet consulta = new QueryDataSet(jdt.getConnection());
		consulta.addTable("livroPorAnoPublicacao", "select * from livro where ano_publicacao = '"
				+ livrosBean.getLivroFilter().getAnoPublicacao().toString() + "'");
		tabelaLivros = consulta.getTable("livroPorAnoPublicacao");
		verificarLivros(5);
	}

	@Test
	public void WhenPesquisaPorAnoDePublicacao00ThenObtemNenhumLivro() throws Exception {
		livrosBean.getLivroFilter().setAnoPublicacao(0);
		assertEquals(0, (int) livrosBean.getLivroFilter().getAnoPublicacao());
		livrosBean.pesquisar();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		assertNotNull(livrosDisponiveis);
		QueryDataSet consulta = new QueryDataSet(jdt.getConnection());
		consulta.addTable("livroPorAnoPublicacao", "select * from livro");
		tabelaLivros = consulta.getTable("livroPorAnoPublicacao");
		verificarLivros(20);
	}

	@Test
	public void WhenPesquisaPorAnoDePublicacao01NegativoMelhoramentosThenObtemNenhumLivro() throws Exception {
		livrosBean.getLivroFilter().setAnoPublicacao(-1);
		assertEquals(0, (int) livrosBean.getLivroFilter().getAnoPublicacao());
		livrosBean.pesquisar();
		livrosDisponiveis = livrosBean.getLivrosFiltrados();
		assertNotNull(livrosDisponiveis);
		QueryDataSet consulta = new QueryDataSet(jdt.getConnection());
		consulta.addTable("livroPorAnoPublicacao", "select * from livro");
		tabelaLivros = consulta.getTable("livroPorAnoPublicacao");
		verificarLivros(20);
	}

}
