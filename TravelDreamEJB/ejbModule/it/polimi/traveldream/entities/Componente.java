package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private int costo;
	@Temporal(TemporalType.TIME)
	private Date dataInizioValidita;
	@Temporal(TemporalType.TIME)
	private Date dataFineValidita;
	private List<DisponibilitaPerData> disponibilitaPerData = new ArrayList<DisponibilitaPerData>(0);
	
	
	

	/**Costruttore*/
	public Componente() {
		super();
	}

	/**@return codiceComponente*/
	public Long getCodiceComponente() {
		
		return this.codiceComponente;
		
	}
	
	

	/**
	 * @param codiceComponente the codiceComponente to set
	 */
	public void setCodiceComponente(Long codiceComponente) {
		this.codiceComponente = codiceComponente;
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
	 * @return the costo
	 */
	public int getCosto() {
		return costo;
	}

	/**
	 * @param costo the costo to set
	 */
	public void setCosto(int costo) {
		this.costo = costo;
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
	 * @return the disponibilitaPerData
	 */
	public List<DisponibilitaPerData> getDisponibilitaPerData() {
		return disponibilitaPerData;
	}

	/**
	 * @param disponibilitaPerData the disponibilitaPerData to set
	 */
	public void setDisponibilitaPerData(
			List<DisponibilitaPerData> disponibilitaPerData) {
		this.disponibilitaPerData = disponibilitaPerData;
	}

	
	
}
