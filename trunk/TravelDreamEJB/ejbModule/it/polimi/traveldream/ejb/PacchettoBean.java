package it.polimi.traveldream.ejb;

import it.polimi.traveldream.entities.Etichetta;
import it.polimi.traveldream.entities.Pacchetto;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class PacchettoBean
 */
@Stateless
public class PacchettoBean implements PacchettoBeanLocal {
	
	@PersistenceContext(unitName="travelDream_project") private 
	EntityManager manager;

    /**
     * Default constructor. 
     */
    public PacchettoBean() {
        // TODO Auto-generated constructor stub
    }

    /**Altri metodi */

    /**@param destinazione
	 * @param etichette
	 * @param descrizione
	 * @param listaComponenti
	 * @return idPacchetto*/
	public Long createPacchetto(String destinazione,ArrayList<Etichetta> etichette,String descrizione,ArrayList<Long> listaComponenti) {
		
		Pacchetto pacchetto=new Pacchetto();
		
		pacchetto.setDestinazione(destinazione);
		pacchetto.setEtichette(etichette);
		pacchetto.setDescrizione(descrizione);
		pacchetto.setListaComponenti(listaComponenti);
		
		return pacchetto.getIdPacchetto();
		
	}
	
	/**@param idPacchetto*/
	public void removePacchetto(Long idPacchetto) {
		
		Pacchetto p=findByIdPacchetto(idPacchetto);
    	manager.remove(p);
		
	}

	/**@param idPacchetto
	 * @param destinazione
	 * @param etichette
	 * @param descrizione
	 * @param listaComponenti*/
	public void updatePacchetto(Long idPacchetto,String destinazione,ArrayList<Etichetta> etichette,String descrizione,ArrayList<Long> listaComponenti) {
	
			if (verificaPresenzaPacchetto(idPacchetto)){
			
			Pacchetto pacchetto = findByIdPacchetto(idPacchetto);
			
			pacchetto.setDestinazione(destinazione);
			pacchetto.setEtichette(etichette);
			pacchetto.setDescrizione(descrizione);
			pacchetto.setListaComponenti(listaComponenti);
			
			manager.merge(pacchetto);
		}	
	}
	
	/**@param destinazione
	 * @return ArrayList<idPacchetto>*/
	public ArrayList<Long> findByDestinazione(String destinazione) {
		
	Query q=manager.createQuery("FROM Pacchetto p WHERE p.destinazione=:new_destinazione");
	
	q.setParameter("new_destinazione", destinazione);
    
	ArrayList<Long> pacchetti=new ArrayList<Long>();
	@SuppressWarnings("unchecked")
	ArrayList<Pacchetto> risultati= (ArrayList<Pacchetto>) q.getResultList();
	
	for(int i=0; i<=risultati.size(); i++){
		
		pacchetti.set(i,risultati.get(i).getIdPacchetto());
	}
		
	return pacchetti;
	
	}
	
	/**@param etichetta
	 * @return ArrayList<idPacchetto>*/
	public ArrayList<Long> findByEtichetta(Etichetta etichetta) {
		
		Query q=manager.createQuery("FROM Pacchetto p");
	    
		ArrayList<Pacchetto> pacchetti= new ArrayList<Pacchetto>();
		ArrayList<Long> idPacchetti= new ArrayList<Long>();
		@SuppressWarnings("unchecked")
		ArrayList<Pacchetto> risultati= (ArrayList<Pacchetto>) q.getResultList();
		
		for(int i=0; i<=risultati.size(); i++){
			
			if(risultati.get(i).getEtichette().contains(etichetta)){
			
				pacchetti.set(i,risultati.get(i));
				
			}
		}
		
		for(int j=0; j<=pacchetti.size(); j++){
			
			if(pacchetti.get(j).equals(null)){
			}
			else {
				idPacchetti.set(j,pacchetti.get(j).getIdPacchetto());
			}
					
		}
		return idPacchetti;
	}

	/**@param idPacchetto
	 * @return Pacchetto*/
	public Pacchetto findByIdPacchetto(Long idPacchetto) {
					
		Query q=manager.createQuery("FROM Pacchetto p WHERE p.idPacchetto=:new_idPacchetto");
    	
		q.setParameter("new_idPacchetto", idPacchetto);
        
		Pacchetto pacchetto=(Pacchetto) q.getSingleResult();
			
		return pacchetto;
	}
	
	/**@return ArrayList<idPacchetto>*/
	public ArrayList<Long> findAll() {
		
		Query q=manager.createQuery("FROM Pacchetto p");
	       
		@SuppressWarnings("unchecked")
		ArrayList<Pacchetto> pacchetti=(ArrayList<Pacchetto>) q.getResultList();
		
		ArrayList<Long> lista=new ArrayList<Long>();
		
		for(int i=0; i<=pacchetti.size(); i++){
			
			lista.set(i, pacchetti.get(i).getIdPacchetto());
		}
		
		return lista;
		
	}
	
	/**Metodi private*/
	
	/**@param idPacchetto
	 * @return true if idPacchetto is not present in DB, otherwise false*/
	private boolean verificaPresenzaPacchetto(Long idPacchetto){
    	try{
    		Query q=manager.createQuery("FROM Pacchetto p WHERE p.idPacchetto=:new_idPacchetto");
	    	
    		q.setParameter("new_idPacchetto", idPacchetto);
	    		    	
	    	@SuppressWarnings("unchecked")
			ArrayList<Pacchetto> pacchetti=(ArrayList<Pacchetto>) q.getResultList();
	    	
	    	if(pacchetti.size()==0){
	    		return true;
	    	
	    	}else{
	    		return false;	
	    	
	    	}
    	}catch(NullPointerException e){
    		return true;
    	}
    }
}
