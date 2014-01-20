package it.polimi.traveldream.web.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import it.polimi.traveldream.ejb.client.ClienteBeanRemote;

public class RegistrazioneBeanP implements RegistrazioneP, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Session bean i cui metodi sono utilizzati nel codice
	@EJB
	private ClienteBeanRemote clienteremoto;

	// Attributi utili per il passaggio di informazioni con il session bean
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String email;
	private String password;

	// Metodi

	/*
	public String registrazione(String nome, String cognome,
			String codiceFiscale, String email, String password) {

		if (clienteremoto.verificaPresenzaClienteRegistrazione(email,codiceFiscale)) {

			clienteremoto.createCliente(email, password, codiceFiscale, nome,cognome);

			return "Registrazione avvenuta con successo";

		} else {

			return "Cliente già registrato";

		}

	}
	
	*/
	
	//NB: CLIENTE REGISTRATO E' LOGGATO, QUINDI DEVO INCLUDERE ANCHE IL LOGIN
	
	public String registrazione(String nome, String cognome,String codiceFiscale, String email, String password) {
		
		    FacesContext context = FacesContext.getCurrentInstance();
		    HttpServletRequest request = (HttpServletRequest) 
		        context.getExternalContext().getRequest();
		    	
		    try{
		    
		    if (clienteremoto.verificaPresenzaClienteRegistrazione(email,codiceFiscale)) {

					clienteremoto.createCliente(email, password, codiceFiscale, nome,cognome);
					
					request.login(email, password);
					
					context.addMessage(null, new FacesMessage("Registrazione avvenuta con successo"));
					
					return "index"; //homepage personalizzata del cliente
		    	
		    	} else {

		      context.addMessage(null, new FacesMessage("Cliente già registrato"));
		      
		      return "registrazioneProva";//pagina di errore
		    
		    }
		    }catch (ServletException e) {
		    	
		    	context.addMessage(null, new FacesMessage("Registrazione fallita"));
			      
			      return "errore";//pagina di errore
		    	
		    }
		    
		  }
}
