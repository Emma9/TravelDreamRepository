package it.polimi.traveldream.ejb;

import it.polimi.traveldream.entities.Componente;
import it.polimi.traveldream.entities.Tipologia;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ComponenteBean
 */
@Stateless
public class ComponenteBean implements ComponenteBeanLocal {
	
	@PersistenceContext(unitName="travelDream_project") private 
	EntityManager manager;

    /**
     * Default constructor. 
     */
    public ComponenteBean() {
        // TODO Auto-generated constructor stub
    }

    /**Altri metodi */

    /**@param tipologia
	 * @param descrizione
	 * @return codiceComponente*/
	public Long createComponente(Tipologia tipologia,String descrizione) {

			Componente componente=new Componente();
    		
    		componente.setTipologia(tipologia);
    		componente.setDescrizione(descrizione);
    		
    		return componente.getCodiceComponente();
    	
    	}		
		
	
	/**@param codiceComponente*/
	public void removeComponente(Long codiceComponente) {
	    	   		    	
	    	Componente c=findByCodiceComponente(codiceComponente);
	    	manager.remove(c);
		
	}

	/**@param codiceComponente
	 * @param tipologia
	 * @param descrizione*/
	public void updateComponente(Long codiceComponente,Tipologia tipologia,String descrizione) {
	
			if (verificaPresenzaComponente(codiceComponente)){
			
			Componente componente = findByCodiceComponente(codiceComponente);
			
			componente.setTipologia(tipologia);
			componente.setDescrizione(descrizione);
			
			manager.merge(componente);
		}	
	
	}
	
	/**@param codiceComponente
	 * @return Componente*/
	public Componente findByCodiceComponente(Long codiceCompoente) {
					
		Query q=manager.createQuery("FROM Componente c WHERE c.codiceComponente=:new_codiceComponente");
    	
		q.setParameter("new_codiceComponente", codiceCompoente);
        
		Componente componente=(Componente) q.getSingleResult();
			
		return componente;
	}	

	/**@return ArrayList<codiceComponente>*/
	public ArrayList<Long> findAll() {
		
		Query q=manager.createQuery("FROM Componente c");
	       
		@SuppressWarnings("unchecked")
		ArrayList<Componente> componenti=(ArrayList<Componente>) q.getResultList();
		
		ArrayList<Long> lista=new ArrayList<Long>();
		
		for(int i=0; i<=componenti.size(); i++){
			
			lista.set(i, componenti.get(i).getCodiceComponente());
		}
		
		return lista;
	}
	
	/**Metodi private*/
	
	/**@param idAmico
	 * @return true if idAmico is not present in DB, otherwise false*/
	private boolean verificaPresenzaComponente(Long codiceComponente){
    	try{
    		Query q=manager.createQuery("FROM Componente c WHERE c.codiceComponente=:new_codiceComponente");
	    	
    		q.setParameter("new_codiceComponente", codiceComponente);
	    		    	
	    	@SuppressWarnings("unchecked")
			ArrayList<Componente> componenti=(ArrayList<Componente>) q.getResultList();
	    	
	    	if(componenti.size()==0){
	    		return true;
	    	
	    	}else{
	    		return false;	
	    	
	    	}
    	}catch(NullPointerException e){
    		return true;
    	}
    }
}
