package org.coursera.eujali.model;

public enum Estilo {
	
	INFORMATICA("INFORMATICA"), 
	CULINARIA("CULINARIA"), 
	LITERATURA_CLASSICA("LITERATURA CLASSICA");

	private String descricao;
	
	private Estilo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
