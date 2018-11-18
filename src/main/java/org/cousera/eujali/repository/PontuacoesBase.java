package org.cousera.eujali.repository;

import org.coursera.eujali.model.Livro;
import org.coursera.eujali.model.Leitura;
import org.coursera.eujali.model.Usuario;

public class PontuacoesBase {
	
	protected Leitura registraPontuacao(Usuario usuario, Livro livro) {
		Leitura pontuacao = new Leitura();
		pontuacao.setLivro(livro);
		pontuacao.setUsuario(usuario);
		pontuacao.calcularPontos();
		return pontuacao;
	}
	
}
