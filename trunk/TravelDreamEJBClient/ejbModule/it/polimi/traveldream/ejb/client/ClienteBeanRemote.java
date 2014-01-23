package it.polimi.traveldream.ejb.client;

import it.polimi.traveldream.entities.ClienteDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface ClienteBeanRemote {

	/**@param email
	 * @param password
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome
	 * @return idCliente*/
	public Long createCliente(String email, String password,String codiceFiscale, String nome, String cognome);

	/**@param idCliente*/
	public void removeCliente(Long idCliente);

	/**@param idCliente
	 * @param email
	 * @param password
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome*/
	public void updateCliente(Long idCliente, String email, String password,String codiceFiscale, String nome, String cognome);

	/**@param email
	 * @return ClienteDTO*/
	public ClienteDTO findByEmailCliente(String email);
	
	/**@param idCliente
	 * @return ClienteDTO*/
	public ClienteDTO findByIdCliente(Long idCliente);

	/**@return ArrayList<idCliente>*/
	public ArrayList<Long> findAll();
	
	/**@param email
	 * @param password
	 * @return long*/
	public long verificaPresenzaClienteLogin(String email, String password);
	
	/**@param email
	 * @return true if email is not present in the DB, otherwise false*/
	public boolean verificaPresenzaClienteRegistrazione(String email, String codiceFiscale);
	
	/**
	 * @param idCliente
	 * @return true if idCliente is not present in the DB, otherwise false
	 */
	public boolean verificaPresenzaClienteId(Long idCliente);
	
	/**
	 * @param id
	 * @return ArrayList<PacchettoPersonalizzato>
	 */
	public ArrayList<PacchettoPersonalizzatoDTO> elencoPacchettiCliente (long id);
	
	/**@param idCliente
	 */
	public void eliminaTuttiPacchettiPersonalizzati(long idCliente);
	
	/**@param idCliente
	 * @return email*/
	public String daIdAEmail(Long idCliente);
	
}
