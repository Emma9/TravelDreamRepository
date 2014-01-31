package it.polimi.traveldream.ejb;


import it.polimi.traveldream.ejb.client.ClienteBeanLocal;

import it.polimi.traveldream.ejb.client.ClienteBeanRemote;
import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.entities.Cliente;
import it.polimi.traveldream.entities.ClienteDTO;
import it.polimi.traveldream.entities.PacchettoDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzato;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;

import java.util.*;

import javax.persistence.*;
import javax.ejb.Stateless;

import org.apache.commons.codec.digest.DigestUtils;

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
		
		
			
			Cliente c = manager.find(Cliente.class, idCliente);
			
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

			Cliente cliente = manager.find(Cliente.class, idCliente);

			cliente.setEmail(email);
			cliente.setPassword(password);
			cliente.setCodiceFiscale(codiceFiscale);
			cliente.setNome(nome);
			cliente.setCognome(cognome);

			manager.merge(cliente);
		}
	}
	
	/**@param email
	 * @return ClienteDTO*/
	public ClienteDTO findByEmailCliente(String email) {

		TypedQuery<Cliente> q = manager.createQuery("FROM Cliente c WHERE c.email=:new_email", Cliente.class);

		q.setParameter("new_email", email);

		ClienteDTO cliente = clienteToDTO(q.getSingleResult());

		return cliente;
	}

	/**@param idCliente
	 * @return ClienteDTO*/
	public ClienteDTO findByIdCliente(Long idCliente) {

		TypedQuery<Cliente> q = manager.createQuery("FROM Cliente c WHERE c.idCliente=:new_idCliente", Cliente.class);

		q.setParameter("new_idCliente", idCliente);

		ClienteDTO cliente = clienteToDTO(q.getSingleResult());

		return cliente;
	}

	/** @return ArrayList<idCliente> */
	public ArrayList<Long> findAll() {

		TypedQuery<Cliente> q = manager.createQuery("FROM Cliente c", Cliente.class);
		ArrayList<Long> lista = new ArrayList<Long>();
		
		for(int i=0; i<q.getResultList().size();i++){
			lista.add(q.getResultList().get(i).getIdCliente());
		}

		return lista;
	}


	/**@param email
	 * @param password
	 * @return idCliente*/
	public long verificaPresenzaClienteLogin(String email, String password) {
		try {
			TypedQuery<Cliente> q = manager
					.createQuery("FROM Cliente c WHERE c.email=:new_email", Cliente.class);
	
			q.setParameter("new_email", email);
	
			ClienteDTO cliente = clienteToDTO(q.getSingleResult());
	
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
	 * @param codiceFiscale
	 * @return true if email is present in the DB, otherwise false*/
	public boolean verificaPresenzaClienteRegistrazione(String email, String codiceFiscale) {
		try {
			TypedQuery<Cliente> q = manager
					.createQuery("FROM Cliente c WHERE c.email=:new_email OR c.codiceFiscale=:new_codiceFiscale", Cliente.class);

			q.setParameter("new_email", email);
			q.setParameter("new_codiceFiscale", codiceFiscale);

			if(q.getResultList().isEmpty()){
				return false;
			}
			return true;
			
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
			TypedQuery<Cliente> q = manager
					.createQuery("FROM Cliente c WHERE c.idCliente=:new_idCliente", Cliente.class);

			q.setParameter("new_idCliente", idCliente);

			
			if(q.getResultList().isEmpty()){
				return false;
			}
			return true;

			
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	/**
	 * @param id
	 * @return ArrayList<PacchettoPersonalizzatoDTO>
	 */
	public ArrayList<PacchettoPersonalizzatoDTO> elencoPacchettiCliente (long id){
		ClienteDTO c = findByIdCliente(id);
		
		ArrayList<PacchettoPersonalizzatoDTO> elencoPacchettiCliente = (ArrayList<PacchettoPersonalizzatoDTO>) c.getPacchettiCliente();
		
		return elencoPacchettiCliente;
	}

	/**
	 * @param id
	 * @return ArrayList<PacchettoPersonalizzatoDTO>
	 */
	public ArrayList<PacchettoPersonalizzatoDTO> giftListCliente (long id){
		ClienteDTO c = findByIdCliente(id);
		
		ArrayList<PacchettoPersonalizzatoDTO> elencoPacchettiCliente = (ArrayList<PacchettoPersonalizzatoDTO>) c.getGiftList();
		
		return elencoPacchettiCliente;
	}
	
	
	/**@param idCliente
	 */
	public void eliminaTuttiPacchettiPersonalizzati(long idCliente) {

		Cliente cliente = manager.find(Cliente.class, idCliente);
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
	

	
	public ClienteDTO clienteToDTO (Cliente cliente){
		
		ClienteDTO clienteDTO= new ClienteDTO();
		clienteDTO.setIdCliente(cliente.getIdCliente());
		clienteDTO.setEmail(cliente.getEmail());	
		clienteDTO.setPassword(cliente.getPassword());
		clienteDTO.setNome(cliente.getNome());
		clienteDTO.setCognome(cliente.getCognome());
		clienteDTO.setCodiceFiscale(cliente.getCodiceFiscale());
		
		List<PacchettoPersonalizzatoDTO> pacchetti= new ArrayList<PacchettoPersonalizzatoDTO>();
		for(int i=0; i<cliente.getPacchettiCliente().size();i++){
			pacchetti.add(pacchettoPersonalizzatoToDTOInCliente(cliente.getPacchettiCliente().get(i)));
		}
		clienteDTO.setPacchettiCliente(pacchetti);
		
		List<PacchettoPersonalizzatoDTO> pacchettiGiftList= new ArrayList<PacchettoPersonalizzatoDTO>();
		for(int i=0; i<cliente.getGiftList().size();i++){
			pacchettiGiftList.add(pacchettoPersonalizzatoToDTOInCliente(cliente.getGiftList().get(i)));
		}
		clienteDTO.setGiftList(pacchettiGiftList);
		
		
		return clienteDTO;
		
		
	}
	
	public Cliente clienteDTOToCliente (ClienteDTO clienteDTO){
		
		Cliente cliente= new Cliente();
		cliente.setIdCliente(clienteDTO.getIdCliente());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setPassword(DigestUtils.sha256Hex(clienteDTO.getPassword()));
		cliente.setNome(clienteDTO.getNome());
		cliente.setCognome(clienteDTO.getCognome());
		cliente.setCodiceFiscale(clienteDTO.getCodiceFiscale());
		
		List<PacchettoPersonalizzato> pacchetti= new ArrayList<PacchettoPersonalizzato>();
		for(int i=0; i<clienteDTO.getPacchettiCliente().size();i++){
			pacchetti.add(pacchettoPersonalizzatoDTOToPacchettoPersInCliente(clienteDTO.getPacchettiCliente().get(i)));
		}
		cliente.setPacchettiCliente(pacchetti);
		
		List<PacchettoPersonalizzato> pacchettiGiftList= new ArrayList<PacchettoPersonalizzato>();
		for(int i=0; i<clienteDTO.getGiftList().size();i++){
			pacchettiGiftList.add(pacchettoPersonalizzatoDTOToPacchettoPersInCliente(clienteDTO.getGiftList().get(i)));
		}
		cliente.setGiftList(pacchettiGiftList);
		
		return cliente;
		
	}
	
	
	public PacchettoPersonalizzatoDTO pacchettoPersonalizzatoToDTOInCliente(PacchettoPersonalizzato pacchettoPersonalizzato){
		
		PacchettoPersonalizzatoBean pacchettoPersonalizzatoBean= new PacchettoPersonalizzatoBean();
		PacchettoPersonalizzatoDTO pacchettoPersonalizzatoDTO= new PacchettoPersonalizzatoDTO();
		pacchettoPersonalizzatoDTO=pacchettoPersonalizzatoBean.pacchettoPersonalizzatoToDTO(pacchettoPersonalizzato);
		
		return pacchettoPersonalizzatoDTO;
	}
	
	
public PacchettoPersonalizzato pacchettoPersonalizzatoDTOToPacchettoPersInCliente(PacchettoPersonalizzatoDTO pacchettoPersonalizzatoDTO){
		
		PacchettoPersonalizzatoBean pacchettoPersonalizzatoBean= new PacchettoPersonalizzatoBean();
		PacchettoPersonalizzato pacchettoPersonalizzato= new PacchettoPersonalizzato();
		pacchettoPersonalizzato=pacchettoPersonalizzatoBean.pacchettoPersonalizzatoDTOToPacchettoPersonalizzato(pacchettoPersonalizzatoDTO);
		
		return pacchettoPersonalizzato;
	}
}
