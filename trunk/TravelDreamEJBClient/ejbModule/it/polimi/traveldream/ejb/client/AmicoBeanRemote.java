package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.AmicoDTO;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface AmicoBeanRemote {

	
	public void createAmico(String email);

	public void removeAmico(String emailAmico);


	public void updateAmico(String emailAmico);

	public AmicoDTO findByEmailAmico(String emailAmico);

	public ArrayList<String> findAll();
	
	/**@param email
	 * @return true if email is present in the DB, otherwise false*/
	public boolean verificaPresenzaAmicoEm(String email);

	

	

}