package org.coursera.eujali.model;

public enum EstadoTrofeu {

	CONCORRENDO("CONCORRENDO"), 
	CONQUISTADO("CONQUISTADO");

	private String descricao;

	private EstadoTrofeu(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
