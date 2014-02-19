package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.ejb.client.PacchettoBeanLocal;
import it.polimi.traveldream.ejb.client.PacchettoBeanRemote;
import it.polimi.traveldream.entities.Componente;
import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.Pacchetto;
import it.polimi.traveldream.entities.PacchettoDTO;
import it.polimi.traveldream.entities.PacchettoPK;
import it.polimi.traveldream.entities.PacchettoPKDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.internal.sessions.DirectCollectionChangeRecord.NULL;

/** Session Bean implementation class PacchettoBean */
@Stateless
public class PacchettoBean implements PacchettoBeanRemote, PacchettoBeanLocal {

	@PersistenceContext(unitName = "travelDream_project")
	private EntityManager manager;

	/** Default constructor */
	public PacchettoBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param destinazione
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param etichetta
	 * @param descrizione
	 * @param listaComponenti
	 * @param sconto
	 * @return idPacchetto
	 */
	public Long createPacchetto(String destinazione, Date dataInizioValidita,
			Date dataFineValidita, String etichetta, String descrizione,
			List<ComponenteDTO> listaComponenti,
			List<ComponenteDTO> listaComponentiSelezionati, int sconto) {

		if ((verificaListaComponenti(listaComponentiSelezionati))
				&& (verificaEtichetta(etichetta))) {

			System.out.println("VERIFICA COMPONENTI & ETICHETTA CORRETTA");

			
			

			List<Componente> listaComponentiEnt = new ArrayList<Componente>();

			for (int i = 0; i < listaComponenti.size(); i++) {
				listaComponentiEnt
						.add(componenteDTOToComponenteInPacchetto(listaComponenti
								.get(i)));
			}

			

			System.out.println("SET LISTA COMPONENTI");

			List<Componente> listaComponentiSelezionatiEnt = new ArrayList<Componente>();
			for (int i = 0; i < listaComponentiSelezionati.size(); i++) {
				listaComponentiSelezionatiEnt
						.add(componenteDTOToComponenteInPacchetto(listaComponentiSelezionati
								.get(i)));
			}

			
			System.out.println("SET LISTA COMPONENTI SELEZIONATI");

		

			int costoPacchetto = calcolaCostoPacchetto(listaComponenti, sconto);
			

			System.out.println("SET COSTO");

			Pacchetto pacchetto = new Pacchetto(destinazione, dataInizioValidita, dataFineValidita, etichetta, descrizione, listaComponentiEnt, listaComponentiSelezionatiEnt, costoPacchetto, sconto);
					
			
			manager.persist(pacchetto);

			System.out.println("PERSIST PACCHETTO");

			return pacchetto.getIdPacchetto();

		} else {

			System.out.println("VERIFICA COMPONENTI & ETICHETTA FALLITA");

			return (long) -1;
		}

	}

	/** @param idPacchetto */
	public void removePacchetto(Long idPacchetto) {

		System.out.println("SESSION PRIMA DI FIND");

		Pacchetto p = manager.find(Pacchetto.class, new PacchettoPKDTO(idPacchetto, (long)0));

		System.out.println("SESSION DOPO FIND");

		manager.remove(p);

		System.out.println("SESSION DOPO RIMOZIONE");
	}

	/**
	 * @param idPacchetto
	 * @param destinazione
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param etichetta
	 * @param descrizione
	 * @param listaComponenti
	 * @param sconto
	 */
	public void updatePacchetto(Long idPacchetto, String destinazione,
			Date dataInizioValidita, Date dataFineValidita, String etichetta,
			String descrizione, List<ComponenteDTO> listaComponenti,
			List<ComponenteDTO> listaComponentiSelezionati, int sconto) {

		if ((verificaPresenzaPacchetto(idPacchetto))
				&& (verificaListaComponenti(listaComponentiSelezionati))
				&& (verificaEtichetta(etichetta))) {

			PacchettoPK chiave= new PacchettoPK(idPacchetto, (long)0);
			
			Pacchetto pacchetto = manager.find(Pacchetto.class, chiave);
			
			
			pacchetto.setDestinazione(destinazione);
			pacchetto.setDataInizioValidita(dataInizioValidita);
			pacchetto.setDataFineValidita(dataFineValidita);
			pacchetto.setEtichetta(etichetta);
			pacchetto.setDescrizione(descrizione);

			List<Componente> listaComponentiEnt = new ArrayList<Componente>();
			for (int i = 0; i < listaComponenti.size(); i++) {
				listaComponentiEnt
						.add(componenteDTOToComponenteInPacchetto(listaComponenti
								.get(i)));
			}
			pacchetto.setListaComponenti(listaComponentiEnt);

			List<Componente> listaComponentiSelezionatiEnt = new ArrayList<Componente>();
			for (int i = 0; i < listaComponentiSelezionati.size(); i++) {
				listaComponentiSelezionatiEnt
						.add(componenteDTOToComponenteInPacchetto(listaComponentiSelezionati
								.get(i)));
			}
			pacchetto
					.setListaComponentiSelezionati(listaComponentiSelezionatiEnt);

			pacchetto.setSconto(sconto);

			int costoPacchetto = calcolaCostoPacchetto(listaComponenti, sconto);
			pacchetto.setCosto(costoPacchetto);

			manager.merge(pacchetto);
		}
	}

