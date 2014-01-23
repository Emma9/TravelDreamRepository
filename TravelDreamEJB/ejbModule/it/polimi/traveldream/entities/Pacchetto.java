package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

/**Pacchetto di TravelDream*/

@Entity
public class Pacchetto implements Serializable {

	/**Version number*/
	private static final long serialVersionUID = 108L;

	/**Attributi*/
	@Id
	@GeneratedValue
	private Long idPacchetto;
	private String destinazione;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private int disponibilita;
	private ArrayList<Etichetta> etichette = new ArrayList<>();
	private String descrizione;
	private ArrayList<Long> listaComponenti;

	/**Costruttore*/
	public Pacchetto() {
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
	 * @return the disponibilita
	 */
	public int getDisponibilita() {
		return disponibilita;
	}

	/**
	 * @param disponibilita the disponibilita to set
	 */
	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}

	/**@return etichette*/
	public ArrayList<Etichetta> getEtichette() {
	
		return this.etichette;
	
	}

	/**@param etichette etichette to set*/
	public void setEtichette(ArrayList<Etichetta> et) {
	
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
