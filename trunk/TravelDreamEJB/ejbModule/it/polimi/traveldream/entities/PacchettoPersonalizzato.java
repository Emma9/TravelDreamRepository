package it.polimi.traveldream.entities;

import java.lang.Long;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/** PacchettoPersonalizzato di TravelDream */

@Entity
public class PacchettoPersonalizzato extends Pacchetto {

	/** Version number */
	private static final long serialVersionUID = 107L;

	/** Attributi */

	private String stato; // SALVATO BLOCCATO CONFERMATO ACCETTATO
	
	@ManyToOne
    @JoinColumn
	private User cliente;
	@Temporal(TemporalType.DATE)
	private Date dataDiPartenza;
	@Temporal(TemporalType.DATE)
	private Date dataDiRitorno;
	private int numPartecipanti;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pacchettoPersonalizzato")
	private List<Invito> invitiPacchetto = new ArrayList<Invito>();

	/** Costruttore */
	public PacchettoPersonalizzato() {
		super();
	}

	/** Costruttore PACCHETTOPERSONALIZZATO */
	public PacchettoPersonalizzato(Long idPacchetto,
			Long idPacchettoPersonalizzato, String destinazione,
			Date dataInizioValidita, Date dateFineValidita, String etichetta,
			String descrizione, List<Componente> listaComponenti,
			List<Componente> listaComponentiSelezionati, int costo, int sconto,
			String stato, User cliente, Date dataDiPartenza,
			Date dataDiRitorno, int numPartecipanti, List<Invito> invitiPacchetto) {

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
	public User getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(User cliente) {
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
	public List<Invito> getInvitiPacchetto() {
		return invitiPacchetto;
	}

	/**
	 * @param invitiPacchetto
	 *            the invitiPacchetto to set
	 */
	public void setInvitiPacchetto(List<Invito> invitiPacchetto) {
		this.invitiPacchetto = invitiPacchetto;
	}

}
