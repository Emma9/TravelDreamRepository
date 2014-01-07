package it.polimi.traveldream.ejb;

import java.util.ArrayList;

import javax.ejb.Local;

@Local
public interface PacchettoPersonalizzatoBeanLocal {
	
	/**@param destinazione
	 * @param etichetta
	 * @param descrizione
	 * @param listaComponenti
	 * @param idPacchetto
	 * @param stato
	 * @param idCliente
	 * @return idPacchettoPersonalizzato*/
	public Long createPacchettoPersonalizzato(String destinazione,String etichetta,String descrizione,ArrayList<Long> listaComponenti,Long idPacchetto,String stato, Long idCliente);
	
	/**@param idPacchettoPersonalizzato*/
	public void removePacchettoPersonalizzato(Long idPacchettoPersonalizzato);

	/**@param idPacchettoPersonalizzato
	 * @param stato
	 * @param idCliente
	 * @param idPacchetto
	 * @param destinazione
	 * @param etichetta
	 * @param descrizione
	 * @param listaComponenti*/
	public void updatePacchettoPersonalizzato(Long idPacchettoPersonalizzato,String stato,Long idCliente,Long idPacchetto,String destinazione,String etichetta,String descrzione,ArrayList<Long> listaComponenti);
	
	/**@param destinazione
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findByDestinazione(String destinazione);
	
	/**@param etichetta
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findByEtichetta(String etichetta);	
	
	/**@param idCliente
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findByIdCliente(Long idCliente);	

	/**@return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findAll();

}
