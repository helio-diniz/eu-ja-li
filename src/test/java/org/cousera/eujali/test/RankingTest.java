package org.cousera.eujali.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.coursera.eujali.model.Livro;
import org.coursera.eujali.model.Usuario;
import org.coursera.eujali.service.LivroService;
import org.cousera.eujali.repository.MockLivros;
import org.cousera.eujali.repository.MockTrofeus;
import org.cousera.eujali.repository.MockUsuarios;
import org.junit.Before;
import org.junit.Test;

public class RankingTest extends SistemaTest {

	@Before
	public void inicializaTeste() {
		// TODO Auto-generated method stub
		usuarios = new MockUsuarios();
		livros = new MockLivros();
		trofeus = new MockTrofeus();
		livroService = new LivroService();

		livroService.setUsuarios(usuarios);
		livroService.setTrofeus(trofeus);
		ranking = new ArrayList<>();
		leitores = new Usuario[QUANTIDADE_LEITORES];
		livrosLidos = new Livro[QUANTIDADE_LIVROS];
	}

	@Test
	public void whenNinguemPontuouThenRanking07UsariosPorOrdemAlfabetica() {
		carregarLeitores(new Long[] { 1L, 2L, 4L, 6L, 11L, 13L, 14L });
		carregarLivros(new Long[] {});
		verificarNaoNulo(new Long[] { 1L, 2L, 4L, 6L, 11L, 13L, 14L }, new Long[] {});
		verificarCarregamento(new Long[] { 1L, 2L, 4L, 6L, 11L, 13L, 14L }, new Long[] {});
		ranking = livroService.ranking(7);
		assertNotNull(ranking);
		assertEquals(7, ranking.size());
		verificarRanking(new String[] { "Ana Paula Félix", "Carla Ferrari", "Carlos Silva", "Edvaldo Pereira",
				"José Silva", "Lucas Souza", "Luciana Campos", "", "", "" },
				new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });

	}

	@Test
	public void whenNinguemPontuouThenRanking10UsariosPorOrdemAlfabetica() {
		carregarLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });
		carregarLivros(new Long[] {});
		verificarNaoNulo(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L },
				new Long[] {});
		verificarCarregamento(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L },
				new Long[] {});
		ranking = livroService.ranking(QUANTIDADE_LEITORES_RANKING);
		assertNotNull(ranking);
		assertEquals(10, ranking.size());
		verificarRanking(
				new String[] { "Ana Paula Félix", "Carla Ferrari", "Carlos Silva", "Edvaldo Pereira", "José Silva",
						"Lucas Souza", "Luciana Campos", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles" },
				new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });

	}

	@Test
	public void whenLuciana04PontosThenRankingComLucianae09UsuariosPorOrdemAlfabetica() {
		carregarLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });
		carregarLivros(new Long[] { 5L });
		verificarNaoNulo(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L },
				new Long[] { 5L });
		verificarCarregamento(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L },
				new Long[] { 5L });
		marcarLido(leitores[7]); // Luciana
		ranking = livroService.ranking(QUANTIDADE_LEITORES_RANKING);
		assertNotNull(ranking);
		assertEquals(10, ranking.size());
		verificarRanking(
				new String[] { "Luciana Campos", "Ana Paula Félix", "Carla Ferrari", "Carlos Silva", "Edvaldo Pereira",
						"José Silva", "Lucas Souza", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles" },
				new int[] { 4, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
	}

	@Test
	public void whenLuciana04PontosCarla03PontosThenRankingLucianaCarla08UsuariosPorOrdemAlfabetica() {
		carregarLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });
		verificarNaoNulosLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });
		verificarCarregamentoLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });

		carregarLivros(new Long[] { 5L });
		verificarNaoNulosLivros(new Long[] { 5L });
		verificarCarregamentoLivros(new Long[] { 5L });
		marcarLido(leitores[7]); // Luciana

		carregarLivros(new Long[] { 3L });
		verificarNaoNulosLivros(new Long[] { 3L });
		verificarCarregamentoLivros(new Long[] { 3L });
		marcarLido(leitores[10]); // Carla

		ranking = livroService.ranking(QUANTIDADE_LEITORES_RANKING);
		assertNotNull(ranking);
		assertEquals(10, ranking.size());
		verificarRanking(
				new String[] { "Luciana Campos", "Carla Ferrari", "Ana Paula Félix", "Carlos Silva", "Edvaldo Pereira",
						"José Silva", "Lucas Souza", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles" },
				new int[] { 4, 3, 0, 0, 0, 0, 0, 0, 0, 0 });
	}

	@Test
	public void whenCarla07PontosLuciana04PontosThenRankingCarlaLuciana08UsuariosPorOrdemAlfabetica() {
		carregarLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });
		verificarNaoNulosLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });
		verificarCarregamentoLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });

		carregarLivros(new Long[] { 5L });
		verificarNaoNulosLivros(new Long[] { 5L });
		verificarCarregamentoLivros(new Long[] { 5L });
		marcarLido(leitores[7]); // Luciana

		carregarLivros(new Long[] { 3L, 5L });
		verificarNaoNulosLivros(new Long[] { 3L, 5L });
		verificarCarregamentoLivros(new Long[] { 3L, 5L });
		marcarLido(leitores[10]); // Carla

		ranking = livroService.ranking(QUANTIDADE_LEITORES_RANKING);
		assertNotNull(ranking);
		assertEquals(10, ranking.size());
		verificarRanking(
				new String[] { "Carla Ferrari", "Luciana Campos", "Ana Paula Félix", "Carlos Silva", "Edvaldo Pereira",
						"José Silva", "Lucas Souza", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles" },
				new int[] { 7, 4, 0, 0, 0, 0, 0, 0, 0, 0 });
	}

	@Test
	public void whenCarlos07PontosCarla07PontosLuciana04PontosThenRankingCarlosCarlaLuciana08UsuariosPorOrdemAlfabetica() {
		carregarLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });
		verificarNaoNulosLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });
		verificarCarregamentoLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });

		carregarLivros(new Long[] { 5L });
		verificarNaoNulosLivros(new Long[] { 5L });
		verificarCarregamentoLivros(new Long[] { 5L });
		marcarLido(leitores[7]); // Luciana

		carregarLivros(new Long[] { 3L, 5L });
		verificarNaoNulosLivros(new Long[] { 3L, 5L });
		verificarCarregamentoLivros(new Long[] { 3L, 5L });
		marcarLido(leitores[10]); // Carla

		carregarLivros(new Long[] { 20L });
		verificarNaoNulosLivros(new Long[] { 20L });
		verificarCarregamentoLivros(new Long[] { 20L });
		marcarLido(leitores[1]); // Carlos

		ranking = livroService.ranking(10);
		assertNotNull(ranking);
		assertEquals(10, ranking.size());
		verificarRanking(
				new String[] { "Carla Ferrari", "Carlos Silva", "Luciana Campos", "Ana Paula Félix", "Edvaldo Pereira",
						"José Silva", "Lucas Souza", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles" },
				new int[] { 7, 7, 4, 0, 0, 0, 0, 0, 0, 0 });
	}

	@Test
	public void whenJose14PontosCarlos07PontosCarla07PontosLuciana04PontosThenRankingJoseCarlosCarlaLuciana08UsuariosPorOrdemAlfabetica() {
		carregarLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });
		verificarNaoNulosLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });
		verificarCarregamentoLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });

		carregarLivros(new Long[] { 5L });
		verificarNaoNulosLivros(new Long[] { 5L });
		verificarCarregamentoLivros(new Long[] { 5L });
		marcarLido(leitores[7]); // Luciana

		carregarLivros(new Long[] { 3L, 5L });
		verificarNaoNulosLivros(new Long[] { 3L, 5L });
		verificarCarregamentoLivros(new Long[] { 3L, 5L });
		marcarLido(leitores[10]); // Carla

		carregarLivros(new Long[] { 20L });
		verificarNaoNulosLivros(new Long[] { 20L });
		verificarCarregamentoLivros(new Long[] { 20L });
		marcarLido(leitores[1]); // Carlos

		carregarLivros(new Long[] { 3L, 5L, 20L });
		verificarNaoNulosLivros(new Long[] { 3L, 5L, 20L });
		verificarCarregamentoLivros(new Long[] { 3L, 5L, 20L });
		marcarLido(leitores[0]);// José

		ranking = livroService.ranking(10);
		assertNotNull(ranking);
		assertEquals(10, ranking.size());
		verificarRanking(
				new String[] { "José Silva", "Carla Ferrari", "Carlos Silva", "Luciana Campos", "Ana Paula Félix",
						"Edvaldo Pereira", "Lucas Souza", "Lúcia Amaro", "Lúcio Duarte", "Marcelo Meireles" },
				new int[] { 14, 7, 7, 4, 0, 0, 0, 0, 0, 0 });
	}

	@Test
	public void whenJose14PontosLuciana08Carla07PontosCarlos07PontosLuciana04PontosMarcelo02PontosThenRankingJoseCarlaCarlosLucianaMarcelo07UsuariosPorOrdemAlfabetica() {
		carregarLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });
		verificarNaoNulosLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });
		verificarCarregamentoLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });

		carregarLivros(new Long[] { 5L });
		verificarNaoNulosLivros(new Long[] { 5L });
		verificarCarregamentoLivros(new Long[] { 5L });
		marcarLido(leitores[7]); // Luciana

		carregarLivros(new Long[] { 3L, 5L });
		verificarNaoNulosLivros(new Long[] { 3L, 5L });
		verificarCarregamentoLivros(new Long[] { 3L, 5L });
		marcarLido(leitores[10]); // Carla

		carregarLivros(new Long[] { 20L });
		verificarNaoNulosLivros(new Long[] { 20L });
		verificarCarregamentoLivros(new Long[] { 20L });
		marcarLido(leitores[1]); // Carlos

		carregarLivros(new Long[] { 3L, 5L, 20L });
		verificarNaoNulosLivros(new Long[] { 3L, 5L, 20L });
		verificarCarregamentoLivros(new Long[] { 3L, 5L, 20L });
		marcarLido(leitores[0]); // José

		carregarLivros(new Long[] { 1L, 6L, 11L });
		verificarNaoNulosLivros(new Long[] { 1L, 6L, 11L });
		verificarCarregamentoLivros(new Long[] { 1L, 6L, 11L });
		marcarLido(leitores[7]); // Luciana

		carregarLivros(new Long[] { 10L });
		verificarNaoNulosLivros(new Long[] { 10L });
		verificarCarregamentoLivros(new Long[] { 10L });
		marcarLido(leitores[9]); // Marcelo

		ranking = livroService.ranking(10);
		assertNotNull(ranking);
		assertEquals(10, ranking.size());
		verificarRanking(
				new String[] { "José Silva", "Luciana Campos", "Carla Ferrari", "Carlos Silva", "Marcelo Meireles",
						"Ana Paula Félix", "Edvaldo Pereira", "Lucas Souza", "Lúcia Amaro", "Lúcio Duarte" },
				new int[] { 14, 11, 7, 7, 3, 0, 0, 0, 0, 0 });
	}

	@Test
	public void whenNovaUsuariaMariana02PontosThenRankingJoseCarlaCarlosLucianaMarceloMariana06UsuariosPorOrdemAlfabetica() {
		carregarLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });
		verificarNaoNulosLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });
		verificarCarregamentoLeitores(new Long[] { 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L });

		carregarLivros(new Long[] { 5L });
		verificarNaoNulosLivros(new Long[] { 5L });
		verificarCarregamentoLivros(new Long[] { 5L });
		marcarLido(leitores[7]); // Luciana

		carregarLivros(new Long[] { 3L, 5L });
		verificarNaoNulosLivros(new Long[] { 3L, 5L });
		verificarCarregamentoLivros(new Long[] { 3L, 5L });
		marcarLido(leitores[10]); // Carla

		carregarLivros(new Long[] { 20L });
		verificarNaoNulosLivros(new Long[] { 20L });
		verificarCarregamentoLivros(new Long[] { 20L });
		marcarLido(leitores[1]); // Carlos

		carregarLivros(new Long[] { 3L, 5L, 20L });
		verificarNaoNulosLivros(new Long[] { 3L, 5L, 20L });
		verificarCarregamentoLivros(new Long[] { 3L, 5L, 20L });
		marcarLido(leitores[0]); // José

		carregarLivros(new Long[] { 1L, 6L, 11L });
		verificarNaoNulosLivros(new Long[] { 1L, 6L, 11L });
		verificarCarregamentoLivros(new Long[] { 1L, 6L, 11L });
		marcarLido(leitores[7]); // Luciana

		carregarLivros(new Long[] { 10L });
		verificarNaoNulosLivros(new Long[] { 10L });
		verificarCarregamentoLivros(new Long[] { 10L });
		marcarLido(leitores[9]); // Marcelo

		carregarLivros(new Long[] { 12L });
		verificarNaoNulosLivros(new Long[] { 12L });
		verificarCarregamentoLivros(new Long[] { 12L });
		marcarLido(leitores[11]); // Mariana

		ranking = livroService.ranking(10);
		assertNotNull(ranking);
		assertEquals(10, ranking.size());
		verificarRanking(
				new String[] { "José Silva", "Luciana Campos", "Carla Ferrari", "Carlos Silva", "Marcelo Meireles",
						"Mariana Zambonaro", "Ana Paula Félix", "Edvaldo Pereira", "Lucas Souza", "Lúcia Amaro" },
				new int[] { 14, 11, 7, 7, 3, 2, 0, 0, 0, 0 });
	}

}
