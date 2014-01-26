package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.Date;

public class DisponibilitaPerDataDTO implements Serializable{
	private Date data;
	private int disponibilita;
	private static final long serialVersionUID = 1L;

	public DisponibilitaPerDataDTO() {
		super();
	}   
	  
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}   
	public int getDisponibilita() {
		return this.disponibilita;
	}

	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}
   
}
