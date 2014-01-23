package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class PacchettoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idPacchetto;
	private String destinazione;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private ArrayList<EtichettaDTO> etichette = new ArrayList<>();
	private String descrizione;
	private ArrayList<Long> listaComponenti;

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

	

	/**@return etichette*/
	public ArrayList<EtichettaDTO> getEtichette() {
	
		return this.etichette;
	
	}

	/**@param etichette etichette to set*/
	public void setEtichette(ArrayList<EtichettaDTO> et) {
	
		for (int i = 0; i <= et.size(); i++) {
	
			this.etichette.set(i, et.get(i));
	
		}
	}

	/**@return descrizione*/
	public String getDescrizione() {
		
		return this.descrizione;
		
	}

	/**@param descrizione descrizione to set*/
	public void setDescrizione(String descrizione) {
		
		this.descrizione = descrizione;
	
	}

	/**@return listaComponenti*/
	public ArrayList<Long> getListaComponenti() {
		
		return this.listaComponenti;
		
	}

	/**@param listaComponenti listaComponenti to set*/
	public void setListaComponenti(ArrayList<Long> listaComponenti) {
		
		this.listaComponenti = listaComponenti;
		
	}
}
