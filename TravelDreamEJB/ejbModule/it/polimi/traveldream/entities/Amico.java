package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Amico di TravelDream
 *
 */

@Entity

public class Amico implements Serializable {

	/**Version number */
	private static final long serialVersionUID = 1L;
	
	/**Attributi */
	@Id
	private String email;
	
	/**Costruttore */
	public Amico() {
		super();
	}
	
	/**@return email*/
	public String getEmail() {
		return this.email;
	}

	/**@param email email to set*/
	public void setEmail(String email) {
		this.email = email;
	}
   
}
