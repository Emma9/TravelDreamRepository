package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.ejb.client.PacchettoPersonalizzatoBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.InvitoDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class DettagliInvitoRicevutoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 147L;

	@EJB
	private InvitoBeanRemote invitoremoto;

	@EJB
	private PacchettoPersonalizzatoBeanRemote pacchettopersonalizzatoremoto;

	private Long idInvito;

	private InvitoDTO invito;

	private List<ComponenteDTO> listaComponentiPacchetto = new ArrayList<ComponenteDTO>(
			0);

	private PacchettoPersonalizzatoDTO pacchetto;

	/**
	 * @return the idInvito
	 */
	public Long getIdInvito() {
		return idInvito;
	}

	/**
	 * @param idInvito
	 *            the idInvito to set
	 */
	public void setIdInvito(Long idInvito) {
		this.idInvito = idInvito;
	}

	/**
	 * @return the invito
	 */
	public InvitoDTO getInvito() {
		return invito;
	}

	/**
	 * @param invito
	 *            the invito to set
	 */
	public void setInvito(InvitoDTO invito) {
		this.invito = invito;
	}

	/**
	 * @return the listaComponentiSelezionati
	 */
	public List<ComponenteDTO> getListaComponentiSelezionati() {
		return listaComponentiPacchetto;
	}

	/**
	 * @param listaComponentiSelezionati
	 *            the listaComponentiSelezionati to set
	 */
	public void setListaComponentiSelezionati(
			List<ComponenteDTO> listaComponentiSelezionati) {
		this.listaComponentiPacchetto = listaComponentiSelezionati;
	}

	/**
	 * @return the pacchetto
	 */
	public PacchettoPersonalizzatoDTO getPacchetto() {
		return pacchetto;
	}

	/**
	 * @param pacchetto
	 *            the pacchetto to set
	 */
	public void setPacchetto(PacchettoPersonalizzatoDTO pacchetto) {
		this.pacchetto = pacchetto;
	}

	public String dettagliInvitoRicevuto() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			// INVITO PER I DETTAGLI
			invito = invitoremoto.findByIdInvito(idInvito);
			/*
			 * //PACCHETTO DELL'INVITO PER LA LISTA DEI COMPONENTI pacchetto =
			 * pacchettopersonalizzatoremoto
			 * .findByIdPacchettoPersonalizzato(invito
			 * .getPacchettoPersonalizzato().getIdPacchetto());
			 */

		} catch (EJBException e) {

			return "dettagliInvitoAmico";

		}

		return "dettagliInvitoAmico";

	}

}
