package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PacchettoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idPacchetto;
	private String destinazione;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private List<EtichettaDTO> etichette = new ArrayList<EtichettaDTO>(0);
	private String descrizione;
	private List<Long> listaComponenti= new ArrayList<Long>(0);

	/**Costruttore*/
	public PacchettoDTO() {
		super();
	}

	/**@return idPacchetto*/
	public Long getIdPacchetto() {
		return this.idPacchetto;
	}

	/**@return destinazione*/
	public String getDestinazione() {
		return this.destinazione;
	}

	/**@param destinazione destinazione to set*/
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
	 * @param dataInizioValidita the dataInizioValidita to set
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
	 * @param dataFineValidita the dataFineValidita to set
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	/**
	 * @param idPacchetto the idPacchetto to set
	 */
	public void setIdPacchetto(Long idPacchetto) {
		this.idPacchetto = idPacchetto;
	}

	/**
	 * @return the etichette
	 */
	public List<EtichettaDTO> getEtichette() {
		return etichette;
	}

	/**
	 * @param etichette the etichette to set
	 */
	public void setEtichette(List<EtichettaDTO> etichette) {
		this.etichette = etichette;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the listaComponenti
	 */
	public List<Long> getListaComponenti() {
		return listaComponenti;
	}

	/**
	 * @param listaComponenti the listaComponenti to set
	 */
	public void setListaComponenti(List<Long> listaComponenti) {
		this.listaComponenti = listaComponenti;
	}

	
}
