package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;



import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean()
@SessionScoped
public class RimozioneComponenteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 67994L;
	
	
	@EJB
	private ComponenteBeanRemote componenteRemoto;
	
	private int codiceComponente;

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
	
	
	public String rimuoviComponente(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		componenteRemoto.removeComponente(codiceComponente);
		
		return "listaComponentiRicercaImpiegato";
		
	}
	
	

}
