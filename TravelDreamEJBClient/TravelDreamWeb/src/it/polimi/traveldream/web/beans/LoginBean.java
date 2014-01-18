package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.ClienteBean;
import it.polimi.traveldream.ejb.client.ClienteBeanRemote;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

public class LoginBean implements Login,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email;  

    private String password; 
    
  //  private String codiceFiscale;
    
    private String nome;
    
    private String cognome;
      
    public String getEmail() {  
        return email;  
    }  
    
    public String getNome() {  
        return nome;  
    }
    
    public String getCognome() {  
        return cognome;  
    }
  
    public void setEmail(String email) {  
        this.email = email;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
	public void login(ActionEvent actionEvent, String email, String password) {
		
		// immagino qui sia context.getInitizialContext() o qualche cosa del genere
		//Context context =Context getInitialContext();
		
		
		FacesMessage msg = null;
		boolean loggedIn = false;

		if (email != null && password != null) {
			ClienteBeanRemote cliente = new ClienteBean();
			cliente.createCliente(email, password, null, null, null);
			long id = cliente.verificaPresenzaClienteLogin(email, password);
			if (id != -1) {
				loggedIn = true;
				msg = new FacesMessage("Benvenuto "+ email);
			}

		} else if (loggedIn==false){
			msg = new FacesMessage("Credenziali non valide");
		}

	//	FacesContext.getCurrentInstance().addMessage("homepage", msg); ((RequestContext) context).addCallbackParam("loggedIn", loggedIn);
	}
}
