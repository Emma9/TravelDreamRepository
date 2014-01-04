package it.polimi.traveldream.ejb;

import java.util.ArrayList;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class AmicoBean
 */
@Stateless
public class AmicoBean implements AmicoBeanLocal {

    /**
     * Default constructor. 
     */
    public AmicoBean() {
        // TODO Auto-generated constructor stub
    }

    /**Altri metodi */

    /**@param email
	 * @return idAmico*/
	public Long createAmico(String email) {
		return null;
	}
	
	/**@param idAmico*/
	public void removeAmico(Long idAmico) {
	}

	/**@param idAmico
	 * @param email*/
	public void updateAmico(Long idAmico,String email) {
	}

	/**@return ArrayList<idAmico>*/
	public ArrayList<Long> findAll() {
		return null;
	}

}
