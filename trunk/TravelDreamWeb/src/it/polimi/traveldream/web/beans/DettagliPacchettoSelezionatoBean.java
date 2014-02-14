package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.PacchettoBeanRemote;
import it.polimi.traveldream.entities.PacchettoDTO;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class DettagliPacchettoSelezionatoBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 670L;
	
	@EJB
	private PacchettoBeanRemote pacchettoRemoto;
	
	private Long idPacchetto;
	
	private PacchettoDTO pacchetto;
	
		
	
	
	
	
	

	/**
	 * @return the idPacchetto
	 */
	public Long getIdPacchetto() {
		return idPacchetto;
	}


	/**
	 * @param idPacchetto the idPacchetto to set
	 */
	public void setIdPacchetto(Long idPacchetto) {
		this.idPacchetto = idPacchetto;
	}


	/**
	 * @return the pacchetto
	 */
	public PacchettoDTO getPacchetto() {
		return pacchetto;
	}


	/**
	 * @param pacchetto the pacchetto to set
	 */
	public void setPacchetto(PacchettoDTO pacchetto) {
		this.pacchetto = pacchetto;
	}


	public String dettagliPacchettoSelezionato(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		try {
			
			pacchetto = pacchettoRemoto.findByIdPacchetto(idPacchetto);			

		}catch (EJBException e) {
		
			return null;

	}

	return "/dettagliPacchettoPredefinitoRicercato";	
	
		}

	
	
	
	public String dettagliPacchettoSelezionatoImpiegato(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		try {
			
			pacchetto = pacchettoRemoto.findByIdPacchetto(idPacchetto);			

		}catch (EJBException e) {
		
			return "dettagliPacchettoSelezionatoImpiegato";

	}

	return "dettagliPacchettoSelezionatoImpiegato";	
	
		}	
	
}

