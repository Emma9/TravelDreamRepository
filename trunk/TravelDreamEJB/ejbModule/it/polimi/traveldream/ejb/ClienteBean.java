package it.polimi.traveldream.ejb;


import it.polimi.traveldream.ejb.client.ClienteBeanLocal;
import it.polimi.traveldream.ejb.client.ClienteBeanRemote;
import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.entities.Cliente;
import it.polimi.traveldream.entities.ClienteDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;

import java.util.*;

import javax.persistence.*;
import javax.ejb.Stateless;
/**Session Bean implementation class ClienteBean*/
@Stateless
public class ClienteBean implements ClienteBeanRemote, ClienteBeanLocal {

	@PersistenceContext(unitName = "travelDream_project") private EntityManager manager;

	/**Default constructor*/
	public ClienteBean() {
		// TODO Auto-generated constructor stub
	}

	/**@param email
	 * @param password
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome
	 * @return idCliente*/
	public Long createCliente(String email, String password,String codiceFiscale, String nome, String cognome) {

		if (!(verificaPresenzaClienteRegistrazione(email, codiceFiscale))) {

			Cliente cliente = new Cliente();

			
			cliente.setEmail(email);
			cliente.setPassword(password);
			cliente.setCodiceFiscale(codiceFiscale);
			cliente.setNome(nome);
			cliente.setCognome(cognome);

			manager.persist(cliente);

			return cliente.getIdCliente();

		} else {
			return (long) -1;
		}
	}

	/**@param idCliente*/
	public void removeCliente(Long idCliente) {
		
		
			ClienteDTO c = findByIdCliente(idCliente);
			InvitoBeanRemote i= new InvitoBean();
			
			
			c.getPacchettiCliente().clear();
			c.getGiftList().clear();
			
			i.removeInvitiCliente(c.getEmail());

			
			manager.remove(c);

		
	}

	/**@param idCliente
	 * @param email
	 * @param password
	 * @param codiceFiscale
	 * @param nome
	 * @param cognome*/
	public void updateCliente(Long idCliente, String email, String password,
			String codiceFiscale, String nome, String cognome) {

		if (verificaPresenzaClienteId(idCliente)) {

			ClienteDTO cliente = findByIdCliente(idCliente);

			cliente.setEmail(email);
			cliente.setPassword(password);
			cliente.setCodiceFiscale(codiceFiscale);
			cliente.setNome(nome);
			cliente.setCognome(cognome);

			manager.merge(cliente);
		}
	}
	
	/**@param email
	 * @return Cliente*/
	public ClienteDTO findByEmailCliente(String email) {

		TypedQuery<ClienteDTO> q = manager.createQuery("FROM Cliente c WHERE c.email=:new_email", ClienteDTO.class);

		q.setParameter("new_email", email);

		ClienteDTO cliente = q.getSingleResult();

		return cliente;
	}

	/**@param idCliente
	 * @return Cliente*/
	public ClienteDTO findByIdCliente(Long idCliente) {

		TypedQuery<ClienteDTO> q = manager.createQuery("FROM Cliente c WHERE c.idCliente=:new_idCliente", ClienteDTO.class);

		q.setParameter("new_idCliente", idCliente);

		ClienteDTO cliente = q.getSingleResult();

		return cliente;
	}

	/** @return ArrayList<idCliente> */
	public ArrayList<Long> findAll() {

		TypedQuery<ClienteDTO> q = manager.createQuery("FROM Cliente c", ClienteDTO.class);

		List<ClienteDTO> clienti = q.getResultList();

		ArrayList<Long> lista = new ArrayList<Long>();

		for (int i = 0; i < clienti.size(); i++) {

			lista.set(i, clienti.get(i).getIdCliente());
		}

		return lista;
	}

	

	/**@param email
	 * @param password
	 * @return idCliente*/
	public long verificaPresenzaClienteLogin(String email, String password) {
		try {
			TypedQuery<ClienteDTO> q = manager
					.createQuery("FROM Cliente c WHERE c.email=:new_email", ClienteDTO.class);
	
			q.setParameter("new_email", email);
	
			ClienteDTO cliente = q.getSingleResult();
	
			if (cliente.getPassword()== password) {
				return cliente.getIdCliente();
	
			} else {
				return -1;
	
			}
		} catch (NullPointerException e) {
			return -1;
		}
	}

	
	
	/**@param email
	 * @return true if email is present in the DB, otherwise false*/
	public boolean verificaPresenzaClienteRegistrazione(String email, String codiceFiscale) {
		try {
			TypedQuery<ClienteDTO> q = manager
					.createQuery("FROM Cliente c WHERE c.email=:new_email OR c.codiceFiscale=:new_codiceFiscale", ClienteDTO.class);

			q.setParameter("new_email", email);
			q.setParameter("new_codiceFiscale", codiceFiscale);

			List<ClienteDTO> clienti = q.getResultList();

			if (clienti.size() == 0) {
				return false;

			} else {
				return true;

			}
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * @param idCliente
	 * @return true if idCliente is present in the DB, otherwise false
	 */
	public boolean verificaPresenzaClienteId(Long idCliente) {
		try {
			TypedQuery<ClienteDTO> q = manager
					.createQuery("FROM Cliente c WHERE c.idCliente=:new_idCliente", ClienteDTO.class);

			q.setParameter("new_idCliente", idCliente);

			
			List<ClienteDTO> clienti = q.getResultList();

			if (clienti.size() == 0) {
				return false;

			} else {
				return true;

			}
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	/**
	 * @param id
	 * @return ArrayList<PacchettoPersonalizzato>
	 */
	public ArrayList<PacchettoPersonalizzatoDTO> elencoPacchettiCliente (long id){
		ClienteDTO c = findByIdCliente(id);
		
		ArrayList<PacchettoPersonalizzatoDTO> elencoPacchettiCliente = (ArrayList<PacchettoPersonalizzatoDTO>) c.getPacchettiCliente();
		
		return elencoPacchettiCliente;
	}

	/**
	 * @param id
	 * @return ArrayList<PacchettoPersonalizzato>
	 */
	public ArrayList<PacchettoPersonalizzatoDTO> giftListCliente (long id){
		ClienteDTO c = findByIdCliente(id);
		
		ArrayList<PacchettoPersonalizzatoDTO> elencoPacchettiCliente = (ArrayList<PacchettoPersonalizzatoDTO>) c.getGiftList();
		
		return elencoPacchettiCliente;
	}
	
	
	/**@param idCliente
	 */
	public void eliminaTuttiPacchettiPersonalizzati(long idCliente) {

		ClienteDTO cliente = findByIdCliente(idCliente);
		cliente.getPacchettiCliente().clear();
		
	}
	
	
	
	/**@param idCliente
	 * @return email*/
	public String daIdAEmail(Long idCliente) {
		
		ClienteDTO c = findByIdCliente(idCliente);
		return c.getEmail();
	}
	
	/**@param email
	 * @return idCliente*/
	public Long daEmailAId(String email) {
		
		ClienteDTO c = findByEmailCliente(email);
		return c.getIdCliente();
	}
	

}
