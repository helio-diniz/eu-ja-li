package org.cousera.eujali.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.coursera.eujali.model.Livro;
import org.coursera.eujali.model.Usuario;
import org.coursera.eujali.repository.Livros;
import org.coursera.eujali.repository.Leituras;
import org.coursera.eujali.repository.Trofeus;
import org.coursera.eujali.repository.Usuarios;
import org.coursera.eujali.service.LivroService;

public class SistemaTest {
	protected static int QUANTIDADE_LIVROS = 20;
	protected static int QUANTIDADE_LEITORES = 20;
	protected static int QUANTIDADE_LEITORES_RANKING = 10;
	protected Usuarios usuarios;
	protected Livros livros;
	protected Leituras leituras;
	protected Trofeus trofeus;
	protected LivroService livroService;
	protected Usuario leitores[];
	protected Livro livrosLidos[];
	protected List<Usuario> ranking;

	protected void carregarLeitores(Long usuarios_id[]) {
		for (int i = 0; i < QUANTIDADE_LEITORES; i++) {
			leitores[i] = null;
			if (i < usuarios_id.length) {
				leitores[i] = usuarios.porId(usuarios_id[i]);
			}
		}
	}

	protected void carregarLivros(Long livros_id[]) {
		for (int i = 0; i < QUANTIDADE_LIVROS; i++) {
			livrosLidos[i] = null;
			if (i < livros_id.length) {
				livrosLidos[i] = livros.porId(livros_id[i]);
			}
		}
	}

	protected void verificarNaoNulo(Long usuarios_id[], Long livros_id[]) {
		verificarNaoNulosLeitores(usuarios_id);
		verificarNaoNulosLivros(livros_id);
	}

	protected void verificarNaoNulosLivros(Long[] livros_id) {
		for (int i = 0; i < livros_id.length; i++) {
			assertNotNull(livrosLidos[i]);
		}
	}

	protected void verificarNaoNulosLeitores(Long[] usuarios_id) {
		for (int i = 0; i < usuarios_id.length; i++) {
			assertNotNull(leitores[i]);
		}
	}

	protected void verificarCarregamento(Long usuarios_id[], Long livros_id[]) {
		verificarCarregamentoLeitores(usuarios_id);
		verificarCarregamentoLivros(livros_id);
	}

	protected void verificarCarregamentoLivros(Long[] livros_id) {
		for (int i = 0; i < livros_id.length; i++) {
			assertEquals(new Long(livros_id[i]), livrosLidos[i].getId());
		}
	}

	protected void verificarCarregamentoLeitores(Long[] usuarios_id) {
		for (int i = 0; i < usuarios_id.length; i++) {
			assertEquals(new Long(usuarios_id[i]), leitores[i].getId());
		}
	}

	public void marcarLido(Usuario leitor) {
		for (int i = 0; i < livrosLidos.length; i++) {
			if (livrosLidos[i] != null) {
				livroService.marcarLido(leitor, livrosLidos[i]);
			}

		}
	}

	protected void verificarRanking(String[] nomes, int[] pontos) {
		for (int i = 0; i < QUANTIDADE_LEITORES_RANKING; i++) {
			if (i < ranking.size()) {
				assertEquals(nomes[i], ranking.get(i).getNome());
				assertEquals(new Integer(pontos[i]), ranking.get(i).getPontos());
			}
		}
	}
}
