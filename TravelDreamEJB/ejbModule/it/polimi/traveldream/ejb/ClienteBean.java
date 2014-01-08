package it.polimi.traveldream.ejb;

import it.polimi.traveldream.entities.Cliente;

import java.util.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class ClienteBean
 */
@Stateless
public class ClienteBean implements ClienteBeanLocal {

	@PersistenceContext(unitName="travelDream_project") private 
	EntityManager manager;
	
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
		
		if(verificaCredenziali(email, codiceFiscale)){
    		
			Cliente cliente=new Cliente();
    		
    		cliente.setEmail(email);
    		cliente.setPassword(password);
    		cliente.setCodiceFiscale(codiceFiscale);
    		cliente.setNome(nome);
    		cliente.setCognome(cognome);
    		    		
    		manager.persist(cliente);
    		
    		return cliente.getIdCliente();

    		
    	}else{
    		return (long) -1;
    	}		
	}
	
	/**@param idCliente*/
	 public void removeCliente (Long id){
	    	InitialContext ctx;
			try {
				ctx = new InitialContext();
		    	PacchettoPersonalizzatoBeanLocal pacchettoPersonalizzatoLocal=(PacchettoPersonalizzatoBeanLocal) ctx.lookup("PacchettoPersonalizzatoBean/local");
		    	InvitoBeanLocal invitoLocal=(InvitoBeanLocal)ctx.lookup("InvitoBean/local");
		    	//ELIMINARE GIFTLIST CLIENTE
		    	
		    	pacchettoPersonalizzatoLocal.removePacchettoPersonalizzato(id);
		    	invitoLocal.removeInvito(daIdaEmail(id));
		    	
		    	Cliente c=findByIdCliente(id);
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
	public void updateCliente(Long idCliente,String email,String password,String codiceFiscale,String nome,String cognome) {
	
		if (verificaPresenzaCliente(idCliente)){
		
			Cliente cliente = findByIdCliente(idCliente);
			
			cliente.setEmail(email);
			cliente.setPassword(password);
			cliente.setCodiceFiscale(codiceFiscale);
			cliente.setNome(nome);
			cliente.setCognome(cognome);
		
			manager.merge(cliente);
		}
	}

	/**@param idCliente
	 * @return Cliente*/
	public Cliente findByIdCliente(Long idCliente) {
					
		Query q=manager.createQuery("FROM Cliente c WHERE c.idCliente=:new_idCliente");
    	
		q.setParameter("new_idCliente", idCliente);
        
		Cliente cliente=(Cliente) q.getSingleResult();
			
		return cliente;
	}	
			
	/**@return ArrayList<idCliente>*/
	public ArrayList<Long> findAll() {
					
		Query q=manager.createQuery("FROM Cliente c");
    			       
		@SuppressWarnings("unchecked")
		ArrayList<Cliente> clienti=(ArrayList<Cliente>) q.getResultList();
		
		ArrayList<Long> lista=new ArrayList<Long>();
		
		for(int i=0; i<=clienti.size(); i++){
			
			lista.set(i, clienti.get(i).getIdCliente());
		}
		
		return lista;
	}

	/**Metodi private*/

	/**@param email
	 * @return true if email is not present in DB, otherwise false*/
	private boolean verificaCredenziali(String email,String codiceFiscale){
    	try{
    		Query q=manager.createQuery("FROM Cliente c WHERE c.email=:new_email AND c.codiceFiscale=:new_codiceFiscale");
	    	
    		q.setParameter("new_email", email);
	    	q.setParameter("new_codiceFiscale", codiceFiscale);
	    	
	    	@SuppressWarnings("unchecked")
			ArrayList<Cliente> clienti=(ArrayList<Cliente>) q.getResultList();
	    	
	    	if(clienti.size()==0){
	    		return true;
	    	
	    	}else{
	    		return false;	
	    	
	    	}
    	}catch(NullPointerException e){
    		return true;
    	}
    }

	/**@param idCliente
	 * @return true if idCliente is not present in DB, otherwise false*/
	private boolean verificaPresenzaCliente(Long idCliente){
    	try{
    		Query q=manager.createQuery("FROM Cliente c WHERE c.idCliente=:new_idCliente");
	    	
    		q.setParameter("new_idCliente", idCliente);
	    		    	
	    	@SuppressWarnings("unchecked")
			ArrayList<Cliente> clienti=(ArrayList<Cliente>) q.getResultList();
	    	
	    	if(clienti.size()==0){
	    		return true;
	    	
	    	}else{
	    		return false;	
	    	
	    	}
    	}catch(NullPointerException e){
    		return true;
    	}
    }

	/**@param idCliente
	 * @return email*/
	private String daIdaEmail(Long idCliente){
		Cliente c=findByIdCliente(idCliente);
		return c.getEmail();
	}
	
}
