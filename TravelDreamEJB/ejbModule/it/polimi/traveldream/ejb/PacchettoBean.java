package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.PacchettoBeanLocal;
import it.polimi.traveldream.ejb.client.PacchettoBeanRemote;
import it.polimi.traveldream.entities.EtichettaDTO;
import it.polimi.traveldream.entities.PacchettoDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	 * @param disponibilita
	 * @param etichette
	 * @param descrizione
	 * @param listaComponenti
	 * @return idPacchetto*/
	public Long createPacchetto(String destinazione, Date dataInizioValidita, Date dataFineValidita, int disponibilita, ArrayList<EtichettaDTO> etichette, String descrizione,ArrayList<Long> listaComponenti) {

		PacchettoDTO pacchetto = new PacchettoDTO();

		pacchetto.setDestinazione(destinazione);
		pacchetto.setDataInizioValidita(dataInizioValidita);
		pacchetto.setDataFineValidita(dataFineValidita);
		pacchetto.setDisponibilita(disponibilita);
		pacchetto.setEtichette(etichette);
		pacchetto.setDescrizione(descrizione);
		pacchetto.setListaComponenti(listaComponenti);

		return pacchetto.getIdPacchetto();

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
	 * @param disponibilita
	 * @param etichette
	 * @param descrizione
	 * @param listaComponenti*/
	public void updatePacchetto(Long idPacchetto, String destinazione, Date dataInizioValidita, Date dataFineValidita, int disponibilita, ArrayList<EtichettaDTO> etichette, String descrizione,ArrayList<Long> listaComponenti) {

		if (verificaPresenzaPacchetto(idPacchetto)) {

			PacchettoDTO pacchetto = findByIdPacchetto(idPacchetto);

			pacchetto.setDestinazione(destinazione);
			pacchetto.setDataInizioValidita(dataInizioValidita);
			pacchetto.setDataFineValidita(dataFineValidita);
			pacchetto.setDisponibilita(disponibilita);
			pacchetto.setEtichette(etichette);
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
	public ArrayList<Long> findByEtichetta(EtichettaDTO etichetta) {

		TypedQuery<PacchettoDTO> q = manager.createQuery("FROM Pacchetto p", PacchettoDTO.class);

		ArrayList<PacchettoDTO> pacchetti = new ArrayList<PacchettoDTO>();
		ArrayList<Long> idPacchetti = new ArrayList<Long>();
		List<PacchettoDTO> risultati = q.getResultList();

		for (int i = 0; i <= risultati.size(); i++) {

			if (risultati.get(i).getEtichette().contains(etichetta)) {

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
		
		else{
			
			return false;
			
		}
		
	}
	
	

	/**@param idPacchetto
	 * @return true if idPacchetto is not present in the DB, otherwise false*/
	private boolean verificaPresenzaPacchetto(Long idPacchetto) {
		try {
			TypedQuery<PacchettoDTO> q = manager.createQuery("FROM Pacchetto p WHERE p.idPacchetto=:new_idPacchetto", PacchettoDTO.class);

			q.setParameter("new_idPacchetto", idPacchetto);

			List<PacchettoDTO> pacchetti = q.getResultList();

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
