package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;

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
public class ListaComponentiAlternativiBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 293L;
	
	@EJB
	private ComponenteBeanRemote componenteRemoto;
	
	private int disponibilita;
	
	//COMPONENTE BASE DA SOSTITUIRE
	private ComponenteDTO componente;

	//COMPONENTE BASE SELEZIONATO
	private ComponenteDTO componenteSelezionato;
	
	//LISTA COMPONENTI BASE INVIATA ALLA PAGINA WEB
	private ArrayList<ComponenteDTO> componentiRicercati = new ArrayList<ComponenteDTO>();

	
	
	
	
	
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

	/**
	 * @return the componenteSelezionato
	 */
	public ComponenteDTO getComponenteSelezionato() {
		return componenteSelezionato;
	}

	/**
	 * @param componenteSelezionato the componenteSelezionato to set
	 */
	public void setComponenteSelezionato(ComponenteDTO componenteSelezionato) {
		this.componenteSelezionato = componenteSelezionato;
	}

	/**
	 * @return the componentiRicercati
	 */
	public ArrayList<ComponenteDTO> getComponentiRicercati() {
		return componentiRicercati;
	}

	/**
	 * @param componentiRicercati the componentiRicercati to set
	 */
	public void setComponentiRicercati(ArrayList<ComponenteDTO> componentiRicercati) {
		this.componentiRicercati = componentiRicercati;
	}

	public String listaComponenti(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

			
		
		try{
			
			
			ArrayList<ComponenteDTO> lista = componenteRemoto.findByLuogo(componente.getLuogo());
			
			ComponenteDTO cp = new ComponenteDTO();
			
		
			
			for(int i=0; i<lista.size();i++){
				
			cp = lista.get(i);
			
			
				
			if(componenteRemoto.verificaDisponibilitaComponenteInPeriodo(disponibilita, componente.getDataInizioValidita(), componente.getDataFineValidita(), cp)){
				
			componentiRicercati.add(cp);	
				
				
			}
			
			}
		
		
		}
		catch(EJBException e){
			
			return "listaComponentiBaseAlternativi"; 
			
		}
		return "listaComponentiBaseAlternativi";
		
		
		
	}
	
	
	

}
