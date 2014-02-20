package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PacchettoPersonalizzatoDTO extends PacchettoDTO implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 200L;

	/** Attributi */

	private String stato; // SALVATO BLOCCATO CONFERMATO ACCETTATO
	private String emailUtente;
	private Date dataDiPartenza;
	private Date dataDiRitorno;
	private int numPartecipanti;

	private List<InvitoDTO> invitiPacchetto = new ArrayList<InvitoDTO>();

	/** Costruttore */
	public PacchettoPersonalizzatoDTO() {
	}

	/** Costruttore PACCHETTOPERSONALIZZATO */
	public PacchettoPersonalizzatoDTO(Long idPacchetto,
			Long idPacchettoPersonalizzato, String destinazione,
			Date dataInizioValidita, Date dateFineValidita, String etichetta,
			String descrizione, List<ComponenteDTO> listaComponenti,
			List<ComponenteDTO> listaComponentiSelezionati, int costo,
			int sconto, String stato, String emailUtente, Date dataDiPartenza,
			Date dataDiRitorno, List<InvitoDTO> invitiPacchetto) {

		super(idPacchettoPersonalizzato, destinazione, dataInizioValidita,
				dateFineValidita, etichetta, descrizione, listaComponenti,
				listaComponentiSelezionati, costo, sconto);

		this.setStato(stato);
		this.setEmailUtente(emailUtente);
		this.setDataDiPartenza(dataDiPartenza);
		this.setDataDiRitorno(dataDiRitorno);
		this.setInvitiPacchetto(invitiPacchetto);

		super.setIdPacchetto(idPacchetto);

	}

	/** @return Stato */
	public String getStato() {

		return this.stato;

	}

	/**
	 * @param stato
	 *            stato to set
	 */
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
	 * @param emailUtente
	 *            the emailUtente to set
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
	 * @param dataDiPartenza
	 *            the dataDiPartenza to set
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
	 * @param dataDiRitorno
	 *            the dataDiRitorno to set
	 */
	public void setDataDiRitorno(Date dataDiRitorno) {
		this.dataDiRitorno = dataDiRitorno;
	}
	
	

	/**
	 * @return the numPartecipanti
	 */
	public int getNumPartecipanti() {
		return numPartecipanti;
	}

	/**
	 * @param numPartecipanti the numPartecipanti to set
	 */
	public void setNumPartecipanti(int numPartecipanti) {
		this.numPartecipanti = numPartecipanti;
	}

	/**
	 * @return the invitiPacchetto
	 */
	public List<InvitoDTO> getInvitiPacchetto() {
		return invitiPacchetto;
	}

	/**
	 * @param invitiPacchetto
	 *            the invitiPacchetto to set
	 */
	public void setInvitiPacchetto(List<InvitoDTO> invitiPacchetto) {
		this.invitiPacchetto = invitiPacchetto;
	}

}
