package it.polimi.traveldream.entities;

import java.io.Serializable;

public class PacchettoPersonalizzatoDTO extends PacchettoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idPacchettoPersonalizzato;
	private StatoDTO stato;
	private Long idCliente;

	/**Costruttore*/
	public PacchettoPersonalizzatoDTO() {
		super();
	}

	/**@return idPacchettoPersonalizzato*/
	public Long getIdPacchettoPersonalizzato() {
		
		return this.idPacchettoPersonalizzato;
		
	}

	/**@return Stato*/
	public StatoDTO getStato() {
		
		return this.stato;
		
	}

	/**@param stato stato to set*/
	public void setStato(StatoDTO stato) {
		
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
