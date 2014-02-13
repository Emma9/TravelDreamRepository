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
			
			//LISTA COMPONENTI PER ID INVIATA ALLA PAGINA WEB
			private ArrayList<ComponenteDTO> componentiRicercatiID = new ArrayList<ComponenteDTO>();
			
			//LISTA COMPONENTI PER TERMINE INVIATA ALLA PAGINA WEB
			private ArrayList<ComponenteDTO> componentiRicercatiT = new ArrayList<ComponenteDTO>();
			
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
			 * @return the componentiRicercatiID
			 */
			public ArrayList<ComponenteDTO> getComponentiRicercatiID() {
				return componentiRicercatiID;
			}
			/**
			 * @param componentiRicercatiID the componentiRicercatiID to set
			 */
			public void setComponentiRicercatiID(
					ArrayList<ComponenteDTO> componentiRicercatiID) {
				this.componentiRicercatiID = componentiRicercatiID;
			}
			/**
			 * @return the componentiRicercatiT
			 */
			public ArrayList<ComponenteDTO> getComponentiRicercatiT() {
				return componentiRicercatiT;
			}
			/**
			 * @param componentiRicercatiT the componentiRicercatiT to set
			 */
			public void setComponentiRicercatiT(
					ArrayList<ComponenteDTO> componentiRicercatiT) {
				this.componentiRicercatiT = componentiRicercatiT;
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
			setComponentiRicercatiID(lista);

		} catch (EJBException e) {

		}

	}

	public void ricercaComponenteImpiegatoTermine() {

		try {

			
			System.out.println("RAMO METODO");
			
			ArrayList<ComponenteDTO> lista2 = new ArrayList<ComponenteDTO>();
			
			
			for (int i = 0; i < componenteremoto.findByTermine(getTermine())
					.size(); i++) {
				lista2.add(componenteremoto.findByTermine(getTermine()).get(i));
			}

		
			setComponentiRicercatiT(lista2);
			// setComponentiRicercati(componenteremoto.findByTermine(termine));

			System.out.println("ESEGUITO SET LISTA");
			
		} catch (EJBException e) {
			
			System.out.println("EJBException RAMO CATCH");

		}

	}

	public String dettagliComponenteSelezionato() {

		int id = componenteSelezionato.getCodiceComponente();

		return "dettagliComponenteSelezionato?faces-redirect=true&cComponente"
				+ id;

	}

}