package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.PacchettoPersonalizzatoBeanLocal;
import it.polimi.traveldream.ejb.client.PacchettoPersonalizzatoBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**Session Bean implementation class PacchettoPersonalizzatoBean*/
@Stateless
public class PacchettoPersonalizzatoBean implements	PacchettoPersonalizzatoBeanRemote, PacchettoPersonalizzatoBeanLocal {

	@PersistenceContext(unitName = "travelDream_project") 
	private EntityManager manager;

	/**Default constructor*/
	public PacchettoPersonalizzatoBean() {
		// TODO Auto-generated constructor stub
	}

    /**@param stato
	 * @param idCliente
	 * @return idPacchettoPersonalizzato*/
	public Long createPacchettoPersonalizzato(String stato, Long idCliente) {

		if(verificaStato(stato)){
			
			PacchettoPersonalizzatoDTO pacchettoPersonalizzato = new PacchettoPersonalizzatoDTO();

			pacchettoPersonalizzato.setStato(stato);
			pacchettoPersonalizzato.setIdCliente(idCliente);

			manager.persist(pacchettoPersonalizzato);
			
			return pacchettoPersonalizzato.getIdPacchettoPersonalizzato();
			
		}else{
			
			return (long) -1;
			
			
		}
		

	}

	/**@param idCliente*/
	public void removePacchettoPersonalizzato(Long idCliente) {

		ArrayList<PacchettoPersonalizzatoDTO> pacchetti = findByIdCliente(idCliente);

		for (int i = 0; i <= pacchetti.size(); i++) {

			manager.remove(pacchetti.get(i));

		}
	}

	/**@param idPacchettoPersonalizzato
	 * @param stato
	 * @param listaComponenti*/
	public void updatePacchettoPersonalizzato(Long idPacchettoPersonalizzato,String stato, List<ComponenteDTO> listaComponenti) {

		if ((verificaPresenzaPacchettoPersonalizzato(idPacchettoPersonalizzato))&&(verificaStato(stato))) {

			PacchettoPersonalizzatoDTO pacchettoPersonalizzato = findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato);

			pacchettoPersonalizzato.setStato(stato);
			pacchettoPersonalizzato.setListaComponenti(listaComponenti);

			manager.merge(pacchettoPersonalizzato);
			
		}
	}

	/**@param destinazione
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findByDestinazione(String destinazione) {

		TypedQuery<PacchettoPersonalizzatoDTO> q = manager.createQuery("FROM PacchettoPersonalizzato p WHERE p.destinazione=:new_destinazione", PacchettoPersonalizzatoDTO.class);

		q.setParameter("new_destinazione", destinazione);

		ArrayList<Long> pacchetti = new ArrayList<Long>();
		List<PacchettoPersonalizzatoDTO> risultati = q.getResultList();

		for (int i = 0; i <= risultati.size(); i++) {

			pacchetti.set(i, risultati.get(i).getIdPacchettoPersonalizzato());
			
		}
		return pacchetti;
	}

	/**@param etichetta
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findByEtichetta(String etichetta) {

		TypedQuery<PacchettoPersonalizzatoDTO> q = manager.createQuery("FROM PacchettoPersonalizzato p", PacchettoPersonalizzatoDTO.class);

		ArrayList<PacchettoPersonalizzatoDTO> pacchetti = new ArrayList<PacchettoPersonalizzatoDTO>();
		ArrayList<Long> idPacchetti = new ArrayList<Long>();
		List<PacchettoPersonalizzatoDTO> risultati = q.getResultList();

		for (int i = 0; i <= risultati.size(); i++) {

			if (risultati.get(i).getEtichetta().equals(etichetta)) {

				pacchetti.set(i, risultati.get(i));

			}
		}

		for (int j = 0; j <= pacchetti.size(); j++) {

			if (pacchetti.get(j).equals(null)) {
			} else {
				idPacchetti.set(j, pacchetti.get(j).getIdPacchettoPersonalizzato());
			}

		}
		return idPacchetti;
	}

	/**@param idPacchettoPersonalizzato
	 * @return PacchettoPersonalizzato*/
	public PacchettoPersonalizzatoDTO findByIdPacchettoPersonalizzato(Long idPacchettoPersonalizzato) {

		TypedQuery<PacchettoPersonalizzatoDTO> q = manager
				.createQuery("FROM PacchettoPersonalizzato p WHERE p.idPacchettoPersonalizzato=:new_idPacchettoPersonalizzato", PacchettoPersonalizzatoDTO.class);

		q.setParameter("new_idPacchettoPersonalizzato",idPacchettoPersonalizzato);

		PacchettoPersonalizzatoDTO pacchettoPersonalizzato = q.getSingleResult();

		return pacchettoPersonalizzato;
	}

	/**@param idCliente
	 * @return ArrayList<PacchettoPersonalizzato>*/
	public ArrayList<PacchettoPersonalizzatoDTO> findByIdCliente(Long idCliente) {

		TypedQuery<PacchettoPersonalizzatoDTO> q = manager.createQuery("FROM PacchettoPersonalizzato p WHERE p.idCliente=:new_idCliente", PacchettoPersonalizzatoDTO.class);

		q.setParameter("new_idCliente", idCliente);

		List<PacchettoPersonalizzatoDTO> pacchettiPersonalizzatiList = q.getResultList();

		ArrayList<PacchettoPersonalizzatoDTO> pacchettiPersonalizzati= new ArrayList<>(pacchettiPersonalizzatiList);
		return pacchettiPersonalizzati;
		
	}

	/**@return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findAll() {

		TypedQuery<PacchettoPersonalizzatoDTO> q = manager.createQuery("FROM PacchettoPersonalizzato p", PacchettoPersonalizzatoDTO.class);

		List<PacchettoPersonalizzatoDTO> pacchetti = q.getResultList();

		ArrayList<Long> lista = new ArrayList<Long>();

		for (int i = 0; i <= pacchetti.size(); i++) {

			lista.set(i, pacchetti.get(i).getIdPacchetto());
			
		}

		return lista;
	}
	
	/**@param idPacchettoPersonalizzato
	 * @return true if idPacchettoPersonalizzato is present in the DB, otherwise false*/
	public boolean verificaPresenzaPacchettoPersonalizzato(Long idPacchettoPersonalizzato) {
		try {
			TypedQuery<PacchettoPersonalizzatoDTO> q = manager.createQuery("FROM PacchettoPersonalizzato p WHERE p.idPacchettoPersonalizzato=:new_idPacchettoPersonalizzato", PacchettoPersonalizzatoDTO.class);

			q.setParameter("new_idPacchettoPersonalizzato",idPacchettoPersonalizzato);

			List<PacchettoPersonalizzatoDTO> pacchetti = q.getResultList();

			if (pacchetti.size() == 0) {
				return false;

			} else {
				return true;

			}
		} catch (NullPointerException e) {
			return false;
		}
	}

	
	/**@param stato
	 * @return true if stato is valid, otherwise false
	 */
public boolean verificaStato (String stato) {
		
		switch (stato){
		
		case "CONFERMATO":return true;
						
			
		case "SALVATO":return true;
						
			
		case "BLOCCATO":return true;
							
		
		case "ACCETTATO":return true;
							
		
		}
		
		return false;
		
	}

}
