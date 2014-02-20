package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.ejb.client.PacchettoBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.PacchettoDTO;

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

	// private List<ComponenteDTO> listaComponenti = new
	// ArrayList<ComponenteDTO>();

	// PACCHETTO SELEZIONATO
	private PacchettoDTO pacchettoSelezionato;

	// LISTA PACCHETTI INVIATA ALLA PAGINA WEB

	private ArrayList<PacchettoDTO> pacchettiRicercatiID = new ArrayList<PacchettoDTO>();

	private ArrayList<PacchettoDTO> pacchettiRicercatiTER = new ArrayList<PacchettoDTO>();

	// PACCHETTO INVIATO ALLA PAGINA WEB
	private PacchettoDTO pacchettoRicercato;

	private Long idpacchetto;
	private String destinazione;
	private String descrizione;
	private int sconto;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private String etichetta;

	// COMPONENTI INSERITI
	private List<ComponenteDTO> listaComponenti = new ArrayList<ComponenteDTO>();

	// COMPONENTI SELEZIONATI
	private List<ComponenteDTO> listaComponentiSelezionati = new ArrayList<ComponenteDTO>();

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

	/**
	 * @return the idpacchetto
	 */
	public Long getIdpacchetto() {
		return idpacchetto;
	}

	/**
	 * @param idpacchetto
	 *            the idpacchetto to set
	 */
	public void setIdpacchetto(Long idpacchetto) {
		this.idpacchetto = idpacchetto;
	}

	/**
	 * @return the destinazione
	 */
	public String getDestinazione() {
		return destinazione;
	}

	/**
	 * @param destinazione
	 *            the destinazione to set
	 */
	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione
	 *            the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the sconto
	 */
	public int getSconto() {
		return sconto;
	}

	/**
	 * @param sconto
	 *            the sconto to set
	 */
	public void setSconto(int sconto) {
		this.sconto = sconto;
	}

	/**
	 * @return the dataInizioValidita
	 */
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	/**
	 * @param dataInizioValidita
	 *            the dataInizioValidita to set
	 */
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	/**
	 * @return the dataFineValidita
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	/**
	 * @param dataFineValidita
	 *            the dataFineValidita to set
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	/**
	 * @return the etichetta
	 */
	public String getEtichetta() {
		return etichetta;
	}

	/**
	 * @param etichetta
	 *            the etichetta to set
	 */
	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
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

		try {
			Long id = pacchettoSelezionato.getIdPacchetto();

			setIdPacchetto(id);

			return "dettagliPacchettoSelezionatoImpiegato?faces-redirect=true&cPacchetto"
					+ id;

		} catch (NullPointerException n) {

			System.out
					.println("dettagliPacchettoSelezionato --> NULLPOINTEREXCEPTION");

			return "listaPacchettiRicercaImpiegato";

		}

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

		setEtichetta(pacchettoSelezionato.getEtichetta());
		setDestinazione(pacchettoSelezionato.getDestinazione());
		setDescrizione(pacchettoSelezionato.getDescrizione());
		setDataInizioValidita(pacchettoSelezionato.getDataInizioValidita());
		setDataFineValidita(pacchettoSelezionato.getDataFineValidita());
		setSconto(pacchettoSelezionato.getSconto());

		return "modificaPacchetto";

	}

	public String creazionePacchetto() {

		FacesContext context = FacesContext.getCurrentInstance();

		try {

			System.out.println("CREAZIONE PACCHETTO --> METODO");

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

		} catch (EJBException e) {

			System.out.println("CREAZIONE PACCHETTO --> EJBEXCEPTION");

			context.addMessage(null, new FacesMessage(
					"Creazione pacchetto fallita"));

			return "index";

		}

		context.addMessage(null, new FacesMessage(
				"Creazione pacchetto riuscita"));

		return "index";

	}

	public String modificaPacchetto(Long id) {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			pacchettoremoto.updatePacchetto(id, destinazione,
					dataInizioValidita, dataFineValidita, etichetta,
					descrizione, listaComponenti, listaComponentiSelezionati,
					sconto);

			context.addMessage(null, new FacesMessage(
					"Modifica pacchetto riuscita"));

			setIdpacchetto(id);

			return "index";

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage(
					"Modifica pacchetto fallita"));

			return "index";

		}

	}

}
