package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.AmicoDTO;

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
	public AmicoDTO findByIdAmico(Long idAmico);

	/**@return ArrayList<idAmico>*/
	public ArrayList<Long> findAll();
	
	public boolean verificaPresenzaAmicoEm(String email);
	
	public boolean verificaPresenzaAmicoId(Long idAmico);
	
	/**@param idAmico
	 * @return email*/
	public String daIdAEmail(Long idAmico);
	
	

}