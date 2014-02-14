package it.polimi.traveldream.entities;



import java.io.Serializable;
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
	private int codiceComponente;
	private String luogo;
	private String tipologia;
	private String descrizione;
	private int costo;
	@Temporal(TemporalType.DATE)
	private Date dataInizioValidita;
	@Temporal(TemporalType.DATE)
	private Date dataFineValidita;
	
	/*@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name="Componente_DisponibilitaPerData"
		, joinColumns={
			@JoinColumn(name="codiceComponente", referencedColumnName="codiceComponente")
			}
		, inverseJoinColumns={
			@JoinColumn(name="data", referencedColumnName="data")
			}
		)
		*/
	@ManyToMany(cascade = CascadeType.ALL)
	private List<DisponibilitaPerData> disponibilitaPerData;
	
	@ManyToMany(mappedBy="listaComponenti"/*, cascade=CascadeType.REMOVE*/)
	private List<Pacchetto> pacchettiComp;
	
	@ManyToMany(mappedBy="listaComponentiSelezionati"/*, cascade=CascadeType.REMOVE*/)
	private List<Pacchetto> pacchettiCompSelezionati;
	

	/**Costruttore*/
	public Componente() {
		super();
	}

	/**@return codiceComponente*/
	public int getCodiceComponente() {
		
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
	public void setCodiceComponente(int codiceComponente) {
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

	/**
	 * @return the pacchettiComp
	 */
	public List<Pacchetto> getPacchettiComp() {
		return pacchettiComp;
	}

	/**
	 * @param pacchettiComp the pacchettiComp to set
	 */
	public void setPacchettiComp(List<Pacchetto> pacchettiComp) {
		this.pacchettiComp = pacchettiComp;
	}

	/**
	 * @return the pacchettiCompSelezionati
	 */
	public List<Pacchetto> getPacchettiCompSelezionati() {
		return pacchettiCompSelezionati;
	}

	/**
	 * @param pacchettiCompSelezionati the pacchettiCompSelezionati to set
	 */
	public void setPacchettiCompSelezionati(List<Pacchetto> pacchettiCompSelezionati) {
		this.pacchettiCompSelezionati = pacchettiCompSelezionati;
	}

	
	
	
}
