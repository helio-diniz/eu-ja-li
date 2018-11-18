package org.cousera.eujali.test;

import static org.junit.Assert.assertEquals;

import org.coursera.eujali.model.EstadoTrofeu;
import org.coursera.eujali.model.Estilo;
import org.coursera.eujali.model.Livro;
import org.coursera.eujali.model.Trofeu;
import org.coursera.eujali.model.Usuario;
import org.coursera.eujali.service.LivroService;
import org.cousera.eujali.repository.MockLivros;
import org.cousera.eujali.repository.MockTrofeus;
import org.cousera.eujali.repository.MockUsuarios;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TrofeuTest extends SistemaTest {

	@Before
	public void inicializaTeste() {
		usuarios = new MockUsuarios();
		livros = new MockLivros();
		trofeus = new MockTrofeus();
		livroService = new LivroService();
		livroService.setUsuarios(usuarios);
		livroService.setTrofeus(trofeus);
		leitores = new Usuario[QUANTIDADE_LEITORES];
		livrosLidos = new Livro[QUANTIDADE_LIVROS];
	}

	@After
	public void finalizaTeste() {
	}

	@Test
	public void when1LivroLidoInformaticaThenRegistra1LeituraTrofeu1Informatica() {
		carregarLeitores(new Long[] { 1L });
		carregarLivros(new Long[] { 1L });
		verificarNaoNulo(new Long[] { 1L }, new Long[] { 1L });
		verificarCarregamento(new Long[] { 1L }, new Long[] { 1L });

		// marcarLivrosLidos(0, 0, usuario);
		Usuario usuario = leitores[0];
		marcarLido(usuario);
		assertEquals(new Integer(0), usuario.getQuantidadeTrofeus());
		assertEquals(1, usuario.getTrofeus().size());
		Trofeu trofeu1 = (Trofeu) usuario.getTrofeus().get(0);
		assertEquals(EstadoTrofeu.CONCORRENDO, trofeu1.getEstado());
		assertEquals(new Integer(1), trofeu1.getQuantidadeLivros());
		assertEquals(new Long(1), trofeu1.getLivros().get(0).getId());
	}

	@Test
	public void when2LivrosLidosInformaticaThenRegistra2LeiturasTrofeu1Informatica() {
		carregarLeitores(new Long[] { 1L });
		carregarLivros(new Long[] { 1L, 2L });
		verificarNaoNulo(new Long[] { 1L }, new Long[] { 1L, 2L });
		verificarCarregamento(new Long[] { 1L }, new Long[] { 1L, 2L });

		// marcarLivrosLidos(0, 1, usuario);
		Usuario usuario = leitores[0];
		marcarLido(usuario);
		assertEquals(new Integer(0), usuario.getQuantidadeTrofeus());
		assertEquals(1, usuario.getTrofeus().size());
		Trofeu trofeu1 = (Trofeu) usuario.getTrofeus().get(0);
		assertEquals(EstadoTrofeu.CONCORRENDO, trofeu1.getEstado());
		assertEquals(new Integer(2), trofeu1.getQuantidadeLivros());
		assertEquals(new Long(1), trofeu1.getLivros().get(0).getId());
		assertEquals(new Long(2), trofeu1.getLivros().get(1).getId());
	}

	@Test
	public void when3LivrosLidosInformaticaThenRegistra3LeiturasTrofeu1Informatica() {
		carregarLeitores(new Long[] { 1L });
		carregarLivros(new Long[] { 1L, 2L, 3L });
		verificarNaoNulo(new Long[] { 1L }, new Long[] { 1L, 2L, 3L });
		verificarCarregamento(new Long[] { 1L }, new Long[] { 1L, 2L, 3L });

		// marcarLivrosLidos(0, 2, usuario);
		Usuario usuario = leitores[0];
		marcarLido(usuario);
		assertEquals(new Integer(0), usuario.getQuantidadeTrofeus());
		assertEquals(1, usuario.getTrofeus().size());
		Trofeu trofeu1 = (Trofeu) usuario.getTrofeus().get(0);
		assertEquals(EstadoTrofeu.CONCORRENDO, trofeu1.getEstado());
		assertEquals(new Integer(3), trofeu1.getQuantidadeLivros());
		assertEquals(new Long(1), trofeu1.getLivros().get(0).getId());
		assertEquals(new Long(2), trofeu1.getLivros().get(1).getId());
		assertEquals(new Long(3), trofeu1.getLivros().get(2).getId());
	}

	@Test
	public void when4LivrosLidosInformaticaThenRegistra4LeiturasTrofeu1Informatica() {
		carregarLeitores(new Long[] { 1L });
		carregarLivros(new Long[] { 1L, 2L, 3L, 4L });
		verificarNaoNulo(new Long[] { 1L }, new Long[] { 1L, 2L, 3L, 4L });
		verificarCarregamento(new Long[] { 1L }, new Long[] { 1L, 2L, 3L, 4L });

		// marcarLivrosLidos(0, 3, usuario);
		Usuario usuario = leitores[0];
		marcarLido(usuario);
		assertEquals(new Integer(0), usuario.getQuantidadeTrofeus());
		assertEquals(1, usuario.getTrofeus().size());
		Trofeu trofeu1 = (Trofeu) usuario.getTrofeus().get(0);
		assertEquals(EstadoTrofeu.CONCORRENDO, trofeu1.getEstado());
		assertEquals(new Integer(4), trofeu1.getQuantidadeLivros());
		assertEquals(new Long(1), trofeu1.getLivros().get(0).getId());
		assertEquals(new Long(2), trofeu1.getLivros().get(1).getId());
		assertEquals(new Long(3), trofeu1.getLivros().get(2).getId());
		assertEquals(new Long(4), trofeu1.getLivros().get(3).getId());
	}

	@Test
	public void when5LivrosLidosInformáticaThenRegistraGanhaTrofeu1Informatica() {
		carregarLeitores(new Long[] { 1L });
		carregarLivros(new Long[] { 1L, 2L, 3L, 4L, 5L });
		verificarNaoNulo(new Long[] { 1L }, new Long[] { 1L, 2L, 3L, 4L, 5L });
		verificarCarregamento(new Long[] { 1L }, new Long[] { 1L, 2L, 3L, 4L, 5L });

		// marcarLivrosLidos(0, 4, usuario);
		Usuario usuario = leitores[0];
		marcarLido(usuario);
		assertEquals(new Integer(1), usuario.getQuantidadeTrofeus());
		assertEquals(1, usuario.getTrofeus().size());
		Trofeu trofeu1 = (Trofeu) usuario.getTrofeus().get(0);
		assertEquals(EstadoTrofeu.CONQUISTADO, trofeu1.getEstado());
		assertEquals(new Integer(5), trofeu1.getQuantidadeLivros());
		assertEquals(new Long(1), trofeu1.getLivros().get(0).getId());
		assertEquals(new Long(2), trofeu1.getLivros().get(1).getId());
		assertEquals(new Long(3), trofeu1.getLivros().get(2).getId());
		assertEquals(new Long(4), trofeu1.getLivros().get(3).getId());
		assertEquals(new Long(5), trofeu1.getLivros().get(4).getId());
	}

	@Test
	public void when6LivrosLidosThenRegistraGanhaTrofeu1E1LeituraTrofeu2Informatica() {
		carregarLeitores(new Long[] { 1L });
		carregarLivros(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L });
		verificarNaoNulo(new Long[] { 1L }, new Long[] { 1L, 2L, 3L, 4L, 5L, 6L });
		verificarCarregamento(new Long[] { 1L }, new Long[] { 1L, 2L, 3L, 4L, 5L, 6L });

		// marcarLivrosLidos(0, 5, usuario);
		Usuario usuario = leitores[0];
		marcarLido(usuario);
		assertEquals(new Integer(1), usuario.getQuantidadeTrofeus());
		assertEquals(2, usuario.getTrofeus().size());
		Trofeu trofeu1 = (Trofeu) usuario.getTrofeus().get(0);
		Trofeu trofeu2 = (Trofeu) usuario.getTrofeus().get(1);
		assertEquals(EstadoTrofeu.CONQUISTADO, trofeu1.getEstado());
		assertEquals(new Integer(5), trofeu1.getQuantidadeLivros());
		assertEquals(5, trofeu1.getLivros().size());
		assertEquals(EstadoTrofeu.CONCORRENDO, trofeu2.getEstado());
		assertEquals(new Integer(1), trofeu2.getQuantidadeLivros());
		assertEquals(new Long(1), trofeu1.getLivros().get(0).getId());
		assertEquals(new Long(2), trofeu1.getLivros().get(1).getId());
		assertEquals(new Long(3), trofeu1.getLivros().get(2).getId());
		assertEquals(new Long(4), trofeu1.getLivros().get(3).getId());
		assertEquals(new Long(5), trofeu1.getLivros().get(4).getId());
		assertEquals(new Long(6), trofeu2.getLivros().get(0).getId());
	}

	@Test
	public void when2LivrosInformaticaE1LivroCulinariaThenRegistra2LeiturasTrofeu1InformaticaE1LeituraTrofeu2Culinaria() {
		carregarLeitores(new Long[] { 1L });
		carregarLivros(new Long[] { 1L, 2L });
		verificarNaoNulo(new Long[] { 1L }, new Long[] { 1L, 2L });
		verificarCarregamento(new Long[] { 1L }, new Long[] { 1L, 2L });

		// marcarLivrosLidos(0, 1, usuario);
		Usuario usuario = leitores[0];
		marcarLido(usuario);
		// verficarLivrosLidosNaoNulo(7, 7);
		// marcarLivrosLidos(7, 7, usuario);
		carregarLivros(new Long[] { 8L });
		verificarNaoNulosLivros(new Long[] { 8L });
		marcarLido(usuario);

		assertEquals(new Integer(0), usuario.getQuantidadeTrofeus());
		assertEquals(2, usuario.getTrofeus().size());
		Trofeu trofeu1 = (Trofeu) usuario.getTrofeus().get(0);
		Trofeu trofeu2 = (Trofeu) usuario.getTrofeus().get(1);
		assertEquals(EstadoTrofeu.CONCORRENDO, trofeu1.getEstado());
		assertEquals(new Integer(2), trofeu1.getQuantidadeLivros());
		assertEquals(2, trofeu1.getLivros().size());
		assertEquals(EstadoTrofeu.CONCORRENDO, trofeu2.getEstado());
		assertEquals(new Integer(1), trofeu2.getQuantidadeLivros());
		assertEquals(new Long(1), trofeu1.getLivros().get(0).getId());
		assertEquals(new Long(2), trofeu1.getLivros().get(1).getId());
		assertEquals(new Long(8), trofeu2.getLivros().get(0).getId());
	}

	@Test
	public void whenLeitura5LivrosInformaticaE3LivrosCulinariaThenRegistra5LeiturasTrofeu1InformaticaE3LeiturasTrofeu2Culinaria() {
		carregarLeitores(new Long[] { 1L });
		carregarLivros(new Long[] { 1L, 2L, 3L, 4L, 5L });
		verificarNaoNulo(new Long[] { 1L }, new Long[] { 1L, 2L, 3L, 4L, 5L });
		verificarCarregamento(new Long[] { 1L }, new Long[] { 1L, 2L, 3L, 4L, 5L });

		// marcarLivrosLidos(0, 4, usuario);
		Usuario usuario = leitores[0];
		marcarLido(usuario);
		// verficarLivrosLidosNaoNulo(7, 9);
		// marcarLivrosLidos(7, 9, usuario);
		carregarLivros(new Long[] { 8L, 9L, 10L });
		verificarNaoNulosLivros(new Long[] { 8L, 9L, 10L });
		marcarLido(usuario);

		assertEquals(new Integer(1), usuario.getQuantidadeTrofeus());
		assertEquals(2, usuario.getTrofeus().size());
		Trofeu trofeu1 = (Trofeu) usuario.getTrofeus().get(0);
		Trofeu trofeu2 = (Trofeu) usuario.getTrofeus().get(1);
		assertEquals(EstadoTrofeu.CONQUISTADO, trofeu1.getEstado());
		assertEquals(new Integer(5), trofeu1.getQuantidadeLivros());
		assertEquals(5, trofeu1.getLivros().size());
		assertEquals(EstadoTrofeu.CONCORRENDO, trofeu2.getEstado());
		assertEquals(new Integer(3), trofeu2.getQuantidadeLivros());
		assertEquals(new Long(1), trofeu1.getLivros().get(0).getId());
		assertEquals(new Long(2), trofeu1.getLivros().get(1).getId());
		assertEquals(new Long(3), trofeu1.getLivros().get(2).getId());
		assertEquals(new Long(4), trofeu1.getLivros().get(3).getId());
		assertEquals(new Long(5), trofeu1.getLivros().get(4).getId());
		assertEquals(new Long(8), trofeu2.getLivros().get(0).getId());
		assertEquals(new Long(9), trofeu2.getLivros().get(1).getId());
		assertEquals(new Long(10), trofeu2.getLivros().get(2).getId());
	}

	@Test
	public void whenLeitura7LivrosInformaticaE3LivrosCulinariaThenGanhaTrofeu1InformaticaERegistra1LeiturasTrofeu1InformaticE3LeiturasTrofeu2Culinaria() {
		carregarLeitores(new Long[] { 1L });
		carregarLivros(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L });
		verificarNaoNulo(new Long[] { 1L }, new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L });
		verificarCarregamento(new Long[] { 1L }, new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L });

		// marcarLivrosLidos(0, 6, usuario);
		Usuario usuario = leitores[0];
		marcarLido(usuario);
		// verficarLivrosLidosNaoNulo(7, 9);
		// marcarLivrosLidos(7, 9, usuario);
		carregarLivros(new Long[] { 8L, 9L, 10L });
		verificarNaoNulosLivros(new Long[] { 8L, 9L, 10L });
		marcarLido(usuario);

		assertEquals(new Integer(1), usuario.getQuantidadeTrofeus());
		assertEquals(3, usuario.getTrofeus().size());
		Trofeu trofeu1 = (Trofeu) usuario.getTrofeus().get(0);
		Trofeu trofeu2 = (Trofeu) usuario.getTrofeus().get(1);
		Trofeu trofeu3 = (Trofeu) usuario.getTrofeus().get(2);
		assertEquals(EstadoTrofeu.CONQUISTADO, trofeu1.getEstado());
		assertEquals(Estilo.INFORMATICA, trofeu1.getEstilo());
		assertEquals(new Integer(5), trofeu1.getQuantidadeLivros());
		assertEquals(5, trofeu1.getLivros().size());

		assertEquals(EstadoTrofeu.CONCORRENDO, trofeu2.getEstado());
		assertEquals(Estilo.INFORMATICA, trofeu2.getEstilo());
		assertEquals(new Integer(2), trofeu2.getQuantidadeLivros());
		assertEquals(2, trofeu2.getLivros().size());

		assertEquals(EstadoTrofeu.CONCORRENDO, trofeu3.getEstado());
		assertEquals(Estilo.CULINARIA, trofeu3.getEstilo());
		assertEquals(new Integer(3), trofeu3.getQuantidadeLivros());

		assertEquals(new Long(1), trofeu1.getLivros().get(0).getId());
		assertEquals(new Long(2), trofeu1.getLivros().get(1).getId());
		assertEquals(new Long(3), trofeu1.getLivros().get(2).getId());
		assertEquals(new Long(4), trofeu1.getLivros().get(3).getId());
		assertEquals(new Long(5), trofeu1.getLivros().get(4).getId());
		assertEquals(new Long(6), trofeu2.getLivros().get(0).getId());
		assertEquals(new Long(7), trofeu2.getLivros().get(1).getId());
		assertEquals(new Long(8), trofeu3.getLivros().get(0).getId());
		assertEquals(new Long(9), trofeu3.getLivros().get(1).getId());
		assertEquals(new Long(10), trofeu3.getLivros().get(2).getId());
	}

}
