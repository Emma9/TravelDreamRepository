package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.PacchettoBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.PacchettoDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class RicercaPerEtichettaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PacchettoBeanRemote pacchettoRemoto;
	
	private String etichetta;
	private Date dataPartenza;
	private Date dataRitorno;
	private int numPartecipanti;
	
	//PACCHETTO SELEZIONATO
		private PacchettoDTO pacchettoSelezionato;
		
		//LISTA PACCHETTI INVIATA ALLA PAGINA WEB
		private ArrayList<PacchettoDTO> pacchettiRicercati = new ArrayList<PacchettoDTO>();
	
	
	/**
	 * @return the etichetta
	 */
	public String getEtichetta() {
		return etichetta;
	}
	/**
	 * @param etichetta the etichetta to set
	 */
	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
	}
	/**
	 * @return the dataPartenza
	 */
	public Date getDataPartenza() {
		return dataPartenza;
	}
	/**
	 * @param dataPartenza the dataPartenza to set
	 */
	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}
	/**
	 * @return the dataRitorno
	 */
	public Date getDataRitorno() {
		return dataRitorno;
	}
	/**
	 * @param dataRitorno the dataRitorno to set
	 */
	public void setDataRitorno(Date dataRitorno) {
		this.dataRitorno = dataRitorno;
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
	 * @return the pacchettoSelezionato
	 */
	public PacchettoDTO getPacchettoSelezionato() {
		return pacchettoSelezionato;
	}
	/**
	 * @param pacchettoSelezionato the pacchettoSelezionato to set
	 */
	public void setPacchettoSelezionato(PacchettoDTO pacchettoSelezionato) {
		this.pacchettoSelezionato = pacchettoSelezionato;
	}
	/**
	 * @return the pacchettiRicercati
	 */
	public ArrayList<PacchettoDTO> getPacchettiRicercati() {
		return pacchettiRicercati;
	}
	/**
	 * @param pacchettiRicercati the pacchettiRicercati to set
	 */
	public void setPacchettiRicercati(ArrayList<PacchettoDTO> pacchettiRicercati) {
		this.pacchettiRicercati = pacchettiRicercati;
	}
	
	
	
	public void ricercaPerEtichetta(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		ArrayList<PacchettoDTO> pacchetti = new ArrayList<PacchettoDTO>();
		
		try {
		
		
		if(this.pacchettoRemoto.verificaConsistenzaDate(this.dataPartenza, this.dataRitorno)){
			//LE DATE INSERITE SONO VALIDE
		
		pacchetti = pacchettoRemoto.ricercaPerEtichetta(this.etichetta, this.dataPartenza, this.dataRitorno);
			//RITORNA LA LISTA DEI PACCHETTI CON DESTINAZIONE DESIDERATA E DISPONIBILI NEL PERIODO RICHIESTO
		
		for(int i=0;i<pacchetti.size();i++){
			//PER OGNI PACCHETTO VERIFICA CHE TUTTI I SUOI COMPONENTI SIANO DISPONIBILI
			
			if(pacchettoRemoto.verificaDisponibilitaComponenti(this.dataPartenza, this.dataRitorno, this.numPartecipanti, (ArrayList<ComponenteDTO>) pacchetti.get(i).getListaComponentiSelezionati())){
			//SE TUTTI I COMPONENTI SONO DISPONIBILI NON RIMUOVE IL PACCHETTO DALLA LISTA
				
			}else{
				pacchetti.set(i, null);
			//SE CI SONO DEI COMPONENTI NON DISPONIBILI ANNULLA IL PACCHETTO CHE LI CONTIENE	
				
			}
			
		}				
		
		for(int j=0;j<pacchetti.size();j++){
			//SCORRE LA LISTA DEI PACCHETTI
		
		if(pacchetti.get(j)==null){
			//SE PACCHETTO == NULL PASSA AL PROSSIMO PACCHETTO
			
		}else{
			//SE PACCHETTO != NULL LO COPIA IN PACCHETTIRICERCATI
			pacchettiRicercati.set(j, pacchetti.get(j));
		}
			
		}
			
		}
		

	} catch (EJBException e) {
		
		//return null;

	}
	
	//return pacchettiRicercati;
		
	}
	
}
