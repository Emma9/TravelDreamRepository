package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.ClienteBeanRemote;
import it.polimi.traveldream.entities.ClienteDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class MostraPacchettiPersonalizzatiBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ClienteBeanRemote clienteRemoto;
	
	private String email;
	
		//PACCHETTO PERSONALIZZATO SELEZIONATO
		private PacchettoPersonalizzatoDTO pacchettoPersonalizzatoSelezionato;
		
		//LISTA PACCHETTI PERSONALIZZATI INVIATA ALLA PAGINA WEB
		private ArrayList<PacchettoPersonalizzatoDTO> pacchettiPersonalizzatiRicercati = new ArrayList<PacchettoPersonalizzatoDTO>();

		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @return the pacchettoPersonalizzatoSelezionato
		 */
		public PacchettoPersonalizzatoDTO getPacchettoPersonalizzatoSelezionato() {
			return pacchettoPersonalizzatoSelezionato;
		}

		/**
		 * @param pacchettoPersonalizzatoSelezionato the pacchettoPersonalizzatoSelezionato to set
		 */
		public void setPacchettoPersonalizzatoSelezionato(
				PacchettoPersonalizzatoDTO pacchettoPersonalizzatoSelezionato) {
			this.pacchettoPersonalizzatoSelezionato = pacchettoPersonalizzatoSelezionato;
		}

		/**
		 * @return the pacchettiPersonalizzatiRicercati
		 */
		public ArrayList<PacchettoPersonalizzatoDTO> getPacchettiPersonalizzatiRicercati() {
			return pacchettiPersonalizzatiRicercati;
		}

		/**
		 * @param pacchettiPersonalizzatiRicercati the pacchettiPersonalizzatiRicercati to set
		 */
		public void setPacchettiPersonalizzatiRicercati(
				ArrayList<PacchettoPersonalizzatoDTO> pacchettiPersonalizzatiRicercati) {
			this.pacchettiPersonalizzatiRicercati = pacchettiPersonalizzatiRicercati;
		}
		
		
		public String mostraPacchettiPersonalizzati(){
			
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context
					.getExternalContext().getRequest();

			Long idCliente = clienteRemoto.daEmailAId(email);
			
			pacchettiPersonalizzatiRicercati = clienteRemoto.elencoPacchettiCliente(idCliente);
			
			return "listaPacchettiPersonalizzatiCliente";
			
		}

}
