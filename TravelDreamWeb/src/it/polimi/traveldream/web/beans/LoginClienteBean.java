package it.polimi.traveldream.web.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name= "loginClienteBean")
@SessionScoped
public class LoginClienteBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email;

	private String password;
	
	private boolean logged;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogged(){
		if(logged==true){
			return true;
		}
		return false;
	}
	
	
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			
			request.login(this.email, this.password);
			
			if(request.getRemoteUser()!=null){
				logged=true;
			}
			
		} catch (ServletException e) {

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login fallito", ""));
			
			return "loginCliente";  // pagina login cliente
		}
		
		if(logged==false){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login fallito", ""));
			return "loginCliente";
		}
		
		return "homepageCliente"; // homepage personalizzata del cliente
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			
			request.logout();
			
		} catch (ServletException e) {

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Logout fallito", ""));
			
			return "homepageCliente"; // home page personalizzata del cliente
			
		}
		return "homepage"; //homepage del sito web
	}

	

}
