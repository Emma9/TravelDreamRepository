package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.PacchettoPersonalizzatoBeanRemote;
import it.polimi.traveldream.ejb.client.UserBeanRemote;
import it.polimi.traveldream.ejb.client.UsrMgr;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;

import java.io.Serializable;
import java.util.ArrayList;

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

			setPacchettiPersonalizzatiCliente((ArrayList<PacchettoPersonalizzatoDTO>) usermanager
					.getUserDTO().getPacchettiCliente());

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

			return "dettagliPacchettoPersonalizzatoSalvato";

		} catch (NullPointerException n) {

			return null;

		}

		catch (EJBException e) {

			return null;

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

			if (!(usermanager.getUserDTO().getGiftList()
					.contains(pacchettoPersonalizzatoSelezionato))) {

				usermanager.getUserDTO().getGiftList()
						.add(pacchettoPersonalizzatoSelezionato);

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

			if (usermanager.getUserDTO().getGiftList()
					.contains(pacchettoPersonalizzatoSelezionato)) {

				usermanager.getUserDTO().getGiftList()
						.remove(pacchettoPersonalizzatoSelezionato);

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

}
