package it.polimi.traveldream.entities;

import java.lang.Long;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**PacchettoPersonalizzato di TravelDream*/

@Entity
public class PacchettoPersonalizzato extends Pacchetto {

	/**Version number*/
	private static final long serialVersionUID = 107L;

	/**Attributi*/

	private String stato; //SALVATO BLOCCATO CONFERMATO ACCETTATO
	private String emailUtente;
	@Temporal(TemporalType.DATE)
	private Date dataDiPartenza;
	@Temporal(TemporalType.DATE)
	private Date dataDiRitorno;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="pacchettoPersonalizzato")
	private List<Invito> invitiPacchetto= new ArrayList<Invito>();
	

	/**Costruttore*/
	public PacchettoPersonalizzato() {
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
	public List<Invito> getInvitiPacchetto() {
		return invitiPacchetto;
	}

	/**
	 * @param invitiPacchetto the invitiPacchetto to set
	 */
	public void setInvitiPacchetto(List<Invito> invitiPacchetto) {
		this.invitiPacchetto = invitiPacchetto;
	}

	
	
	
	
	
}
