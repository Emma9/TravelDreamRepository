package it.polimi.traveldream.ejb;

import java.util.ArrayList;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class PacchettoBean
 */
@Stateless
public class PacchettoBean implements PacchettoBeanLocal {

    /**
     * Default constructor. 
     */
    public PacchettoBean() {
        // TODO Auto-generated constructor stub
    }

    /**Altri metodi */

    /**@param destinazione
	 * @param etichetta
	 * @param descrizione
	 * @param listaComponenti
	 * @return idPacchetto*/
	public Long createPacchetto(String destinazione,String etichetta,String descrizione,ArrayList<Long> listaComponenti) {
		return null;
	}
	
	/**@param idPacchetto*/
	public void removePacchetto(Long idPacchetto) {
	}

	/**@param idPacchetto
	 * @param destinazione
	 * @param etichetta
	 * @param descrizione
	 * @param listaComponenti*/
	public void updatePacchetto(Long idPacchetto,String destinazione,String etichetta,String descrzione,ArrayList<Long> listaComponenti) {
	}
	
	/**@param destinazione
	 * @return ArrayList<idPacchetto>*/
	public ArrayList<Long> findByDestinazione(String destinazione) {
		return null;
	}
	
	/**@param etichetta
	 * @return ArrayList<idPacchetto>*/
	public ArrayList<Long> findByEtichetta(String etichetta) {
		return null;
	}	

	/**@return ArrayList<idPacchetto>*/
	public ArrayList<Long> findAll() {
		return null;
	}







}
