package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.ejb.client.PacchettoPersonalizzatoBeanRemote;
import it.polimi.traveldream.ejb.client.UsrMgr;
import it.polimi.traveldream.entities.AmicoDTO;
import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.InvitoDTO;
import it.polimi.traveldream.entities.PacchettoPKDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;
import it.polimi.traveldream.entities.UserDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	private ComponenteDTO componenteSelezionato;

	private ArrayList<InvitoDTO> invitiPacchetto = new ArrayList<InvitoDTO>();

	private String statoInvito;

	private String mailDest;

	private InvitoDTO invitoViaggio;

	private InvitoDTO invitoRegalo;

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
	 * @return the componenteSelezionato
	 */
	public ComponenteDTO getComponenteSelezionato() {
		return componenteSelezionato;
	}

	/**
	 * @param componenteSelezionato
	 *            the componenteSelezionato to set
	 */
	public void setComponenteSelezionato(ComponenteDTO componenteSelezionato) {
		this.componenteSelezionato = componenteSelezionato;
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

		System.out.println("SETMAILDEST " + mailDest);
	}

	/**
	 * @return the invitoViaggio
	 */
	public InvitoDTO getInvitoViaggio() {
		return invitoViaggio;
	}

	/**
	 * @param invitoViaggio
	 *            the invitoViaggio to set
	 */
	public void setInvitoViaggio(InvitoDTO invitoViaggio) {
		this.invitoViaggio = invitoViaggio;
	}

	/**
	 * @return the invitoRegalo
	 */
	public InvitoDTO getInvitoRegalo() {
		return invitoRegalo;
	}

	/**
	 * @param invitoRegalo
	 *            the invitoRegalo to set
	 */
	public void setInvitoRegalo(InvitoDTO invitoRegalo) {
		this.invitoRegalo = invitoRegalo;
	}

	public String gestioneInviti(Long idpp) {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			setIdPacchettoPersonalizzato(idpp);

			invitiPacchetto.clear();

			String emailmit = usermgr.getUserDTO().getEmail();

			// setMittente(usermgr.getUserDTO());

			ArrayList<InvitoDTO> invitiCliente = invitoremoto
					.findByEmailMittente(emailmit);

			for (int i = 0; i < invitiCliente.size(); i++) {

				if (idpp.equals(invitiCliente.get(i)
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

			System.out.println("INVITA AMICO --> METODO " + mailDest);

			setPacchettoPersonalizzato(pacchettoPersRemoto
					.findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato));

			System.out
					.println("INVITA AMICO --> PACCHETTOPERSONALIZZATO TROVATO");

			Date dataCorrente = new Date();

			System.out.println("INVITA AMICO --> DATA");

			String emailmitt = usermgr.getUserDTO().getEmail();

			System.out.println("INVITA AMICO --> EMAILMITTENTE " + emailmitt);

			invitoremoto.createInvito(emailmitt, mailDest,
					idPacchettoPersonalizzato, dataCorrente, false);

			System.out.println("INVITA AMICO --> INVITO CREATO");

			context.addMessage(null, new FacesMessage("Invito inviato "));

			return "index";

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita "));

			return "listaPacchettiPersonalizzatiCliente";

		}

	}

	public String mostraPropostaPacchettoViaggio() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			System.out.println("IDPP  " + idPacchettoPersonalizzato);

			setPacchettoPersonalizzato(pacchettoPersRemoto
					.findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato));

			if (!(pacchettoPersonalizzato.getStato()
					.equalsIgnoreCase("giftlist"))) {

				ArrayList<InvitoDTO> inviti = invitoremoto.findAll();

				for (int i = 0; i < inviti.size(); i++) {

					if ((inviti.get(i).getIdPacchettoPersonalizzato()
							.equals(idPacchettoPersonalizzato))
							&& (inviti.get(i).getEmailDestinatario()
									.equalsIgnoreCase(mailDest))) {

						if (inviti.get(i).getStato() == false) {

							setInvitoViaggio(inviti.get(i));

							return "/dettagliInvitoViaggio";

						} else {

							context.addMessage(null, new FacesMessage(
									" Invito gia utilizzato"));

							return "homepage";

						}

					}

				}

				context.addMessage(null, new FacesMessage("Invito non trovato"));

				return "homepage";

			} else {

				context.addMessage(null, new FacesMessage(
						"Errore stato pacchetto"));

				return "homepage";

			}

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita "));

			return "homepage";

		}

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

	public String accettaPropostaPacchettoViaggio() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			return "/user/dettagliPacchettoInvitoViaggio";

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita "));

			return "homepage";

		}

	}

	public String creaPacchettoAmico() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			UserDTO cl = usermgr.getUserDTO();

			Date datap = pacchettoPersonalizzato.getDataDiPartenza();

			Date datar = pacchettoPersonalizzato.getDataDiRitorno();

			List<ComponenteDTO> list = pacchettoPersonalizzato
					.getListaComponentiSelezionati();

			Long idpp = pacchettoPersonalizzato.getIdPacchetto();

			pacchettoPersRemoto.createPacchettoPersonalizzato("salvato", cl,
					datap, datar, 1, list, new PacchettoPKDTO(idpp, (long) 0));

			Long idin = invitoViaggio.getIdInvito();

			String mi = invitoViaggio.getEmailMittente();

			String de = invitoViaggio.getEmailDestinatario();

			Long idpa = invitoViaggio.getIdPacchettoPersonalizzato();

			Date datainv = invitoViaggio.getData();

			invitoremoto.updateInvito(idin, mi, de, idpa, datainv, true);

			context.addMessage(null, new FacesMessage("Invito accettato"));
			
			return "index";

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita "));

			return "homepage";

		}

	}

	public String mostraPropostaPacchettoRegalo() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			System.out.println("IDPP  " + idPacchettoPersonalizzato);

			setPacchettoPersonalizzato(pacchettoPersRemoto
					.findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato));

			if (pacchettoPersonalizzato.getStato().equalsIgnoreCase("giftlist")) {

				ArrayList<InvitoDTO> inviti = invitoremoto.findAll();

				for (int i = 0; i < inviti.size(); i++) {

					if ((inviti.get(i).getIdPacchettoPersonalizzato()
							.equals(idPacchettoPersonalizzato))
							&& (inviti.get(i).getEmailDestinatario()
									.equalsIgnoreCase(mailDest))) {

						if (inviti.get(i).getStato() == false) {

							setInvitoRegalo(inviti.get(i));

							return "/dettagliInvitoRegalo";

						} else {

							context.addMessage(null, new FacesMessage(
									" Invito gia utilizzato"));

							return "homepage";

						}

					}

				}

				context.addMessage(null, new FacesMessage("Invito non trovato"));

				return "homepage";

			} else {

				context.addMessage(null, new FacesMessage(
						"Errore stato pacchetto"));

				return "homepage";

			}

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

	public String regala() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			return "/user/dettagliPacchettoInvitoRegalo";

			
		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita "));

			return "homepage";

		}

	}

	public String confermaRegalo() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		System.out.println("CONFERMA REGALO --> INIZIO");
		

		try {
			
			System.out.println("CONFERMA REGALO --> TRY");

			int cc = componenteSelezionato.getCodiceComponente();
			
			System.out.println("CODICE COMPONENTE SELEZIONATO --> "+cc);

			// AGGIORNAMENTO STATO INVITO

			Long idin = invitoRegalo.getIdInvito();

			String mi = invitoRegalo.getEmailMittente();

			String de = invitoRegalo.getEmailDestinatario();

			Long idpa = invitoRegalo.getIdPacchettoPersonalizzato();

			Date datainv = invitoRegalo.getData();

			invitoremoto.updateInvito(idin, mi, de, idpa, datainv, true);
			
			System.out.println("CONFERMA REGALO --> INVITO AGGIORNATO");

			// VERIFICA SE 3 INVITI UTILIZZATI

			ArrayList<InvitoDTO> inviti = invitoremoto.findAll();

			ArrayList<InvitoDTO> invitiRegaloPacchetto = new ArrayList<InvitoDTO>();

			for (int i = 0; i < inviti.size(); i++) {

				if ((inviti.get(i).getIdPacchettoPersonalizzato().equals(idpa))
						&& (inviti.get(i).getEmailMittente()
								.equalsIgnoreCase(mi))
						&& (inviti.get(i).getStato() == true)) {

					invitiRegaloPacchetto.add(inviti.get(i));

				}

			}
			
			System.out.println("CONFERMA REGALO --> VERIFICA INVITI");

			// PACCHETTOPERSONALIZZATO --> STATO CONFERMATO

			if (invitiRegaloPacchetto.size() == 3) {
				
				System.out.println("CONFERMA REGALO --> MODIFICA PACCHETTO");

				UserDTO cl = pacchettoPersonalizzato.getCliente();

				Date datap = pacchettoPersonalizzato.getDataDiPartenza();

				Date datar = pacchettoPersonalizzato.getDataDiRitorno();

				List<ComponenteDTO> list = pacchettoPersonalizzato
						.getListaComponentiSelezionati();

				int nump = pacchettoPersonalizzato.getNumPartecipanti();

				Long idp = pacchettoPersonalizzato.getIdPacchetto();

				Long idpp = pacchettoPersonalizzato
						.getIdPacchettoPersonalizzato();

				PacchettoPKDTO pacchettoPK = new PacchettoPKDTO(idp, idpp);

				pacchettoPersRemoto.updatePacchettoPersonalizzato(pacchettoPK,
						cl, "confermato", datap, datar, nump, list);
				
				System.out.println("CONFERMA REGALO --> UPDATE PACCHETTO");

			}

			// RETURN

			context.addMessage(null, new FacesMessage("Regalo effettuato"));

			return "index";

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita "));

			return "homepage";

		} catch (NullPointerException n) {

			System.out.println("NULLPOINTEREXCEPTION");
			
			
			return null;

		}

	}

}
