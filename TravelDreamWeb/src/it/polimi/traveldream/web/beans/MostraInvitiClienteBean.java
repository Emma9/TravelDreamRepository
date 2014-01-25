package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.entities.InvitoDTO;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class MostraInvitiClienteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private InvitoBeanRemote invitoRemoto;
	
	private String email;
	
			//INVITO SELEZIONATO
			private InvitoDTO invitoSelezionato;
			
			//LISTA INVITI INVIATA ALLA PAGINA WEB
			private ArrayList<InvitoDTO> invitiRicercati = new ArrayList<InvitoDTO>();

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
			 * @return the invitoSelezionato
			 */
			public InvitoDTO getInvitoSelezionato() {
				return invitoSelezionato;
			}

			/**
			 * @param invitoSelezionato the invitoSelezionato to set
			 */
			public void setInvitoSelezionato(InvitoDTO invitoSelezionato) {
				this.invitoSelezionato = invitoSelezionato;
			}

			/**
			 * @return the invitiRicercati
			 */
			public ArrayList<InvitoDTO> getInvitiRicercati() {
				return invitiRicercati;
			}

			/**
			 * @param invitiRicercati the invitiRicercati to set
			 */
			public void setInvitiRicercati(ArrayList<InvitoDTO> invitiRicercati) {
				this.invitiRicercati = invitiRicercati;
			}
			
			
			
			public String mostraInvitiCliente(){
				
				FacesContext context = FacesContext.getCurrentInstance();
				HttpServletRequest request = (HttpServletRequest) context
						.getExternalContext().getRequest();
		
				invitiRicercati = invitoRemoto.findByEmailMittente(this.email);
				
				return "gestioneInviti";
				
			}

}
