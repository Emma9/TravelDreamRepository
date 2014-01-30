package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.InvitoBeanRemote;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class InvitaAmicoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 335L;
	
	// Session bean i cui metodi sono utilizzati nel codice
		@EJB
		private InvitoBeanRemote invitoremoto;
		
		private String emailMittente;
		private String emailDestinatario;
		private String idPacchettoPersonalizzato;
	
		
		/**
		 * @return the emailMittente
		 */
		public String getEmailMittente() {
			return emailMittente;
		}
		/**
		 * @param emailMittente the emailMittente to set
		 */
		public void setEmailMittente(String emailMittente) {
			this.emailMittente = emailMittente;
		}
		/**
		 * @return the emailDestinatario
		 */
		public String getEmailDestinatario() {
			return emailDestinatario;
		}
		/**
		 * @param emailDestinatario the emailDestinatario to set
		 */
		public void setEmailDestinatario(String emailDestinatario) {
			this.emailDestinatario = emailDestinatario;
		}
		/**
		 * @return the idPacchettoPersonalizzato
		 */
		public String getIdPacchettoPersonalizzato() {
			return idPacchettoPersonalizzato;
		}
		/**
		 * @param idPacchettoPersonalizzato the idPacchettoPersonalizzato to set
		 */
		public void setIdPacchettoPersonalizzato(String idPacchettoPersonalizzato) {
			this.idPacchettoPersonalizzato = idPacchettoPersonalizzato;
		}
	
	
	public String invitaAmico(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		try {
			
			Date dataCorrente = new Date();
			
			invitoremoto.createInvito(emailMittente, emailDestinatario, idPacchettoPersonalizzato, dataCorrente, false);

		}catch (EJBException e) {
		
		return null;

	}

	return "gestioneInviti";		
		}
		
	}


