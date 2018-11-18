package org.cousera.eujali.repository;

import java.util.ArrayList;
import java.util.List;

import org.coursera.eujali.filter.LivroFilter;
import org.coursera.eujali.model.Estilo;
import org.coursera.eujali.model.Livro;
import org.coursera.eujali.repository.Livros;

public class MockLivros implements Livros {
	
	private List<Livro> livros = new ArrayList<>();

	public MockLivros() {
		super();
		livros.add(new Livro(1L, "O que é DevOps?: Colaboração como caminho para entregar valor ao negócio", 
				"DevOps2.jpg", Estilo.INFORMATICA, "KINDLE", new Integer(26), new Integer(2016), null));
		livros.add(new Livro(2L, "DevOps na prática: entrega de software confiável e automatizada", 
				"DevOps.jpg", Estilo.INFORMATICA, "Casa do Código", new Integer(257), new Integer(2015), null));
		livros.add(new Livro(3L, "Descomplicando o Docker",
				"Docker.jpg", Estilo.INFORMATICA, "BRASPORT", new Integer(120), new Integer(2016), null));
		livros.add(new Livro(4L, "Scrum e Métodos Ágeis: Um Guia Prático", 
				"Scrum.jpg", Estilo.INFORMATICA, "BRASPORT", new Integer(110), new Integer(2016), null));
		livros.add(new Livro(5L, "Scrum: Gestão ágil para projetos de sucesso", 
				"ScrumProjetos.jpg",Estilo.INFORMATICA, "Casa do Código", new Integer(297), new Integer(2015), null));
		livros.add(new Livro(6L, "Programação de Software em Java", 
				"Java.jpg", Estilo.INFORMATICA, "Universo dos Livros", new Integer(160), new Integer(2014), null));
		livros.add(new Livro(7L, "Introdução ao jQuery", 
				"jQuery.jpg", Estilo. INFORMATICA, "Itacaiunas", new Integer(142), new Integer(2015), null));
		livros.add(new Livro(8L,"Culinária Francesa Bistrô Clássico", 
				"Bistro.jpg", Estilo.CULINARIA, "Casa dos Livros", new Integer(160), new Integer(2013), null));
		livros.add(new Livro(9L, "Risotos", 
				"Risotos.jpg", Estilo.CULINARIA, "Senac SP", new Integer(114), new Integer(2013), null));
		livros.add(new Livro(10L, "Massas Gourmet", 
				"Massas.jpg", Estilo.CULINARIA, "Senac SP", new Integer(112), new Integer(2015), null));		
		livros.add(new Livro(11L, "Tortas Deliciosas (Minicozinha)", "Tortas.jpg", 
				Estilo.CULINARIA, "Melhoramentos" , new Integer(86), new Integer(2014), null));
		livros.add(new Livro(12L, "Carnes Deliciosas (Minicozinha)", 
				"Carne.jpg", Estilo. CULINARIA, "Melhoramentos", new Integer(81), new Integer(2014), null));
		livros.add(new Livro(13L, "Diário de Olivier-As Receitas da Bocaina", "Olivier.jpg", 
				Estilo.CULINARIA, "Globo Estilo" , new Integer(208), new Integer(2016), null));
		livros.add(new Livro(14L, "Memórias Póstumas de Brás Cubas", "BrasCubas.jpg", 
				Estilo.LITERATURA_CLASSICA, "Rafael Vidal" , new Integer(218), new Integer(2016), null));
		livros.add(new Livro(15L, "Dom Casmurro", 
				"DomCasmurro.jpg", Estilo. LITERATURA_CLASSICA, "Wohnrecht", new Integer(200), new Integer(2014), null));
		livros.add(new Livro(16L, "O cortiço", "OCortico.jpg", 
				Estilo.LITERATURA_CLASSICA, "Melhoramentos" , new Integer(264), new Integer(2015), null));
		livros.add(new Livro(17L, "Quincas Borba", "QuincasBorba.jpg", 
				Estilo.LITERATURA_CLASSICA, "Wohnrecht" , new Integer(306), new Integer(2014), null));
		livros.add(new Livro(18L, "Memórias de um Sargento de Milícias", 
				"SargentoMilicia.jpg", Estilo. LITERATURA_CLASSICA, "Centaur", new Integer(201), new Integer(2012), null));
		livros.add(new Livro(19L, "Triste Fim de Policarpo Quaresma", "PolicarpoQuaresma.jpg", 
				Estilo.LITERATURA_CLASSICA, "Centaur " , new Integer(176), new Integer(2011), null));
		livros.add(new Livro(20L, "Os Maias", 
				"OsMaias.jpg", Estilo. LITERATURA_CLASSICA, "Centaur", new Integer(599), new Integer(2012), null));
	}

	@Override
	public Livro porId(Long id) {
		for (Livro livro: livros){
			if (id.equals(livro.getId())){
				return livro;
			}
		}
		return null;
	}

	@Override
	public List<Livro> todos() {
		return livros;
	}

	@Override
	public void salvar(Livro livro) {
		
	}

	@Override
	public List<Livro> filtrados(LivroFilter livroFilter) {
		// TODO Auto-generated method stub
		return null;
	}

}
