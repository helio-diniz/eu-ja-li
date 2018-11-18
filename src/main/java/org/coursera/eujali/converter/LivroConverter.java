package org.coursera.eujali.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.coursera.eujali.model.Livro;
import org.coursera.eujali.repository.LivrosDAO;
import org.coursera.eujali.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Livro.class)
public class LivroConverter implements Converter {

	private LivrosDAO livros;

	public LivroConverter() {
		livros = CDIServiceLocator.getBean(LivrosDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			System.out.println("Valor do Id: "+ value);
			return livros.porId(new Long(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Livro) value).getId().toString();
		}
		return "";
	}

}