	/**
	 * @param destinazione
	 * @return ArrayList<idPacchetto>
	 */
	public ArrayList<Long> findByDestinazione(String destinazione) {

		TypedQuery<Pacchetto> q = manager.createQuery(
				"FROM Pacchetto p WHERE p.destinazione=:new_destinazione",
				Pacchetto.class);

		q.setParameter("new_destinazione", destinazione);

		ArrayList<Long> pacchetti = new ArrayList<Long>();

		for (int i = 0; i < q.getResultList().size(); i++) {

			pacchetti.add(q.getResultList().get(i).getIdPacchetto());

		}
		return pacchetti;
	}

	/**
	 * @param etichetta
	 * @return ArrayList<idPacchetto>
	 */
	public ArrayList<Long> findByEtichetta(String etichetta) {

		TypedQuery<Pacchetto> q = manager.createQuery("FROM Pacchetto p",
				Pacchetto.class);

		ArrayList<Long> pacchetti = new ArrayList<Long>();

		for (int i = 0; i < q.getResultList().size(); i++) {

			if (q.getResultList().get(i).getEtichetta().contains(etichetta)) {
				pacchetti.add(q.getResultList().get(i).getIdPacchetto());
			}

		}
		return pacchetti;
	}

	/**
	 * @param etichetta
	 * @return ArrayList<PacchettoDTO>
	 */
	public ArrayList<PacchettoDTO> findByEtichettaOGG(String etichetta) {

		// TypedQuery<Pacchetto> q = manager.createQuery("FROM Pacchetto p"
		// WHERE p.idCliente=:NULL",Pacchetto.class);
		TypedQuery<Pacchetto> q = manager.createQuery("FROM Pacchetto p",
				Pacchetto.class);

		ArrayList<PacchettoDTO> pacchetti = new ArrayList<PacchettoDTO>();

		for (int i = 0; i < q.getResultList().size(); i++) {

			if (q.getResultList().get(i).getEtichetta().contains(etichetta)) {
				pacchetti.add(pacchettoToDTO(q.getResultList().get(i)));
			}

		}

		return pacchetti;
	}

	/**
	 * @param idPacchetto
	 * @return PacchettoDTO
	 */
	public PacchettoDTO findByIdPacchetto(Long idPacchetto) {

		
		PacchettoDTO pacchetto = pacchettoToDTO(manager.find(Pacchetto.class, new PacchettoPKDTO(idPacchetto, (long)0)));

		return pacchetto;
	}

	/**
	 * @param termine
	 * @return ArrayList<PacchettoDTO>
	 */
	public ArrayList<PacchettoDTO> findByTermine(String termine) {

		ArrayList<PacchettoDTO> listaPacchetti = new ArrayList<PacchettoDTO>();

		ArrayList<PacchettoDTO> listaPAC = new ArrayList<PacchettoDTO>();

		PacchettoDTO pacchetto = new PacchettoDTO();

		ArrayList<Long> listaID = findAll();

		for (int i = 0; i < listaID.size(); i++) {

			pacchetto = findByIdPacchetto(listaID.get(i));

			listaPAC.add(pacchetto);

		}

		for (int j = 0; j < listaPAC.size(); j++) {

			if (listaPAC.get(j).getDescrizione().contains(termine)) {

				listaPacchetti.add(listaPAC.get(j));

			}

		}

		return listaPacchetti;
	}

	/** @return ArrayList<idPacchetto> */
	public ArrayList<Long> findAll() {

		TypedQuery<Pacchetto> q = manager.createQuery("FROM Pacchetto p",
				Pacchetto.class);

		ArrayList<Long> pacchetti = new ArrayList<Long>();

		for (int i = 0; i < q.getResultList().size(); i++) {
			pacchetti.add(q.getResultList().get(i).getIdPacchetto());
		}

		return pacchetti;
	}

