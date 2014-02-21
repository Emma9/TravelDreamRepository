package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.ejb.client.PacchettoPersonalizzatoBeanRemote;
import it.polimi.traveldream.ejb.client.UsrMgr;
import it.polimi.traveldream.entities.AmicoDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;
import it.polimi.traveldream.entities.UserDTO;

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
	@EJB
	private UsrMgr usermgr;
	
	private UserDTO mittente;
	private AmicoDTO destinatario;

	private Long idPacchettoPersonalizzato;
	private PacchettoPersonalizzatoDTO pacchettoPersonalizzato;

	
	/**
	 * @return the mittente
	 */
	public UserDTO getMittente() {
		return mittente;
	}

	/**
	 * @param mittente the mittente to set
	 */
	public void setMittente(UserDTO mittente) {
		this.mittente = mittente;
	}

	/**
	 * @return the destinatario
	 */
	public AmicoDTO getDestinatario() {
		return destinatario;
	}

	/**
	 * @param destinatario the destinatario to set
	 */
	public void setDestinatario(AmicoDTO destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * @return the idPacchettoPersonalizzato
	 */
	public Long getIdPacchettoPersonalizzato() {
		return idPacchettoPersonalizzato;
	}

	/**
	 * @param idPacchettoPersonalizzato
	 *            the idPacchettoPersonalizzato to set
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
	 * @param pacchettoPersonalizzato
	 *            the pacchettoPersonalizzato to set
	 */
	public void setPacchettoPersonalizzato(
			PacchettoPersonalizzatoDTO pacchettoPersonalizzato) {
		this.pacchettoPersonalizzato = pacchettoPersonalizzato;
	}

	public String invitaAmico() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			mittente=usermgr.getUserDTO();
			setPacchettoPersonalizzato(pacchettoPersRemoto.findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato));

			Date dataCorrente = new Date();

			invitoremoto.createInvito(mittente, destinatario, pacchettoPersonalizzato, dataCorrente, false);

		} catch (EJBException e) {

			return null;

		}

		return "gestioneInviti";
	}

	public String mostraPropostaPacchettoViaggio() {

setPacchettoPersonalizzato(pacchettoPersRemoto.findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato));
		
		for(int i=0; i< pacchettoPersonalizzato.getInvitiPacchetto().size();i++){
			if(pacchettoPersonalizzato.getInvitiPacchetto().get(i).getDestinatario().equals(destinatario)){

				return "/visualizzarePropostaViaggio";

			}

		}

		return "/formAccessoPropostaViaggioAmico";

	}

	public String mostraPropostaPacchettoRegalo() {

		setPacchettoPersonalizzato(pacchettoPersRemoto
				.findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato));

		for (int i = 0; i < pacchettoPersonalizzato.getInvitiPacchetto().size(); i++) {
			if (pacchettoPersonalizzato.getInvitiPacchetto().get(i)
					.getDestinatario().equals(destinatario)) {
				return "/visualizzarePropostaViaggio";

			}

		}

		return "/formAccessoPropostaRegaloAmico";

	}

	public String rifiutaPropostaPacchettoViaggio() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			context.addMessage(null, new FacesMessage("Proposta rifiutata"));

			return "homepage";

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita "));

			return "homepage";

		}
	}

	public String rifiutaPropostaPacchettoRegalo() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			context.addMessage(null, new FacesMessage("Proposta rifiutata"));

			return "homepage";

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita "));

			return "homepage";

		}

	}

}
