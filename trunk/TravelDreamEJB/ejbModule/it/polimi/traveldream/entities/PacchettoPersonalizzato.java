package it.polimi.traveldream.entities;

import java.lang.Long;
import javax.persistence.*;

/**PacchettoPersonalizzato di TravelDream*/

@Entity
public class PacchettoPersonalizzato extends Pacchetto {

	/**Version number*/
	private static final long serialVersionUID = 107L;

	/**Attributi*/

	@GeneratedValue
	private Long idPacchettoPersonalizzato;
	private Stato stato;
	private Long idCliente;

	/**Costruttore*/
	public PacchettoPersonalizzato() {
		super();
	}

	/**@return idPacchettoPersonalizzato*/
	public Long getIdPacchettoPersonalizzato() {
		
		return this.idPacchettoPersonalizzato;
		
	}

	/**@return Stato*/
	public Stato getStato() {
		
		return this.stato;
		
	}

	/**@param stato stato to set*/
	public void setStato(Stato stato) {
		
		this.stato = stato;

	}

	/**@return idCliente*/
	public Long getIdCliente() {
		
		return this.idCliente;
	
	}

	/**@param idCliente idCliente to set*/
	public void setIdCliente(Long idCliente) {
		
		this.idCliente = idCliente;
		
	}
}
