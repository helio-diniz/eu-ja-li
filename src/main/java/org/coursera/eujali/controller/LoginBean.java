package org.coursera.eujali.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.coursera.eujali.util.jsf.FacesUtil;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codigo;
	@Inject
	private FacesContext facesContext;
	@Inject
	private HttpServletRequest request;
	@Inject
	private HttpServletResponse response;
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void preRender(ComponentSystemEvent event){
		if("true".equals(request.getParameter("invalid"))){
			FacesUtil.addErroMessage("Usuário ou senha inválido!");
		}
	}
	
	public void login() throws ServletException, IOException{
		///j_spring_security_check : url de login do Spring Security
		RequestDispatcher dispatch =  request.getRequestDispatcher("/j_spring_security_check");
		dispatch.forward(request, response);
		
		facesContext.responseComplete(); //interrompe ciclo de vida do JSF
	}

}
