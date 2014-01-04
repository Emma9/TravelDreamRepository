package it.polimi.traveldream.ejb;

import java.util.ArrayList;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class ClienteBean
 */
@Stateless
public class ClienteBean implements ClienteBeanLocal {

    /**
     * Default constructor. 
     */
    public ClienteBean() {
        // TODO Auto-generated constructor stub
    }

    /**Altri metodi */

    /**@param email
	 * @param password
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome
	 * @return idCliente*/
	public Long createCliente(String email,String password,String codiceFiscale,String nome,String cognome) {
		return null;
	}
	
	/**@param idCliente*/
	public void removeCliente(Long idCliente) {
	}

	/**@param idCliente
	 * @param email
	 * @param password
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome*/
	public void updateCliente(Long idCliente,String email,String password,String codiceFiscale,String nome,String cognome) {
	}

	/**@return ArrayList<idCliente>*/
	public ArrayList<Long> findAll() {
		return null;
	}







}
