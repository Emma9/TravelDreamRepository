package it.polimi.traveldream.ejb;

import java.util.ArrayList;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class ImpiegatoBean
 */
@Stateless
public class ImpiegatoBean implements ImpiegatoBeanLocal {

    /**
     * Default constructor. 
     */
    public ImpiegatoBean() {
        // TODO Auto-generated constructor stub
    }

    /**Altri metodi */

    /**@param codiceImpiegato
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome
	 * @return idImpiegato*/
	public Long createImpiegato(String codiceImpiegato,String codiceFiscale,String nome,String cognome) {
		return null;
	}
	
	/**@param idImpiegato*/
	public void removeImpiegato(Long idImpiegato) {
	}

	/**@param idImpiegato
	 * @param codiceImpiegato
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome*/
	public void updateImpiegato(Long idImpiegato,String codiceImpiegato,String codiceFiscale,String nome,String cognome) {
	}

	/**@return ArrayList<idImpiegato>*/
	public ArrayList<Long> findAll() {
		return null;
	}







}
