package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.InvitoDTO;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface InvitoBeanRemote {

	/**@param emailMittente
	 * @param emailDestinatario
	 * @param idPacchettoPersonalizzato
	 * @param data
	 * @param stato
	 * @return idInvito*/
	public Long createInvito(String emailMittente, String emailDestinatario,String idPacchettoPersonalizzato, String data, String stato);

	/**@param idInvito*/
	public void removeInvito(String email);

	/**@param idInvito
	 * @param emailMittente
	 * @param emailDestinatario
	 * @param idPacchettoPersonalizzato
	 * @param data
	 * @param stato*/
	public void updateInvito(Long idInvito, String emailMittente,String emailDestinatario, String idPacchettoPersonalizzato,String data, String stato);

	/**@param idInvito
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public InvitoDTO findByIdInvito(Long idInvito);

	/**@param emailMittente
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<InvitoDTO> findByEmailMittente(String emailMittente);

	/**@return ArrayList<idInvito>*/
	public ArrayList<Long> findAll();
	
	/**@param idInvito
	 * @return true if idInvito is not present in the DB, otherwise false*/
	public boolean verificaPresenzaInvito(Long idInvito);

}