	/**
	 * @param dataPartenza
	 * @param dataRitorno
	 * @return true if dataPartenza comes before dataRitorno, otherwise false
	 */
	public boolean verificaConsistenzaDate(Date dataPartenza, Date dataRitorno) {

		if (dataPartenza.before(dataRitorno)) {

			return true;
		}
		return false;
	}

	/**
	 * @param dataPartenza
	 * @param dataRitorno
	 * @param disponibilitaRichiesta
	 * @param listaComponenti
	 * @return true if all componente are available, otherwise false
	 */
	public boolean verificaDisponibilitaComponenti(Date dataPartenza,
			Date dataRitorno, int disponibilitaRichiesta,
			List<ComponenteDTO> listaComponentiSelezionati) {

		System.out.println("VERIFICADISPONIBILITACOMPONENTI --> INIZIO METODO");

		ComponenteBeanRemote componenteRemoto = new ComponenteBean();

		ComponenteDTO componente = new ComponenteDTO();

		for (int i = 0; i < listaComponentiSelezionati.size(); i++) {

			// ciclo sui componenti della lista

			componente = listaComponentiSelezionati.get(i);

			System.out
					.println("ID COMPONENTE LISTA COMPONENTI SELEZIONATI --> "
							+ componente.getCodiceComponente());

			if (!componenteRemoto.verificaValiditaComponente(dataPartenza,
					dataRitorno, componente)) {

				System.out
						.println("VERIFICAVALIDITACOMPONENTE --> RETURN FALSE");

				return false;
			}

			if (!componenteRemoto.verificaDisponibilitaComponenteInPeriodo(
					disponibilitaRichiesta, dataPartenza, dataRitorno,
					componente)) {

				System.out
						.println("VERIFICADISPONIBILITACOMPONENTEINPERIODO --> RETURN FALSE");

				return false;
			}

			// un componente valido disponibile in tutto il periodo
			// richiesto

		}

		System.out.println("TUTTI I COMPONENTI DISPONIBILI --> RETURN TRUE");

		return true;

	}

