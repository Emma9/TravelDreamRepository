package it.polimi.traveldream.web.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class LoginClienteBean implements LoginCliente,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email;  

    private String password; 
    
      
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



	  public String login () {
	    FacesContext context = FacesContext.getCurrentInstance();
	    HttpServletRequest request = (HttpServletRequest) 
	        context.getExternalContext().getRequest();
	    try {
	      request.login(this.email, this.password);
	    } catch (ServletException e) {

	      context.addMessage(null, new FacesMessage("Login fallito."));
	      return "errore";
	    }
	    return "index"; //homepage personalizzata del cliente
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
