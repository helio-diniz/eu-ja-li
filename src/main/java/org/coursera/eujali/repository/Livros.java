package org.coursera.eujali.repository;

import java.util.List;

import org.coursera.eujali.filter.LivroFilter;
import org.coursera.eujali.model.Livro;

public interface Livros {

	public Livro porId(Long id);
	public List<Livro> todos();
	public void salvar(Livro livro);
	public List<Livro> filtrados(LivroFilter livroFilter);
}
