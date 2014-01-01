package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Cliente di TravelDream
 *
 */

@Entity

public class Cliente implements Serializable {

	/**Version number */
	private static final long serialVersionUID = 1L;
	   
	/**Attributi */
	@Id
	private String email;
	private String password;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	
	/**Costruttore */
	public Cliente() {
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
	
	/**@return password*/
	public String getPassword() {
		return this.password;
	}

	/**@param password password to set*/
	public void setPassword(String password) {
		this.password = password;
	} 
	
	/**@return codiceFiscale*/
	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	/**@param codiceFiscale codiceFiscale to set*/
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	
	/**@return nome*/
	public String getNome() {
		return this.nome;
	}

	/**@param nome nome to set*/
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**@return cognome*/
	public String getCognome() {
		return this.cognome;
	}

	/**@param cognome cognome to set*/
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
   
}
