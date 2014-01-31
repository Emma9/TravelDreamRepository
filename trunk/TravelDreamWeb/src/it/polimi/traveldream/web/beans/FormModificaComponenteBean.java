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
public class FormModificaComponenteBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 808L;

		// Session bean i cui metodi sono utilizzati nel codice
		@EJB
		private ComponenteBeanRemote componenteremoto;
		
		private int codiceComponente;
		
		private ComponenteDTO componente = new ComponenteDTO();
		
	
		
		
		
		
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
		public ComponenteDTO getComponente() {
			return componente;
		}






		/**
		 * @param componente the componente to set
		 */
		public void setComponente(ComponenteDTO componente) {
			this.componente = componente;
		}






	public String formModificaComponente(){
		

		
		try {
			
			
			componente = componenteremoto.findByCodiceComponente(codiceComponente);
			
			

		}catch (EJBException e) {
		
		return null;

	}

	return "formModificaComponenteBaseImpiegato";		
		}
		
	}


