package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.ejb.client.PacchettoPersonalizzatoBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.InvitoDTO;

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
public class DettagliInvitoRicevutoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 147L;
	
	@EJB
	private InvitoBeanRemote invitoremoto;
	
	@EJB
	private PacchettoPersonalizzatoBeanRemote pacchettopersonalizzatoremoto;
	
	
	private Long idInvito;
	
	private InvitoDTO invito;
	
	private List<ComponenteDTO> listaComponentiSelezionati= new ArrayList<ComponenteDTO>(0);

	/**
	 * @return the idInvito
	 */
	public Long getIdInvito() {
		return idInvito;
	}

	/**
	 * @param idInvito the idInvito to set
	 */
	public void setIdInvito(Long idInvito) {
		this.idInvito = idInvito;
	}

	/**
	 * @return the invito
	 */
	public InvitoDTO getInvito() {
		return invito;
	}

	/**
	 * @param invito the invito to set
	 */
	public void setInvito(InvitoDTO invito) {
		this.invito = invito;
	}

	/**
	 * @return the listaComponentiSelezionati
	 */
	public List<ComponenteDTO> getListaComponentiSelezionati() {
		return listaComponentiSelezionati;
	}

	/**
	 * @param listaComponentiSelezionati the listaComponentiSelezionati to set
	 */
	public void setListaComponentiSelezionati(
			List<ComponenteDTO> listaComponentiSelezionati) {
		this.listaComponentiSelezionati = listaComponentiSelezionati;
	}


	public String dettagliInvitoRicevuto(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		try {
			
			invito = invitoremoto.findByIdInvito(idInvito);
			
			//listaComponentiSelezionati = pacchettopersonalizzatoremoto  METODO CHE RESTITUISCE LA LISTA

		}catch (EJBException e) {
		
			return "dettagliInvitoAmico";

	}

	return "dettagliInvitoAmico";	
	
		
	}
	
	
	

}
