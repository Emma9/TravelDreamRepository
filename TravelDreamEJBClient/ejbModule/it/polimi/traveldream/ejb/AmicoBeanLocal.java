package it.polimi.traveldream.ejb;

import java.util.ArrayList;

import javax.ejb.Local;

@Local
public interface AmicoBeanLocal {
	
	/**@param email
	 * @return idAmico*/
	public Long createAmico(String email);
	
	/**@param idAmico*/
	public void removeAmico(Long idAmico);

	/**@param idAmico
	 * @param email*/
	public void updateAmico(Long idAmico,String email);

	/**@return ArrayList<idAmico>*/
	public ArrayList<Long> findAll();

}
