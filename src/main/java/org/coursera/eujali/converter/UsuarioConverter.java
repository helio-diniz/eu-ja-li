package org.coursera.eujali.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.coursera.eujali.model.Usuario;
import org.coursera.eujali.repository.UsuariosDAO;
import org.coursera.eujali.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	private UsuariosDAO usuarios;

	public UsuarioConverter() {
		super();
		usuarios = CDIServiceLocator.getBean(UsuariosDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario usuario = null;
		if (value != null) {
			return usuarios.porId(new Long(value));
		}
		return usuario;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			return ((Usuario) value).getCodigo().toString();
		}
		return "";
	}

}
