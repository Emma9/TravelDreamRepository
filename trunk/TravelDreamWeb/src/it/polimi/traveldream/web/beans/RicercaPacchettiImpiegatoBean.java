package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.PacchettoBeanRemote;

import it.polimi.traveldream.entities.PacchettoDTO;

import java.io.Serializable;
import java.util.ArrayList;




import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class RicercaPacchettiImpiegatoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 567L;
	
	// Session bean i cui metodi sono utilizzati nel codice
			@EJB
			private PacchettoBeanRemote pacchettoremoto;
		
			private Long idPacchetto;
			private String termine;
			
			//PACCHETTO SELEZIONATO
			private PacchettoDTO pacchettoSelezionato;
			
			//LISTA PACCHETTI INVIATA ALLA PAGINA WEB
			private ArrayList<PacchettoDTO> pacchettiRicercati = new ArrayList<PacchettoDTO>();
			
			//PACCHETTO INVIATO ALLA PAGINA WEB
			private PacchettoDTO pacchettoRicercato;
			
	
					
			
			
			/**
			 * @return the idPacchetto
			 */
			public Long getIdPacchetto() {
				return idPacchetto;
			}





			/**
			 * @param idPacchetto the idPacchetto to set
			 */
			public void setIdPacchetto(Long idPacchetto) {
				this.idPacchetto = idPacchetto;
			}





			/**
			 * @return the termine
			 */
			public String getTermine() {
				return termine;
			}





			/**
			 * @param termine the termine to set
			 */
			public void setTermine(String termine) {
				this.termine = termine;
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





			/**
			 * @return the pacchettoRicercato
			 */
			public PacchettoDTO getPacchettoRicercato() {
				return pacchettoRicercato;
			}





			/**
			 * @param pacchettoRicercato the pacchettoRicercato to set
			 */
			public void setPacchettoRicercato(PacchettoDTO pacchettoRicercato) {
				this.pacchettoRicercato = pacchettoRicercato;
			}





			public String ricercaPacchettoImpiegatoId(){
				
				FacesContext context = FacesContext.getCurrentInstance();
				HttpServletRequest request = (HttpServletRequest) context
						.getExternalContext().getRequest();
				
				try {
					
					//pacchettoRicercato = pacchettoremoto.findByIdPacchetto(idPacchetto);
						
					setPacchettoRicercato(pacchettoremoto.findByIdPacchetto(idPacchetto));
					
				}catch (EJBException e) {
					
					System.out.println("EJBException");
				
				return null;

			}

			return "admin/listaPacchettiRicercaImpiegato";		
				}

			
			
			
			
			public String ricercaPacchettoImpiegatoTermine(){
							
				FacesContext context = FacesContext.getCurrentInstance();
				HttpServletRequest request = (HttpServletRequest) context
						.getExternalContext().getRequest();
				
				try {
					
				pacchettiRicercati=pacchettoremoto.findByTermine(termine);
					

				}catch (EJBException e) {
				
					System.out.println("EJBException");
										
				return null;

			}

			return "listaPacchettiRicercaImpiegato";		
				}
			
				
			}
	