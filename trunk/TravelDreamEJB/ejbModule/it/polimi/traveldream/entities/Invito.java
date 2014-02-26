package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/** Invito di TravelDream */

@Entity
public class Invito implements Serializable {

	/** Version number */
	private static final long serialVersionUID = 106L;

	/** Attributi */
	@Id
	@GeneratedValue
	private Long idInvito;
	// private User mittente;

	// private Amico destinatario;
	// private Long idPacchettoPersonalizzato;

	private String emailDestinatario;

	private String emailMittente;

	@Temporal(TemporalType.DATE)
	private Date data;
	private boolean stato; // TRUE=>ACCETTATO

	// @ManyToOne(fetch=FetchType.EAGER)
	// @JoinColumns({@JoinColumn(name = "idPacchetto", referencedColumnName =
	// "idPacchetto"), @JoinColumn(name = "idPacchettoPersonalizzato",
	// referencedColumnName = "idPacchettoPersonalizzato")})
	private Long idPacchettoPersonalizzato;

	/** Costruttore */
	public Invito() {
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

}
