package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ComponenteDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 205L;
	private Long codiceComponente;
	private String luogo;
	private String tipologia;
	private String descrizione;
	private int costo;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private List<DisponibilitaPerDataDTO> disponibilitaPerData = new ArrayList<DisponibilitaPerDataDTO>(0);
	
	
	

	/**Costruttore*/
	public ComponenteDTO() {
		super();
	}

	/**@return codiceComponente*/
	public Long getCodiceComponente() {
		
		return this.codiceComponente;
		
	}
	
	
	
	
	


	/**
	 * @return the luogo
	 */
	public String getLuogo() {
		return luogo;
	}

	/**
	 * @param luogo the luogo to set
	 */
	public void setLuogo(String luogo) {
		this.luogo = luogo;
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
	public List<DisponibilitaPerDataDTO> getDisponibilitaPerData() {
		return disponibilitaPerData;
	}

	/**
	 * @param disponibilitaPerData the disponibilitaPerData to set
	 */
	public void setDisponibilitaPerData(
			List<DisponibilitaPerDataDTO> disponibilitaPerData) {
		this.disponibilitaPerData = disponibilitaPerData;
	}

		
	
	
}
