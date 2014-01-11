package it.polimi.traveldream.ejb;

import it.polimi.traveldream.entities.Cliente;

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
	 * @return Cliente*/
	public Cliente findByEmailCliente(String email);
	
	/**@param idCliente
	 * @return Cliente*/
	public Cliente findByIdCliente(Long idCliente);

	/**@return ArrayList<idCliente>*/
	public ArrayList<Long> findAll();
	
	/**@param email
	 * @param password
	 * @return long*/
	public long verificaPresenzaClienteLogin(String email, String password);

}
