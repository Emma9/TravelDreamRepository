package it.polimi.traveldream.web.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class LoginImpiegatoBean implements LoginImpiegato,Serializable{

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



	  public String login () {
	    FacesContext context = FacesContext.getCurrentInstance();
	    HttpServletRequest request = (HttpServletRequest) 
	        context.getExternalContext().getRequest();
	    try {
	      request.login(this.idImpiegato, this.codiceImpiegato);
	    } catch (ServletException e) {

	      context.addMessage(null, new FacesMessage("Login fallito."));
	      return "errore";
	    }
	    return "index"; //homepage personalizzata dell'impiegato
	  }

	  public void logout() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    HttpServletRequest request = (HttpServletRequest) 
	        context.getExternalContext().getRequest();
	    try {
	      request.logout();
	    } catch (ServletException e) {

	      context.addMessage(null, new FacesMessage("Logout fallito."));
	    }
	  }
}
