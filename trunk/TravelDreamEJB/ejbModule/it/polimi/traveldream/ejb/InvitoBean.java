package it.polimi.traveldream.ejb;

import it.polimi.traveldream.entities.Invito;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class InvitoBean
 */
@Stateless
public class InvitoBean implements InvitoBeanLocal {

	@PersistenceContext(unitName="travelDream_project") private EntityManager manager;
	
    /**
     * Default constructor. 
     */
    public InvitoBean() {
        // TODO Auto-generated constructor stub
    }

    /**Altri metodi */

    /**@param emailMittente
	 * @param emailDestinatario
	 * @param idPacchettoPersonalizzato
	 * @param data
	 * @param stato
	 * @return idInvito*/
	public Long createInvito(String emailMittente,String emailDestinatario,String idPacchettoPersonalizzato,String data,String stato) {

		Invito invito=new Invito();
		
		invito.setEmailMittente(emailMittente);
		invito.setEmailDestinatario(emailDestinatario);
		invito.setIdPacchettoPersonalizzato(idPacchettoPersonalizzato);
		invito.setData(data);
		invito.setStato(stato);
				
		return invito.getIdInvito();
		
	}
	
	/**@param idInvito*/
	public void removeInvito(String emailMittente) {
		
		ArrayList<Invito> inviti = findByEmailMittente(emailMittente);
		
		for(int i=0;i<=inviti.size();i++){
	
			manager.remove(inviti.get(i));
			}	  	
	}

	/**@param idInvito
	 * @param emailMittente
	 * @param emailDestinatario
	 * @param idPacchettoPersonalizzato
	 * @param data
	 * @param stato*/
	public void updateInvito(Long idInvito,String emailMittente,String emailDestinatario,String idPacchettoPersonalizzato,String data,String stato) {
	
		if (verificaPresenzaInvito(idInvito)){
			
		Invito invito = findByIdInvito(idInvito);
		
		invito.setEmailMittente(emailMittente);
		invito.setEmailDestinatario(emailDestinatario);
		invito.setIdPacchettoPersonalizzato(idPacchettoPersonalizzato);
		invito.setData(data);
		
		manager.merge(invito);
	}	
		
	}

	/**@param idInvito
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public Invito findByIdInvito(Long idInvito) {
		
		Query q=manager.createQuery("FROM Invito i WHERE i.idInvito=:new_idInvito");
		
		q.setParameter("new_idInvito", idInvito);
	    
		Invito invito=(Invito) q.getSingleResult();
							
		return invito;	
		
	}	
	
	/**@param email
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Invito> findByEmailMittente(String emailMittente) {
		
		Query q=manager.createQuery("FROM Invito i WHERE i.emailMittente=:new_emailMittente");
		
		q.setParameter("new_emailMittente", emailMittente);
	    
		ArrayList<Invito> inviti=new ArrayList<Invito>();
		@SuppressWarnings("unchecked")
		ArrayList<Invito> risultati= (ArrayList<Invito>) q.getResultList();
		
		for(int i=0; i<=risultati.size(); i++){
			
			inviti.set(i,risultati.get(i));
		}
			
		return inviti;	
		
	}	
	
	/**@return ArrayList<idInvito>*/
	public ArrayList<Long> findAll() {
		return null;
	}
	
	/**Metodi private*/
	
	/**@param idInvito
	 * @return true if idInvito is not present in DB, otherwise false*/
	private boolean verificaPresenzaInvito(Long idInvito){
    	try{
    		Query q=manager.createQuery("FROM Invito i WHERE i.idInvito=:new_idInvito");
	    	
    		q.setParameter("new_idInvito", idInvito);
	    		    	
	    	@SuppressWarnings("unchecked")
			ArrayList<Invito> inviti=(ArrayList<Invito>) q.getResultList();
	    	
	    	if(inviti.size()==0){
	    		return true;
	    	
	    	}else{
	    		return false;	
	    	
	    	}
    	}catch(NullPointerException e){
    		return true;
    	}
    }
	
	}
