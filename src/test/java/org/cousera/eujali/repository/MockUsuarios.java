package org.cousera.eujali.repository;

import java.util.ArrayList;
import java.util.List;

import org.coursera.eujali.model.Usuario;
import org.coursera.eujali.repository.Usuarios;

public class MockUsuarios implements Usuarios {

	private List<Usuario> usuarios = new ArrayList<>();

	public MockUsuarios() {
		super();
		usuarios.add(new Usuario(1L, "José Silva", "jose.silva", "123"));
		usuarios.add(new Usuario(2L, "Carlos Silva", "carlos.silva", "123"));
		usuarios.add(new Usuario(3L, "Maria Martins", "maria.martins", "123"));
		usuarios.add(new Usuario(4L, "Lúcia Amaro", "lucia.amaro", "123"));
		usuarios.add(new Usuario(5L, "Marcos Martins", "marcos.martins", "123"));
		usuarios.add(new Usuario(6L, "Edvaldo Pereira", "edivaldo.pereira", "123"));
		usuarios.add(new Usuario(7L, "Nilo Silvério", "nilo.silverio", "123"));
		usuarios.add(new Usuario(8L, "Luciana Campos", "luciana.campos", "123"));
		usuarios.add(new Usuario(9L, "Maurício Silveira", "mauricio.silveira", "123"));
		usuarios.add(new Usuario(10L, "Marcelo Meireles", "marcelo.meireles", "123"));
		usuarios.add(new Usuario(11L, "Carla Ferrari", "carla.ferrari", "123"));
		usuarios.add(new Usuario(12L, "Mariana Zambonaro", "mariana.zambonaro", "123"));
		usuarios.add(new Usuario(13L, "Lucas Souza", "lucas.souza", "123"));
		usuarios.add(new Usuario(14L, "Ana Paula Félix", "ana.felix", "123"));
		usuarios.add(new Usuario(15L, "Lúcio Duarte", "lucio.duarte", "123"));
	}

	@Override
	public Usuario porId(Long id) {
		for (Usuario usuario : usuarios) {
			if (id.equals(usuario.getId())) {
				return usuario;
			}
		}
		return null;
	}

	@Override
	public List<Usuario> todos() {
		return usuarios;
	}

	@Override
	public void salvar(Usuario usuario) {
		
	}

	@Override
	public Usuario porCodigo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

}
