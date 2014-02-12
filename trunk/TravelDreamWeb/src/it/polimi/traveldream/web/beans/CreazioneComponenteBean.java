package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class CreazioneComponenteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 519L;
	
	@EJB
	private ComponenteBeanRemote componenteRemoto;
	
	private String tipologia;
	private String luogo;
	private String descrizione;
	private int costo;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private int disponibilita;
	
	
	/**
	 * @return the tipologia
	 */
	public String getTipologia() {
		return tipologia;
	}
	/**
	 * @param tipologia the tipologia to set
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
	 * @param luogo the luogo to set
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
	 * @param descrizione the descrizione to set
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
	 * @param costo the costo to set
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
	 * @param dataInizioValidita the dataInizioValidita to set
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
	 * @param dataFineValidita the dataFineValidita to set
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
	 * @param disponibilita the disponibilita to set
	 */
	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}
	public String creazioneComponente(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		try {
			
			componenteRemoto.createComponente(tipologia, luogo, descrizione, costo, dataInizioValidita, dataFineValidita, disponibilita);
			
			context.addMessage(null, new FacesMessage("Creazione componente riuscita"));
			
			return "index";

		}catch (EJBException e) {
		
			context.addMessage(null, new FacesMessage("Creazione componente fallita"));
			
			return "index";
			
			

	}

		//context.addMessage(null, new FacesMessage("Creazione componente riuscita"));	
		
	//return "homePageImpiegato";	
		
		
	}
	
	

}
