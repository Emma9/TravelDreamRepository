package it.polimi.traveldream.ejb;

import it.polimi.traveldream.entities.Etichetta;
import it.polimi.traveldream.entities.PacchettoPersonalizzato;
import it.polimi.traveldream.entities.Stato;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**Session Bean implementation class PacchettoPersonalizzatoBean*/
@Stateless
public class PacchettoPersonalizzatoBean implements	PacchettoPersonalizzatoBeanRemote, PacchettoPersonalizzatoBeanLocal {

	@PersistenceContext(unitName = "travelDream_project") private EntityManager manager;

	/**Default constructor*/
	public PacchettoPersonalizzatoBean() {
		// TODO Auto-generated constructor stub
	}

    /**@param stato
	 * @param idCliente
	 * @return idPacchettoPersonalizzato*/
	public Long createPacchettoPersonalizzato(Stato stato, Long idCliente) {

		PacchettoPersonalizzato pacchettoPersonalizzato = new PacchettoPersonalizzato();

		pacchettoPersonalizzato.setStato(stato);
		pacchettoPersonalizzato.setIdCliente(idCliente);

		return pacchettoPersonalizzato.getIdPacchettoPersonalizzato();

	}

	/**@param idCliente*/
	public void removePacchettoPersonalizzato(Long idCliente) {

		ArrayList<PacchettoPersonalizzato> pacchetti = findByIdCliente(idCliente);

		for (int i = 0; i <= pacchetti.size(); i++) {

			manager.remove(pacchetti.get(i));

		}
	}

	/**@param idPacchettoPersonalizzato
	 * @param stato
	 * @param listaComponenti*/
	public void updatePacchettoPersonalizzato(Long idPacchettoPersonalizzato,Stato stato, ArrayList<Long> listaComponenti) {

		if (verificaPresenzaPacchettoPersonalizzato(idPacchettoPersonalizzato)) {

			PacchettoPersonalizzato pacchettoPersonalizzato = findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato);

			pacchettoPersonalizzato.setStato(stato);
			pacchettoPersonalizzato.setListaComponenti(listaComponenti);

			manager.merge(pacchettoPersonalizzato);
			
		}
	}

	/**@param destinazione
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findByDestinazione(String destinazione) {

		Query q = manager.createQuery("FROM PacchettoPersonalizzato p WHERE p.destinazione=:new_destinazione");

		q.setParameter("new_destinazione", destinazione);

		ArrayList<Long> pacchetti = new ArrayList<Long>();
		@SuppressWarnings("unchecked")
		ArrayList<PacchettoPersonalizzato> risultati = (ArrayList<PacchettoPersonalizzato>) q.getResultList();

		for (int i = 0; i <= risultati.size(); i++) {

			pacchetti.set(i, risultati.get(i).getIdPacchettoPersonalizzato());
			
		}
		return pacchetti;
	}

	/**@param etichetta
	 * @return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findByEtichetta(Etichetta etichetta) {

		Query q = manager.createQuery("FROM PacchettoPersonalizzato p");

		ArrayList<PacchettoPersonalizzato> pacchetti = new ArrayList<PacchettoPersonalizzato>();
		ArrayList<Long> idPacchetti = new ArrayList<Long>();
		@SuppressWarnings("unchecked")
		ArrayList<PacchettoPersonalizzato> risultati = (ArrayList<PacchettoPersonalizzato>) q.getResultList();

		for (int i = 0; i <= risultati.size(); i++) {

			if (risultati.get(i).getEtichette().contains(etichetta)) {

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
	public PacchettoPersonalizzato findByIdPacchettoPersonalizzato(Long idPacchettoPersonalizzato) {

		Query q = manager
				.createQuery("FROM PacchettoPersonalizzato p WHERE p.idPacchettoPersonalizzato=:new_idPacchettoPersonalizzato");

		q.setParameter("new_idPacchettoPersonalizzato",idPacchettoPersonalizzato);

		PacchettoPersonalizzato pacchettoPersonalizzato = (PacchettoPersonalizzato) q.getSingleResult();

		return pacchettoPersonalizzato;
	}

	/**@param idCliente
	 * @return ArrayList<PacchettoPersonalizzato>*/
	public ArrayList<PacchettoPersonalizzato> findByIdCliente(Long idCliente) {

		Query q = manager.createQuery("FROM PacchettoPersonalizzato p WHERE p.idCliente=:new_idCliente");

		q.setParameter("new_idCliente", idCliente);

		@SuppressWarnings("unchecked")
		ArrayList<PacchettoPersonalizzato> pacchettiPersonalizzati = (ArrayList<PacchettoPersonalizzato>) q.getResultList();

		return pacchettiPersonalizzati;
		
	}

	/**@return ArrayList<idPacchettoPersonalizzato>*/
	public ArrayList<Long> findAll() {

		Query q = manager.createQuery("FROM PacchettoPersonalizzato p");

		@SuppressWarnings("unchecked")
		ArrayList<PacchettoPersonalizzato> pacchetti = (ArrayList<PacchettoPersonalizzato>) q.getResultList();

		ArrayList<Long> lista = new ArrayList<Long>();

		for (int i = 0; i <= pacchetti.size(); i++) {

			lista.set(i, pacchetti.get(i).getIdPacchetto());
			
		}

		return lista;
	}

	/** Metodi private */

	@Override
	public void eliminaTuttiPacchettiPersonalizzati(long idCliente) {
		// TODO Auto-generated method stub
		//da implementare!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
	}

	/**@param idPacchettoPersonalizzato
	 * @return true if idPacchettoPersonalizzato is not present in the DB, otherwise false*/
	private boolean verificaPresenzaPacchettoPersonalizzato(Long idPacchettoPersonalizzato) {
		try {
			Query q = manager.createQuery("FROM PacchettoPersonalizzato p WHERE p.idPacchettoPersonalizzato=:new_idPacchettoPersonalizzato");

			q.setParameter("new_idPacchettoPersonalizzato",idPacchettoPersonalizzato);

			@SuppressWarnings("unchecked")
			ArrayList<PacchettoPersonalizzato> pacchetti = (ArrayList<PacchettoPersonalizzato>) q.getResultList();

			if (pacchetti.size() == 0) {
				return true;

			} else {
				return false;

			}
		} catch (NullPointerException e) {
			return true;
		}
	}
}
