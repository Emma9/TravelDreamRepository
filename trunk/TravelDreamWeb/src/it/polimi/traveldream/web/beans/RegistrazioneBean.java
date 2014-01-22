package it.polimi.traveldream.web.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import it.polimi.traveldream.ejb.client.ClienteBeanRemote;

public class RegistrazioneBean implements Registrazione, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Session bean i cui metodi sono utilizzati nel codice
	@EJB
	private ClienteBeanRemote clienteremoto;
	
	
	public String registrazione(String nome, String cognome,String codiceFiscale, String email, String password) {
		
		    FacesContext context = FacesContext.getCurrentInstance();
		    HttpServletRequest request = (HttpServletRequest) 
		        context.getExternalContext().getRequest();
		    	
		    try{
		    
		    if (clienteremoto.verificaPresenzaClienteRegistrazione(email,codiceFiscale)) {

					clienteremoto.createCliente(email, password, codiceFiscale, nome,cognome);
					
					request.login(email, password);
					
					context.addMessage(null, new FacesMessage("Registrazione avvenuta con successo"));
					
					return "homepageCliente"; //homepage personalizzata del cliente
		    	
		    	} else {

		      context.addMessage(null, new FacesMessage("Cliente già registrato"));
		      
		      return "registrazione";//pagina di registrazione
		    
		    }
		    }catch (ServletException e) {
		    	
		    	context.addMessage(null, new FacesMessage("ERRORE: Registrazione fallita"));
			      
			      return "registrazione";//pagina di registrazione
		    	
		    }
		    
		  }
}
