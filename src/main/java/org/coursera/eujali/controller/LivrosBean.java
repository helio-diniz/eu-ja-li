package org.coursera.eujali.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.coursera.eujali.filter.LivroFilter;
import org.coursera.eujali.model.Estilo;
import org.coursera.eujali.model.Livro;
import org.coursera.eujali.repository.Livros;

@Named
@ViewScoped
public class LivrosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private Livros livros;
	private LivroFilter livroFilter;
	private List<Livro> livrosFiltrados;

	public LivrosBean() {
		super();
		livroFilter = new LivroFilter();
		livrosFiltrados = new ArrayList<>();
	}

	public Livros getLivros() {
		return livros;
	}

	public void setLivros(Livros livros) {
		this.livros = livros;
	}

	@PostConstruct
	public void pesquisar() {
		livrosFiltrados =  livros.filtrados(livroFilter);
	}

	public LivroFilter getLivroFilter() {
		return livroFilter;
	}

	public List<Livro> getLivrosFiltrados() {
		return livrosFiltrados;
	}
	
	public Estilo[] getEstilos(){
		return Estilo.values();
	}
	

}
