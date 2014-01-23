package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.Amico;



import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface AmicoBeanRemote {

	/**@param email
	 * @return idAmico*/
	public Long createAmico(String email);

	/**@param idAmico*/
	public void removeAmico(Long idAmico);

	/**@param idAmico
	 * @param email*/
	public void updateAmico(Long idAmico, String email);

	/**@param idAmico
	 * @return Amico*/
	public Amico findByIdAmico(Long idAmico);

	/**@return ArrayList<idAmico>*/
	public ArrayList<Long> findAll();

}