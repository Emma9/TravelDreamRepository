package it.polimi.traveldream.ejb;

import it.polimi.traveldream.entities.Cliente;
import it.polimi.traveldream.entities.PacchettoPersonalizzato;

import java.util.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
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

		if (verificaPresenzaClienteRegistrazione(email, codiceFiscale)) {

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
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			PacchettoPersonalizzatoBeanRemote pacchettoPersonalizzatoLocal = (PacchettoPersonalizzatoBeanRemote) ctx.lookup("PacchettoPersonalizzatoBean/local");
			InvitoBeanRemote invitoLocal = (InvitoBeanRemote) ctx.lookup("InvitoBean/local");
			// ELIMINARE GIFTLIST CLIENTE

			pacchettoPersonalizzatoLocal.removePacchettoPersonalizzato(idCliente);
			invitoLocal.removeInvito(daIdAEmail(idCliente));

			Cliente c = findByIdCliente(idCliente);
			manager.remove(c);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

			Cliente cliente = findByIdCliente(idCliente);

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
	public Cliente findByEmailCliente(String email) {

		Query q = manager.createQuery("FROM Cliente c WHERE c.email=:new_email");

		q.setParameter("new_email", email);

		Cliente cliente = (Cliente) q.getSingleResult();

		return cliente;
	}

	/**@param idCliente
	 * @return Cliente*/
	public Cliente findByIdCliente(Long idCliente) {

		Query q = manager.createQuery("FROM Cliente c WHERE c.idCliente=:new_idCliente");

		q.setParameter("new_idCliente", idCliente);

		Cliente cliente = (Cliente) q.getSingleResult();

		return cliente;
	}

	/** @return ArrayList<idCliente> */
	public ArrayList<Long> findAll() {

		Query q = manager.createQuery("FROM Cliente c");

		@SuppressWarnings("unchecked")
		ArrayList<Cliente> clienti = (ArrayList<Cliente>) q.getResultList();

		ArrayList<Long> lista = new ArrayList<Long>();

		for (int i = 0; i <= clienti.size(); i++) {

			lista.set(i, clienti.get(i).getIdCliente());
		}

		return lista;
	}

	

	/**@param email
	 * @param password
	 * @return long*/
	public long verificaPresenzaClienteLogin(String email, String password) {
		try {
			Query q = manager
					.createQuery("FROM Cliente c WHERE c.email=:new_email");
	
			q.setParameter("new_email", email);
	
			Cliente cliente =  (Cliente) q.getSingleResult();
	
			if (cliente.getPassword()== password) {
				return cliente.getIdCliente();
	
			} else {
				return -1;
	
			}
		} catch (NullPointerException e) {
			return -1;
		}
	}

	
	
	/** Metodi private */
	
	public ArrayList<PacchettoPersonalizzato> elencoPacchettiCliente (long id){
		Cliente c = findByIdCliente(id);
		
		ArrayList<PacchettoPersonalizzato> elencoPacchettiCliente = c.getPacchettiCliente();
		
		return elencoPacchettiCliente;
	}

	/**@param email
	 * @return true if email is not present in the DB, otherwise false*/
	private boolean verificaPresenzaClienteRegistrazione(String email, String codiceFiscale) {
		try {
			Query q = manager
					.createQuery("FROM Cliente c WHERE c.email=:new_email AND c.codiceFiscale=:new_codiceFiscale");

			q.setParameter("new_email", email);
			q.setParameter("new_codiceFiscale", codiceFiscale);

			@SuppressWarnings("unchecked")
			ArrayList<Cliente> clienti = (ArrayList<Cliente>) q.getResultList();

			if (clienti.size() == 0) {
				return true;

			} else {
				return false;

			}
		} catch (NullPointerException e) {
			return true;
		}
	}

	/**
	 * @param idCliente
	 * @return true if idCliente is not present in the DB, otherwise false
	 */
	private boolean verificaPresenzaClienteId(Long idCliente) {
		try {
			Query q = manager
					.createQuery("FROM Cliente c WHERE c.idCliente=:new_idCliente");

			q.setParameter("new_idCliente", idCliente);

			@SuppressWarnings("unchecked")
			ArrayList<Cliente> clienti = (ArrayList<Cliente>) q.getResultList();

			if (clienti.size() == 0) {
				return true;

			} else {
				return false;

			}
		} catch (NullPointerException e) {
			return true;
		}
	}

	/**@param idCliente
	 * @return email*/
	private String daIdAEmail(Long idCliente) {
		
		Cliente c = findByIdCliente(idCliente);
		return c.getEmail();
	}
	
	
}
