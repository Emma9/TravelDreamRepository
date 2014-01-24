package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.ComponenteBeanLocal;
import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**Session Bean implementation class ComponenteBean*/
@Stateless
public class ComponenteBean implements ComponenteBeanRemote, ComponenteBeanLocal {

	@PersistenceContext(unitName = "travelDream_project") private EntityManager manager;

	/**Default constructor*/
	public ComponenteBean() {
		// TODO Auto-generated constructor stub
	}

	/**@param tipologia
	 * @param descrizione
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param disponibilita
	 * @return codiceComponente*/
	public Long createComponente(String tipologia, String descrizione, Date dataInizioValidita, Date dataFineValidita, int disponibilita) {

		if(verificaTipologia(tipologia)){
		
			ComponenteDTO componente = new ComponenteDTO();

			componente.setTipologia(tipologia);
			componente.setDescrizione(descrizione);
			componente.setDataInizioValidita(dataInizioValidita);
			componente.setDataFineValidita(dataFineValidita);
			componente.setDisponibilita(disponibilita);

			return componente.getCodiceComponente();
			
		}
		
		return (long) -1;

	}

	/**@param codiceComponente*/
	public void removeComponente(Long codiceComponente) {

		ComponenteDTO c = findByCodiceComponente(codiceComponente);
		
		manager.remove(c);

	}

	/**@param codiceComponente
	 * @param tipologia
	 * @param descrizione
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param disponibilita
	 */
	public void updateComponente(Long codiceComponente, String tipologia, String descrizione, Date dataInizioValidita, Date dataFineValidita, int disponibilita) {

		if ((verificaPresenzaComponente(codiceComponente))&&(verificaTipologia(tipologia))) {

			ComponenteDTO componente = findByCodiceComponente(codiceComponente);

			componente.setTipologia(tipologia);
			componente.setDescrizione(descrizione);
			componente.setDataInizioValidita(dataInizioValidita);
			componente.setDataFineValidita(dataFineValidita);
			componente.setDisponibilita(disponibilita);			

			manager.merge(componente);
		}

	}

	/**@param codiceComponente
	 * @return ComponenteDTO*/
	public ComponenteDTO findByCodiceComponente(Long codiceCompoente) {

		TypedQuery<ComponenteDTO> q = manager.createQuery("FROM Componente c WHERE c.codiceComponente=:new_codiceComponente", ComponenteDTO.class);

		q.setParameter("new_codiceComponente", codiceCompoente);

		ComponenteDTO componente = q.getSingleResult();

		return componente;
	}

	/**@return ArrayList<codiceComponente>*/
	public ArrayList<Long> findAll() {

		TypedQuery<ComponenteDTO> q = manager.createQuery("FROM Componente c", ComponenteDTO.class);

		List<ComponenteDTO> componenti = q.getResultList();

		ArrayList<Long> lista = new ArrayList<Long>();

		for (int i = 0; i <= componenti.size(); i++) {

			lista.set(i, componenti.get(i).getCodiceComponente());
			
		}
		return lista;
	}

	
	/**@param dataPartenza
	 * @param dataRitorno
	 * @param codiceComponente
	 * @return true if componente is valid, otherwise false
	 */
	public boolean verificaValiditaComponente (Date dataPartenza, Date dataRitorno, Long codiceComponente){
		
		ComponenteDTO componente = findByCodiceComponente(codiceComponente);
				
		
		if((!dataPartenza.before(componente.getDataInizioValidita())) && (!dataPartenza.after(componente.getDataFineValidita())) && (!dataRitorno.before(componente.getDataInizioValidita())) && (!dataRitorno.after(componente.getDataFineValidita()))){
			
			return true;
			
		}else{
		
			return false;
			
		}
					
		
	}
	
	
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!! disponibilità in una data!?!?!

	/**@param disponibilita
	 * @param codiceComponente
	 * @return true if componente is available, otherwise false	
	 */
public boolean verificaDisponibilitaComponente (int disponibilita, Long codiceComponente){
		
		ComponenteDTO componente = findByCodiceComponente(codiceComponente); 
		
		if(componente.getDisponibilita()<disponibilita){
			
			return false;
						
		}else{
			
			return true;
			
		}
				
		
	}
	
	/**@param codiceComponente
	 * @return true if codiceComponente is not present in the DB, otherwise false*/
	public boolean verificaPresenzaComponente(Long codiceComponente) {
		try {
			TypedQuery<ComponenteDTO> q = manager.createQuery("FROM Componente c WHERE c.codiceComponente=:new_codiceComponente", ComponenteDTO.class);

			q.setParameter("new_codiceComponente", codiceComponente);

			List<ComponenteDTO> componenti = q.getResultList();

			if (componenti.size() == 0) {
				return true;

			} else {
				return false;

			}
		} catch (NullPointerException e) {
			return true;
		}
	}
	
public boolean verificaTipologia (String tipologia) {
		
		switch (tipologia){
		
		case "HOTEL":return true;
						
			
		case "VOLO":return true;
						
			
		case "ESCURSIONE":return true;
						
		
		}
		
		return false;
		
	}
	
}
