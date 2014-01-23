package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class ClienteDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idCliente;
	private String email;
	private String password;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private ArrayList<PacchettoPersonalizzatoDTO> pacchettiCliente = new ArrayList<PacchettoPersonalizzatoDTO>();
	private ArrayList<PacchettoPersonalizzatoDTO> giftList = new ArrayList<PacchettoPersonalizzatoDTO>();

	/**Costruttore*/
	public ClienteDTO() {
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
	
	/**@return ArrayList<PacchettoPersonalizzatoDTO>*/
	public ArrayList<PacchettoPersonalizzatoDTO> getPacchettiCliente() {
		
		return this.pacchettiCliente;
		
	}

	/**@param pacchettiCliente paccettiCliente to set*/
	public void setPacchettiCliente(ArrayList<PacchettoPersonalizzatoDTO> pacchettiCliente ) {
		
		this.pacchettiCliente = pacchettiCliente;
		
	}
	
	/**@return ArrayList<PacchettoPersonalizzatoDTO>*/
	public ArrayList<PacchettoPersonalizzatoDTO> getGiftList() {
		
		return this.giftList;
		
	}

	/**@param giftList giftList to set*/
	public void setGiftList(ArrayList<PacchettoPersonalizzatoDTO> giftList ) {
		
		this.giftList = giftList;
		
	}
	

}
