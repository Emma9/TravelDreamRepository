package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class DettagliComponenteSelezionatoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 987L;
	
	@EJB
	private ComponenteBeanRemote componenteRemoto;
	
	private Long codiceComponente;
	
	private ComponenteDTO componente;
	
	
	

	/**
	 * @return the componenteRemoto
	 */
	public ComponenteBeanRemote getComponenteRemoto() {
		return componenteRemoto;
	}

	/**
	 * @param componenteRemoto the componenteRemoto to set
	 */
	public void setComponenteRemoto(ComponenteBeanRemote componenteRemoto) {
		this.componenteRemoto = componenteRemoto;
	}

	/**
	 * @return the codiceComponente
	 */
	public Long getCodiceComponente() {
		return codiceComponente;
	}

	/**
	 * @param codiceComponente the codiceComponente to set
	 */
	public void setCodiceComponente(Long codiceComponente) {
		this.codiceComponente = codiceComponente;
	}

	/**
	 * @return the componente
	 */
	public ComponenteDTO getComponente() {
		return componente;
	}

	/**
	 * @param componente the componente to set
	 */
	public void setComponente(ComponenteDTO componente) {
		this.componente = componente;
	}
	

	public String DettagliComponenteSelezionato(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		try {
			
			componente = componenteRemoto.findByCodiceComponente(codiceComponente);			

		}catch (EJBException e) {
		
			return "dettagliComponenteSelezionato";

	}

	return "dettagliComponenteSelezionato";	
	
		}

	
	public String DettagliComponenteSelezionatoImpiegato(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		try {
			
			componente = componenteRemoto.findByCodiceComponente(codiceComponente);			

		}catch (EJBException e) {
		
			return "dettagliComponenteSelezionatoImpiegato";

	}

	return "dettagliComponenteSelezionatoImpiegato";	
	
		}
			
	
	
	
}
