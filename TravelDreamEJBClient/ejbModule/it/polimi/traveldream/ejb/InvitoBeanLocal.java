package it.polimi.traveldream.ejb;

import java.util.ArrayList;

import javax.ejb.Local;

@Local
public interface InvitoBeanLocal {

	/**@param emailMittente
	 * @param emailDestinatario
	 * @param idPacchettoPersonalizzato
	 * @param data
	 * @param stato
	 * @return idInvito*/
	public Long createInvito(String emailMittente,String emailDestinatario,String idPacchettoPersonalizzato,String data,String stato);
	
	/**@param idInvito*/
	public void removeInvito(String email);

	/**@param idInvito
	 * @param emailMittente
	 * @param emailDestinatario
	 * @param idPacchettoPersonalizzato
	 * @param data
	 * @param stato*/
	public void updateInvito(Long idInvito,String emailMittente,String emailDestinatario,String idPacchettoPersonalizzato,String data,String stato);

	/**@param email
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findByEmail(String email);	
	
	/**@return ArrayList<idInvito>*/
	public ArrayList<Long> findAll();
	
}
