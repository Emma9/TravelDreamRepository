package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.ejb.client.PacchettoPersonalizzatoBeanRemote;
import it.polimi.traveldream.ejb.client.UsrMgr;
import it.polimi.traveldream.entities.AmicoDTO;
import it.polimi.traveldream.entities.InvitoDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;
import it.polimi.traveldream.entities.UserDTO;

import java.io.Serializable;
import java.util.ArrayList;
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

	private ArrayList<InvitoDTO> invitiPacchetto = new ArrayList<InvitoDTO>();

	private String statoInvito;

	private String mailDest;

	/**
	 * @return the mittente
	 */
	public UserDTO getMittente() {
		return mittente;
	}

	/**
	 * @param mittente
	 *            the mittente to set
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
	 * @param destinatario
	 *            the destinatario to set
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

	/**
	 * @return the invitiPacchetto
	 */
	public ArrayList<InvitoDTO> getInvitiPacchetto() {
		return invitiPacchetto;
	}

	/**
	 * @param invitiPacchetto
	 *            the invitiPacchetto to set
	 */
	public void setInvitiPacchetto(ArrayList<InvitoDTO> invitiPacchetto) {
		this.invitiPacchetto = invitiPacchetto;
	}

	/**
	 * @return the statoInvito
	 */
	public String getStatoInvito() {
		return statoInvito;
	}

	/**
	 * @param statoInvito
	 *            the statoInvito to set
	 */
	public void setStatoInvito(String statoInvito) {
		this.statoInvito = statoInvito;
	}

	/**
	 * @return the mailDest
	 */
	public String getMailDest() {
		return mailDest;
	}

	/**
	 * @param mailDest
	 *            the mailDest to set
	 */
	public void setMailDest(String mailDest) {
		this.mailDest = mailDest;
	}

	public String gestioneInviti(Long idpp) {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			setIdPacchettoPersonalizzato(idpp);

			invitiPacchetto.clear();

			setMittente(usermgr.getUserDTO());

			ArrayList<InvitoDTO> invitiCliente = invitoremoto
					.findByMittente(mittente);

			for (int i = 0; i < invitiCliente.size(); i++) {

				if (idpp.equals(invitiCliente.get(i)
						.getPacchettoPersonalizzato()
						.getIdPacchettoPersonalizzato())) {

					invitiPacchetto.add(invitiCliente.get(i));

				}

			}

			return "gestioneInviti";

		} catch (NullPointerException n) {

			context.addMessage(null, new FacesMessage("Operazione fallita"));

			return "listaPacchettiPersonalizzatiCliente";

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita"));

			return "listaPacchettiPersonalizzatiCliente";

		}

	}

	public String formInvitaAmico() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			return "formInvitoAmico";

		} catch (NullPointerException n) {

			context.addMessage(null, new FacesMessage("Operazione fallita"));

			return "listaPacchettiPersonalizzatiCliente";

		}

	}

	public String invitaAmico() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			setPacchettoPersonalizzato(pacchettoPersRemoto
					.findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato));

			System.out.println("INVITA AMICO --> METODO");

			AmicoDTO dest = new AmicoDTO();

			dest.setEmail(mailDest);

			setDestinatario(dest);

			Date dataCorrente = new Date();

			invitoremoto.createInvito(mittente, destinatario,
					pacchettoPersonalizzato, dataCorrente, false);

			context.addMessage(null, new FacesMessage("Invito inviato "));

			return "index";

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita "));

			return "listaPacchettiPersonalizzatiCliente";

		}

	}

	public String mostraPropostaPacchettoViaggio() {

		setPacchettoPersonalizzato(pacchettoPersRemoto
				.findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato));

		/*
		 * 
		 * for (int i = 0; i <
		 * pacchettoPersonalizzato.getInvitiPacchetto().size(); i++) { if
		 * (pacchettoPersonalizzato.getInvitiPacchetto().get(i)
		 * .getDestinatario().equals(destinatario)) {
		 * 
		 * return "/visualizzarePropostaViaggio";
		 * 
		 * }
		 * 
		 * }
		 */
		return "/formAccessoPropostaViaggioAmico";

	}

	public String mostraPropostaPacchettoRegalo() {

		setPacchettoPersonalizzato(pacchettoPersRemoto
				.findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato));
		/*
		 * for (int i = 0; i <
		 * pacchettoPersonalizzato.getInvitiPacchetto().size(); i++) {
		 * 
		 * if (pacchettoPersonalizzato.getInvitiPacchetto().get(i)
		 * .getDestinatario().equals(destinatario)) {
		 * 
		 * return "/visualizzarePropostaViaggio";
		 * 
		 * }
		 * 
		 * }
		 */

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

	public String accettaPropostaPacchettoViaggio() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			return "homepage";

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita "));

			return "homepage";

		}

	}

	public String regalaComponente() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			return "homepage";

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita "));

			return "homepage";

		}

	}

}
