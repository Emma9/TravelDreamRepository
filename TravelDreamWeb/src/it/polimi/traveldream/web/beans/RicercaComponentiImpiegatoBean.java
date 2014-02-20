package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class RicercaComponentiImpiegatoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 565L;

	// Session bean i cui metodi sono utilizzati nel codice
	@EJB
	private ComponenteBeanRemote componenteremoto;

	private String termine;
	private int id;
	private String tipologia;
	private String luogo;
	private String descrizione;
	private int costo;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private int disponibilita;

	private String termineM;
	private int idM;

	// COMPONENTE SELEZIONATO
	private ComponenteDTO componenteSelezionato;

	// LISTA COMPONENTI PER ID INVIATA ALLA PAGINA WEB
	private ArrayList<ComponenteDTO> componentiRicercatiID = new ArrayList<ComponenteDTO>();

	// LISTA COMPONENTI PER TERMINE INVIATA ALLA PAGINA WEB
	private ArrayList<ComponenteDTO> componentiRicercatiT = new ArrayList<ComponenteDTO>();

	// COMPONENTE INVIATO ALLA PAGINA WEB
	// private ComponenteDTO componenteRicercato;

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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the tipologia
	 */
	public String getTipologia() {
		return tipologia;
	}

	/**
	 * @param tipologia
	 *            the tipologia to set
	 */
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	/**
	 * @return the luogo
	 */
	public String getLuogo() {
		return luogo;
	}

	/**
	 * @param luogo
	 *            the luogo to set
	 */
	public void setLuogo(String luogo) {
		this.luogo = luogo;
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
	 * @return the costo
	 */
	public int getCosto() {
		return costo;
	}

	/**
	 * @param costo
	 *            the costo to set
	 */
	public void setCosto(int costo) {
		this.costo = costo;
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
	 * @return the disponibilita
	 */
	public int getDisponibilita() {
		return disponibilita;
	}

	/**
	 * @param disponibilita
	 *            the disponibilita to set
	 */
	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}

	/**
	 * @return the termineM
	 */
	public String getTermineM() {
		return termineM;
	}

	/**
	 * @param termineM
	 *            the termineM to set
	 */
	public void setTermineM(String termineM) {
		this.termineM = termineM;
	}

	/**
	 * @return the idM
	 */
	public int getIdM() {
		return idM;
	}

	/**
	 * @param idM
	 *            the idM to set
	 */
	public void setIdM(int idM) {
		this.idM = idM;
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
	 * @return the componentiRicercatiID
	 */
	public ArrayList<ComponenteDTO> getComponentiRicercatiID() {
		return componentiRicercatiID;
	}

	/**
	 * @param componentiRicercatiID
	 *            the componentiRicercatiID to set
	 */
	public void setComponentiRicercatiID(
			ArrayList<ComponenteDTO> componentiRicercatiID) {
		this.componentiRicercatiID = componentiRicercatiID;
	}

	/**
	 * @return the componentiRicercatiT
	 */
	public ArrayList<ComponenteDTO> getComponentiRicercatiT() {
		return componentiRicercatiT;
	}

	/**
	 * @param componentiRicercatiT
	 *            the componentiRicercatiT to set
	 */
	public void setComponentiRicercatiT(
			ArrayList<ComponenteDTO> componentiRicercatiT) {
		this.componentiRicercatiT = componentiRicercatiT;
	}

	/**
	 * @return the componenteRicercato
	 */
	// public ComponenteDTO getComponenteRicercato() {
	// return componenteRicercato;
	// }
	/**
	 * @param componenteRicercato
	 *            the componenteRicercato to set
	 */
	// public void setComponenteRicercato(ComponenteDTO componenteRicercato) {
	// this.componenteRicercato = componenteRicercato;
	// }

	public String ricercaComponenteImpiegatoId() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			ArrayList<ComponenteDTO> lista = new ArrayList<ComponenteDTO>();
			lista.add(componenteremoto.findByCodiceComponente(id));
			setComponentiRicercatiID(lista);

			System.out.println("ricercaComponenteImpiegatoID --> METODO");

			return "listaComponentiRicercaImpiegatoID";

		}

		catch (NoResultException r) {

			System.out
					.println("ricercaComponenteImpiegatoID --> NORESULTEXCEPTION");

			context.addMessage(null, new FacesMessage(
					"Nessun componente trovato"));

			return "index";

		}

		catch (EJBException e) {

			System.out.println("ricercaComponenteImpiegatoID --> EJBEXCEPTION");

			context.addMessage(null, new FacesMessage(
					"Ricerca componente fallita"));

			return "index";

		}

	}

	public String ricercaComponenteImpiegatoTermine() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			ArrayList<ComponenteDTO> lista2 = new ArrayList<ComponenteDTO>();

			for (int i = 0; i < componenteremoto.findByTermine(getTermine())
					.size(); i++) {
				lista2.add(componenteremoto.findByTermine(getTermine()).get(i));
			}

			setComponentiRicercatiID(lista2);

			System.out.println("ricercaComponenteImpiegatoTER --> METODO");

			return "listaComponentiRicercaImpiegatoTER";

		}

		catch (NoResultException r) {

			System.out
					.println("ricercaComponenteImpiegatoTER --> NORESULTEXCEPTION");

			context.addMessage(null, new FacesMessage(
					"Nessun componente trovato"));

			return "index";

		}

		catch (EJBException e) {

			System.out
					.println("ricercaComponenteImpiegatoTER --> EJBEXCEPTION");

			context.addMessage(null, new FacesMessage(
					"Ricerca componente fallita"));

			return "index";

		}

	}

	public String dettagliComponenteSelezionatoID() {

		try {
			id = componenteSelezionato.getCodiceComponente();

			// setIdM(id);

			System.out.println("dettagliComponenteSelezionatoID --> METODO");

			return "dettagliComponenteSelezionatoImpiegatoID?faces-redirect=true&cComponente"
					+ id;

		} catch (NullPointerException n) {

			System.out
					.println("dettagliComponenteSelezionatoID --> NULLPOINTEREXCEPTION");

			return "listaComponentiRicercaImpiegatoID";

		}

	}

	public String dettagliComponenteSelezionatoTER() {

		try {
			id = componenteSelezionato.getCodiceComponente();

			// setTermineM(termine);

			System.out.println("dettagliComponenteSelezionatoTER --> METODO");

			return "dettagliComponenteSelezionatoImpiegatoTER?faces-redirect=true&cComponente"
					+ id;

		} catch (NullPointerException n) {

			System.out
					.println("dettagliComponenteSelezionatoTER --> NULLPOINTEREXCEPTION");

			return "listaComponentiRicercaImpiegatoTER";

		}

	}

	public String rimuoviComponenteID() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			componenteremoto.removeComponente(id);

			// setId(idM);

			System.out.println("rimuoviComponenteID --> METODO");

			context.addMessage(null, new FacesMessage(
					"Rimozione componente riuscita"));

			return "index";

		} catch (EJBException e) {

			System.out.println("rimuoviComponenteID --> EJBEXCEPTION");

			context.addMessage(null, new FacesMessage(
					"Rimozione componente fallita"));

			return null;

		}

	}

	public String rimuoviComponenteTER() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			componenteremoto.removeComponente(id);

			// setTermine(termineM);

			System.out.println("rimuoviComponenteTER --> METODO");

			context.addMessage(null, new FacesMessage(
					"Rimozione componente riuscita"));

			return "index";

		} catch (EJBException e) {

			System.out.println("rimuoviComponenteTER --> EJBEXCEPTION");

			context.addMessage(null, new FacesMessage(
					"Rimozione componente fallita"));

			return null;

		}

	}

	public String formModificaID(int idc) {

		setId(idc);

		setId(componenteSelezionato.getCodiceComponente());
		setDescrizione(componenteSelezionato.getDescrizione());
		setLuogo(componenteSelezionato.getLuogo());
		setCosto(componenteSelezionato.getCosto());
		setDataInizioValidita(componenteSelezionato.getDataInizioValidita());
		setDataFineValidita(componenteSelezionato.getDataFineValidita());
		setDisponibilita(0);

		return "modificaComponenteID";

	}

	public String formModificaTER(int idc) {

		setId(idc);

		setId(componenteSelezionato.getCodiceComponente());
		setDescrizione(componenteSelezionato.getDescrizione());
		setLuogo(componenteSelezionato.getLuogo());
		setCosto(componenteSelezionato.getCosto());
		setDataInizioValidita(componenteSelezionato.getDataInizioValidita());
		setDataFineValidita(componenteSelezionato.getDataFineValidita());
		setDisponibilita(0);

		return "modificaComponenteTER";

	}

	public String creazioneComponente() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			componenteremoto.createComponente(tipologia, luogo, descrizione,
					costo, dataInizioValidita, dataFineValidita, disponibilita);

			context.addMessage(null, new FacesMessage(
					"Creazione componente riuscita"));

			return "index";

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage(
					"Creazione componente fallita"));

			return "index";

		}

	}

	public String modificaComponenteID(int id) {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			componenteremoto.updateComponente(id, tipologia, luogo,
					descrizione, costo, dataInizioValidita, dataFineValidita,
					disponibilita);

			context.addMessage(null, new FacesMessage(
					"Modifica componente riuscita"));

			return "index";

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage(
					"Modifica componente fallita"));

			return "index";

		}

	}

	public String modificaComponenteTER(int id) {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {

			componenteremoto.updateComponente(id, tipologia, luogo,
					descrizione, costo, dataInizioValidita, dataFineValidita,
					disponibilita);

			context.addMessage(null, new FacesMessage(
					"Modifica componente riuscita"));

			return "index";

		} catch (EJBException e) {

			context.addMessage(null, new FacesMessage(
					"Modifica componente fallita"));

			return "index";

		}

	}

}
