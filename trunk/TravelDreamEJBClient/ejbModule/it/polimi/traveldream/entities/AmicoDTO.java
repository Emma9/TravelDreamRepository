package it.polimi.traveldream.entities;

import java.io.Serializable;

public class AmicoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 207L;

	private String email;

	/** Costruttore */
	public AmicoDTO() {
		super();
	}

	/** @return email */
	public String getEmail() {

		return this.email;

	}

	/**
	 * @param email
	 *            email to set
	 */
	public void setEmail(String email) {

		this.email = email;

	}
}