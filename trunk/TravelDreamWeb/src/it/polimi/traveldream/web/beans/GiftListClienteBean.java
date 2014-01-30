package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.ClienteBeanRemote;
import it.polimi.traveldream.entities.PacchettoDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class GiftListClienteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 345L;
	
	@EJB
	private ClienteBeanRemote clienteRemoto;
	
	private Long idCliente;
	
	//PACCHETTO SELEZIONATO
	private PacchettoPersonalizzatoDTO pacchettoSelezionato;
	
	//LISTA PACCHETTI INVIATA ALLA PAGINA WEB
	private ArrayList<PacchettoPersonalizzatoDTO> pacchettiRicercati = new ArrayList<PacchettoPersonalizzatoDTO>();


	/**
	 * @return the pacchettoSelezionato
	 */
	public PacchettoDTO getPacchettoSelezionato() {
		return pacchettoSelezionato;
	}

	/**
	 * @param pacchettoSelezionato the pacchettoSelezionato to set
	 */
	public void setPacchettoSelezionato(PacchettoPersonalizzatoDTO pacchettoSelezionato) {
		this.pacchettoSelezionato = pacchettoSelezionato;
	}

	/**
	 * @return the pacchettiRicercati
	 */
	public ArrayList<PacchettoPersonalizzatoDTO> getPacchettiRicercati() {
		return pacchettiRicercati;
	}

	/**
	 * @param pacchettiRicercati the pacchettiRicercati to set
	 */
	public void setPacchettiRicercati(ArrayList<PacchettoPersonalizzatoDTO> pacchettiRicercati) {
		this.pacchettiRicercati = pacchettiRicercati;
	}

	
	/**
	 * @return the idCliente
	 */
	public Long getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String giftList(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		try {
			
			pacchettiRicercati = clienteRemoto.giftListCliente(idCliente);			

		}catch (EJBException e) {
		
		return null;

	}

	return "giftListCliente";		
		}
			
		
	}