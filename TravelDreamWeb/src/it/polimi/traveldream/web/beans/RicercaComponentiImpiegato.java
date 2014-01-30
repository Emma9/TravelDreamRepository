package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.ejb.client.ImpiegatoBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;

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
public class RicercaComponentiImpiegato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 565L;
	
	// Session bean i cui metodi sono utilizzati nel codice
			@EJB
			private ComponenteBeanRemote componenteremoto;
		
			private Long codiceComponente;
			private String termine;
			
			//COMPONENTE SELEZIONATO
			private ComponenteDTO componenteSelezionato;
			
			//LISTA COMPONENTI INVIATA ALLA PAGINA WEB
			private ArrayList<ComponenteDTO> componentiRicercati = new ArrayList<ComponenteDTO>();
			
			//COMPONENTE INVIATO ALLA PAGINA WEB
			private ComponenteDTO componenteRicercato;
			
	
			/**
			 * @return the codiceComponente
			 */
			public Long getCodiceComponente() {
				return codiceComponente;
			}
			/**
			 * @param codiceComponente the codiceComponente to set
			 */
			public void setCodiceComponente(Long codiceComponente) {
				this.codiceComponente = codiceComponente;
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
			 * @return the componenteSelezionato
			 */
			public ComponenteDTO getComponenteSelezionato() {
				return componenteSelezionato;
			}
			/**
			 * @param componenteSelezionato the componenteSelezionato to set
			 */
			public void setComponenteSelezionato(ComponenteDTO componenteSelezionato) {
				this.componenteSelezionato = componenteSelezionato;
			}
			/**
			 * @return the componentiRicercati
			 */
			public ArrayList<ComponenteDTO> getComponentiRicercati() {
				return componentiRicercati;
			}
			/**
			 * @param componentiRicercati the componentiRicercati to set
			 */
			public void setComponentiRicercati(ArrayList<ComponenteDTO> componentiRicercati) {
				this.componentiRicercati = componentiRicercati;
			}
			/**
			 * @return the componenteRicercato
			 */
			public ComponenteDTO getComponenteRicercato() {
				return componenteRicercato;
			}
			/**
			 * @param componenteRicercato the componenteRicercato to set
			 */
			public void setComponenteRicercato(ComponenteDTO componenteRicercato) {
				this.componenteRicercato = componenteRicercato;
			}
			
			
			
			public String ricercaComponenteImpiegatoId(){
				
				FacesContext context = FacesContext.getCurrentInstance();
				HttpServletRequest request = (HttpServletRequest) context
						.getExternalContext().getRequest();
				
				try {
					
					componenteRicercato = componenteremoto.findByCodiceComponente(codiceComponente);

				}catch (EJBException e) {
				
				return null;

			}

			return "listaComponentiRicercaImpiegato";		
				}

			
			
			
			
			public String ricercaComponenteImpiegatoTermine(){
							
				FacesContext context = FacesContext.getCurrentInstance();
				HttpServletRequest request = (HttpServletRequest) context
						.getExternalContext().getRequest();
				
				try {
					
				componentiRicercati = componenteremoto.findByTermine(termine);

				}catch (EJBException e) {
				
				return null;

			}

			return "listaComponentiRicercaImpiegato";		
				}
			
				
			}
	