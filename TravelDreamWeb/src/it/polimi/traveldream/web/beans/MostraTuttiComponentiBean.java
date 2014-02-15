package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.common.collect.ComputationException;

@ManagedBean()
@SessionScoped
public class MostraTuttiComponentiBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 137921L;

	
	@EJB
	private ComponenteBeanRemote componenteremoto;
	
	private List<ComponenteDTO> listaComponenti = new ArrayList<ComponenteDTO>();

	/**
	 * @return the listaComponenti
	 */
	public List<ComponenteDTO> getListaComponenti() {
		return listaComponenti;
	}

	/**
	 * @param listaComponenti the listaComponenti to set
	 */
	public void setListaComponenti(List<ComponenteDTO> listaComponenti) {
		this.listaComponenti = listaComponenti;
	}
	
	
	public String mostraTuttiComponenti(){
		
		try {
						
			setListaComponenti(componenteremoto.findAll());

		}catch (EJBException e) {
	
	}
		return "creazionePacchettoPredefinito";

		
		
	}
	
	
public String mostraTuttiComponentiMOD(){
		
		try {
						
			setListaComponenti(componenteremoto.findAll());

		}catch (EJBException e) {
	
	}
		return "modificaPacchetto";

		
		
	}
	
	
}
