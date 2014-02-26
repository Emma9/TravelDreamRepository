package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class InvitoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 202L;
	private Long idInvito;
	// private UserDTO mittente;
	// private AmicoDTO destinatario;
	private String emailDestinatario;

	private String emailMittente;
	private Date data;
	private boolean stato; // TRUE=>ACCETTATO

	private Long idPacchettoPersonalizzato;

	/** Costruttore */
	public InvitoDTO() {
		super();
	}

	/** @return idInvito */
	public Long getIdInvito() {

		return this.idInvito;

	}

	/**
	 * @param idInvito
	 *            the idInvito to set
	 */
	public void setIdInvito(Long idInvito) {
		this.idInvito = idInvito;
	}

	/**
	 * @return the emailDestinatario
	 */
	public String getEmailDestinatario() {
		return emailDestinatario;
	}

	/**
	 * @param emailDestinatario
	 *            the emailDestinatario to set
	 */
	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	/**
	 * @return the emailMittente
	 */
	public String getEmailMittente() {
		return emailMittente;
	}

	/**
	 * @param emailMittente
	 *            the emailMittente to set
	 */
	public void setEmailMittente(String emailMittente) {
		this.emailMittente = emailMittente;
	}

	/** @return data */
	public Date getData() {

		return this.data;

	}

	/**
	 * @param data
	 *            data to set
	 */
	public void setData(Date data) {

		this.data = data;

	}

	/** @return stato */
	public boolean getStato() {

		return this.stato;

	}

	/**
	 * @param stato
	 *            stato to set
	 */
	public void setStato(boolean stato) {

		this.stato = stato;

	}

	/**
	 * @return the idPacchettoPersonalizzato
	 */
	public Long getIdPacchettoPersonalizzato() {
		return idPacchettoPersonalizzato;
	}

	/**
	 * @param idPacchettoPersonalizzato
	 *            the idPacchettoPersonalizzato to set
	 */
	public void setIdPacchettoPersonalizzato(Long idPacchettoPersonalizzato) {
		this.idPacchettoPersonalizzato = idPacchettoPersonalizzato;
	}

}
