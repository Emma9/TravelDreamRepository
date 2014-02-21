package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class InvitoDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 202L;
	private Long idInvito;
	private UserDTO mittente;
	private AmicoDTO destinatario;
	private Date data;
	private boolean stato; //TRUE=>ACCETTATO
	
	private PacchettoPersonalizzatoDTO pacchettoPersonalizzato;

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

	
	

	

	/**
	 * @return the mittente
	 */
	public UserDTO getMittente() {
		return mittente;
	}

	/**
	 * @param mittente the mittente to set
	 */
	public void setMittente(UserDTO mittente) {
		this.mittente = mittente;
	}

	/**
	 * @return the destinatario
	 */
	public AmicoDTO getDestinatario() {
		return destinatario;
	}

	/**
	 * @param destinatario the destinatario to set
	 */
	public void setDestinatario(AmicoDTO destinatario) {
		this.destinatario = destinatario;
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
