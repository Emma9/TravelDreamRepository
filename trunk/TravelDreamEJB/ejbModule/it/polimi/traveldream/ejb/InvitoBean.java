package it.polimi.traveldream.ejb;

import java.util.ArrayList;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class InvitoBean
 */
@Stateless
public class InvitoBean implements InvitoBeanLocal {

    /**
     * Default constructor. 
     */
    public InvitoBean() {
        // TODO Auto-generated constructor stub
    }

    /**Altri metodi */

    /**@param emailMittente
	 * @param emailDestinatario
	 * @param idPacchettoPersonalizzato
	 * @param data
	 * @param stato
	 * @return idInvito*/
	public Long createInvito(String emailMittente,String emailDestinatario,String idPacchettoPersonalizzato,String data,String stato) {
		return null;
	}
	
	/**@param idInvito*/
	public void removeInvito(Long idInvito) {
	}

	/**@param idInvito
	 * @param emailMittente
	 * @param emailDestinatario
	 * @param idPacchettoPersonalizzato
	 * @param data
	 * @param stato*/
	public void updateInvito(Long idInvito,String emailMittente,String emailDestinatario,String idPacchettoPersonalizzato,String data,String stato) {
	}

	/**@return ArrayList<idInvito>*/
	public ArrayList<Long> findAll() {
		return null;
	}
	







}
