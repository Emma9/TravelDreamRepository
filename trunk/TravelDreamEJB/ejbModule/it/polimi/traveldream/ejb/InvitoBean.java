package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.client.InvitoBeanLocal;
import it.polimi.traveldream.ejb.client.InvitoBeanRemote;
import it.polimi.traveldream.entities.InvitoDTO;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**Session Bean implementation class InvitoBean*/
@Stateless
public class InvitoBean implements InvitoBeanRemote, InvitoBeanLocal {

	@PersistenceContext(unitName = "travelDream_project") private EntityManager manager;

	/**Default constructor*/
	public InvitoBean() {
		// TODO Auto-generated constructor stub
	}

	/**@param emailMittente
	 * @param emailDestinatario
	 * @param idPacchettoPersonalizzato
	 * @param data
	 * @param stato
	 * @return idInvito*/
	public Long createInvito(String emailMittente, String emailDestinatario,String idPacchettoPersonalizzato, String data, String stato) {

		InvitoDTO invito = new InvitoDTO();

		invito.setEmailMittente(emailMittente);
		invito.setEmailDestinatario(emailDestinatario);
		invito.setIdPacchettoPersonalizzato(idPacchettoPersonalizzato);
		invito.setData(data);
		invito.setStato(stato);

		return invito.getIdInvito();
	}

	/**@param idInvito*/
	public void removeInvito(String emailMittente) {

		ArrayList<InvitoDTO> inviti = findByEmailMittente(emailMittente);

		for (int i = 0; i <= inviti.size(); i++) {

			manager.remove(inviti.get(i));
		}
	}

	/**@param idInvito
	 * @param emailMittente
	 * @param emailDestinatario
	 * @param idPacchettoPersonalizzato
	 * @param data
	 * @param stato*/
	public void updateInvito(Long idInvito, String emailMittente,String emailDestinatario, String idPacchettoPersonalizzato,String data, String stato) {

		if (verificaPresenzaInvito(idInvito)) {

			InvitoDTO invito = findByIdInvito(idInvito);

			invito.setEmailMittente(emailMittente);
			invito.setEmailDestinatario(emailDestinatario);
			invito.setIdPacchettoPersonalizzato(idPacchettoPersonalizzato);
			invito.setData(data);

			manager.merge(invito);
		}

	}

	/**@param idInvito
	 * @return invito*/
	public InvitoDTO findByIdInvito(Long idInvito) {

		TypedQuery<InvitoDTO> q = manager.createQuery("FROM Invito i WHERE i.idInvito=:new_idInvito", InvitoDTO.class);

		q.setParameter("new_idInvito", idInvito);

		InvitoDTO invito = q.getSingleResult();

		return invito;
	}

	/**@param email
	 * @return ArrayList<Invito>*/
	public ArrayList<InvitoDTO> findByEmailMittente(String emailMittente) {

		TypedQuery<InvitoDTO> q = manager.createQuery("FROM Invito i WHERE i.emailMittente=:new_emailMittente", InvitoDTO.class);

		q.setParameter("new_emailMittente", emailMittente);

		ArrayList<InvitoDTO> inviti = new ArrayList<InvitoDTO>();
		List<InvitoDTO> risultati = q.getResultList();

		for (int i = 0; i <= risultati.size(); i++) {

			inviti.set(i, risultati.get(i));
			
		}
		return inviti;
	}

	/**@return ArrayList<idInvito>*/
	public ArrayList<Long> findAll() {
		return null;
	}

	/** Metodi private */

	/**@param idInvito
	 * @return true if idInvito is not present in the DB, otherwise false*/
	public boolean verificaPresenzaInvito(Long idInvito) {
		try {
			TypedQuery<InvitoDTO> q = manager.createQuery("FROM Invito i WHERE i.idInvito=:new_idInvito", InvitoDTO.class);

			q.setParameter("new_idInvito", idInvito);

			List<InvitoDTO> inviti = q.getResultList();

			if (inviti.size() == 0) {
				
				return true;

			} else {
				
				return false;

			}
		} catch (NullPointerException e) {
			return true;
		}
	}

	@Override
	public void eliminaTuttiInviti(long idCliente) {
		// TODO Auto-generated method stub
		
	}
}
