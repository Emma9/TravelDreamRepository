package it.polimi.traveldream.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 206L;
	
	private Long idCliente;
	private String email;
	private String password;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private List<PacchettoPersonalizzatoDTO> pacchettiCliente = new ArrayList<PacchettoPersonalizzatoDTO>(0);
	private List<PacchettoPersonalizzatoDTO> giftList = new ArrayList<PacchettoPersonalizzatoDTO>(0);

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

	/**
	 * @return the pacchettiCliente
	 */
	public List<PacchettoPersonalizzatoDTO> getPacchettiCliente() {
		return pacchettiCliente;
	}

	/**
	 * @param pacchettiCliente the pacchettiCliente to set
	 */
	public void setPacchettiCliente(List<PacchettoPersonalizzatoDTO> pacchettiCliente) {
		this.pacchettiCliente = pacchettiCliente;
	}

	/**
	 * @return the giftList
	 */
	public List<PacchettoPersonalizzatoDTO> getGiftList() {
		return giftList;
	}

	/**
	 * @param giftList the giftList to set
	 */
	public void setGiftList(List<PacchettoPersonalizzatoDTO> giftList) {
		this.giftList = giftList;
	}
	
}
	
	
	