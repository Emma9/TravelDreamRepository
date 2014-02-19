package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.ejb.client.PacchettoPersonalizzatoBeanRemote;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;

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
		@EJB
		private PacchettoPersonalizzatoBeanRemote pacchettoPersRemoto;
		
		
		private String emailMittente;
		private String emailDestinatario;
		private Long idPacchettoPersonalizzato;
		private PacchettoPersonalizzatoDTO pacchettoPersonalizzato;
		
		
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
		public Long getIdPacchettoPersonalizzato() {
			return idPacchettoPersonalizzato;
		}
		/**
		 * @param idPacchettoPersonalizzato the idPacchettoPersonalizzato to set
		 */
		public void setIdPacchettoPersonalizzato(Long idPacchettoPersonalizzato) {
			this.idPacchettoPersonalizzato = idPacchettoPersonalizzato;
		}
	
	
	/**
		 * @return the pacchettoPersonalizzato
		 */
		public PacchettoPersonalizzatoDTO getPacchettoPersonalizzato() {
			return pacchettoPersonalizzato;
		}
		/**
		 * @param pacchettoPersonalizzato the pacchettoPersonalizzato to set
		 */
		public void setPacchettoPersonalizzato(
				PacchettoPersonalizzatoDTO pacchettoPersonalizzato) {
			this.pacchettoPersonalizzato = pacchettoPersonalizzato;
		}
	public String invitaAmico(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		try {
			
			
			emailMittente=request.getUserPrincipal().getName();
			setPacchettoPersonalizzato(pacchettoPersRemoto.findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato));
			
			Date dataCorrente = new Date();
			
			invitoremoto.createInvito(emailMittente, emailDestinatario, pacchettoPersonalizzato, dataCorrente, false);

		}catch (EJBException e) {
		
		return null;

	}

	return "gestioneInviti";		
		}
	
	
	
	public String mostraPropostaPacchettoViaggio(){
		

		setPacchettoPersonalizzato(pacchettoPersRemoto.findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato));
		
		for(int i=0; i< pacchettoPersonalizzato.getInvitiPacchetto().size();i++){
			if(pacchettoPersonalizzato.getInvitiPacchetto().get(i).getEmailDestinatario().equals(emailDestinatario)){
				return "/visualizzarePropostaViaggio";
				
			}
			
		}
		
		
		return "/formAccessoPropostaViaggioAmico";
		
		
		
	}
	
		
	}




