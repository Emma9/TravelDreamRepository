package it.polimi.traveldream.web.beans;

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
		private static final long serialVersionUID = 5997L;
		
		@EJB
		private PacchettoBeanRemote pacchettoRemoto;
		
		private String destinazione;
		private String descrizione;
		private int sconto;
		private Date dataInizioValidita;
		private Date dataFineValidita;
		private String etichetta;
		
		
		
		
		
		/**
		 * @return the destinazione
		 */
		public String getDestinazione() {
			return destinazione;
		}





		/**
		 * @param destinazione the destinazione to set
		 */
		public void setDestinazione(String destinazione) {
			this.destinazione = destinazione;
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
		 * @param etichetta the etichetta to set
		 */
		public void setEtichetta(String etichetta) {
			this.etichetta = etichetta;
		}





		public String creazionePacchetto(){
			
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context
					.getExternalContext().getRequest();
			
			try {
				
				//COME INSERISCO LA LISTA COMPONENTI & LA LISTA COMPONENTI SELEZIONATI ????????
				
				//pacchettoRemoto.createPacchetto(destinazione, dataInizioValidita, dataFineValidita, etichetta, descrizione, listaComponenti, listaComponentiSelezionati, sconto);
				

			}catch (EJBException e) {
			
				context.addMessage(null, new FacesMessage("Creazione pacchetto fallita"));
				
				return "homePageImpiegato";

		}

			context.addMessage(null, new FacesMessage("Creazione pacchetto riuscita"));
			
			return "homePageImpiegato";	
			
			
		}
		
		

	}
