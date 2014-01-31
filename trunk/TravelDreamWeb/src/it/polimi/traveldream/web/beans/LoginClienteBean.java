package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.entities.ClienteDTO;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name= "loginClienteBean")
@SessionScoped
public class LoginClienteBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private String email;

	//private String password;
	
	private boolean logged;
	
	private ClienteDTO cliente= new ClienteDTO();
	

	
	
	
	/**
	 * @return the cliente
	 */
	public ClienteDTO getCliente() {
		return cliente;
	}


	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}


	/*public String getEmail() {
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
*/
	public boolean isLogged(){
		if(logged==true){
			return true;
		}
		return false;
	}
	
	
	public String login() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		
		//if(request.getUserPrincipal()==null){
		
		try {
			
			byte[] bytesPassword = cliente.getPassword().getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesPassword);
			
			request.login(cliente.getEmail(), thedigest.toString());
			
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
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "homepage?faces-redirect=true";
		
		
		/*FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			
			request.logout();
			
		} catch (ServletException e) {

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Logout fallito", ""));
			
			return "homepageCliente"; // home page personalizzata del cliente
			
		}
		return "homepage"; //homepage del sito web*/
	}

	

}
