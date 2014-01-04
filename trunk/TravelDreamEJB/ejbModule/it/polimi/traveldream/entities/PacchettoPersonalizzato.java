package it.polimi.traveldream.entities;

import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

/**
 * PacchettoPersonalizzato di TravelDream
 *
 */

@Entity

public class PacchettoPersonalizzato extends Pacchetto {

	/**Version number */
	private static final long serialVersionUID = 107L;
	   
	/**Attributi */

	@GeneratedValue
	private Long idPacchettoPersonalizzato;
	private String stato;
	
	/**Costruttore */
	public PacchettoPersonalizzato() {
		super();
	} 
	
	/**@return idPacchettoPersonalizzato*/
	public Long getIdPacchettoPersonalizzato() {
		return this.idPacchettoPersonalizzato;
	}

	/**@param idPacchettoPersonalizzato idPacchettoPersonalizzato to set
	public void setIdPacchettoPersonalizzato(Long idPacchettoPersonalizzato) {
		this.idPacchettoPersonalizzato = idPacchettoPersonalizzato;
	}*/
	
	/**@return stato*/
	public String getStato() {
		return this.stato;
	}

	/**@param stato stato to set*/
	public void setStato(String stato) {
		this.stato = stato;
	}
   
}
