package it.polimi.traveldream.web.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class LoginImpiegatoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idImpiegato;

	private String codiceImpiegato;

	public String getidImpiegato() {
		return this.idImpiegato;
	}

	public void setIdImpiegato(String idImpiegato) {
		this.idImpiegato = idImpiegato;
	}

	public String getCodiceImpiegato() {
		return this.codiceImpiegato;
	}

	public void setCodiceImpiegato(String codiceImpiegato) {
		this.codiceImpiegato = codiceImpiegato;
	}

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.login(this.idImpiegato, this.codiceImpiegato);
			
		} catch (ServletException e) {

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login fallito", ""));
			
			return "loginImpiegato";    // pagina di login dell'impiegato
		}
		
		return "homepageImpiegato"; // homepage personalizzata dell'impiegato
	
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			
			request.logout();
			
		} catch (ServletException e) {

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Logout fallito", ""));
			
			return "homepageImpiegato"; // home page personalizzata dell'impiegato
			
		}
		
		return "homepage"; //homepage del sito web
	}
}