	/**
	 * @param idPacchetto
	 * @return true if idPacchetto is present in the DB, otherwise false
	 */
	public boolean verificaPresenzaPacchetto(Long idPacchetto) {
		try {
			
			PacchettoPK chiave= new PacchettoPK(idPacchetto, (long)0);
			
			Pacchetto pacchetto = manager.find(Pacchetto.class, chiave);
			if (pacchetto==null) {
				return false;
			}

			return true;

		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * @param listaComponenti
	 * @return true if listaComponenti contains more than two elements and at
	 *         least three of them are of a different type, otherwise false
	 */
	public boolean verificaListaComponenti(List<ComponenteDTO> listaComponenti) {

		if (listaComponenti.size() > 2) {

			boolean flagHotel = false;
			boolean flagVolo = false;
			boolean flagEscursione = false;

			for (int i = 0; i < listaComponenti.size(); i++) {

				ComponenteDTO componente = listaComponenti.get(i);

				switch (componente.getTipologia()) {

				case "HOTEL":
					flagHotel = true;
					break;

				case "VOLO":
					flagVolo = true;
					break;

				case "ESCURSIONE":
					flagEscursione = true;
					break;

				}

			}

			if ((flagHotel == true) && (flagVolo == true)
					&& (flagEscursione == true)) {

				System.out.println("VERIFICA LISTA COMPONENTI CORRETTA");

				return true;
			}

		}

		System.out.println("VERIFICA LISTA COMPONENTI FALLITA");

		return false;
	}

	/**
	 * @param etichetta
	 * @return true if etichetta is valid, otherwise false
	 */
	public boolean verificaEtichetta(String etichetta) {

		System.out.println("VALORE ETICHETTA --> " + etichetta);

		String[] etichette = splitEtichetta(etichetta);
		for (int i = 0; i < etichette.length; i++) {

			if (etichette[i] == null) {

				System.out.println("VERIFICA ETICHETTA CORRETTA");

				return true;

			}

			switch (etichette[i]) {

			case "LASTMINUTE":
				break;
			case "lastminute":
				break;
			case "Lastminute":
				break;

			case "OFFERTA":
				break;
			case "offerta":
				break;
			case "Offerta":
				break;

			case "MARE":
				break;
			case "mare":
				break;
			case "Mare":
				break;

			case "MONTAGNA":
				break;
			case "montagna":
				break;
			case "Montagna":
				break;

			default:

				System.out.println("VERIFICA ETICHETTA FALLITA");

				return false;

			}

		}

		System.out.println("VERIFICA ETICHETTA CORRETTA");

		return true;

	}

	/**
	 * @param listaComponenti
	 * @param listaComponentiSelezionati
	 * @return true if listaComponenti contains listaComponentiSelezionati,
	 *         otherwise false
	 */
	public boolean verificaComponentiSelezionatiInComponenti(
			List<ComponenteDTO> listaComponenti,
			List<ComponenteDTO> listaComponentiSelezionati) {

		for (int i = 0; i < listaComponentiSelezionati.size(); i++) {

			if (!listaComponenti.contains(listaComponentiSelezionati.get(i))) {
				return false;

			}

		}

		return true;

	}

	/**
	 * @param listaComponentiSelezionati
	 * @return true if listaComponentiSelezionati contains 3 elements, otherwise
	 *         false
	 */
	public boolean verificaTreComponentiSelezionati(
			List<ComponenteDTO> listaComponentiSelezionati) {

		if (verificaListaComponenti(listaComponentiSelezionati)) {
			if (listaComponentiSelezionati.size() == 3) {
				return true;
			}
		}
		return false;

	}

	/**
	 * @param destinazione
	 * @param dataPartenza
	 * @param dataRitorno
	 * @return List<PacchettoDTO>
	 */
	public List<PacchettoDTO> ricercaPacchetti(String destinazione,
			Date dataPartenza, Date dataRitorno) {

		try {

			// DESTINAZIONE
			// TypedQuery<PacchettoDTO> q1 =
			// manager.createQuery("FROM Pacchetto p WHERE p.destinazione=:new_destinazione",
			// PacchettoDTO.class);

			// DATE
			// TypedQuery<PacchettoDTO> q2 =
			// manager.createQuery("FROM Pacchetto p WHERE p.dataInizioValidita<=:new_dataPartenza AND p.dataInizioValidita<=:new_dataRitorno AND p.dataFineValidita>=:new_dataPartenza AND p.dataFineValidita>=:new_dataRitorno",
			// PacchettoDTO.class);

			// q2.setParameter("new_dataPartenza", dataPartenza);

			TypedQuery<Pacchetto> q = manager
					.createQuery(
							"FROM Pacchetto p WHERE p IN (SELECT p FROM Pacchetto p WHERE p.destinazione=:new_destinazione) AND p IN (SELECT p FROM Pacchetto p WHERE p.dataInizioValidita<=:new_dataPartenza AND p.dataInizioValidita<=:new_dataRitorno AND p.dataFineValidita>=:new_dataPartenza AND p.dataFineValidita>=:new_dataRitorno)",
							Pacchetto.class);
			q.setParameter("new_destinazione", destinazione);
			q.setParameter("new_dataPartenza", dataRitorno);
			q.setParameter("new_dataRitorno", dataRitorno);

			List<PacchettoDTO> pacchetti = new ArrayList<PacchettoDTO>();
			for (int i = 0; i < q.getResultList().size(); i++) {

				pacchetti.add(pacchettoToDTO(q.getResultList().get(i)));
			}

			return pacchetti;

		} catch (NullPointerException e) {

			return null;

		}

	}

	/**
	 * @param etichetta
	 * @param dataPartenza
	 * @param dataRitorno
	 * @return ArrayList<Pacchetto>
	 */
	public ArrayList<PacchettoDTO> ricercaPerEtichetta(String etichetta,
			Date dataPartenza, Date dataRitorno) {

		try {

			ArrayList<PacchettoDTO> listaPacchettiRicercati = new ArrayList<PacchettoDTO>();

			// ETICHETTA
			// Query q1 =
			// manager.createQuery("FROM Pacchetto p WHERE p.etichetta=:new_etichetta");

			// q1.setParameter("new_etichetta", etichetta);

			// DATE
			TypedQuery<Pacchetto> q = manager
					.createQuery(
							"FROM Pacchetto p WHERE (p.dataInizioValidita<:new_dataPartenza OR p.dataInizioValidita=:new_dataPartenza) AND (p.dataInizioValidita<:new_dataRitorno OR p.dataInizioValidita=:new_dataRitorno) AND (p.dataFineValidita>:new_dataPartenza OR p.dataFineValidita=:new_dataPartenza) AND (p.dataFineValidita>:new_dataRitorno OR p.dataFineValidita=:new_dataRitorno)",
							Pacchetto.class);

			q.setParameter("new_dataPartenza", dataPartenza);
			q.setParameter("new_dataRitorno", dataRitorno);

			// INNER JOIN
			// Query q3 = manager.createQuery("q1 INNER JOIN q2");

			// q3.setParameter("q1", q1);
			// q3.setParameter("q2", q2);
			System.out.println("QUERY - NUMERO RISULTATI: "
					+ q.getResultList().size());
			CharSequence charseq = new String();
			charseq = etichetta;

			for (int i = 0; i < q.getResultList().size(); i++) {

				if (q.getResultList().get(i).getEtichetta().contains(charseq)) {
					listaPacchettiRicercati.add(pacchettoToDTO(q
							.getResultList().get(i)));
				}

			}

			return listaPacchettiRicercati;

		} catch (NullPointerException e) {

			return null;

		}

	}

	/**
	 * @param listaComponentiselzionati
	 * @param componenteDaInserire
	 * @return List<ComponenteDTO>
	 */
	/*
	 * è utile?????? c'è già l'update!
	 * 
	 * public List<ComponenteDTO> modificaListaComponentiSelezionati
	 * (List<ComponenteDTO> listaComponentiSelezionati, ComponenteDTO
	 * componenteDaInserire){
	 * 
	 * ArrayList<ComponenteDTO> listaComponentiModificata= new
	 * ArrayList<ComponenteDTO>();
	 * listaComponentiModificata=(ArrayList<ComponenteDTO>)
	 * listaComponentiSelezionati;
	 * 
	 * for(int i=0; i<listaComponentiModificata.size(); i++){
	 * if(listaComponentiModificata
	 * .get(i).getTipologia()==componenteDaInserire.getTipologia()){
	 * listaComponentiModificata.remove(i); } }
	 * 
	 * listaComponentiModificata.add(componenteDaInserire);
	 * 
	 * return listaComponentiModificata;
	 * 
	 * }
	 */

	/**
	 * @param listaComponenti
	 * @param sconto
	 * @return costoTotale
	 */
	public int calcolaCostoPacchetto(List<ComponenteDTO> listaComponenti,
			int sconto) {

		int costoTotale = 0;

		for (int i = 0; i < listaComponenti.size(); i++) {

			costoTotale = costoTotale + listaComponenti.get(i).getCosto();

		}

		costoTotale = costoTotale - (costoTotale * (sconto / 100));

		return costoTotale;
	}

	/**
	 * @param etichetta
	 * @return String[]
	 */
	public String[] splitEtichetta(String etichetta) {
		StringTokenizer StrTkn = new StringTokenizer(etichetta, ",");
		ArrayList<String> ArrLis = new ArrayList<String>(0);
		while (StrTkn.hasMoreTokens()) {
			ArrLis.add(StrTkn.nextToken());
		}
		return ArrLis.toArray(new String[0]);
	}

	public PacchettoDTO pacchettoToDTO(Pacchetto pacchetto) {
		PacchettoDTO pacchettoDTO = new PacchettoDTO();

		pacchettoDTO.setIdPacchetto(pacchetto.getIdPacchetto());
		pacchettoDTO.setCosto(pacchetto.getCosto());
		pacchettoDTO.setDataFineValidita(pacchetto.getDataFineValidita());
		pacchettoDTO.setDataInizioValidita(pacchetto.getDataInizioValidita());
		pacchettoDTO.setDescrizione(pacchetto.getDescrizione());
		pacchettoDTO.setDestinazione(pacchetto.getDestinazione());
		pacchettoDTO.setEtichetta(pacchetto.getEtichetta());
		pacchettoDTO.setSconto(pacchetto.getSconto());

		List<ComponenteDTO> listaComponenti = new ArrayList<ComponenteDTO>();
		for (int i = 0; i < pacchetto.getListaComponenti().size(); i++) {
			listaComponenti.add(componenteToDTOInPacchetto(pacchetto
					.getListaComponenti().get(i)));

		}

		pacchettoDTO.setListaComponenti(listaComponenti);

		List<ComponenteDTO> listaComponentiSelezionati = new ArrayList<ComponenteDTO>();
		for (int i = 0; i < pacchetto.getListaComponentiSelezionati().size(); i++) {
			listaComponentiSelezionati.add(componenteToDTOInPacchetto(pacchetto
					.getListaComponentiSelezionati().get(i)));

		}

		pacchettoDTO.setListaComponentiSelezionati(listaComponentiSelezionati);

		return pacchettoDTO;

	}

	public ComponenteDTO componenteToDTOInPacchetto(Componente componente) {

		ComponenteBean componenteBean = new ComponenteBean();
		ComponenteDTO componenteDTO = componenteBean
				.componenteToDTO(componente);
		return componenteDTO;
	}

	public Componente componenteDTOToComponenteInPacchetto(
			ComponenteDTO componenteDTO) {

		ComponenteBean componenteBean = new ComponenteBean();
		Componente componente = componenteBean
				.componenteDTOToComponente(componenteDTO);
		return componente;
	}

}