package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**Componente di TravelDream*/

@Entity
public class Componente implements Serializable {

	/**Version number*/
	private static final long serialVersionUID = 104L;

	/**Attributi*/
	@Id
	@GeneratedValue
	private Long codiceComponente;
	private String tipologia;
	private String descrizione;
	@Temporal(TemporalType.TIME)
	private Date dataInizioValidita;
	@Temporal(TemporalType.TIME)
	private Date dataFineValidita;
	private int disponibilita;

	/**Costruttore*/
	public Componente() {
		super();
	}

	/**@return codiceComponente*/
	public Long getCodiceComponente() {
		
		return this.codiceComponente;
		
	}

	/**@return tipologia*/
	public String getTipologia() {

		return this.tipologia;

	}
	
	/**@param tipologia tipologia to set*/
	public void setTipologia(String tipologia) {

		this.tipologia = tipologia;

	}

	/**@return descrizione*/
	public String getDescrizione() {
		
		return this.descrizione;
		
	}

	/**@param descrizione descrizione to set*/
	public void setDescrizione(String descrizione) {
		
		this.descrizione = descrizione;
		
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
	
	
	
	
	
	
}
