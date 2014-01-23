package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.ComponenteBeanLocal;
import it.polimi.traveldream.ejb.client.ComponenteBeanRemote;
import it.polimi.traveldream.entities.ComponenteDTO;
import it.polimi.traveldream.entities.TipologiaDTO;

import java.util.ArrayList;
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
	 * @return codiceComponente*/
	public Long createComponente(TipologiaDTO tipologia, String descrizione) {

		ComponenteDTO componente = new ComponenteDTO();

		componente.setTipologia(tipologia);
		componente.setDescrizione(descrizione);

		return componente.getCodiceComponente();

	}

	/**@param codiceComponente*/
	public void removeComponente(Long codiceComponente) {

		ComponenteDTO c = findByCodiceComponente(codiceComponente);
		
		manager.remove(c);

	}

	/**@param codiceComponente
	 * @param tipologia
	 * @param descrizione*/
	public void updateComponente(Long codiceComponente, TipologiaDTO tipologia,String descrizione) {

		if (verificaPresenzaComponente(codiceComponente)) {

			ComponenteDTO componente = findByCodiceComponente(codiceComponente);

			componente.setTipologia(tipologia);
			componente.setDescrizione(descrizione);

			manager.merge(componente);
		}

	}

	/**@param codiceComponente
	 * @return Componente*/
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

	/** Metodi private */

	/**@param codiceComponente
	 * @return true if codiceComponente is not present in the DB, otherwise false*/
	private boolean verificaPresenzaComponente(Long codiceComponente) {
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
}
