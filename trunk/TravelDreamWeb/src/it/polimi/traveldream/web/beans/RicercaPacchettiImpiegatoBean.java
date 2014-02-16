package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.ejb.client.PacchettoBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.PacchettoDTO;

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
public class RicercaPacchettiImpiegatoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 567L;

	// Session bean i cui metodi sono utilizzati nel codice
	@EJB
	private PacchettoBeanRemote pacchettoremoto;

	@EJB
	private ComponenteBeanRemote componenteremoto;

	private Long idPacchetto;
	private String termine;

	private List<ComponenteDTO> listaComponenti = new ArrayList<ComponenteDTO>();

	// PACCHETTO SELEZIONATO
	private PacchettoDTO pacchettoSelezionato;

	// LISTA PACCHETTI INVIATA ALLA PAGINA WEB

	private ArrayList<PacchettoDTO> pacchettiRicercatiID = new ArrayList<PacchettoDTO>();

	private ArrayList<PacchettoDTO> pacchettiRicercatiTER = new ArrayList<PacchettoDTO>();

	// PACCHETTO INVIATO ALLA PAGINA WEB
	private PacchettoDTO pacchettoRicercato;

	/**
	 * @return the idPacchetto
	 */
	public Long getIdPacchetto() {
		return idPacchetto;
	}

	/**
	 * @param idPacchetto
	 *            the idPacchetto to set
	 */
	public void setIdPacchetto(Long idPacchetto) {
		this.idPacchetto = idPacchetto;
	}

	/**
	 * @return the termine
	 */
	public String getTermine() {
		return termine;
	}

	/**
	 * @param termine
	 *            the termine to set
	 */
	public void setTermine(String termine) {
		this.termine = termine;
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
	 * @return the pacchettoSelezionato
	 */
	public PacchettoDTO getPacchettoSelezionato() {
		return pacchettoSelezionato;
	}

	/**
	 * @param pacchettoSelezionato
	 *            the pacchettoSelezionato to set
	 */
	public void setPacchettoSelezionato(PacchettoDTO pacchettoSelezionato) {
		this.pacchettoSelezionato = pacchettoSelezionato;
	}

	/**
	 * @return the pacchettiRicercatiID
	 */
	public ArrayList<PacchettoDTO> getPacchettiRicercatiID() {
		return pacchettiRicercatiID;
	}

	/**
	 * @param pacchettiRicercatiID
	 *            the pacchettiRicercatiID to set
	 */
	public void setPacchettiRicercatiID(
			ArrayList<PacchettoDTO> pacchettiRicercatiID) {
		this.pacchettiRicercatiID = pacchettiRicercatiID;
	}

	/**
	 * @return the pacchettiRicercatiTER
	 */
	public ArrayList<PacchettoDTO> getPacchettiRicercatiTER() {
		return pacchettiRicercatiTER;
	}

	/**
	 * @param pacchettiRicercatiTER
	 *            the pacchettiRicercatiTER to set
	 */
	public void setPacchettiRicercatiTER(
			ArrayList<PacchettoDTO> pacchettiRicercatiTER) {
		this.pacchettiRicercatiTER = pacchettiRicercatiTER;
	}

	/**
	 * @return the pacchettoRicercato
	 */
	public PacchettoDTO getPacchettoRicercato() {
		return pacchettoRicercato;
	}

	/**
	 * @param pacchettoRicercato
	 *            the pacchettoRicercato to set
	 */
	public void setPacchettoRicercato(PacchettoDTO pacchettoRicercato) {
		this.pacchettoRicercato = pacchettoRicercato;
	}

	public String ricercaPacchettoImpiegatoId() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			// pacchettoRicercato =
			// pacchettoremoto.findByIdPacchetto(idPacchetto);

			ArrayList<PacchettoDTO> lista = new ArrayList<PacchettoDTO>();

			lista.add(pacchettoremoto.findByIdPacchetto(idPacchetto));

			setPacchettiRicercatiID(lista);

		} catch (EJBException e) {

			System.out.println("EJBException");

			return null;

		}

		return "listaPacchettiRicercaImpiegato";

	}

	public String ricercaPacchettoImpiegatoTermine() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			setPacchettiRicercatiID(pacchettoremoto.findByTermine(termine));

		} catch (EJBException e) {

			System.out.println("EJBException");

			return null;

		}

		return "listaPacchettiRicercaImpiegato";

	}

	public String dettagliPacchettoSelezionato() {

		Long id = pacchettoSelezionato.getIdPacchetto();

		setIdPacchetto(id);

		return "dettagliPacchettoSelezionatoImpiegato?faces-redirect=true&cPacchetto"
				+ id;

	}

	public String rimuoviPacchetto() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			System.out.println("rimuoviPacchetto --> METODO");

			pacchettoremoto.removePacchetto(idPacchetto);

			return "index";

		} catch (EJBException e) {

			System.out.println("rimuoviPacchetto --> EJBEXCEPTION");

			return null;

		}

	}

	public String formModifica(Long idp) {

		setIdPacchetto(idp);

		setListaComponenti(componenteremoto.findAll());

		return "modificaPacchetto";

	}

}