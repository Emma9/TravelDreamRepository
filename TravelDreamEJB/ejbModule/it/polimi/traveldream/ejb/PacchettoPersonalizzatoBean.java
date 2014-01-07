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
	 * @param idCliente
	 * @return idPacchettoPersonalizzato*/
	public Long createPacchettoPersonalizzato(String destinazione,String etichetta,String descrizione,ArrayList<Long> listaComponenti,Long idPacchetto,String stato,Long idCliente) {
		return null;
	}
	
	/**@param idCliente*/
	public void removePacchettoPersonalizzato(Long idCliente) {
	}

	/**@param idPacchettoPersonalizzato
	 * @param stato
	 * @param idCliente
	 * @param idPacchetto
	 * @param destinazione
	 * @param etichetta
	 * @param descrizione
	 * @param listaComponenti*/
	public void updatePacchettoPersonalizzato(Long idPacchettoPersonalizzato,String stato,Long idCliente,Long idPacchetto,String destinazione,String etichetta,String descrzione,ArrayList<Long> listaComponenti) {
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
	
	/**@param idCliente
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findByIdCliente(Long idCliente) {
		return null;
	}	

	/**@return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findAll() {
		return null;
	}

}
