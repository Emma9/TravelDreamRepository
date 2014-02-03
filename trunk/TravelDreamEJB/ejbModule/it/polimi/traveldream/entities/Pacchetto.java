package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	@Temporal(TemporalType.DATE)
	private Date dataInizioValidita;
	@Temporal(TemporalType.DATE)
	private Date dataFineValidita;
	private String etichetta;
	private String descrizione;
	
	@ManyToMany
	@JoinTable(
		name="Pacchetto_componenti"
		, joinColumns={
			@JoinColumn(name="idPacchetto", referencedColumnName="idPacchetto")
			}
		, inverseJoinColumns={
			@JoinColumn(name="codiceComponente", referencedColumnName="codiceComponente")
			}
		)
	private List<Componente> listaComponenti= new ArrayList<Componente>();
	
	@ManyToMany
	@JoinTable(
		name="Pacchetto_componentiSelezionati"
		, joinColumns={
			@JoinColumn(name="idPacchetto", referencedColumnName="idPacchetto")
			}
		, inverseJoinColumns={
			@JoinColumn(name="codiceComponente", referencedColumnName="codiceComponente")
			}
		)
	private List<Componente> listaComponentiSelezionati= new ArrayList<Componente>();
	private int costo;
	private int sconto;

	/**Costruttore*/
	public Pacchetto() {
		super();
	}
	
	
	/**
	 * @return clone
	 */
	public Pacchetto getClone() {
		
		Pacchetto clone= new Pacchetto();
		try {
	
			clone=(Pacchetto) this.clone();
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clone;
	}

	/**@return idPacchetto*/
	public Long getIdPacchetto() {
		return this.idPacchetto;
	}

	
	
	/**
	 * @param idPacchetto the idPacchetto to set
	 */
	public void setIdPacchetto(Long idPacchetto) {
		this.idPacchetto = idPacchetto;
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
	 * @return the etichetta
	 */
	public String getEtichetta() {
		return etichetta;
	}

	/**
	 * @param etichette the etichetta to set
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
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the listaComponenti
	 */
	public List<Componente> getListaComponenti() {
		return listaComponenti;
	}

	/**
	 * @param listaComponenti the listaComponenti to set
	 */
	public void setListaComponenti(List<Componente> listaComponenti) {
		this.listaComponenti = listaComponenti;
	}

	/**
	 * @return the listaComponentiSelezionati
	 */
	public List<Componente> getListaComponentiSelezionati() {
		return listaComponentiSelezionati;
	}

	/**
	 * @param listaComponentiSelezionati the listaComponentiSelezionati to set
	 */
	public void setListaComponentiSelezionati(
			List<Componente> listaComponentiSelezionati) {
		this.listaComponentiSelezionati = listaComponentiSelezionati;
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
	 * @return the sconto
	 */
	public int getSconto() {
		return sconto;
	}


	/**
	 * @param sconto the sconto to set
	 */
	public void setSconto(int sconto) {
		this.sconto = sconto;
	}

		
}
