package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.PacchettoBeanRemote;
import it.polimi.traveldream.entities.PacchettoDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean()
@SessionScoped
public class RicercaPacchettiBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private PacchettoBeanRemote pacchettoRemoto;

	private String destinazione;
	private Date dataPartenza;
	private Date dataArrivo;
	private int numPartecipanti;
	
	//PACCHETTO SELEZIONATO
	private PacchettoDTO pacchettoSelezionato;
	
	//LISTA PACCHETTI INVIATA ALLA PAGINA WEB
	private ArrayList<PacchettoDTO> pacchettiRicercati = new ArrayList<PacchettoDTO>();

	/**
	 * @return the destinazione
	 */
	public String getDestinazione() {
		return destinazione;
	}

	/**
	 * @param destinazione
	 *            the destinazione to set
	 */
	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	/**
	 * @return the dataPartenza
	 */
	public Date getDataPartenza() {
		return dataPartenza;
	}

	/**
	 * @param dataPartenza
	 *            the dataPartenza to set
	 */
	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}

	/**
	 * @return the dataArrivo
	 */
	public Date getDataArrivo() {
		return dataArrivo;
	}

	/**
	 * @param dataArrivo
	 *            the dataArrivo to set
	 */
	public void setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	/**
	 * @return the numPartecipanti
	 */
	public int getNumPartecipanti() {
		return numPartecipanti;
	}

	/**
	 * @param numPartecipanti
	 *            the numPartecipanti to set
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

	public void ricercaPacchetti() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		ArrayList<PacchettoDTO> pacchetti = new ArrayList<PacchettoDTO>();
	

		//try {
			
			if(this.pacchettoRemoto.verificaConsistenzaDate(this.dataPartenza, this.dataArrivo)){
				//LE DATE INSERITE SONO VALIDE
			
			pacchetti = pacchettoRemoto.ricercaPacchetti(this.destinazione, this.dataPartenza, this.dataArrivo);
				//RITORNA LA LISTA DEI PACCHETTI CON DESTINAZIONE DESIDERATA E DISPONIBILI NEL PERIODO RICHIESTO
			
			for(int i=0;i<=pacchetti.size();i++){
				//PER OGNI PACCHETTO VERIFICA CHE TUTTI I SUOI COMPONENTI SIANO DISPONIBILI
				
				if(pacchettoRemoto.verificaDisponibilitaComponenti(this.dataPartenza, this.dataArrivo, this.numPartecipanti, pacchetti.get(i).getListaComponenti())){
				//SE TUTTI I COMPONENTI SONO DISPONIBILI NON RIMUOVE IL PACCHETTO DALLA LISTA
					
				}else{
					pacchetti.set(i, null);
				//SE CI SONO DEI COMPONENTI NON DISPONIBILI ANNULLA IL PACCHETTO CHE LI CONTIENE	
					
				}
				
			}				
			
			for(int j=0;j<=pacchetti.size();j++){
				//SCORRE LA LISTA DEI PACCHETTI
			
			if(pacchetti.get(j)==null){
				//SE PACCHETTO == NULL PASSA AL PROSSIMO PACCHETTO
				
			}else{
				//SE PACCHETTO != NULL LO COPIA IN PACCHETTIRICERCATI
				pacchettiRicercati.set(j, pacchetti.get(j));
			}
				
			}
				
			}
			

		/*} catch (ServletException e) {
			
			return null;

		}*/
		
		//return pacchettiRicercati;

	}
	
	
	
}
