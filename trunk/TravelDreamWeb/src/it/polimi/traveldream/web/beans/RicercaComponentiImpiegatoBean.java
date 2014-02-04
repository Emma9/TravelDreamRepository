package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean()
@SessionScoped
public class RicercaComponentiImpiegatoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 565L;
	
	// Session bean i cui metodi sono utilizzati nel codice
			@EJB
			private ComponenteBeanRemote componenteremoto;
		
			private int codiceComponente;
			private String termine;
			
			//COMPONENTE SELEZIONATO
			private ComponenteDTO componenteSelezionato;
			
			//LISTA COMPONENTI INVIATA ALLA PAGINA WEB
			private ArrayList<ComponenteDTO> componentiRicercati = new ArrayList<ComponenteDTO>();
			
			//COMPONENTE INVIATO ALLA PAGINA WEB
			//private ComponenteDTO componenteRicercato;
			
	
			/**
			 * @return the codiceComponente
			 */
			public int getCodiceComponente() {
				return codiceComponente;
			}
			/**
			 * @param codiceComponente the codiceComponente to set
			 */
			public void setCodiceComponente(int codiceComponente) {
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
			//public ComponenteDTO getComponenteRicercato() {
				//return componenteRicercato;
			//}
			/**
			 * @param componenteRicercato the componenteRicercato to set
			 */
			//public void setComponenteRicercato(ComponenteDTO componenteRicercato) {
				//this.componenteRicercato = componenteRicercato;
			//}
			
			
	public void ricercaComponenteImpiegatoId() {

		try {

			ArrayList<ComponenteDTO> lista = new ArrayList<ComponenteDTO>();
			lista.add(componenteremoto.findByCodiceComponente(codiceComponente));
			setComponentiRicercati(lista);

		} catch (EJBException e) {

		}

	}

	public void ricercaComponenteImpiegatoTermine() {

		try {

			ArrayList<ComponenteDTO> lista = new ArrayList<ComponenteDTO>();
			for (int i = 0; i < componenteremoto.findByTermine(getTermine())
					.size(); i++) {
				lista.add(componenteremoto.findByTermine(getTermine()).get(i));
			}

		
			setComponentiRicercati(lista);
			// setComponentiRicercati(componenteremoto.findByTermine(termine));

		} catch (EJBException e) {

		}

	}

	public String dettagliComponenteSelezionato() {

		int id = componenteSelezionato.getCodiceComponente();

		return "dettagliComponenteSelezionato?faces-redirect=true&cComponente"
				+ id;

	}

}
