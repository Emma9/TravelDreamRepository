package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.PacchettoPKDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface PacchettoPersonalizzatoBeanRemote {

	
	/**
	 * @param stato
	 * @param emailUtente
	 * @param dataDiPartenza
	 * @param dataDiRitorno
	 * @param listaComponentiSelezionati
	 * @param pacchettoPKDTO
	 * @return
	 */
	public Long createPacchettoPersonalizzato(String stato, String emailUtente,
			Date dataDiPartenza, Date dataDiRitorno,
			List<ComponenteDTO> listaComponentiSelezionati, PacchettoPKDTO pacchettoPKDTO);	
	/**@param idPacchettoPersonalizzato*/
	public void removePacchettoPersonalizzato(Long idPacchettoPersonalizzato);	
	
	
	/**
	 * @param pacchettoPKDTO
	 * @param emailUtente
	 * @param stato
	 * @param dataDiPartenza
	 * @param dataDiRitorno
	 * @param listaComponentiSelezionati
	 */
	public void updatePacchettoPersonalizzato(PacchettoPKDTO pacchettoPKDTO,
			String emailUtente, String stato, Date dataDiPartenza,
			Date dataDiRitorno, List<ComponenteDTO> listaComponentiSelezionati);
	
	/**@param idPacchettoPersonalizzato
	 * @return PacchettoPersonalizzato*/
	public PacchettoPersonalizzatoDTO findByIdPacchettoPersonalizzato(Long idPacchettoPersonalizzato);

	/**@param idCliente
	 * @return ArrayList<PacchettoPersonalizzato>*/
	public ArrayList<PacchettoPersonalizzatoDTO> findByIdCliente(Long idCliente);

	/**@return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findAll();
	
	
	/**
	 * @param pacchettoPKDTO
	 * @return
	 */
	public boolean verificaPresenzaPacchettoPersonalizzato(
			PacchettoPKDTO pacchettoPKDTO);
	
	/**@param stato
	 * @return true if stato is valid, otherwise false
	 */
	public boolean verificaStato (String stato);
	
	public boolean verificaStatoPerCreazione(String stato);


}