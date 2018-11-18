package org.cousera.eujali.test;

import static org.junit.Assert.*;

import org.coursera.eujali.model.Livro;
import org.coursera.eujali.model.Usuario;
import org.coursera.eujali.service.LivroService;
import org.coursera.eujali.service.NegocioException;
import org.coursera.eujali.service.UsuarioNuloException;
import org.coursera.eujali.service.LivroNuloException;
import org.cousera.eujali.repository.MockLivros;
//import org.cousera.eujali.repository.MockLeituras;
import org.cousera.eujali.repository.MockTrofeus;
import org.cousera.eujali.repository.MockUsuarios;
import org.junit.Before;
import org.junit.Test;

public class LeituraTest extends SistemaTest {

	private Livro livro;
	private Usuario usuario;

	@Before
	public void inicializaTeste() {
		usuarios = new MockUsuarios();
		livros = new MockLivros();
		trofeus = new MockTrofeus();
		livroService = new LivroService();
		livroService.setUsuarios(usuarios);
		livroService.setTrofeus(trofeus);

	}

	public void carregar(Long usuario_id, Long livro_id) {
		usuario = usuarios.porId(usuario_id);
		livro = livros.porId(livro_id);
	}

	@Test
	public void whenLivroLido26PaginasThenRegistra02PontosJoséSilva() {
		carregar(1L, 1L);
		assertNotNull(livro);
		assertNotNull(usuario);
		livroService.marcarLido(usuario, livro);
		assertEquals("José Silva", usuario.getNome());
		assertEquals("O que é DevOps?: Colaboração como caminho para entregar valor ao negócio", livro.getTitulo());
		assertEquals(new Integer(26), livro.getPaginas());
		assertEquals(new Integer(2), usuario.getPontos());
	}

	@Test
	public void whenLivroLido81PaginasThenRegistra02PontosJoséSilva() {
		carregar(1L, 12L);
		assertNotNull(livro);
		assertNotNull(usuario);
		livroService.marcarLido(usuario, livro);
		assertEquals("José Silva", usuario.getNome());
		assertEquals("Carnes Deliciosas (Minicozinha)", livro.getTitulo());
		assertEquals(new Integer(81), livro.getPaginas());
		assertEquals(new Integer(2), usuario.getPontos());
	}

	@Test
	public void whenLivroLido142PaginasThenRegistra03PontosJoséSilva() {
		carregar(1L, 7L);
		assertNotNull(livro);
		assertNotNull(usuario);
		livroService.marcarLido(usuario, livro);
		assertEquals("José Silva", usuario.getNome());
		assertEquals("Introdução ao jQuery", livro.getTitulo());
		assertEquals(new Integer(142), livro.getPaginas());
		assertEquals(new Integer(3), usuario.getPontos());
	}

	@Test
	public void whenLivroLido200PaginasThenRegistra03PontosJoséSilva() {
		carregar(1L, 15L);
		assertNotNull(livro);
		assertNotNull(usuario);
		livroService.marcarLido(usuario, livro);
		assertEquals("José Silva", usuario.getNome());
		assertEquals("Dom Casmurro", livro.getTitulo());
		assertEquals(new Integer(200), livro.getPaginas());
		assertEquals(new Integer(3), usuario.getPontos());
	}

	@Test
	public void whenLivroLido201PaginasThenRegistra04PontosJoséSilva() {
		carregar(1L, 18L);
		assertNotNull(livro);
		assertNotNull(usuario);
		livroService.marcarLido(usuario, livro);
		assertEquals("José Silva", usuario.getNome());
		assertEquals("Memórias de um Sargento de Milícias", livro.getTitulo());
		assertEquals(new Integer(201), livro.getPaginas());
		assertEquals(new Integer(4), usuario.getPontos());
	}

	@Test
	public void whenLivroLido599PaginasThenRegistra07PontosJoséSilva() {
		carregar(1L, 20L);
		assertNotNull(livro);
		assertNotNull(usuario);
		livroService.marcarLido(usuario, livro);
		assertEquals("José Silva", usuario.getNome());
		assertEquals("Os Maias", livro.getTitulo());
		assertEquals(new Integer(599), livro.getPaginas());
		assertEquals(new Integer(7), usuario.getPontos());
	}

