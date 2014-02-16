package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface PacchettoPersonalizzatoBeanRemote {

	/**@param stato
	 * @param idCliente
	 * @return idPacchettoPersonalizzato*/
	public Long createPacchettoPersonalizzato(String stato, String emailUtente,
			Date dataDiPartenza, Date dataDiRitorno,
			List<ComponenteDTO> listaComponentiSelezionati);
	
	/**@param idPacchettoPersonalizzato*/
	public void removePacchettoPersonalizzato(Long idPacchettoPersonalizzato);	
	
	/**@param idPacchettoPersonalizzato
	 * @param stato
	 * @param listaComponenti*/
	public void updatePacchettoPersonalizzato(Long idPacchettoPersonalizzato,
			Long idCliente, String stato, Date dataDiPartenza,
			Date dataDiRitorno, List<ComponenteDTO> listaComponentiSelezionati);
	
	
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
	
	/**@param stato
	 * @return true if stato is valid, otherwise false
	 */
	public boolean verificaStato (String stato);
	
	public boolean verificaStatoPerCreazione(String stato);


}