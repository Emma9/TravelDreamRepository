package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.EtichettaDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;
import it.polimi.traveldream.entities.StatoDTO;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface PacchettoPersonalizzatoBeanRemote {

	/**@param stato
	 * @param idCliente
	 * @return idPacchettoPersonalizzato*/
	public Long createPacchettoPersonalizzato(StatoDTO stato, Long idCliente);

	/**@param idPacchettoPersonalizzato*/
	public void removePacchettoPersonalizzato(Long idPacchettoPersonalizzato);

	/**@param idPacchettoPersonalizzato
	 * @param stato
	 * @param listaComponenti*/
	public void updatePacchettoPersonalizzato(Long idPacchettoPersonalizzato,StatoDTO stato, ArrayList<Long> listaComponenti);

	/**@param destinazione
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findByDestinazione(String destinazione);

	/**@param etichetta
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findByEtichetta(EtichettaDTO etichetta);

	/**@param idPacchettoPersonalizzato
	 * @return PacchettoPersonalizzato*/
	public PacchettoPersonalizzatoDTO findByIdPacchettoPersonalizzato(Long idPacchettoPersonalizzato);

	/**@param idCliente
	 * @return ArrayList<PacchettoPersonalizzato>*/
	public ArrayList<PacchettoPersonalizzatoDTO> findByIdCliente(Long idCliente);

	/**@return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findAll();
	
	/**@param idPacchettoPersonalizzato
	 * @return true if idPacchettoPersonalizzato is not present in the DB, otherwise false*/
	public boolean verificaPresenzaPacchettoPersonalizzato(Long idPacchettoPersonalizzato);
	
	

}