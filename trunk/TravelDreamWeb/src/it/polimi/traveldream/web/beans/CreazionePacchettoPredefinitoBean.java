package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.ejb.client.PacchettoBeanRemote;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class CreazionePacchettoPredefinitoBean implements Serializable {

	
		/**
		 * 
		 */
		private static final long serialVersionUID = 599L;
		
		@EJB
		private PacchettoBeanRemote pacchettoRemoto;
		
		private String destinazione;
		private String descrizione;
		private int sconto;
		private Date dataInizioValidita;
		private Date dataFineValidita;
		private String etichetta;
		
		
		
		
		
		public String creazionePacchetto(){
			
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context
					.getExternalContext().getRequest();
			
			try {
				
				//pacchettoRemoto.createPacchetto(destinazione, dataInizioValidita, dataFineValidita, etichetta, descrizione, listaComponenti, listaComponentiSelezionati, sconto);
				

			}catch (EJBException e) {
			
				context.addMessage(null, new FacesMessage("Creazione pacchetto fallita"));
				
				return "homePageImpiegato";

		}

			context.addMessage(null, new FacesMessage("Creazione pacchetto riuscita"));
			
			return "homePageImpiegato";	
			
			
		}
		
		

	}
