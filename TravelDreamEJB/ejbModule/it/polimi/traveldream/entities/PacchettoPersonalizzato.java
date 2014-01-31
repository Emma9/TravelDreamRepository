package it.polimi.traveldream.entities;

import java.lang.Long;
import java.util.Date;

import javax.persistence.*;

/**PacchettoPersonalizzato di TravelDream*/

@Entity
public class PacchettoPersonalizzato extends Pacchetto {

	/**Version number*/
	private static final long serialVersionUID = 107L;

	/**Attributi*/

	@GeneratedValue
	private Long idPacchettoPersonalizzato;
	private String stato;
	private Long idCliente;
	@Temporal(TemporalType.DATE)
	private Date dataDiPartenza;
	@Temporal(TemporalType.DATE)
	private Date dataDiRitorno;

	/**Costruttore*/
	public PacchettoPersonalizzato() {
		super();
	}

	/**@return idPacchettoPersonalizzato*/
	public Long getIdPacchettoPersonalizzato() {
		
		return this.idPacchettoPersonalizzato;
		
	}
	
	

	/**
	 * @param idPacchettoPersonalizzato the idPacchettoPersonalizzato to set
	 */
	public void setIdPacchettoPersonalizzato(Long idPacchettoPersonalizzato) {
		this.idPacchettoPersonalizzato = idPacchettoPersonalizzato;
	}

	/**@return Stato*/
	public String getStato() {
		
		return this.stato;
		
	}

	/**@param stato stato to set*/
	public void setStato(String stato) {
		
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

	/**
	 * @return the dataDiPartenza
	 */
	public Date getDataDiPartenza() {
		return dataDiPartenza;
	}

	/**
	 * @param dataDiPartenza the dataDiPartenza to set
	 */
	public void setDataDiPartenza(Date dataDiPartenza) {
		this.dataDiPartenza = dataDiPartenza;
	}

	/**
	 * @return the dataDiRitorno
	 */
	public Date getDataDiRitorno() {
		return dataDiRitorno;
	}

	/**
	 * @param dataDiRitorno the dataDiRitorno to set
	 */
	public void setDataDiRitorno(Date dataDiRitorno) {
		this.dataDiRitorno = dataDiRitorno;
	}
	
	
	
	
	
	
}
