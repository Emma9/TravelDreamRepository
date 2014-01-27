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
	 * @param descrizione
	 * @param costo
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 * @return codiceComponente
	 */
	public Long createComponente(String tipologia, String descrizione,
			int costo, Date dataInizioValidita, Date dataFineValidita,
			int disponibilitaDaSettare) {

		if (verificaTipologia(tipologia)) {

			ComponenteDTO componente = new ComponenteDTO();

			componente.setTipologia(tipologia);
			componente.setDescrizione(descrizione);
			componente.setCosto(costo);
			componente.setDataInizioValidita(dataInizioValidita);
			componente.setDataFineValidita(dataFineValidita);

			// disponibilitaPerData=;
			creaListaDisponibilitaPerData(componente, dataInizioValidita,
					dataFineValidita, disponibilitaDaSettare);

			manager.persist(componente);

			return componente.getCodiceComponente();

		}

		return (long) -1;

	}

	/** @param codiceComponente */
	public void removeComponente(Long codiceComponente) {

		ComponenteDTO c = findByCodiceComponente(codiceComponente);

		manager.remove(c);

	}

	/**
	 * @param codiceComponente
	 * @param tipologia
	 * @param descrizione
	 * @param dataInizioValidita
	 * @param dataFineValidita
	 */
	public void updateComponente(Long codiceComponente, String tipologia,
			String descrizione, int costo, Date dataInizioValidita, Date dataFineValidita,
			int disponibilitaDaSettare) {

		if ((verificaPresenzaComponente(codiceComponente))
				&& (verificaTipologia(tipologia))) {

			ComponenteDTO componente = findByCodiceComponente(codiceComponente);

			componente.setTipologia(tipologia);
			componente.setDescrizione(descrizione);
			componente.setCosto(costo);
			componente.setDataInizioValidita(dataInizioValidita);
			componente.setDataFineValidita(dataFineValidita);
			
			// disponibilitaPerData=;
			creaListaDisponibilitaPerData(componente, dataInizioValidita,
								dataFineValidita, disponibilitaDaSettare);

			manager.merge(componente);
		}

	}

	/**
	 * @param codiceComponente
	 * @return ComponenteDTO
	 */
	public ComponenteDTO findByCodiceComponente(Long codiceComponente) {

		TypedQuery<ComponenteDTO> q = manager
				.createQuery(
						"FROM Componente c WHERE c.codiceComponente=:new_codiceComponente",
						ComponenteDTO.class);

		q.setParameter("new_codiceComponente", codiceComponente);

		ComponenteDTO componente = q.getSingleResult();

		return componente;
	}

	/** @return ArrayList<codiceComponente> */
	public ArrayList<Long> findAll() {

		TypedQuery<ComponenteDTO> q = manager.createQuery("FROM Componente c",
				ComponenteDTO.class);

		List<ComponenteDTO> componenti = q.getResultList();

		ArrayList<Long> lista = new ArrayList<Long>();

		for (int i = 0; i < componenti.size(); i++) {

			lista.set(i, componenti.get(i).getCodiceComponente());

		}
		return lista;
	}

	/**
	 * @param dataPartenza
	 * @param dataRitorno
	 * @param codiceComponente
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

	public int disponibilitaInData(ComponenteDTO componente, Date data) {

		for (int i = 0; i < componente.getDisponibilitaPerData().size(); i++)
			if (componente.getDisponibilitaPerData().get(i).getData()
					.equals(data)) {
				return componente.getDisponibilitaPerData().get(i)
						.getDisponibilita();
			}

		return -1;
	}

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
	 * @param codiceComponente
	 * @return true if componente is available, otherwise false
	 */
	public boolean verificaDisponibilitaComponenteInUnaData(
			int disponibilitaRichiesta, Date data, ComponenteDTO componente) {

		if (disponibilitaInData(componente, data) >= disponibilitaRichiesta) {
			return true;
		}

		return false;

	}

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
			TypedQuery<ComponenteDTO> q = manager
					.createQuery(
							"FROM Componente c WHERE c.codiceComponente=:new_codiceComponente",
							ComponenteDTO.class);

			q.setParameter("new_codiceComponente", codiceComponente);

			List<ComponenteDTO> componenti = q.getResultList();

			if (componenti.size() == 0) {
				return false;

			} else {
				return true;

			}
		} catch (NullPointerException e) {
			return false;
		}
	}

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

}
