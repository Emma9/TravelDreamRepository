package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.Date;




public class DisponibilitaPerDataDTO implements Serializable{
	
	private int codiceComponente;
	private Date data;
	private int disponibilita;
	private static final long serialVersionUID = 1L;
	
	

	
	
	//private List<ComponenteDTO> componenti;
	
	
	/**
	 * 
	 */
	public DisponibilitaPerDataDTO() {
		super();
	}   
	  
	/**
	 * @return data
	 */
	public Date getData() {
		return this.data;
	}

	/**
	 * @param data
	 */
	public void setData(Date data) {
		this.data = data;
	}  
	
	/**
	 * @return disponibilita
	 */
	public int getDisponibilita() {
		return this.disponibilita;
	}

	/**
	 * @param disponibilita
	 */
	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
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

	

	
   
}
