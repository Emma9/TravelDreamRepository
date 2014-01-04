package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

/**
 * Invito di TravelDream
 *
 */

@Entity

public class Invito implements Serializable {

	/**Version number */
	private static final long serialVersionUID = 106L;
	   
	/**Attributi */
	@Id
	@GeneratedValue
	private Long idInvito;
	private String emailMittente;
	private String emailDestinatario;
	private String idPacchettoPersonalizzato;
	private String data;
	private String stato;
	
	/**Costruttore */
	public Invito() {
		super();
	}  
	
	/**@return idInvito*/
	public Long getIdInvito() {
		return this.idInvito;
	}

	/**@param idInvito idInvito to set
	public void setIdInvito(Long idInvito) {
		this.idInvito = idInvito;
	}*/  
	
	/**@return emailMittente*/
	public String getEmailMittente() {
		return this.emailMittente;
	}

	/**@param emailMittente emailMittente to set*/
	public void setEmailMittente(String emailMittente) {
		this.emailMittente = emailMittente;
	}  
	
	/**@return emailDestinatario*/
	public String getEmailDestinatario() {
		return this.emailDestinatario;
	}

	/**@param emailDestinatario emailDestinatario to set*/
	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}
	
	/**@return idPacchettoPersonalizzato*/
	public String getIdPacchettoPersonalizzato() {
		return this.idPacchettoPersonalizzato;
	}

	/**@param idPacchettoPersonalizzato idPacchettoPersonalizzato to set*/
	public void setIdPacchettoPersonalizzato(String idPacchettoPersonalizzato) {
		this.idPacchettoPersonalizzato = idPacchettoPersonalizzato;
	}  
	
	/**@return data*/
	public String getData() {
		return this.data;
	}

	/**@param data data to set*/
	public void setData(String data) {
		this.data = data;
	}
	
	/**@return stato*/
	public String getStato() {
		return this.stato;
	}

	/**@param stato stato to set*/
	public void setStato(String stato) {
		this.stato = stato;
	}
   
}
