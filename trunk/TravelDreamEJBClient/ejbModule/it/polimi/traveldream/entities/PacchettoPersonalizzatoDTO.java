package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class PacchettoPersonalizzatoDTO extends PacchettoDTO implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 200L;

	/** Attributi */

private String stato; // SALVATO BLOCCATO CONFERMATO ACCETTATO
	
	
	private UserDTO cliente;
	private Date dataDiPartenza;
	private Date dataDiRitorno;
	private int numPartecipanti;

	private List<InvitoDTO> invitiPacchetto = new ArrayList<InvitoDTO>();

	/** Costruttore */
	public PacchettoPersonalizzatoDTO() {
		super();
	}

	/** Costruttore PACCHETTOPERSONALIZZATO */
	public PacchettoPersonalizzatoDTO(Long idPacchetto,
			Long idPacchettoPersonalizzato, String destinazione,
			Date dataInizioValidita, Date dateFineValidita, String etichetta,
			String descrizione, List<ComponenteDTO> listaComponenti,
			List<ComponenteDTO> listaComponentiSelezionati, int costo, int sconto,
			String stato, UserDTO cliente, Date dataDiPartenza,
			Date dataDiRitorno, int numPartecipanti, List<InvitoDTO> invitiPacchetto) {

		super(idPacchettoPersonalizzato, destinazione, dataInizioValidita,
				dateFineValidita, etichetta, descrizione, listaComponenti,
				listaComponentiSelezionati, costo, sconto);

		this.setStato(stato);
		this.setCliente(cliente);
		this.setDataDiPartenza(dataDiPartenza);
		this.setDataDiRitorno(dataDiRitorno);
		this.setNumPartecipanti(numPartecipanti);
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
	 * @return the cliente
	 */
	public UserDTO getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(UserDTO cliente) {
		this.cliente = cliente;
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
