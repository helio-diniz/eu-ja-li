package org.coursera.eujali.service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import org.coursera.eujali.model.Livro;
import org.coursera.eujali.model.EstadoTrofeu;
import org.coursera.eujali.model.Leitura;
import org.coursera.eujali.model.Trofeu;
import org.coursera.eujali.model.Usuario;
import org.coursera.eujali.repository.Trofeus;
import org.coursera.eujali.repository.Usuarios;
import org.coursera.eujali.util.jpa.Transactional;

@Named
@Default
public class LivroService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private Usuarios usuarios;
	@Inject
	private Trofeus trofeus;

	public LivroService() {
		super();
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Trofeus getTrofeus() {
		return trofeus;
	}

	public void setTrofeus(Trofeus trofeus) {
		this.trofeus = trofeus;
	}

	@Transactional
	public void marcarLido(Usuario usuario, Livro livro) {	
		if (usuario == null){
			throw new UsuarioNuloException("Usuário Nulo!");
		}
		
		if (livro == null){
			throw new LivroNuloException("Livro Nulo!");
		}
		Usuario usuarioNovo = usuarios.porId(usuario.getId());
		Leitura leitura = pontuar(usuarioNovo, livro);
		Trofeu trofeu = premiar(usuarioNovo, livro);
		lerLivro(usuarioNovo, livro, trofeu, leitura);
		
		usuarios.salvar(usuarioNovo);
		trofeus.salvar(trofeu);
		
		usuario = usuarioNovo;
	}

	private void lerLivro(Usuario usuario, Livro livro, Trofeu trofeu, Leitura leitura){
		usuario.adicionaTrofeu(trofeu);
		usuario.lerLivro(livro, leitura);
	}
	
	private Trofeu premiar(Usuario usuario, Livro livro) {
		Trofeu trofeu = trofeus.porEstilo(usuario,livro.getEstilo());
		if (trofeu == null) {
			trofeu = new Trofeu();
			trofeu.setEstado(EstadoTrofeu.CONCORRENDO);
			trofeu.setEstilo(livro.getEstilo());
			trofeu.setUsuario(usuario);
		}
		trofeu.adicionarLivro(livro);
		return trofeu;
	}

	private Leitura pontuar(Usuario usuario, Livro livro) {
		Leitura leitura = new Leitura();
		leitura.setLivro(livro);
		leitura.setUsuario(usuario);
		leitura.calcularPontos();
		return leitura;
	}

	public List<Usuario> ranking(int totalUsuarios) {
		List<Usuario> ranking = usuarios.todos();
		Collections.sort(ranking);

		for (int i = ranking.size() - 1; i >= totalUsuarios; i--) {
			ranking.remove(i);
		}
		return ranking;
	}

}
