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

	// PACCHETTO PERSONALIZZATO SELEZIONATO
	private PacchettoPersonalizzatoDTO pacchettoPersonalizzatoSelezionato;

	// LISTA PACCHETTI PERSONALIZZATI INVIATA ALLA PAGINA WEB
	private ArrayList<PacchettoPersonalizzatoDTO> pacchettiPersonalizzatiCliente = new ArrayList<PacchettoPersonalizzatoDTO>();

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

			/*
			 * Long id = pacchettoPersonalizzatoSelezionato
			 * .getIdPacchettoPersonalizzato();
			 * 
			 * setIdPacchettoPersonalizzato(id);
			 */

			System.out.println("DETTAGLI --> METODO");

			System.out.println("DETTAGLI --> IDP "
					+ pacchettoPersonalizzatoSelezionato
							.getIdPacchettoPersonalizzato());

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

		try {

			System.out.println("MODIFICA PACCHETTO --> METODO");
/*
			for (int j = 0; j < listaComponentiSelezionati.size(); j++) {

				if (!(listaComponenti.contains(listaComponentiSelezionati
						.get(j)))) {

					listaComponenti.add(listaComponentiSelezionati.get(j));

				}

			}

			pacchettoremoto.createPacchetto(destinazione, dataInizioValidita,
					dataFineValidita, etichetta, descrizione, listaComponenti,
					listaComponentiSelezionati, sconto);

			System.out.println("CREAZIONE PACCHETTO --> PACCHETTO CREATO");

			context.addMessage(null, new FacesMessage(
					"Creazione pacchetto riuscita"));*/

			return "index";

		} catch (EJBException e) {

			System.out.println("CREAZIONE PACCHETTO --> EJBEXCEPTION");

			context.addMessage(null, new FacesMessage(
					"Creazione pacchetto fallita"));

			return "index";

		}

	}
	
	
	
	
	public String rimuoviPacchettoSelezionato() {

		try {

			pacchettopersremote.removePacchettoPersonalizzato(
					pacchettoPersonalizzatoSelezionato
							.getIdPacchettoPersonalizzato(),
					pacchettoPersonalizzatoSelezionato.getIdPacchetto());

			return "index";

		} catch (NullPointerException n) {

			return "index";

		}

	}

	public String aggiungiAllaGiftlist() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			if (!(pacchettoPersonalizzatoSelezionato.getStato()
					.equalsIgnoreCase("giftlist"))) {

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

				return "listaPacchettiPersonalizzatiCliente";

			} else {

				context.addMessage(null, new FacesMessage(
						"Pacchetto gia presente in gift list"));

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

			if (pacchettoPersonalizzatoSelezionato.getStato()
			.equalsIgnoreCase("giftlist")) {

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

			if (!(pacchettoPersonalizzatoSelezionato.getStato()
					.equalsIgnoreCase("bloccato"))) {

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

				return "listaPacchettiPersonalizzatiCliente";

			} else {

				context.addMessage(null, new FacesMessage(
						"Pacchetto gia bloccato"));

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

			if (!(pacchettoPersonalizzatoSelezionato.getStato()
					.equalsIgnoreCase("confermato"))) {

				System.out.println("CONFERMA --> IF");

				PacchettoPKDTO pacchettoPK = new PacchettoPKDTO(pacchettoPersonalizzatoSelezionato.getIdPacchetto(), pacchettoPersonalizzatoSelezionato.getIdPacchettoPersonalizzato());

				UserDTO cli = pacchettoPersonalizzatoSelezionato.getCliente();

				Date datap = pacchettoPersonalizzatoSelezionato
						.getDataDiPartenza();

				Date datar = pacchettoPersonalizzatoSelezionato
						.getDataDiRitorno();

				int nump = pacchettoPersonalizzatoSelezionato
						.getNumPartecipanti();

				List<ComponenteDTO> listacs = pacchettoPersonalizzatoSelezionato
						.getListaComponentiSelezionati();

				pacchettopersremote.updatePacchettoPersonalizzato(pacchettoPK, cli, "confermato", datap, datar numPartecipanti, listaComponentiSelezionati);

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

	public String gestioneInviti() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			return "gestioneInviti";

		} catch (NullPointerException n) {

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

}
