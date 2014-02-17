package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class InvitoDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 202L;
	
	private Long idInvito;
	private String emailMittente;
	private String emailDestinatario;
	private PacchettoPersonalizzatoDTO pacchettoPersonalizzato;
	@Temporal(TemporalType.TIME)
	private Date data;
	private boolean stato;

	/**Costruttore*/
	public InvitoDTO() {
		super();
	}

	/**@return idInvito*/
	public Long getIdInvito() {
		
		return this.idInvito;
		
	}
	

	/**
	 * @param idInvito the idInvito to set
	 */
	public void setIdInvito(Long idInvito) {
		this.idInvito = idInvito;
	}

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

	

	/**
	 * @return the pacchettoPersonalizzato
	 */
	public PacchettoPersonalizzatoDTO getPacchettoPersonalizzato() {
		return pacchettoPersonalizzato;
	}

	/**
	 * @param pacchettoPersonalizzato the pacchettoPersonalizzato to set
	 */
	public void setPacchettoPersonalizzato(
			PacchettoPersonalizzatoDTO pacchettoPersonalizzato) {
		this.pacchettoPersonalizzato = pacchettoPersonalizzato;
	}

	/**@return data*/
	public Date getData() {
		
		return this.data;
		
	}

	/**@param data data to set*/
	public void setData(Date data) {
		
		this.data = data;
		
	}

	/**@return stato*/
	public boolean getStato() {
		
		return this.stato;
		
	}

	/**@param stato stato to set*/
	public void setStato(boolean stato) {
		
		this.stato = stato;
		
	}

}
