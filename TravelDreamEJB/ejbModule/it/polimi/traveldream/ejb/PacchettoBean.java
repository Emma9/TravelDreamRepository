package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.ejb.client.PacchettoBeanLocal;
import it.polimi.traveldream.ejb.client.PacchettoBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.PacchettoDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**Session Bean implementation class PacchettoBean*/
@Stateless
public class PacchettoBean implements PacchettoBeanRemote, PacchettoBeanLocal {

	@PersistenceContext(unitName = "travelDream_project") private EntityManager manager;

	/**Default constructor*/
	public PacchettoBean() {
		// TODO Auto-generated constructor stub
	}

	/**@param destinazione
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param etichette
	 * @param descrizione
	 * @param listaComponenti
	 * @return idPacchetto*/
	public Long createPacchetto(String destinazione, Date dataInizioValidita, Date dataFineValidita, String etichetta, String descrizione, List<ComponenteDTO> listaComponenti) {

		
		if((verificaListaComponenti(listaComponenti))&&(verificaEtichetta(etichetta))){
		
		PacchettoDTO pacchetto = new PacchettoDTO();

		pacchetto.setDestinazione(destinazione);
		pacchetto.setDataInizioValidita(dataInizioValidita);
		pacchetto.setDataFineValidita(dataFineValidita);
		pacchetto.setEtichetta(etichetta);
		pacchetto.setDescrizione(descrizione);
		pacchetto.setListaComponenti(listaComponenti);

		manager.persist(pacchetto);
		
		return pacchetto.getIdPacchetto();

		}else{
			return (long) -1;
		}
		
	}

	/**@param idPacchetto*/
	public void removePacchetto(Long idPacchetto) {

		PacchettoDTO p = findByIdPacchetto(idPacchetto);
		
		manager.remove(p);

	}

	/**@param idPacchetto
	 * @param destinazione
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param etichette
	 * @param descrizione
	 * @param listaComponenti*/
	public void updatePacchetto(Long idPacchetto, String destinazione, Date dataInizioValidita, Date dataFineValidita, String etichetta, String descrizione, List<ComponenteDTO> listaComponenti) {

		if ((verificaPresenzaPacchetto(idPacchetto))&&(verificaListaComponenti(listaComponenti))&&(verificaEtichetta(etichetta))) {
			
			PacchettoDTO pacchetto = findByIdPacchetto(idPacchetto);

			pacchetto.setDestinazione(destinazione);
			pacchetto.setDataInizioValidita(dataInizioValidita);
			pacchetto.setDataFineValidita(dataFineValidita);
			pacchetto.setEtichetta(etichetta);
			pacchetto.setDescrizione(descrizione);
			pacchetto.setListaComponenti(listaComponenti);

			manager.merge(pacchetto);
		}
	}

	/**@param destinazione
	 * @return ArrayList<idPacchetto>*/
	public ArrayList<Long> findByDestinazione(String destinazione) {

		TypedQuery<PacchettoDTO> q = manager.createQuery("FROM Pacchetto p WHERE p.destinazione=:new_destinazione", PacchettoDTO.class);

		q.setParameter("new_destinazione", destinazione);

		ArrayList<Long> pacchetti = new ArrayList<Long>();
		List<PacchettoDTO> risultati = q.getResultList();

		for (int i = 0; i <= risultati.size(); i++) {

			pacchetti.set(i, risultati.get(i).getIdPacchetto());
			
		}
		return pacchetti;
	}

	/**@param etichetta
	 * @return ArrayList<idPacchetto>*/
	public ArrayList<Long> findByEtichetta(String etichetta) {

		TypedQuery<PacchettoDTO> q = manager.createQuery("FROM Pacchetto p", PacchettoDTO.class);

		ArrayList<PacchettoDTO> pacchetti = new ArrayList<PacchettoDTO>();
		ArrayList<Long> idPacchetti = new ArrayList<Long>();
		List<PacchettoDTO> risultati = q.getResultList();

		for (int i = 0; i <= risultati.size(); i++) {

			if (risultati.get(i).getEtichetta().equals(etichetta)) {

				pacchetti.set(i, risultati.get(i));

			}
		}

		for (int j = 0; j <= pacchetti.size(); j++) {

			if (pacchetti.get(j).equals(null)) {
				
			} else {
				
				idPacchetti.set(j, pacchetti.get(j).getIdPacchetto());
			
			}
		}
		return idPacchetti;
	}

	/**@param idPacchetto
	 * @return Pacchetto*/
	public PacchettoDTO findByIdPacchetto(Long idPacchetto) {

		TypedQuery<PacchettoDTO> q = manager.createQuery("FROM Pacchetto p WHERE p.idPacchetto=:new_idPacchetto", PacchettoDTO.class);

		q.setParameter("new_idPacchetto", idPacchetto);

		PacchettoDTO pacchetto = q.getSingleResult();

		return pacchetto;
	}
	

	/** @return ArrayList<idPacchetto> */
	public ArrayList<Long> findAll() {

		TypedQuery<PacchettoDTO> q = manager.createQuery("FROM Pacchetto p", PacchettoDTO.class);

		List<PacchettoDTO> pacchetti = q.getResultList();

		ArrayList<Long> lista = new ArrayList<Long>();

		for (int i = 0; i <= pacchetti.size(); i++) {

			lista.set(i, pacchetti.get(i).getIdPacchetto());
		}
		return lista;
	}
	
