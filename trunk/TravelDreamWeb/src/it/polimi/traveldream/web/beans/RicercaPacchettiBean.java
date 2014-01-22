package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.PacchettoBeanRemote;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class RicercaPacchettiBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private PacchettoBeanRemote pacchettoRemoto;

	private String destinazione;

	private Date dataPartenza;
	private Date dataRitorno;

	private int disponibilita;

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
	 * @return the dataPartenza
	 */
	public Date getDataPartenza() {
		return dataPartenza;
	}

	/**
	 * @param dataPartenza
	 *            the dataPartenza to set
	 */
	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}

	/**
	 * @return the dataRitorno
	 */
	public Date getDataRitorno() {
		return dataRitorno;
	}

	/**
	 * @param dataRitorno
	 *            the dataRitorno to set
	 */
	public void setDataRitorno(Date dataRitorno) {
		this.dataRitorno = dataRitorno;
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
	public String ricercaPacchetti()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {
			
			
			

		} catch (ServletException e) {

		}
	}**/

}
