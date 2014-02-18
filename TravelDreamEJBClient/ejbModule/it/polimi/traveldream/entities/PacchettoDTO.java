package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PacchettoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 201L;

	private Long idPacchetto;
	private Long idPacchettoPersonalizzato;
	private String destinazione;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private String etichetta;
	private String descrizione;
	private List<ComponenteDTO> listaComponenti = new ArrayList<ComponenteDTO>(
			0);
	private List<ComponenteDTO> listaComponentiSelezionati = new ArrayList<ComponenteDTO>(
			0);
	private int costo;
	private int sconto;

	/** Costruttore */
	public PacchettoDTO() {
	};

	/** Costruttore PACCHETTO */
	public PacchettoDTO(String destinazione, Date dataInizioValidita,
			Date dateFineValidita, String etichetta, String descrizione,
			List<ComponenteDTO> listaComponenti,
			List<ComponenteDTO> listaComponentiSelezionati, int costo,
			int sconto) {

		this.setIdPacchettoPersonalizzato((long) 0);
		this.setDestinazione(destinazione);
		this.setDataInizioValidita(dataInizioValidita);
		this.setDataFineValidita(dateFineValidita);
		this.setEtichetta(etichetta);
		this.setDescrizione(descrizione);
		this.setListaComponenti(listaComponenti);
		this.setListaComponentiSelezionati(listaComponentiSelezionati);
		this.setCosto(costo);
		this.setSconto(sconto);

	}

	/** Costruttore PACCHETTOPERSONALIZZATO */
	public PacchettoDTO(Long idPacchettoPersonalizzato, String destinazione,
			Date dataInizioValidita, Date dateFineValidita, String etichetta,
			String descrizione, List<ComponenteDTO> listaComponenti,
			List<ComponenteDTO> listaComponentiSelezionati, int costo,
			int sconto) {

		this.setIdPacchettoPersonalizzato(idPacchettoPersonalizzato);
		this.setDestinazione(destinazione);
		this.setDataInizioValidita(dataInizioValidita);
		this.setDataFineValidita(dateFineValidita);
		this.setEtichetta(etichetta);
		this.setDescrizione(descrizione);
		this.setListaComponenti(listaComponenti);
		this.setListaComponentiSelezionati(listaComponentiSelezionati);
		this.setCosto(costo);
		this.setSconto(sconto);

	}

	/**
	 * @return clone
	 */
	public PacchettoDTO getClone() {

		PacchettoDTO clone = new PacchettoDTO();
		try {

			clone = (PacchettoDTO) this.clone();

		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clone;
	}

	/** @return idPacchetto */
	public Long getIdPacchetto() {
		return this.idPacchetto;
	}

	/**
	 * @param idPacchetto
	 *            the idPacchetto to set
	 */
	public void setIdPacchetto(Long idPacchetto) {
		this.idPacchetto = idPacchetto;
	}

	/** @return destinazione */
	public String getDestinazione() {
		return this.destinazione;
	}

	/**
	 * @return the idPacchettoPersonalizzato
	 */
	public Long getIdPacchettoPersonalizzato() {
		return idPacchettoPersonalizzato;
	}

	/**
	 * @param idPacchettoPersonalizzato
	 *            the idPacchettoPersonalizzato to set
	 */
	public void setIdPacchettoPersonalizzato(Long idPacchettoPersonalizzato) {
		this.idPacchettoPersonalizzato = idPacchettoPersonalizzato;
	}

	/**
	 * @param destinazione
	 *            destinazione to set
	 */
	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	/**
	 * @return the dataInizioValidita
	 */
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	/**
	 * @param dataInizioValidita
	 *            the dataInizioValidita to set
	 */
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	/**
	 * @return the dataFineValidita
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	/**
	 * @param dataFineValidita
	 *            the dataFineValidita to set
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	/**
	 * @return the etichetta
	 */
	public String getEtichetta() {
		return etichetta;
	}

	/**
	 * @param etichette
	 *            the etichetta to set
	 */
	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione
	 *            the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the listaComponenti
	 */
	public List<ComponenteDTO> getListaComponenti() {
		return listaComponenti;
	}

	/**
	 * @param listaComponenti
	 *            the listaComponenti to set
	 */
	public void setListaComponenti(List<ComponenteDTO> listaComponenti) {
		this.listaComponenti = listaComponenti;
	}

	/**
	 * @return the listaComponentiSelezionati
	 */
	public List<ComponenteDTO> getListaComponentiSelezionati() {
		return listaComponentiSelezionati;
	}

	/**
	 * @param listaComponentiSelezionati
	 *            the listaComponentiSelezionati to set
	 */
	public void setListaComponentiSelezionati(
			List<ComponenteDTO> listaComponentiSelezionati) {
		this.listaComponentiSelezionati = listaComponentiSelezionati;
	}

	/**
	 * @return the costo
	 */
	public int getCosto() {
		return costo;
	}

	/**
	 * @param costo
	 *            the costo to set
	 */
	public void setCosto(int costo) {
		this.costo = costo;
	}

	/**
	 * @return the sconto
	 */
	public int getSconto() {
		return sconto;
	}

	/**
	 * @param sconto
	 *            the sconto to set
	 */
	public void setSconto(int sconto) {
		this.sconto = sconto;
	}

}
