package it.polimi.traveldream.ejb;

import java.util.ArrayList;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class PacchettoPersonalizzatoBean
 */
@Stateless
public class PacchettoPersonalizzatoBean implements PacchettoPersonalizzatoBeanLocal {

    /**
     * Default constructor. 
     */
    public PacchettoPersonalizzatoBean() {
        // TODO Auto-generated constructor stub
    }

    /**Altri metodi */

    /**@param destinazione
	 * @param etichetta
	 * @param descrizione
	 * @param listaComponenti
	 * @param idPacchetto
	 * @param stato
	 * @return idPacchettoPersonalizzato*/
	public Long createPacchettoPersonalizzato(String destinazione,String etichetta,String descrizione,ArrayList<Long> listaComponenti,Long idPacchetto,String stato) {
		return null;
	}
	
	/**@param idPacchettoPersonalizzato*/
	public void removePacchettoPersonalizzato(Long idPacchettoPersonalizzato) {
	}

	/**@param idPacchettoPersonalizzato
	 * @param stato
	 * @param idPacchetto
	 * @param destinazione
	 * @param etichetta
	 * @param descrizione
	 * @param listaComponenti*/
	public void updatePacchettoPersonalizzato(Long idPacchettoPersonalizzato,String stato,Long idPacchetto,String destinazione,String etichetta,String descrzione,ArrayList<Long> listaComponenti) {
	}
	
	/**@param destinazione
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findByDestinazione(String destinazione) {
		return null;
	}
	
	/**@param etichetta
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findByEtichetta(String etichetta) {
		return null;
	}	

	/**@return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findAll() {
		return null;
	}

}
