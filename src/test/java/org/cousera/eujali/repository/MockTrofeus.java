package org.cousera.eujali.repository;

import java.util.ArrayList;
import java.util.List;

import org.coursera.eujali.model.EstadoTrofeu;
import org.coursera.eujali.model.Estilo;
import org.coursera.eujali.model.Trofeu;
import org.coursera.eujali.model.Usuario;
import org.coursera.eujali.repository.Trofeus;

public class MockTrofeus implements Trofeus {
	private List<Trofeu> trofeus;
	private Long id = 0L;

	public MockTrofeus() {
		super();
		trofeus = new ArrayList<>();
	}

	@Override
	public Trofeu porEstilo(Usuario usuario, Estilo estilo) {
		// TODO Auto-generated method stub
		Trofeu trofeuSelecionado = null;
		for (Trofeu trofeu : trofeus) {
			if (usuario.equals(trofeu.getUsuario()) && estilo.equals(trofeu.getEstilo())
					&& trofeu.getEstado().equals(EstadoTrofeu.CONCORRENDO)) {
				trofeuSelecionado = trofeu;
				break;
			}
		}

		return trofeuSelecionado;
	}

	@Override
	public void salvar(Trofeu trofeu) {
		if (!trofeuSalvo(trofeu)){
			this.id += 1;
			trofeu.setId(id);
			trofeus.add(trofeu);
		}
	}

	public boolean trofeuSalvo(Trofeu trofeu) {
		for (int i=0; i<trofeus.size(); i++){
			if(trofeus.get(i).getId().equals(trofeu.getId())){
				trofeus.set(i, trofeu);
				return true;
			}
		}
		return false;
	}

	@Override
	public Trofeu porId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
