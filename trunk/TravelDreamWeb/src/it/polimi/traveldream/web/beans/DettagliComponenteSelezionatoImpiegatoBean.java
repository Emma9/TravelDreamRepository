package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean()
@SessionScoped
public class DettagliComponenteSelezionatoImpiegatoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 987L;
	
	@EJB
	private ComponenteBeanRemote componenteRemoto;
	
	private int codiceComponente;
	
	private ComponenteDTO componenteSelezionato;
	
	
	

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
	public int getCodiceComponente() {
		return codiceComponente;
	}

	/**
	 * @param codiceComponente the codiceComponente to set
	 */
	public void setCodiceComponente(int codiceComponente) {
		this.codiceComponente = codiceComponente;
	}

	/**
	 * @return the componente
	 */
	public ComponenteDTO getComponenteSelezionato() {
		return componenteSelezionato;
	}

	/**
	 * @param componente the componente to set
	 */
	public void setComponenteSelezionato(ComponenteDTO componente) {
		this.componenteSelezionato = componente;
	}
	

	

	
	public String DettagliComponenteSelezionatoImpiegato(){
		
		
		try {
			
			componenteSelezionato = componenteRemoto.findByCodiceComponente(codiceComponente);			

		}catch (EJBException e) {
		
			return "dettagliComponenteSelezionatoImpiegato";

	}

	return "dettagliComponenteSelezionatoImpiegato";	
	
		}
			
	
	
	
}
