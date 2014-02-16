package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.Date;


public class PacchettoPersonalizzatoDTO extends PacchettoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 200L;
	
	private Long idPacchettoPersonalizzato;
	private String stato;
	private String emailUtente;
	private Date dataDiPartenza;
	private Date dataDiRitorno;

	/**Costruttore*/
	public PacchettoPersonalizzatoDTO() {
		super();
	}

	/**@return idPacchettoPersonalizzato*/
	public Long getIdPacchettoPersonalizzato() {
		
		return this.idPacchettoPersonalizzato;
		
	}
	
	

	/**
	 * @param idPacchettoPersonalizzato the idPacchettoPersonalizzato to set
	 */
	public void setIdPacchettoPersonalizzato(Long idPacchettoPersonalizzato) {
		this.idPacchettoPersonalizzato = idPacchettoPersonalizzato;
	}

	/**@return Stato*/
	public String getStato() {
		
		return this.stato;
		
	}

	/**@param stato stato to set*/
	public void setStato(String stato) {
		
		this.stato = stato;

	}

	

	/**
	 * @return the emailUtente
	 */
	public String getEmailUtente() {
		return emailUtente;
	}

	/**
	 * @param emailUtente the emailUtente to set
	 */
	public void setEmailUtente(String emailUtente) {
		this.emailUtente = emailUtente;
	}

	/**
	 * @return the dataDiPartenza
	 */
	public Date getDataDiPartenza() {
		return dataDiPartenza;
	}

	/**
	 * @param dataDiPartenza the dataDiPartenza to set
	 */
	public void setDataDiPartenza(Date dataDiPartenza) {
		this.dataDiPartenza = dataDiPartenza;
	}

	/**
	 * @return the dataDiRitorno
	 */
	public Date getDataDiRitorno() {
		return dataDiRitorno;
	}

	/**
	 * @param dataDiRitorno the dataDiRitorno to set
	 */
	public void setDataDiRitorno(Date dataDiRitorno) {
		this.dataDiRitorno = dataDiRitorno;
	}
	
	
	
}