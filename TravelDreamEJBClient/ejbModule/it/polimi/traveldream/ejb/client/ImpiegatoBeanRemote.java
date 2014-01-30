package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.ImpiegatoDTO;

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
	 * @return ImpiegatoDTO*/
	public ImpiegatoDTO findByIdImpiegato(Long idImpiegato);

	/**@return ArrayList<idImpiegato>*/
	public ArrayList<Long> findAll();
	
	/**@param codiceFiscale
	 * @return true if codiceFiscale is present in the DB, otherwise false*/
	public boolean verificaPresenzaImpiegatoCf(String codiceFiscale);
	
	/**@param idImpiegato
	 * @return true if idImpiegato is present in the DB, otherwise false*/
	public boolean verificaPresenzaImpiegatoId(Long idImpiegato);
		

}