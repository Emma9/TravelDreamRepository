package it.polimi.traveldream.web.beans;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import it.polimi.traveldream.ejb.client.ClienteBeanRemote;

@ManagedBean()
@SessionScoped
public class RegistrazioneBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Session bean i cui metodi sono utilizzati nel codice
	@EJB
	private ClienteBeanRemote clienteremoto;
	
	

	private String nome;
	
	private String cognome;
	
	private String codiceFiscale;
	
	private String email;
	
	private String password;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}


	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}


	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	public String registrazione() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		    FacesContext context = FacesContext.getCurrentInstance();
		    HttpServletRequest request = (HttpServletRequest) 
		        context.getExternalContext().getRequest();
		    	
		    try{
		    
		    if (!clienteremoto.verificaPresenzaClienteRegistrazione(this.email,this.codiceFiscale)) {

					Long idCliente=clienteremoto.createCliente(this.email, this.password, this.codiceFiscale, this.nome,this.cognome);
					
					
		    	
					if(clienteremoto.verificaPresenzaClienteId(idCliente)){
						context.addMessage(null, new FacesMessage("Registrazione ok"));
					}
					if(!clienteremoto.verificaPresenzaClienteId(idCliente)){
						context.addMessage(null, new FacesMessage("Registrazione fallita"));
						//return "registrazione";
					}
					
					byte[] bytesPassword = password.getBytes("UTF-8");

					MessageDigest md = MessageDigest.getInstance("MD5");
					byte[] thedigest = md.digest(bytesPassword);
					
					request.login(email, thedigest.toString());
					
					request.login(email, password);
					
					context.addMessage(null, new FacesMessage("Registrazione avvenuta con successo"));
					
					return "homepageCliente"; //homepage personalizzata del cliente
		    	
		    	} else {

		      context.addMessage(null, new FacesMessage("Cliente gi� registrato"));
		      
		      return "registrazione";//pagina di registrazione
		    
		    }
		    }catch (ServletException e) {
		    	
		    	context.addMessage(null, new FacesMessage("ERRORE: Registrazione fallita"));
			      
			      return "registrazione";//pagina di registrazione
		    	
		    }
		    
		  }
}