	@Test
	public void whenLivrosLidos26E81PaginasThenRegistra04PontosJoséSilva() {
		carregar(1L, 1L);
		assertNotNull(livro);
		assertNotNull(usuario);
		livroService.marcarLido(usuario, livro);
		Livro livro2 = livros.porId(12L);
		livroService.marcarLido(usuario, livro2);
		assertEquals("José Silva", usuario.getNome());
		assertEquals("O que é DevOps?: Colaboração como caminho para entregar valor ao negócio", livro.getTitulo());
		assertEquals("Carnes Deliciosas (Minicozinha)", livro2.getTitulo());
		assertEquals(new Integer(4), usuario.getPontos());
	}

	@Test
	public void whenLivrosLidos26E81E201PaginasThenRegistra08PontosJoséSilva() {
		carregar(1L, 1L);
		assertNotNull(livro);
		assertNotNull(usuario);
		livroService.marcarLido(usuario, livro);
		Livro livro2 = livros.porId(12L);
		livroService.marcarLido(usuario, livro2);
		Livro livro3 = livros.porId(18L);
		livroService.marcarLido(usuario, livro3);
		assertEquals("José Silva", usuario.getNome());
		assertEquals("O que é DevOps?: Colaboração como caminho para entregar valor ao negócio", livro.getTitulo());
		assertEquals("Carnes Deliciosas (Minicozinha)", livro2.getTitulo());
		assertEquals("Memórias de um Sargento de Milícias", livro3.getTitulo());
		assertEquals(new Integer(8), usuario.getPontos());
	}

	@Test
	public void whenLivrosLidos26E81E201E599PaginasThenRegistra15PontosJoséSilva() {
		carregar(1L, 1L);
		assertNotNull(livro);
		assertNotNull(usuario);
		livroService.marcarLido(usuario, livro);
		Livro livro2 = livros.porId(12L);
		livroService.marcarLido(usuario, livro2);
		Livro livro3 = livros.porId(18L);
		livroService.marcarLido(usuario, livro3);
		Livro livro4 = livros.porId(20L);
		livroService.marcarLido(usuario, livro4);
		assertEquals("José Silva", usuario.getNome());
		assertEquals("O que é DevOps?: Colaboração como caminho para entregar valor ao negócio", livro.getTitulo());
		assertEquals("Carnes Deliciosas (Minicozinha)", livro2.getTitulo());
		assertEquals("Memórias de um Sargento de Milícias", livro3.getTitulo());
		assertEquals("Os Maias", livro4.getTitulo());
		assertEquals(new Integer(15), usuario.getPontos());
	}

	@Test
	public void whenLivrosLidos26E201PaginasE81E599PaginasThenRegistra06PontosJoséSilva() {
		carregar(1L, 1L);
		Livro livro2 = livros.porId(12L);
		Livro livro3 = livros.porId(18L);
		Livro livro4 = livros.porId(20L);
		Usuario usuario2 = usuarios.porId(2L);
		assertNotNull(usuario);
		assertNotNull(usuario2);
		assertNotNull(livro);
		assertNotNull(livro2);
		assertNotNull(livro3);
		assertNotNull(livro4);
		livroService.marcarLido(usuario, livro);
		livroService.marcarLido(usuario, livro3);
		livroService.marcarLido(usuario2, livro2);
		livroService.marcarLido(usuario2, livro4);
		assertEquals("José Silva", usuario.getNome());
		assertEquals("Carlos Silva", usuario2.getNome());
		assertEquals("O que é DevOps?: Colaboração como caminho para entregar valor ao negócio", livro.getTitulo());
		assertEquals("Carnes Deliciosas (Minicozinha)", livro2.getTitulo());
		assertEquals("Memórias de um Sargento de Milícias", livro3.getTitulo());
		assertEquals("Os Maias", livro4.getTitulo());
		assertEquals(new Integer(6), usuario.getPontos());
		assertEquals(new Integer(9), usuario2.getPontos());
	}

	@Test(expected = NegocioException.class)
	public void whenLivroLido26Paginas02VezesThenNegocioExceptionLivroJaLido() {
		carregar(1L, 1L);
		livroService.marcarLido(usuario, livro);
		livroService.marcarLido(usuario, livro);
	}

	@Test(expected = UsuarioNuloException.class)
	public void whenUsuarioNuloThenNegocioExcetionUsuarioNulo() {
		carregar(1L, 1L);
		usuario = null;
		livroService.marcarLido(usuario, livro);
	}

	@Test(expected = LivroNuloException.class)
	public void whenLivroNuloThenNegocioExcetionLivroNulo() {
		carregar(1L, 1L);
		livro = null;
		livroService.marcarLido(usuario, livro);

	}

}