	/**@param dataPartenza
	 * @param dataRitorno
	 * @return true if dataPartenza comes before dataRitorno, otherwise false*/
	public boolean verificaConsistenzaDate (Date dataPartenza, Date dataRitorno){
		
		if(dataPartenza.before(dataRitorno)){
			
			return true;	
		}
		return false;
	}
	
	
	/**@param dataPartenza
	 * @param dataRitorno
	 * @param disponibilita
	 * @param listaComponenti
	 * @return true if , otherwise false*/
	public boolean verificaDisponibilitaComponenti (Date dataPartenza, Date dataRitorno, int disponibilita, List<ComponenteDTO> listaComponenti){
		
		ComponenteBeanRemote componenteRemoto = new ComponenteBean();		
		
		for(int i=0; i<=listaComponenti.size(); i++){
			
			componenteRemoto.findByCodiceComponente(listaComponenti.get(i).getCodiceComponente());
			
			if(componenteRemoto.verificaValiditaComponente(dataPartenza, dataRitorno, listaComponenti.get(i))){
				
				//componenti validi
				
				//if(componenteRemoto.verificaDisponibilitaComponente(disponibilita, listaComponenti.get(i))){
					
					//return true;
				//}
			}	
		}
		
		return false;
		
	}
	
	

	/**@param idPacchetto
	 * @return true if idPacchetto is present in the DB, otherwise false*/
	public boolean verificaPresenzaPacchetto(Long idPacchetto) {
		try {
			TypedQuery<PacchettoDTO> q = manager.createQuery("FROM Pacchetto p WHERE p.idPacchetto=:new_idPacchetto", PacchettoDTO.class);

			q.setParameter("new_idPacchetto", idPacchetto);

			List<PacchettoDTO> pacchetti = q.getResultList();

			if (pacchetti.size() == 0) {
				return false;

			} else {
				
				return true;

			}
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	
	/**@param listaComponenti
	 * @return true if listaComponenti contains more than two elements
	 *  and at least three of them are of a different type, otherwise false
	 */
	public boolean verificaListaComponenti (List<ComponenteDTO> listaComponenti){
		
		if(listaComponenti.size()>2){
				
			
			boolean flagHotel=false;
			boolean flagVolo=false;
			boolean flagEscursione=false;
			
			for(int i=0; i<=listaComponenti.size(); i++){
				
				ComponenteDTO componente = listaComponenti.get(i);
				
				switch (componente.getTipologia()){
				
				case "HOTEL":flagHotel=true;
									break;
					
				case "VOLO":flagVolo=true;
									break;
					
				case "ESCURSIONE":flagEscursione=true;
									break;	
				
				}
				
			}			
			
			if((flagHotel==true)&&(flagVolo==true)&&(flagEscursione==true)){
				
				return true;
			}	
			
		}
		return false;
	}

	/**@param destinazione
	 * @param dataPartenza
	 * @param dataRitorno
	 * @return ArrayList<Pacchetto>
	 */
	public ArrayList<PacchettoDTO> ricercaPacchetti (String destinazione, Date dataPartenza, Date dataRitorno){
		
		try {
			
			//DESTINAZIONE
			Query q1 = manager.createQuery("FROM Pacchetto p WHERE p.destinazione=:new_destinazione");

			q1.setParameter("new_destinazione", destinazione);
			
			//DATE
			Query q2 = manager.createQuery("FROM Pacchetto p WHERE p.dataInizioValidita<=new_dataPartenza AND p.dataInizioValidita<=new_dataRitorno AND p.dataFineValidita>=new_dataPartenza AND p.dataFineValidita>=new_dataRitorno");

			q2.setParameter("new_dataPartenza", dataPartenza);
			q2.setParameter("new_dataRitorno", dataRitorno);
			
			//INNER JOIN
			Query q3 = manager.createQuery("q1 INNER JOIN q2");

			q3.setParameter("q1", q1);
			q3.setParameter("q2", q2);			
			
			@SuppressWarnings("unchecked")
			ArrayList<PacchettoDTO> pacchetti = (ArrayList<PacchettoDTO>) q3.getResultList();
			
			return pacchetti;

			} catch (NullPointerException e) {
				
			return null;
			
		}

		
	}
	
	/**@param etichetta
	 * @param dataPartenza
	 * @param dataRitorno
	 * @return ArrayList<Pacchetto>
	 */
	public ArrayList<PacchettoDTO> ricercaPerEtichetta (String etichetta, Date dataPartenza, Date dataRitorno){
		
		try {
			
			
			
			//ETICHETTA
			Query q1 = manager.createQuery("FROM Pacchetto p WHERE p.etichette=:new_etichetta");

			q1.setParameter("new_etichetta", etichetta);
			
			//DATE
			Query q2 = manager.createQuery("FROM Pacchetto p WHERE p.dataInizioValidita<=new_dataPartenza AND p.dataInizioValidita<=new_dataRitorno AND p.dataFineValidita>=new_dataPartenza AND p.dataFineValidita>=new_dataRitorno");

			q2.setParameter("new_dataPartenza", dataPartenza);
			q2.setParameter("new_dataRitorno", dataRitorno);
			
			//INNER JOIN
			Query q3 = manager.createQuery("q1 INNER JOIN q2");

			q3.setParameter("q1", q1);
			q3.setParameter("q2", q2);			
			
			@SuppressWarnings("unchecked")
			ArrayList<PacchettoDTO> pacchetti = (ArrayList<PacchettoDTO>) q3.getResultList();
			
			return pacchetti;

			} catch (NullPointerException e) {
				
			return null;
			
		}

		
	}

public boolean verificaEtichetta (String etichetta) {
		
		switch (etichetta){
		
		case "LASTMINUTE":return true;
						
			
		case "OFFERTA":return true;
						
			
		case "MARE":return true;
							
		
		case "MONTAGNA":return true;
							
		
		}
		
		return false;
		
	}
	
}

	
	


