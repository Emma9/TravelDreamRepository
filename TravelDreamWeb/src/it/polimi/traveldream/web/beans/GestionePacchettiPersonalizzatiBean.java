package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.PacchettoPersonalizzatoBeanRemote;
import it.polimi.traveldream.ejb.client.UserBeanRemote;
import it.polimi.traveldream.ejb.client.UsrMgr;
import it.polimi.traveldream.entities.ComponenteDTO;
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
public class GestionePacchettiPersonalizzatiBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 549L;

	@EJB
	private UserBeanRemote utenteRemoto;
	@EJB
	private UsrMgr usermanager;
	@EJB
	private PacchettoPersonalizzatoBeanRemote pacchettopersremote;

	private String email;

	private Long idPacchettoPersonalizzato;

	private String stringStato;

	private String statolista;

	// PACCHETTO PERSONALIZZATO SELEZIONATO
	private PacchettoPersonalizzatoDTO pacchettoPersonalizzatoSelezionato;

	// LISTA PACCHETTI PERSONALIZZATI INVIATA ALLA PAGINA WEB
	private ArrayList<PacchettoPersonalizzatoDTO> pacchettiPersonalizzatiCliente = new ArrayList<PacchettoPersonalizzatoDTO>();

	private List<ComponenteDTO> listaComponenti;

	private List<ComponenteDTO> listaComponentiSelezionati;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the stringStato
	 */
	public String getStringStato() {
		return stringStato;
	}

	/**
	 * @param stringStato
	 *            the stringStato to set
	 */
	public void setStringStato(String stringStato) {
		this.stringStato = stringStato;
	}

	/**
	 * @return the statolista
	 */
	public String getStatolista() {
		return statolista;
	}

	/**
	 * @param statolista
	 *            the statolista to set
	 */
	public void setStatolista(String statolista) {
		this.statolista = statolista;
	}

	/**
	 * @return the pacchettoPersonalizzatoSelezionato
	 */
	public PacchettoPersonalizzatoDTO getPacchettoPersonalizzatoSelezionato() {
		return pacchettoPersonalizzatoSelezionato;
	}

	/**
	 * @param pacchettoPersonalizzatoSelezionato
	 *            the pacchettoPersonalizzatoSelezionato to set
	 */
	public void setPacchettoPersonalizzatoSelezionato(
			PacchettoPersonalizzatoDTO pacchettoPersonalizzatoSelezionato) {
		this.pacchettoPersonalizzatoSelezionato = pacchettoPersonalizzatoSelezionato;
	}

	/**
	 * @return the pacchettiPersonalizzatiCliente
	 */
	public ArrayList<PacchettoPersonalizzatoDTO> getPacchettiPersonalizzatiCliente() {
		return pacchettiPersonalizzatiCliente;
	}

	/**
	 * @param pacchettiPersonalizzatiCliente
	 *            the pacchettiPersonalizzatiCliente to set
	 */
	public void setPacchettiPersonalizzatiCliente(
			ArrayList<PacchettoPersonalizzatoDTO> pacchettiPersonalizzatiCliente) {
		this.pacchettiPersonalizzatiCliente = pacchettiPersonalizzatiCliente;
	}

	/**
	 * @return the listaComponenti
	 */
	public List<ComponenteDTO> getListaComponenti() {
		return listaComponenti;
	}

	/**
	 * @param listaComponenti
	 *            the listaComponenti to set
	 */
	public void setListaComponenti(List<ComponenteDTO> listaComponenti) {
		this.listaComponenti = listaComponenti;
	}

	/**
	 * @return the listaComponentiSelezionati
	 */
	public List<ComponenteDTO> getListaComponentiSelezionati() {
		return listaComponentiSelezionati;
	}

	/**
	 * @param listaComponentiSelezionati
	 *            the listaComponentiSelezionati to set
	 */
	public void setListaComponentiSelezionati(
			List<ComponenteDTO> listaComponentiSelezionati) {
		this.listaComponentiSelezionati = listaComponentiSelezionati;
	}

	public String mostraPacchettiPersonalizzati() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			ArrayList<PacchettoPersonalizzatoDTO> pacchettiPersonalizzati = new ArrayList<PacchettoPersonalizzatoDTO>();
			pacchettiPersonalizzati = pacchettopersremote
					.findByEmailCliente(usermanager.getPrincipalEmail());
			setPacchettiPersonalizzatiCliente(pacchettiPersonalizzati);

		} catch (EJBException e) {

			return "listaPacchettiPersonalizzatiCliente";

		}
		return "listaPacchettiPersonalizzatiCliente";

	}

	public String dettagliPacchettoSelezionato() {

		try {

			System.out.println("DETTAGLI --> METODO");

			System.out.println("DETTAGLI --> IDP "
					+ pacchettoPersonalizzatoSelezionato
							.getIdPacchettoPersonalizzato());

			setStringStato(pacchettoPersonalizzatoSelezionato.getStato());

			System.out.println("VALORE STATO " + stringStato);

			return "dettagliPacchettoPersonalizzatoSalvato";

		} catch (NullPointerException n) {

			System.out.println("DETTAGLI --> NULLPOINTEREXCEPTION");

			return null;

		}

		catch (EJBException e) {

			System.out.println("DETTAGLI --> EJBEXCEPTION");

			return null;

		}

	}

	public String modificaPacchetto() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			setStatolista(pacchettoPersonalizzatoSelezionato.getStato());

			if (!((statolista.equalsIgnoreCase("bloccato"))
					|| (statolista.equalsIgnoreCase("confermato")) || (statolista
						.equalsIgnoreCase("giftlist")))) {

				PacchettoPKDTO pacchettoPK = new PacchettoPKDTO(
						pacchettoPersonalizzatoSelezionato.getIdPacchetto(),
						pacchettoPersonalizzatoSelezionato
								.getIdPacchettoPersonalizzato());

				UserDTO cli = pacchettoPersonalizzatoSelezionato.getCliente();

				Date datap = pacchettoPersonalizzatoSelezionato
						.getDataDiPartenza();

				Date datar = pacchettoPersonalizzatoSelezionato
						.getDataDiRitorno();

				int nump = pacchettoPersonalizzatoSelezionato
						.getNumPartecipanti();

				Long idp = pacchettopersremote.updatePacchettoPersonalizzato(
						pacchettoPK, cli, stringStato, datap, datar, nump,
						listaComponentiSelezionati);

				if (idp > 0) {

					context.addMessage(null, new FacesMessage(
							"Pacchetto modificato"));

					return "index";

				} else {

					context.addMessage(null, new FacesMessage(
							"Operazione fallita"));

					return "listaPacchettiPersonalizzatiCliente";

				}
			} else {

				context.addMessage(null, new FacesMessage(
						"Impossibile modificare il pacchetto"));

				return "listaPacchettiPersonalizzatiCliente";

			}

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita"));

			return "listaPacchettiPersonalizzatiCliente";

		}

	}

	public String rimuoviPacchettoSelezionato() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			setStatolista(pacchettoPersonalizzatoSelezionato.getStato());

			if (!(statolista.equalsIgnoreCase("confermato"))) {

				pacchettopersremote.removePacchettoPersonalizzato(
						pacchettoPersonalizzatoSelezionato
								.getIdPacchettoPersonalizzato(),
						pacchettoPersonalizzatoSelezionato.getIdPacchetto());

				context.addMessage(null, new FacesMessage("Pacchetto rimosso"));

				return "index";

			} else {

				context.addMessage(null, new FacesMessage(
						"Impossibile rimuovere pacchetto confermato"));

				return "listaPacchettiPersonalizzatiCliente";

			}

		} catch (NullPointerException n) {

			context.addMessage(null, new FacesMessage("Operazione fallita"));

			return "index";

		}

	}

	public String aggiungiAllaGiftlist() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			setStatolista(pacchettoPersonalizzatoSelezionato.getStato());

			if (!((statolista.equalsIgnoreCase("bloccato"))
					|| (statolista.equalsIgnoreCase("confermato")) || (statolista
						.equalsIgnoreCase("giftlist")))) {

				PacchettoPKDTO pacchettoPK = new PacchettoPKDTO(
						pacchettoPersonalizzatoSelezionato.getIdPacchetto(),
						pacchettoPersonalizzatoSelezionato
								.getIdPacchettoPersonalizzato());

				UserDTO cli = pacchettoPersonalizzatoSelezionato.getCliente();

				Date datap = pacchettoPersonalizzatoSelezionato
						.getDataDiPartenza();

				Date datar = pacchettoPersonalizzatoSelezionato
						.getDataDiRitorno();

				int nump = pacchettoPersonalizzatoSelezionato
						.getNumPartecipanti();

				List<ComponenteDTO> listacs = pacchettoPersonalizzatoSelezionato
						.getListaComponentiSelezionati();

				pacchettopersremote.updatePacchettoPersonalizzato(pacchettoPK,
						cli, "giftlist", datap, datar, nump, listacs);

				context.addMessage(null, new FacesMessage(
						"Pacchetto inserito in gift list"));

				return "index";

			} else {

				context.addMessage(null, new FacesMessage(
						"Impossibile inserire il pacchetto in giftlist"));

				return "listaPacchettiPersonalizzatiCliente";
			}

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita"));

			return "listaPacchettiPersonalizzatiCliente";

		}

	}

	public String rimuoviDallaGiftlist() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			if (pacchettoPersonalizzatoSelezionato.getStato().equalsIgnoreCase(
					"giftlist")) {

				PacchettoPKDTO pacchettoPK = new PacchettoPKDTO(
						pacchettoPersonalizzatoSelezionato.getIdPacchetto(),
						pacchettoPersonalizzatoSelezionato
								.getIdPacchettoPersonalizzato());

				UserDTO cli = pacchettoPersonalizzatoSelezionato.getCliente();

				Date datap = pacchettoPersonalizzatoSelezionato
						.getDataDiPartenza();

				Date datar = pacchettoPersonalizzatoSelezionato
						.getDataDiRitorno();

				int nump = pacchettoPersonalizzatoSelezionato
						.getNumPartecipanti();

				List<ComponenteDTO> listacs = pacchettoPersonalizzatoSelezionato
						.getListaComponentiSelezionati();

				pacchettopersremote.updatePacchettoPersonalizzato(pacchettoPK,
						cli, "salvato", datap, datar, nump, listacs);

				context.addMessage(null, new FacesMessage(
						"Pacchetto rimosso dalla gift list"));

				return "listaPacchettiPersonalizzatiCliente";

			} else {

				context.addMessage(null, new FacesMessage(
						"Pacchetto non presente in gift list"));

				return "listaPacchettiPersonalizzatiCliente";
			}

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita"));

			return "listaPacchettiPersonalizzatiCliente";

		}

	}

	public String bloccaPacchetto() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			setStatolista(pacchettoPersonalizzatoSelezionato.getStato());

			if (!((statolista.equalsIgnoreCase("bloccato"))
					|| (statolista.equalsIgnoreCase("confermato")) || (statolista
						.equalsIgnoreCase("giftlist")))) {

				PacchettoPKDTO pacchettoPK = new PacchettoPKDTO(
						pacchettoPersonalizzatoSelezionato.getIdPacchetto(),
						pacchettoPersonalizzatoSelezionato
								.getIdPacchettoPersonalizzato());

				UserDTO cli = pacchettoPersonalizzatoSelezionato.getCliente();

				Date datap = pacchettoPersonalizzatoSelezionato
						.getDataDiPartenza();

				Date datar = pacchettoPersonalizzatoSelezionato
						.getDataDiRitorno();

				int nump = pacchettoPersonalizzatoSelezionato
						.getNumPartecipanti();

				List<ComponenteDTO> listacs = pacchettoPersonalizzatoSelezionato
						.getListaComponentiSelezionati();

				pacchettopersremote.updatePacchettoPersonalizzato(pacchettoPK,
						cli, "bloccato", datap, datar, nump, listacs);

				context.addMessage(null, new FacesMessage("Pacchetto bloccato"));

				return "index";

			} else {

				context.addMessage(null, new FacesMessage(
						"Impossibile bloccare pacchetto"));

				return "listaPacchettiPersonalizzatiCliente";
			}

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage("Operazione fallita"));

			return "listaPacchettiPersonalizzatiCliente";

		}

	}

	public String confermaViaggio() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {
			System.out.println("CONFERMA --> TRY");

			setStatolista(pacchettoPersonalizzatoSelezionato.getStato());

			if (!(statolista.equalsIgnoreCase("confermato"))) {

				System.out.println("CONFERMA --> IF");

				PacchettoPKDTO pacchettoPK = new PacchettoPKDTO(
						pacchettoPersonalizzatoSelezionato.getIdPacchetto(),
						pacchettoPersonalizzatoSelezionato
								.getIdPacchettoPersonalizzato());

				UserDTO cli = pacchettoPersonalizzatoSelezionato.getCliente();

				Date datap = pacchettoPersonalizzatoSelezionato
						.getDataDiPartenza();

				Date datar = pacchettoPersonalizzatoSelezionato
						.getDataDiRitorno();

				int nump = pacchettoPersonalizzatoSelezionato
						.getNumPartecipanti();

				List<ComponenteDTO> listacs = pacchettoPersonalizzatoSelezionato
						.getListaComponentiSelezionati();

				pacchettopersremote.updatePacchettoPersonalizzato(pacchettoPK,
						cli, "confermato", datap, datar, nump, listacs);

				context.addMessage(null, new FacesMessage("Viaggio confermato"));

				return "index";

			} else {

				System.out.println("CONFERMA --> ELSE");

				context.addMessage(null, new FacesMessage(
						"Viaggio gia confermato"));

				return "listaPacchettiPersonalizzatiCliente";
			}

		} catch (EJBException e) {

			System.out.println("CONFERMA --> EJBEXCEPTION");

			context.addMessage(null, new FacesMessage("Operazione fallita"));

			return "listaPacchettiPersonalizzatiCliente";

		}

	}

	/*
	 * public String gestioneInviti() {
	 * 
	 * FacesContext context = FacesContext.getCurrentInstance();
	 * HttpServletRequest request = (HttpServletRequest) context
	 * .getExternalContext().getRequest();
	 * 
	 * try {
	 * 
	 * return "gestioneInviti";
	 * 
	 * } catch (NullPointerException n) {
	 * 
	 * context.addMessage(null, new FacesMessage("Operazione fallita"));
	 * 
	 * return "listaPacchettiPersonalizzatiCliente";
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public String formInvitaAmico() {
	 * 
	 * FacesContext context = FacesContext.getCurrentInstance();
	 * HttpServletRequest request = (HttpServletRequest) context
	 * .getExternalContext().getRequest();
	 * 
	 * try {
	 * 
	 * return "formInvitoAmico";
	 * 
	 * } catch (NullPointerException n) {
	 * 
	 * context.addMessage(null, new FacesMessage("Operazione fallita"));
	 * 
	 * return "listaPacchettiPersonalizzatiCliente";
	 * 
	 * }
	 * 
	 * }
	 */

	// MODIFICA DEL PACCHETTO PERSONALIZZATO CLIENTE

	public String formModificaPersonalizzatoCliente() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			if (stringStato.equalsIgnoreCase("salvato")) {

				System.out.println("formModificaPersonalizzato --> METODO");

				Long id = pacchettoPersonalizzatoSelezionato.getIdPacchetto();

				System.out.println("formModificaPersonalizzatoCliente --> ID "
						+ id);

				setIdPacchettoPersonalizzato(id);

				setListaComponenti(pacchettoPersonalizzatoSelezionato
						.getListaComponenti());

				System.out
						.println("formModificaPersonalizzatoCliente --> RETURN");

				return "formModificaPersonalizzatoCliente";

			} else {

				context.addMessage(null, new FacesMessage(
						"Impossibile modificare: stato non valido"));

				return "listaPacchettiPersonalizzatiCliente";

			}

		} catch (NullPointerException n) {

			System.out
					.println("formModificaPersonalizzatoCliente --> NULLPOINTEREXCEPTION");

			return "listaPacchettiPersonalizzatiCliente";

		}

	}

}
