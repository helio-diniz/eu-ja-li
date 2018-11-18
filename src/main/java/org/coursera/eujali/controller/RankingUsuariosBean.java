package org.coursera.eujali.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.coursera.eujali.model.Usuario;
import org.coursera.eujali.service.LivroService;

@Named
@ViewScoped
public class RankingUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static int QUANTIDADE_USUARIOS_RANKING = 10;
	@Inject
	private LivroService livroService;
	private List<Usuario> ranking;

	public RankingUsuariosBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void inicializar() {
		System.out.println("Inicializando");
		this.ranking = livroService.ranking(QUANTIDADE_USUARIOS_RANKING);
	}

	public LivroService getLivroService() {
		return livroService;
	}

	public void setLivroService(LivroService livroService) {
		this.livroService = livroService;
	}

	public List<Usuario> getRanking() {
		return ranking;
	}


}
