package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.Impiegato;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface ImpiegatoBeanRemote {

	/**@param codiceFiscale
	 * @param nome
	 * @param cognome
	 * @return idImpiegato*/
	public Long createImpiegato(String codiceFiscale,String nome, String cognome);

	/**@param idImpiegato*/
	public void removeImpiegato(Long idImpiegato);

	/**@param idImpiegato
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome*/
	public void updateImpiegato(Long idImpiegato,String codiceFiscale, String nome, String cognome);

	/**@param idImpiegato
	 * @return Impiegato*/
	public Impiegato findByIdImpiegato(Long idImpiegato);

	/**@return ArrayList<idImpiegato>*/
	public ArrayList<Long> findAll();

}