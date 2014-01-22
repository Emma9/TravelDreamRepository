package it.polimi.traveldream.web.beans;

public interface Registrazione {

	public String registrazione();
	
	
	/**
	 * @return the nome
	 */
	public String getNome();

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome);

	/**
	 * @return the cognome
	 */
	public String getCognome();

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome);

	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale();

	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale);

	/**
	 * @return the email
	 */
	public String getEmail();

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email);

	/**
	 * @return the password
	 */
	public String getPassword();
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password);
	
}
