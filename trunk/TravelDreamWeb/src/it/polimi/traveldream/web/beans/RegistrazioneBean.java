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

import org.apache.commons.codec.digest.DigestUtils;

import it.polimi.traveldream.ejb.client.ClienteBeanRemote;
import it.polimi.traveldream.entities.ClienteDTO;

@ManagedBean(name="registrazioneBean")
@SessionScoped
public class RegistrazioneBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 333L;
	
	// Session bean i cui metodi sono utilizzati nel codice
	@EJB
	private ClienteBeanRemote clienteremoto;
	
	private ClienteDTO cliente= new ClienteDTO();

	
	
	/**
	 * @return the clienteremoto
	 */
	public ClienteBeanRemote getClienteremoto() {
		return clienteremoto;
	}




	/**
	 * @param clienteremoto the clienteremoto to set
	 */
	public void setClienteremoto(ClienteBeanRemote clienteremoto) {
		this.clienteremoto = clienteremoto;
	}




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




	public String registrazione() throws NoSuchAlgorithmException,
			UnsupportedEncodingException {

		/*
		 * clienteremoto.createCliente(this.cliente.getEmail(),
		 * this.cliente.getPassword(), this.cliente.getCodiceFiscale(),
		 * this.cliente.getNome(), this.cliente.getCognome());
		 * if(!clienteremoto.
		 * findByEmailCliente(cliente.getEmail()).equals(null)){ return
		 * "homepageCliente"; } return "formInvitoAmico";
		 */

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			if (!clienteremoto.verificaPresenzaClienteRegistrazione(
					this.cliente.getEmail(), this.cliente.getCodiceFiscale())) {

				String hashPassword= DigestUtils.sha256Hex(this.cliente.getPassword());
				Long idCliente = clienteremoto.createCliente(
						this.cliente.getEmail(), hashPassword,
						this.cliente.getCodiceFiscale(),
						this.cliente.getNome(), this.cliente.getCognome());

				if (clienteremoto.verificaPresenzaClienteId(idCliente)) {
					context.addMessage(null, new FacesMessage(
							"Registrazione ok"));
				}
				if (!clienteremoto.verificaPresenzaClienteId(idCliente)) {
					context.addMessage(null, new FacesMessage(
							"Registrazione fallita"));
					// return "registrazione";
				}

				byte[] bytesPassword = cliente.getPassword().getBytes("UTF-8");

				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] thedigest = md.digest(bytesPassword);

				request.login(cliente.getEmail(), thedigest.toString());

				// request.login(cliente.getEmail(), cliente.getPassword());

				context.addMessage(null, new FacesMessage(
						"Registrazione avvenuta con successo"));

				return "homepageCliente"; // homepage personalizzata del cliente

			} else {

				context.addMessage(null, new FacesMessage(
						"Cliente già registrato"));

				return "registrazione";// pagina di registrazione

			}
		} catch (ServletException e) {

			context.addMessage(null, new FacesMessage(
					"ERRORE: Registrazione fallita"));

			return "registrazione";// pagina di registrazione

		}

	}
}
