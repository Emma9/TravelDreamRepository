package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DisponibilitaPerData
 *
 */
@Entity
public class DisponibilitaPerData implements Serializable {

	   
	@Id
	@Temporal(TemporalType.TIME)
	private Date data;
	private int disponibilita;
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DisponibilitaPerData() {
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

	
   
}
