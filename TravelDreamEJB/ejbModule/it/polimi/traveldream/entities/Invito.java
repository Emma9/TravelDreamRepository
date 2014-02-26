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
	private User mittente;
	private Amico destinatario;
	// private Long idPacchettoPersonalizzato;
	@Temporal(TemporalType.DATE)
	private Date data;
	private boolean stato; // TRUE=>ACCETTATO

	// @ManyToOne(fetch=FetchType.EAGER)
	// @JoinColumns({@JoinColumn(name = "idPacchetto", referencedColumnName =
	// "idPacchetto"), @JoinColumn(name = "idPacchettoPersonalizzato",
	// referencedColumnName = "idPacchettoPersonalizzato")})
	private PacchettoPersonalizzato pacchettoPersonalizzato;

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
	 * @return the mittente
	 */
	public User getMittente() {
		return mittente;
	}

	/**
	 * @param mittente
	 *            the mittente to set
	 */
	public void setMittente(User mittente) {
		this.mittente = mittente;
	}

	/**
	 * @return the destinatario
	 */
	public Amico getDestinatario() {
		return destinatario;
	}

	/**
	 * @param destinatario
	 *            the destinatario to set
	 */
	public void setDestinatario(Amico destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * @return the pacchettoPersonalizzato
	 */
	public PacchettoPersonalizzato getPacchettoPersonalizzato() {
		return pacchettoPersonalizzato;
	}

	/**
	 * @param pacchettoPersonalizzato
	 *            the pacchettoPersonalizzato to set
	 */
	public void setPacchettoPersonalizzato(
			PacchettoPersonalizzato pacchettoPersonalizzato) {
		this.pacchettoPersonalizzato = pacchettoPersonalizzato;
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
