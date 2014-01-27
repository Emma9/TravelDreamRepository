package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.Date;

public class DisponibilitaPerDataDTO implements Serializable{
	private Date data;
	private int disponibilita;
	private static final long serialVersionUID = 204L;

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
	 * 
	 * @param disponibilita
	 */
	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}
   
}
