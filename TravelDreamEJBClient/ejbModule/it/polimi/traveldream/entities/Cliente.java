package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;

import javax.persistence.*;

/**Cliente di TravelDream*/

@Entity
public class Cliente implements Serializable {

	/**Version number*/
	private static final long serialVersionUID = 103L;

	/**Attributi*/
	@Id
	@GeneratedValue
	private Long idCliente;
	private String email;
	private String password;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private ArrayList<PacchettoPersonalizzato> pacchettiCliente = new ArrayList<PacchettoPersonalizzato>();
	private ArrayList<PacchettoPersonalizzato> giftList = new ArrayList<PacchettoPersonalizzato>();

	/**Costruttore*/
	public Cliente() {
		super();
	}

	/**@return idCliente*/
	public Long getIdCliente() {
		
		return this.idCliente;
		
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
	
	/**@return ArrayList<PacchettoPersonalizzato>*/
	public ArrayList<PacchettoPersonalizzato> getPacchettiCliente() {
		
		return this.pacchettiCliente;
		
	}

	/**@param pacchettiCliente paccettiCliente to set*/
	public void setPacchettiCliente(ArrayList<PacchettoPersonalizzato> pacchettiCliente ) {
		
		this.pacchettiCliente = pacchettiCliente;
		
	}
	
	/**@return ArrayList<PacchettoPersonalizzato>*/
	public ArrayList<PacchettoPersonalizzato> getGiftList() {
		
		return this.giftList;
		
	}

	/**@param giftList giftList to set*/
	public void setGiftList(ArrayList<PacchettoPersonalizzato> giftList ) {
		
		this.giftList = giftList;
		
	}
}
