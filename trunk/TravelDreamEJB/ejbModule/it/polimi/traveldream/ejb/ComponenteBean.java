package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.ComponenteBeanLocal;
import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.entities.Componente;
import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.DisponibilitaPerData;
import it.polimi.traveldream.entities.DisponibilitaPerDataDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.joda.time.DateTime;
import org.joda.time.Days;

/** Session Bean implementation class ComponenteBean */
@Stateless
public class ComponenteBean implements ComponenteBeanRemote,
		ComponenteBeanLocal {

	@PersistenceContext(unitName = "travelDream_project")
	private EntityManager manager;

	/** Default constructor */
	public ComponenteBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param tipologia
	 * @param luogo
	 * @param descrizione
	 * @param costo
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param disponibilitaDaSettare
	 * @return codiceComponente
	 */
	public Long createComponente(String tipologia,String luogo, String descrizione,
			int costo, Date dataInizioValidita, Date dataFineValidita,
			int disponibilitaDaSettare) {

		if (verificaTipologia(tipologia)) {

			Componente componente = new Componente();

			componente.setTipologia(tipologia);
			componente.setLuogo(luogo);
			componente.setDescrizione(descrizione);
			componente.setCosto(costo);
			componente.setDataInizioValidita(dataInizioValidita);
			componente.setDataFineValidita(dataFineValidita);

			// disponibilitaPerData=;
			creaListaDisponibilitaPerData(componenteToDTO(componente), dataInizioValidita,
					dataFineValidita, disponibilitaDaSettare);

			manager.persist(componente);

			return componente.getCodiceComponente();

		}

		return (long) -1;

	}

	/** @param codiceComponente */
	public void removeComponente(Long codiceComponente) {

		Componente c = manager.find(Componente.class, codiceComponente);
		
		manager.remove(c);

	}

	/**
	 * @param codiceComponente
	 * @param tipologia
	 * @param luogo
	 * @param descrizione
	 * @param costo
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param disponibilitaDaSettare
	 */
	public void updateComponente(Long codiceComponente, String tipologia, String luogo,
			String descrizione, int costo, Date dataInizioValidita, Date dataFineValidita,
			int disponibilitaDaSettare) {

		if ((verificaPresenzaComponente(codiceComponente))
				&& (verificaTipologia(tipologia))) {

			Componente componente = manager.find(Componente.class, codiceComponente);

			componente.setTipologia(tipologia);
			componente.setLuogo(luogo);
			componente.setDescrizione(descrizione);
			componente.setCosto(costo);
			componente.setDataInizioValidita(dataInizioValidita);
			componente.setDataFineValidita(dataFineValidita);
			
			// disponibilitaPerData=;
			creaListaDisponibilitaPerData(componenteToDTO(componente), dataInizioValidita,
								dataFineValidita, disponibilitaDaSettare);

			manager.merge(componente);
		}

	}

	/**
	 * @param codiceComponente
	 * @return ComponenteDTO
	 */
	public ComponenteDTO findByCodiceComponente(Long codiceComponente) {

		TypedQuery<Componente> q = manager
				.createQuery(
						"FROM Componente c WHERE c.codiceComponente=:new_codiceComponente",
						Componente.class);

		q.setParameter("new_codiceComponente", codiceComponente);

		ComponenteDTO componente = componenteToDTO(q.getSingleResult());
		
		

		return componente;
	}
	
	/**
	 * @param luogo
	 * @return ArrayList<ComponenteDTO>
	 */
	public ArrayList<ComponenteDTO> findByLuogo(String luogo) {

		TypedQuery<Componente> q = manager
				.createQuery(
						"FROM Componente c WHERE c.luogo=:new_luogo",
						Componente.class);

		q.setParameter("new_luogo", luogo);
		
		ArrayList<ComponenteDTO> componenti = new ArrayList<ComponenteDTO>();
		
		ArrayList<Componente> lista = (ArrayList<Componente>) q.getResultList();
		
		for(int i=0;i<lista.size();i++){
			

		componenti.add(componenteToDTO(lista.get(i)));
		
		
		}

		return componenti;
	}
	
	
	/**
	 * @param termine
	 * @return ArrayList<ComponenteDTO>
	 */
	public ArrayList<ComponenteDTO> findByTermine(String termine) {

		
		ArrayList<ComponenteDTO> listaComponenti = new ArrayList<ComponenteDTO>();

		ArrayList<ComponenteDTO> listaCOM = new ArrayList<ComponenteDTO>();
		
		ComponenteDTO componente = new ComponenteDTO();
		
		ArrayList<Long> listaCOD = findAll();
			
		
		for(int i=0; i<listaCOD.size();i++){
			
			componente = findByCodiceComponente(listaCOD.get(i));
			
			listaCOM.add(componente);
			
		}
		
		for(int j=0; j<listaCOM.size();j++){
			
			if (listaCOM.get(j).getDescrizione().contains(termine)){
				
				listaComponenti.add(listaCOM.get(j));
				
			}
			
		}

		return listaComponenti;
	}
	

	/** @return ArrayList<codiceComponente> */
	public ArrayList<Long> findAll() {

		TypedQuery<Componente> q = manager.createQuery("FROM Componente c",
				Componente.class);

		

		ArrayList<Long> lista = new ArrayList<Long>();

		for (int i = 0; i < q.getResultList().size(); i++) {

			lista.add(q.getResultList().get(i).getCodiceComponente());

		}
		return lista;
	}

	/**
	 * @param dataPartenza
	 * @param dataRitorno
	 * @param componente
	 * @return true if componente is valid, otherwise false
	 */
	public boolean verificaValiditaComponente(Date dataPartenza,
			Date dataRitorno, ComponenteDTO componente) {

		if ((!dataPartenza.before(componente.getDataInizioValidita()))
				&& (!dataPartenza.after(componente.getDataFineValidita()))
				&& (!dataRitorno.before(componente.getDataInizioValidita()))
				&& (!dataRitorno.after(componente.getDataFineValidita()))) {

			return true;

		} else {

			return false;

		}

	}

	/**@param componente
	 * @param data
	 * @return int
	 */
	public int disponibilitaInData(ComponenteDTO componente, Date data) {

		for (int i = 0; i < componente.getDisponibilitaPerData().size(); i++)
			if (componente.getDisponibilitaPerData().get(i).getData()
					.equals(data)) {
				return componente.getDisponibilitaPerData().get(i)
						.getDisponibilita();
			}

		return -1;
	}

	/**@param componente
	 * @param data
	 * @param disponibilita
	 */
	public void setDisponibilitaInData(ComponenteDTO componente, Date data,
			int disponibilita) {

		for (int i = 0; i < componente.getDisponibilitaPerData().size(); i++) {
			if (componente.getDisponibilitaPerData().get(i).getData()
					.equals(data)) {
				componente.getDisponibilitaPerData().get(i)
						.setDisponibilita(disponibilita);
			}

		}
	}
	


	/**
	 * @param disponibilita
	 * @param data
	 * @param componente
	 * @return true if componente is available, otherwise false
	 */
	public boolean verificaDisponibilitaComponenteInUnaData(
			int disponibilitaRichiesta, Date data, ComponenteDTO componente) {

		if (disponibilitaInData(componente, data) >= disponibilitaRichiesta) {
			return true;
		}

		return false;

	}

	/**@param disponibilitaRichiesta
	 * @param dataPartenza
	 * @param dataRitorno
	 * @param componente
	 * @return true if componente is available in the requested period, otherwise false
	 */
	public boolean verificaDisponibilitaComponenteInPeriodo(
			int disponibilitaRichiesta, Date dataPartenza, Date dataRitorno,
			ComponenteDTO componente) {

		int giorniIntervallo = Days.daysBetween(new DateTime(dataPartenza),
				new DateTime(dataRitorno)).getDays();

		for (int i = 0; i < giorniIntervallo; i++) {

			DateTime dataDaVerificareJ = new DateTime();
			dataDaVerificareJ = dataDaVerificareJ.plusDays(i);
			Date dataDaVerificare = dataDaVerificareJ.toDate();

			if (verificaDisponibilitaComponenteInUnaData(
					disponibilitaRichiesta, dataDaVerificare, componente) == false) {
				return false;
			}

		}

		return true;

	}

	/**
	 * @param codiceComponente
	 * @return true if codiceComponente is present in the DB, otherwise false
	 */
	public boolean verificaPresenzaComponente(Long codiceComponente) {
		try {
			if(manager.find(Componente.class, codiceComponente).equals(null)){
				return false;

			}
			return true;

			
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**@param tipologia
	 * @return true if tipologia is valid, otherwise false
	 */
	public boolean verificaTipologia(String tipologia) {

		switch (tipologia) {

		case "HOTEL":
			return true;

		case "VOLO":
			return true;

		case "ESCURSIONE":
			return true;

		}

		return false;

	}

	/**
	 * @param componente
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @param disponibilitaDaSettare
	 */
	public void creaListaDisponibilitaPerData(ComponenteDTO componente,
			Date dataInizioValidita, Date dataFineValidita,
			int disponibilitaDaSettare) {

		int giorniIntervallo = Days.daysBetween(
				new DateTime(dataInizioValidita),
				new DateTime(dataFineValidita)).getDays();

		for (int i = 0; i < giorniIntervallo; i++) {
			DateTime dataDaSettareJ = new DateTime();
			dataDaSettareJ = dataDaSettareJ.plusDays(i);
			Date dataDaSettare = dataDaSettareJ.toDate();

			setDisponibilitaInData(componente, dataDaSettare,
					disponibilitaDaSettare);

		}

	}
	


	public ComponenteDTO componenteToDTO(Componente componente) {

		ComponenteDTO componenteDTO = new ComponenteDTO();

		componenteDTO.setCodiceComponente(componente.getCodiceComponente());
		componenteDTO.setCosto(componente.getCosto());
		componenteDTO.setDataFineValidita(componente.getDataFineValidita());
		componenteDTO.setDataInizioValidita(componente.getDataInizioValidita());
		componenteDTO.setDescrizione(componente.getDescrizione());
		componenteDTO.setTipologia(componente.getTipologia());
		List<DisponibilitaPerDataDTO> lista = new ArrayList<DisponibilitaPerDataDTO>();

		for (int i = 0; i < componente.getDisponibilitaPerData().size(); i++) {
			lista.add(disponibilitaPerDataToDTO(componente
					.getDisponibilitaPerData().get(i)));

		}
		componenteDTO.setDisponibilitaPerData(lista);
		
		return componenteDTO;

	}

	public Componente componenteDTOToComponente(ComponenteDTO componenteDTO) {

		Componente componente = new Componente();

		componente.setCodiceComponente(componenteDTO.getCodiceComponente());
		componente.setCosto(componenteDTO.getCosto());
		componente.setDataFineValidita(componenteDTO.getDataFineValidita());
		componente.setDataInizioValidita(componenteDTO.getDataInizioValidita());
		componente.setDescrizione(componenteDTO.getDescrizione());
		componente.setTipologia(componenteDTO.getTipologia());
		List<DisponibilitaPerData> lista = new ArrayList<DisponibilitaPerData>();

		for (int i = 0; i < componenteDTO.getDisponibilitaPerData().size(); i++) {
			lista.add(disponibilitaPerDataDTOToDisponibilitaPerData(componenteDTO.getDisponibilitaPerData().get(i)));

		}
		componente.setDisponibilitaPerData(lista);
		
		return componente;

	}
	
	
	public DisponibilitaPerDataDTO disponibilitaPerDataToDTO(
			DisponibilitaPerData disponibilitaPerData) {

		DisponibilitaPerDataDTO disponibilitaPerDataDTO = new DisponibilitaPerDataDTO();
		disponibilitaPerDataDTO.setData(disponibilitaPerData.getData());
		disponibilitaPerDataDTO.setDisponibilita(disponibilitaPerData
				.getDisponibilita());

		return disponibilitaPerDataDTO;

	}

	public DisponibilitaPerData disponibilitaPerDataDTOToDisponibilitaPerData(
			DisponibilitaPerDataDTO disponibilitaPerDataDTO) {

		DisponibilitaPerData disponibilitaPerData = new DisponibilitaPerData();
		disponibilitaPerData.setData(disponibilitaPerDataDTO.getData());
		disponibilitaPerData.setDisponibilita(disponibilitaPerDataDTO
				.getDisponibilita());

		return disponibilitaPerData;

	}
	

}
