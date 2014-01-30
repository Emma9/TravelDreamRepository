package it.polimi.traveldream.entities;

import java.io.Serializable;

public class ImpiegatoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 203L;

	private Long idImpiegato;
	private String codiceImpiegato;
	private String codiceFiscale;
	private String nome;
	private String cognome;

	/**Costruttore*/
	public ImpiegatoDTO() {
		super();
	}

	/**@return idImpiegato*/
	public Long getIdImpiegato() {
		
		return this.idImpiegato;
		
	}
	

	/**
	 * @param idImpiegato the idImpiegato to set
	 */
	public void setIdImpiegato(Long idImpiegato) {
		this.idImpiegato = idImpiegato;
	}

	/**@return codiceImpiegato*/
	public String getCodiceImpiegato() {
		
		return this.codiceImpiegato;
		
	}

	/**@param codiceImpiegato codiceImpiegato to set*/
	public void setCodiceImpiegato(String codiceImpiegato) {
		
		this.codiceImpiegato = codiceImpiegato;
		
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
