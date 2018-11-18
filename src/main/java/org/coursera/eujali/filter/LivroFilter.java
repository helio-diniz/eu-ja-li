package org.coursera.eujali.filter;

import java.io.Serializable;

import org.coursera.eujali.model.Estilo;

public class LivroFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	private String titulo;
	private String editora;
	private Integer anoPublicacao = 0;
	private Estilo[] estilos;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setEditora(String editora) {
		this.editora = editora;

	}

	public String getEditora() {
		return this.editora;
	}

	public void setAnoPublicacao(Integer anoPublicacao) {
		if (anoPublicacao == null){
			this.anoPublicacao = 0;
			return;
		}
		if (anoPublicacao <= 0) {
			this.anoPublicacao = 0;
			return;
		}
		if (anoPublicacao.toString().length() <= 2) {
			this.anoPublicacao = anoPublicacao + 2000;
			return;
		}
		this.anoPublicacao = anoPublicacao;

	}

	public Integer getAnoPublicacao() {
		return this.anoPublicacao;
	}

	public void setEstilos(Estilo[] estilos) {
		this.estilos = estilos;
	}

	public Estilo[] getEstilos() {
		return this.estilos;
	}
}
