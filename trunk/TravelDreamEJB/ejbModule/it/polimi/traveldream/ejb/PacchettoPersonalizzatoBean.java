package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.PacchettoBeanRemote;
import it.polimi.traveldream.ejb.client.PacchettoPersonalizzatoBeanLocal;
import it.polimi.traveldream.ejb.client.PacchettoPersonalizzatoBeanRemote;
import it.polimi.traveldream.entities.Componente;
import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.Invito;
import it.polimi.traveldream.entities.Pacchetto;
import it.polimi.traveldream.entities.PacchettoDTO;
import it.polimi.traveldream.entities.PacchettoPK;
import it.polimi.traveldream.entities.PacchettoPKDTO;
import it.polimi.traveldream.entities.PacchettoPersonalizzato;
import it.polimi.traveldream.entities.PacchettoPersonalizzatoDTO;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/** Session Bean implementation class PacchettoPersonalizzatoBean */
@Stateless
public class PacchettoPersonalizzatoBean implements
		PacchettoPersonalizzatoBeanRemote, PacchettoPersonalizzatoBeanLocal {

	@PersistenceContext(unitName = "travelDream_project")
	private EntityManager manager;
	
	private SecureRandom random = new SecureRandom();

	/** Default constructor */
	public PacchettoPersonalizzatoBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param stato
	 * @param idCliente
	 * @return idPacchettoPersonalizzato
	 */
	public Long createPacchettoPersonalizzato(String stato, String emailUtente,
			Date dataDiPartenza, Date dataDiRitorno,
			List<ComponenteDTO> listaComponentiSelezionati, PacchettoPKDTO pacchettoPKDTO) {
		
			System.out.println("BEAN --> CREAPACCHETTOPERSONALIZZATO --> INIZIO METODO");

		if (verificaStatoPerCreazione(stato)) {
			
			System.out.println("BEAN --> CREAPACCHETTOPERSONALIZZATO --> STATO CORRETTO");

			
			//UTILIZZARE NUOVO COSTRUTTORE E GENERARE IDPACCHETTOPERSONALIZZATO COME COMPONENTEBEAN
			
			BigInteger big = new BigInteger(130, random);
			
			int cod = big.intValue();
			//System.out.println("COD_BIG"+cod);
			if((cod*(-1)>0)){
			cod= cod*(-1);
			}
			Long codice = (long)cod;

			List<Componente> componentiSelezionati = new ArrayList<Componente>();
			for (int i = 0; i < listaComponentiSelezionati.size(); i++) {
				componentiSelezionati
						.add(componenteDTOToComponenteInPacchettoPers(listaComponentiSelezionati
								.get(i)));
			}
			
			PacchettoPK pacchettoPK= new PacchettoPK(pacchettoPKDTO.getIdPacchetto(), pacchettoPKDTO.getIdPacchettoPersonalizzato());
			Pacchetto pacchetto= manager.find(Pacchetto.class, pacchettoPK);
			
			List<Invito> invitiPacchetto= new ArrayList<Invito>();
			
			PacchettoPersonalizzato pacchettoPersonalizzato = new PacchettoPersonalizzato(pacchetto.getIdPacchetto(), codice, pacchetto.getDestinazione(), pacchetto.getDataInizioValidita(), pacchetto.getDataFineValidita(), pacchetto.getEtichetta(), pacchetto.getDescrizione(), pacchetto.getListaComponenti(), componentiSelezionati, pacchetto.getCosto(), pacchetto.getSconto(), stato, emailUtente, dataDiPartenza, dataDiRitorno, invitiPacchetto);
					
			System.out.println("BEAN --> CREAPACCHETTOPERSONALIZZATO --> PRIMA DI PERSIST");
			
			manager.persist(pacchettoPersonalizzato);
			
			System.out.println("BEAN --> CREAPACCHETTOPERSONALIZZATO --> DOPO PERSIST");

			return pacchettoPersonalizzato.getIdPacchettoPersonalizzato();

		} else {

			System.out.println("BEAN --> CREAPACCHETTOPERSONALIZZATO --> STATO ERRATO");
			
			return (long) -1;

		}

	}

	/** @param idPacchettoPersonalizzato */
	public void removePacchettoPersonalizzato(Long idPacchettoPersonalizzato, Long idPacchetto) {

		// PacchettoPersonalizzatoDTO pacchetto=
		// findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato);

		PacchettoPersonalizzato pacchetto = manager.find(
				PacchettoPersonalizzato.class, new PacchettoPK(idPacchetto, idPacchettoPersonalizzato));

		manager.remove(pacchetto);
	}

	/**
	 * @param idPacchettoPersonalizzato
	 * @param stato
	 * @param listaComponenti
	 */
	public void updatePacchettoPersonalizzato(PacchettoPKDTO pacchettoPKDTO,
			String emailUtente, String stato, Date dataDiPartenza,
			Date dataDiRitorno, List<ComponenteDTO> listaComponentiSelezionati) {

		if ((verificaPresenzaPacchettoPersonalizzato(pacchettoPKDTO))
				&& (verificaStato(stato))) {

			// PacchettoPersonalizzatoDTO pacchettoPersonalizzato =
			// findByIdPacchettoPersonalizzato(idPacchettoPersonalizzato);

			PacchettoPK pacchettoPK= new PacchettoPK(pacchettoPKDTO.getIdPacchetto(), pacchettoPKDTO.getIdPacchettoPersonalizzato());
			PacchettoPersonalizzato pacchettoPersonalizzato = manager.find(
					PacchettoPersonalizzato.class,pacchettoPK);

			pacchettoPersonalizzato.setStato(stato);
			pacchettoPersonalizzato.setDataDiPartenza(dataDiPartenza);
			pacchettoPersonalizzato.setDataDiRitorno(dataDiRitorno);
			pacchettoPersonalizzato.setEmailUtente(emailUtente);

			List<Componente> componentiSelezionati = new ArrayList<Componente>();
			for (int i = 0; i < listaComponentiSelezionati.size(); i++) {
				componentiSelezionati
						.add(componenteDTOToComponenteInPacchettoPers(listaComponentiSelezionati
								.get(i)));
			}
			pacchettoPersonalizzato
					.setListaComponentiSelezionati(componentiSelezionati);

			manager.merge(pacchettoPersonalizzato);

		}
	}

	/**
	 * @param destinazione
	 * @return ArrayList<idPacchettoPersonalizzato>
	 */
	/*
	 * public ArrayList<Long> findByDestinazione(String destinazione) {
	 * 
	 * TypedQuery<PacchettoPersonalizzato> q = manager.createQuery(
	 * "FROM PacchettoPersonalizzato p WHERE p.destinazione=:new_destinazione",
	 * PacchettoPersonalizzato.class);
	 * 
	 * q.setParameter("new_destinazione", destinazione);
	 * 
	 * ArrayList<Long> pacchetti = new ArrayList<Long>();
	 * List<PacchettoPersonalizzatoDTO> risultati = q.getResultList();
	 * 
	 * for (int i = 0; i < risultati.size(); i++) {
	 * 
	 * pacchetti.set(i, risultati.get(i).getIdPacchettoPersonalizzato());
	 * 
	 * } return pacchetti; }
	 */

	/**
	 * @param etichetta
	 * @return ArrayList<idPacchettoPersonalizzato>
	 */
	/*
	 * public ArrayList<Long> findByEtichetta(String etichetta) {
	 * 
	 * TypedQuery<PacchettoPersonalizzato> q =
	 * manager.createQuery("FROM PacchettoPersonalizzato p",
	 * PacchettoPersonalizzato.class);
	 * 
	 * ArrayList<PacchettoPersonalizzatoDTO> pacchetti = new
	 * ArrayList<PacchettoPersonalizzatoDTO>(); ArrayList<Long> idPacchetti =
	 * new ArrayList<Long>(); List<PacchettoPersonalizzatoDTO> risultati =
	 * q.getResultList();
	 * 
	 * for (int i = 0; i < risultati.size(); i++) {
	 * 
	 * if (risultati.get(i).getEtichetta().equals(etichetta)) {
	 * 
	 * pacchetti.set(i, risultati.get(i));
	 * 
	 * } }
	 * 
	 * for (int j = 0; j < pacchetti.size(); j++) {
	 * 
	 * if (pacchetti.get(j).equals(null)) { } else { idPacchetti.set(j,
	 * pacchetti.get(j).getIdPacchettoPersonalizzato()); }
	 * 
	 * } return idPacchetti; }
	 */
	/**
	 * @param idPacchettoPersonalizzato
	 * @return PacchettoPersonalizzatoDTO
	 */
	public PacchettoPersonalizzatoDTO findByIdPacchettoPersonalizzato(
			Long idPacchettoPersonalizzato) {

		TypedQuery<PacchettoPersonalizzato> q = manager
				.createQuery(
						"FROM PacchettoPersonalizzato p WHERE p.idPacchettoPersonalizzato=:new_idPacchettoPersonalizzato",
						PacchettoPersonalizzato.class);

		q.setParameter("new_idPacchettoPersonalizzato",
				idPacchettoPersonalizzato);

		PacchettoPersonalizzatoDTO pacchettoPersonalizzato = pacchettoPersonalizzatoToDTO(q.getSingleResult());

		return pacchettoPersonalizzato;
	}

	/**
	 * @param idCliente
	 * @return ArrayList<PacchettoPersonalizzatoDTO>
	 */
	public ArrayList<PacchettoPersonalizzatoDTO> findByIdCliente(Long idCliente) {

		TypedQuery<PacchettoPersonalizzato> q = manager
				.createQuery(
						"FROM PacchettoPersonalizzato p WHERE p.idCliente=:new_idCliente",
						PacchettoPersonalizzato.class);

		q.setParameter("new_idCliente", idCliente);

		ArrayList<PacchettoPersonalizzatoDTO> pacchettiPersonalizzati = new ArrayList<PacchettoPersonalizzatoDTO>();
		
		for(int i=0; i<q.getResultList().size();i++){
			pacchettiPersonalizzati.add(pacchettoPersonalizzatoToDTO(q.getResultList().get(i)));
		}
		return pacchettiPersonalizzati;

	}

	/** @return ArrayList<idPacchettoPersonalizzato> */
	public ArrayList<Long> findAll() {

		TypedQuery<PacchettoPersonalizzato> q = manager.createQuery(
				"FROM PacchettoPersonalizzato p",
				PacchettoPersonalizzato.class);

		

		ArrayList<Long> lista = new ArrayList<Long>();

		for (int i = 0; i < q.getResultList().size(); i++) {

			lista.add(q.getResultList().get(i).getIdPacchettoPersonalizzato());

		}

		return lista;
	}

	/**
	 * @param idPacchettoPersonalizzato
	 * @return true if idPacchettoPersonalizzato is present in the DB, otherwise
	 *         false
	 */
	public boolean verificaPresenzaPacchettoPersonalizzato(
			PacchettoPKDTO pacchettoPKDTO) {
		try {
			TypedQuery<PacchettoPersonalizzato> q = manager
					.createQuery(
							"FROM PacchettoPersonalizzato p WHERE (p.idPacchettoPersonalizzato=:new_idPacchettoPersonalizzato",
							PacchettoPersonalizzato.class);

			q.setParameter("new_idPacchettoPersonalizzato",
					pacchettoPKDTO.getIdPacchettoPersonalizzato());

			if(q.getResultList().isEmpty()){
				return false;
			
			} 
			return true;

		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * @param stato
	 * @return true if stato is valid, otherwise false
	 */
	public boolean verificaStato(String stato) {

		switch (stato) {

		case "CONFERMATO":
			return true;

		case "SALVATO":
			return true;

		case "BLOCCATO":
			return true;

		case "ACCETTATO":
			return true;

		}

		return false;

	}

	public boolean verificaStatoPerCreazione(String stato) {

		switch (stato) {

		case "SALVATO":
			return true;

		case "ACCETTATO":
			return true;
			
		case "salvato":
			return true;

		case "accettato":
			return true;	

		case "Salvato":
			return true;

		case "Accettato":
			return true;	
			
		}

		return false;

	}

	public ComponenteDTO componenteToDTOInPacchettoPers(Componente componente) {

		ComponenteBean componenteBean = new ComponenteBean();
		ComponenteDTO componenteDTO = componenteBean
				.componenteToDTO(componente);
		return componenteDTO;
	}

	public Componente componenteDTOToComponenteInPacchettoPers(
			ComponenteDTO componenteDTO) {

		ComponenteBean componenteBean = new ComponenteBean();
		Componente componente = componenteBean
				.componenteDTOToComponente(componenteDTO);
		return componente;
	}

	public PacchettoPersonalizzatoDTO pacchettoPersonalizzatoToDTO(
			PacchettoPersonalizzato pacchettoPersonalizzato) {

		PacchettoPersonalizzatoDTO pacchettoPersonalizzatoDTO = new PacchettoPersonalizzatoDTO();
		pacchettoPersonalizzatoDTO
				.setIdPacchettoPersonalizzato(pacchettoPersonalizzato
						.getIdPacchettoPersonalizzato());
		pacchettoPersonalizzatoDTO.setDataDiPartenza(pacchettoPersonalizzato
				.getDataDiPartenza());
		pacchettoPersonalizzatoDTO.setDataDiRitorno(pacchettoPersonalizzato
				.getDataDiRitorno());
		pacchettoPersonalizzatoDTO.setStato(pacchettoPersonalizzato.getStato());
		pacchettoPersonalizzatoDTO.setEmailUtente(pacchettoPersonalizzato
				.getEmailUtente());
		pacchettoPersonalizzatoDTO.setIdPacchetto(pacchettoPersonalizzato
				.getIdPacchetto());
		pacchettoPersonalizzatoDTO.setCosto(pacchettoPersonalizzato.getCosto());
		pacchettoPersonalizzatoDTO.setDataFineValidita(pacchettoPersonalizzato
				.getDataFineValidita());
		pacchettoPersonalizzatoDTO
				.setDataInizioValidita(pacchettoPersonalizzato
						.getDataInizioValidita());
		pacchettoPersonalizzatoDTO.setDescrizione(pacchettoPersonalizzato
				.getDescrizione());
		pacchettoPersonalizzatoDTO.setDestinazione(pacchettoPersonalizzato
				.getDestinazione());
		pacchettoPersonalizzatoDTO.setEtichetta(pacchettoPersonalizzato
				.getEtichetta());
		pacchettoPersonalizzatoDTO.setSconto(pacchettoPersonalizzato
				.getSconto());

		List<ComponenteDTO> listaComponenti = new ArrayList<ComponenteDTO>();
		for (int i = 0; i < pacchettoPersonalizzato.getListaComponenti().size(); i++) {
			listaComponenti
					.add(componenteToDTOInPacchettoPers(pacchettoPersonalizzato
							.getListaComponenti().get(i)));

		}

		pacchettoPersonalizzatoDTO.setListaComponenti(listaComponenti);

		List<ComponenteDTO> listaComponentiSelezionati = new ArrayList<ComponenteDTO>();
		for (int i = 0; i < pacchettoPersonalizzato
				.getListaComponentiSelezionati().size(); i++) {
			listaComponentiSelezionati
					.add(componenteToDTOInPacchettoPers(pacchettoPersonalizzato
							.getListaComponentiSelezionati().get(i)));

		}

		pacchettoPersonalizzatoDTO
				.setListaComponentiSelezionati(listaComponentiSelezionati);

		return pacchettoPersonalizzatoDTO;

	}

	public PacchettoPersonalizzato pacchettoPersonalizzatoDTOToPacchettoPersonalizzato(
			PacchettoPersonalizzatoDTO pacchettoPersonalizzatoDTO) {

		PacchettoPersonalizzato pacchettoPersonalizzato = new PacchettoPersonalizzato();
		pacchettoPersonalizzato
				.setIdPacchettoPersonalizzato(pacchettoPersonalizzatoDTO
						.getIdPacchettoPersonalizzato());
		pacchettoPersonalizzato.setDataDiPartenza(pacchettoPersonalizzatoDTO
				.getDataDiPartenza());
		pacchettoPersonalizzato.setDataDiRitorno(pacchettoPersonalizzatoDTO
				.getDataDiRitorno());
		pacchettoPersonalizzato.setStato(pacchettoPersonalizzatoDTO.getStato());
		pacchettoPersonalizzato.setEmailUtente(pacchettoPersonalizzatoDTO
				.getEmailUtente());
		pacchettoPersonalizzato.setIdPacchetto(pacchettoPersonalizzatoDTO
				.getIdPacchetto());
		pacchettoPersonalizzato.setCosto(pacchettoPersonalizzatoDTO.getCosto());
		pacchettoPersonalizzato.setDataFineValidita(pacchettoPersonalizzatoDTO
				.getDataFineValidita());
		pacchettoPersonalizzato
				.setDataInizioValidita(pacchettoPersonalizzatoDTO
						.getDataInizioValidita());
		pacchettoPersonalizzato.setDescrizione(pacchettoPersonalizzatoDTO
				.getDescrizione());
		pacchettoPersonalizzato.setDestinazione(pacchettoPersonalizzatoDTO
				.getDestinazione());
		pacchettoPersonalizzato.setEtichetta(pacchettoPersonalizzatoDTO
				.getEtichetta());
		pacchettoPersonalizzato.setSconto(pacchettoPersonalizzatoDTO
				.getSconto());

		List<Componente> listaComponenti = new ArrayList<Componente>();
		for (int i = 0; i < pacchettoPersonalizzatoDTO.getListaComponenti().size(); i++) {
			listaComponenti
					.add(componenteDTOToComponenteInPacchettoPers(pacchettoPersonalizzatoDTO
							.getListaComponenti().get(i)));

		}

		pacchettoPersonalizzato.setListaComponenti(listaComponenti);

		List<Componente> listaComponentiSelezionati = new ArrayList<Componente>();
		for (int i = 0; i < pacchettoPersonalizzatoDTO
				.getListaComponentiSelezionati().size(); i++) {
			listaComponentiSelezionati
					.add(componenteDTOToComponenteInPacchettoPers(pacchettoPersonalizzatoDTO
							.getListaComponentiSelezionati().get(i)));

		}

		pacchettoPersonalizzato
				.setListaComponentiSelezionati(listaComponentiSelezionati);

		return pacchettoPersonalizzato;

	}

	
}
