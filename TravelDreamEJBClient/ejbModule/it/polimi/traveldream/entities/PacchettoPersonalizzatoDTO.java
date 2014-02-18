package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class PacchettoPersonalizzatoDTO extends PacchettoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 200L;
	
	
	/**Attributi*/


	private String stato; //SALVATO BLOCCATO CONFERMATO ACCETTATO
	private String emailUtente;
	private Date dataDiPartenza;
	private Date dataDiRitorno;
	
	private List<InvitoDTO> invitiPacchetto= new ArrayList<InvitoDTO>();
	

	/**Costruttore*/
	public PacchettoPersonalizzatoDTO() {
		super();
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

	/**
	 * @return the invitiPacchetto
	 */
	public List<InvitoDTO> getInvitiPacchetto() {
		return invitiPacchetto;
	}

	/**
	 * @param invitiPacchetto the invitiPacchetto to set
	 */
	public void setInvitiPacchetto(List<InvitoDTO> invitiPacchetto) {
		this.invitiPacchetto = invitiPacchetto;
	}

	
	
	
	
	
}
